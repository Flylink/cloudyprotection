package pro.cloudyprotection.vpn;

import java.time.Instant;
import java.util.UUID;

public interface VpnProvisioningService {

    /**
     * Создать нового VPN-клиента
     */
    void createClient(VpnServer server, UUID clientUuid, Instant expiresAt);

    /**
     * Продлить существующего клиента
     */
    void extendClient(VpnServer server, UUID clientUuid, Instant newExpiresAt);

    /**
     * Отключить клиента
     */
    void disableClient(VpnServer server, UUID clientUuid);
}
