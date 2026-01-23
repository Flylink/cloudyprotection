package pro.cloudyprotection.vpn;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VpnServerRepository extends JpaRepository<VpnServer, Long> {

    List<VpnServer> findByEnabledTrue();
}
