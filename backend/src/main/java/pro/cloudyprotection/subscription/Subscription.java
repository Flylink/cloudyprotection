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

    // 🔹 ОБЯЗАТЕЛЬНЫЙ пустой конструктор для JPA
    protected Subscription() {
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return id;
    }

    public VpnServer getVpnServer() {
        return vpnServer;
    }

    public UUID getClientUuid() {
        return clientUuid;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public SubscriptionStatus getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setVpnServer(VpnServer vpnServer) {
        this.vpnServer = vpnServer;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }

    public void setClientUuid(UUID clientUuid) {
        this.clientUuid = clientUuid;
    }

    public void setStatus(SubscriptionStatus status) {
        this.status = status;
    }
}
