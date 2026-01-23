package pro.cloudyprotection.vpn;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class NoopVpnProvisioningService implements VpnProvisioningService {

    @Override
    public void createClient(VpnServer server, UUID clientUuid, Instant expiresAt) {
        // TODO: implement 3X-UI API call
    }

    @Override
    public void extendClient(VpnServer server, UUID clientUuid, Instant newExpiresAt) {
        // TODO: implement 3X-UI API call
    }

    @Override
    public void disableClient(VpnServer server, UUID clientUuid) {
        // TODO: implement 3X-UI API call
    }
}