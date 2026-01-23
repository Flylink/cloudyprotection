package pro.cloudyprotection.tariff;

import jakarta.persistence.*;

@Entity
@Table(name = "tariffs")
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int days;

    @Column(nullable = false)
    private int priceRub;

    @Column(nullable = false)
    private boolean enabled = true;

    // 🔹 ОБЯЗАТЕЛЬНЫЙ пустой конструктор для JPA
    protected Tariff() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getPriceRub() {
        return priceRub;
    }

    public void setPriceRub(int priceRub) {
        this.priceRub = priceRub;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}