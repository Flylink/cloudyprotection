package pro.cloudyprotection.subscription;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.cloudyprotection.tariff.Tariff;
import pro.cloudyprotection.user.User;
import pro.cloudyprotection.vpn.VpnProvisioningService;
import pro.cloudyprotection.vpn.VpnServer;
import pro.cloudyprotection.vpn.VpnServerRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final VpnServerRepository vpnServerRepository;
    private final VpnProvisioningService vpnProvisioningService;

    public SubscriptionService(
            SubscriptionRepository subscriptionRepository,
            VpnServerRepository vpnServerRepository,
            VpnProvisioningService vpnProvisioningService
    ) {
        this.subscriptionRepository = subscriptionRepository;
        this.vpnServerRepository = vpnServerRepository;
        this.vpnProvisioningService = vpnProvisioningService;
    }

    @Transactional
    public Subscription createOrExtendSubscription(User user, Tariff tariff) {

        Subscription subscription = subscriptionRepository.findByUser(user)
                                            .stream()
                                            .findFirst()
                                            .orElse(null);

        Instant now = Instant.now();
        Instant newExpiresAt;

        if (subscription == null) {
            //Новая подписка
            VpnServer server = selectVpnServer();

            subscription = new Subscription();
            subscription.setUser(user);
            subscription.setVpnServer(server);
            subscription.setClientUuid(UUID.randomUUID());

            newExpiresAt = now.plus(tariff.getDays(), ChronoUnit.DAYS);
            subscription.setExpiresAt(newExpiresAt);
            subscription.setStatus(SubscriptionStatus.ACTIVE);

            subscriptionRepository.save(subscription);

            vpnProvisioningService.createClient(
                    server,
                    subscription.getClientUuid(),
                    newExpiresAt
            );

            return subscription;
        }

        //Продление подписки
        Instant baseDate = subscription.getExpiresAt().isAfter(now)
                                   ? subscription.getExpiresAt()
                                   : now;

        newExpiresAt = baseDate.plus(tariff.getDays(), ChronoUnit.DAYS);

        subscription.setExpiresAt(newExpiresAt);
        subscription.setStatus(SubscriptionStatus.ACTIVE);

        subscriptionRepository.save(subscription);

        vpnProvisioningService.extendClient(
                subscription.getVpnServer(),
                subscription.getClientUuid(),
                newExpiresAt
        );

        return subscription;
    }

    private VpnServer selectVpnServer() {
        return vpnServerRepository.findByEnabledTrue()
                       .stream()
                       .findFirst()
                       .orElseThrow(() ->
                                            new IllegalStateException("No enabled VPN servers available")
                       );
    }
}
