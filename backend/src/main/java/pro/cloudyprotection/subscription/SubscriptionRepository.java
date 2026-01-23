package pro.cloudyprotection.subscription;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.cloudyprotection.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    List<Subscription> findByUser(User user);

    Optional<Subscription> findByClientUuid(UUID clientUuid);
}
