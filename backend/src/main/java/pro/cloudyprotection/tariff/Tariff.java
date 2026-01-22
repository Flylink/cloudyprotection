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

    // getters / setters
}