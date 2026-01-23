package pro.cloudyprotection.subscription;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Component
public class SubscriptionExpiryScheduler {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionExpiryScheduler(
            SubscriptionRepository subscriptionRepository
    ) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Scheduled(fixedDelay = 60_000) // 1 минута
    @Transactional
    public void disableExpired() {

        List<Subscription> expired =
                subscriptionRepository.findByStatusAndExpiresAtBefore(
                        SubscriptionStatus.ACTIVE,
                        Instant.now()
                );

        for (Subscription subscription : expired) {
            subscription.setStatus(SubscriptionStatus.EXPIRED);
        }
    }
}
