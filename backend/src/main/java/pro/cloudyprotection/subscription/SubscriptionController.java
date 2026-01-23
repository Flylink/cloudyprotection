package pro.cloudyprotection.subscription;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pro.cloudyprotection.subscription.dto.SubscriptionAccessDto;
import pro.cloudyprotection.tariff.Tariff;
import pro.cloudyprotection.tariff.TariffRepository;
import pro.cloudyprotection.user.User;
import pro.cloudyprotection.user.UserRepository;
import pro.cloudyprotection.vpn.VlessUriBuilder;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final TariffRepository tariffRepository;
    private final UserRepository userRepository;
    private final VlessUriBuilder vlessUriBuilder;

    public SubscriptionController(
            SubscriptionService subscriptionService,
            TariffRepository tariffRepository,
            UserRepository userRepository,
            VlessUriBuilder vlessUriBuilder
    ) {
        this.subscriptionService = subscriptionService;
        this.tariffRepository = tariffRepository;
        this.userRepository = userRepository;
        this.vlessUriBuilder = vlessUriBuilder;
    }

    @PostMapping("/buy/{tariffId}")
    public SubscriptionAccessDto buy(
            @PathVariable("tariffId") Long tariffId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                            .orElseThrow();

        Tariff tariff = tariffRepository.findById(tariffId)
                                .orElseThrow();

        Subscription subscription =
                subscriptionService.createOrExtendSubscription(user, tariff);

        String vlessUri = vlessUriBuilder.build(subscription);

        return new SubscriptionAccessDto(
                vlessUri,
                subscription.getExpiresAt(),
                subscription.getVpnServer().getName()
        );
    }
}