package pro.cloudyprotection.subscription;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pro.cloudyprotection.subscription.dto.SubscriptionResponse;
import pro.cloudyprotection.tariff.*;
import pro.cloudyprotection.user.*;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final TariffRepository tariffRepository;
    private final UserRepository userRepository;

    public SubscriptionController(
            SubscriptionService subscriptionService,
            TariffRepository tariffRepository,
            UserRepository userRepository
    ) {
        this.subscriptionService = subscriptionService;
        this.tariffRepository = tariffRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/buy/{tariffId}")
    public SubscriptionResponse buy(
            @PathVariable("tariffId") Long tariffId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                            .orElseThrow(() -> new RuntimeException("User not found"));

        Tariff tariff = tariffRepository.findById(tariffId)
                                .orElseThrow(() -> new RuntimeException("Tariff not found"));

        Subscription subscription =
                subscriptionService.createOrExtendSubscription(user, tariff);

        return SubscriptionResponse.from(subscription);
    }
}