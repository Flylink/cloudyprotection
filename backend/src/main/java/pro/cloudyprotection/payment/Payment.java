package pro.cloudyprotection.payment;

import jakarta.persistence.*;
import pro.cloudyprotection.tariff.Tariff;
import pro.cloudyprotection.user.User;

import java.time.Instant;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Tariff tariff;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    @Column(nullable = false)
    private Instant createdAt;

    // JPA
    protected Payment() {
    }

    public Payment(User user, Tariff tariff, PaymentStatus status) {
        this.user = user;
        this.tariff = tariff;
        this.status = status;
        this.createdAt = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}