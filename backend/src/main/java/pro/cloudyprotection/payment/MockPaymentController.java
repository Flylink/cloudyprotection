package pro.cloudyprotection.payment;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pro.cloudyprotection.subscription.Subscription;
import pro.cloudyprotection.subscription.SubscriptionService;
import pro.cloudyprotection.tariff.Tariff;
import pro.cloudyprotection.tariff.TariffRepository;
import pro.cloudyprotection.user.User;
import pro.cloudyprotection.user.UserRepository;

@RestController
@RequestMapping("/api/payments")
public class MockPaymentController {

    private final PaymentRepository paymentRepository;
    private final TariffRepository tariffRepository;
    private final UserRepository userRepository;
    private final SubscriptionService subscriptionService;

    public MockPaymentController(
            PaymentRepository paymentRepository,
            TariffRepository tariffRepository,
            UserRepository userRepository,
            SubscriptionService subscriptionService
    ) {
        this.paymentRepository = paymentRepository;
        this.tariffRepository = tariffRepository;
        this.userRepository = userRepository;
        this.subscriptionService = subscriptionService;
    }

    /**
     * MOCK-оплата: всегда успешна
     */
    @PostMapping("/mock/{tariffId}")
    @Transactional
    public Subscription payMock(
            @PathVariable("tariffId") Long tariffId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                            .orElseThrow(() -> new IllegalStateException("User not found"));

        Tariff tariff = tariffRepository.findById(tariffId)
                                .orElseThrow(() -> new IllegalStateException("Tariff not found"));

        // 1. Создаём платёж (PAID сразу)
        Payment payment = new Payment(user, tariff, PaymentStatus.PAID);
        paymentRepository.save(payment);

        // 2. Активируем подписку
        return subscriptionService.createOrExtendSubscription(user, tariff);
    }
}