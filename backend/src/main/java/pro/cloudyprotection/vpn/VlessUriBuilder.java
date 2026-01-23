package pro.cloudyprotection.vpn;

import org.springframework.stereotype.Component;
import pro.cloudyprotection.subscription.Subscription;

@Component
public class VlessUriBuilder {

    public String build(Subscription subscription) {

        String uuid = subscription.getClientUuid().toString(); // ✅ ВОТ ЗДЕСЬ
        String host = extractHost(subscription.getVpnServer().getApiUrl());
        int port = 443;

        return "vless://" + uuid + "@" + host + ":" + port +
                       "?encryption=none&security=tls&type=tcp" +
                       "#CloudyVPN-" + subscription.getVpnServer().getName();
    }

    private String extractHost(String apiUrl) {
        return apiUrl
                       .replace("http://", "")
                       .replace("https://", "")
                       .split(":")[0];
    }
}