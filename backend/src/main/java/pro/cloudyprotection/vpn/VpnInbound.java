package pro.cloudyprotection.vpn;

import jakarta.persistence.*;

@Entity
@Table(name = "vpn_inbounds")
public class VpnInbound {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private VpnServer server;

    private Long inboundId;
    private int port;
    private String protocol; // VLESS

    private boolean enabled = true;

    protected VpnInbound() {}
}

