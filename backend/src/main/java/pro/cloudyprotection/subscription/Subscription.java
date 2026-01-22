package pro.cloudyprotection.subscription;

import jakarta.persistence.*;
import pro.cloudyprotection.user.User;
import pro.cloudyprotection.vpn.VpnServer;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private VpnServer vpnServer;

    @Column(nullable = false, unique = true)
    private UUID clientUuid;

    @Column(nullable = false)
    private Instant expiresAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubscriptionStatus status = SubscriptionStatus.ACTIVE;

    // getters / setters
}
