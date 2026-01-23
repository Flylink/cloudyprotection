package pro.cloudyprotection.vpn;

import org.springframework.stereotype.Service;
import pro.cloudyprotection.subscription.Subscription;
import pro.cloudyprotection.vpn.threexui.ThreeXuiClient;
import pro.cloudyprotection.vpn.threexui.dto.AddClientRequest;

import java.util.UUID;

@Service
public class VlessProvisioningService {

    private final ThreeXuiClient threeXuiClient;

    public VlessProvisioningService(ThreeXuiClient threeXuiClient) {
        this.threeXuiClient = threeXuiClient;
    }

    public void provision(Subscription subscription) {

        UUID uuid = subscription.getClientUuid();

        long expiryMillis = subscription.getExpiresAt().toEpochMilli();


        AddClientRequest request = new AddClientRequest(uuid, subscription.getUser().getEmail(), expiryMillis);

        threeXuiClient.addVlessClient(subscription.getVpnServer(), request);
    }
}
