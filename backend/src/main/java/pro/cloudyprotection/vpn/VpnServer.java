package pro.cloudyprotection.vpn;

import jakarta.persistence.*;

@Entity
@Table(name = "vpn_servers")
public class VpnServer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String apiUrl;

    @Column(nullable = false)
    private String apiUsername;

    @Column(nullable = false)
    private String apiPassword;

    @Column(nullable = false)
    private Long inboundId;

    @Column(nullable = false)
    private boolean enabled = true;

    // getters / setters
}