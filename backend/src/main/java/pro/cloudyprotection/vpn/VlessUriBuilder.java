package pro.cloudyprotection.vpn;

import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Component
public class VlessUriBuilder {

    public String build(
            VpnServer server,
            UUID clientUuid
    ) {

        String host = extractHost(server.getApiUrl());

        String remark = URLEncoder.encode(
                "CloudyVPN-" + server.getName(),
                StandardCharsets.UTF_8
        );

        return String.format(
                "vless://%s@%s:443?encryption=none&security=tls&type=tcp#%s",
                clientUuid,
                host,
                remark
        );
    }

    private String extractHost(String apiUrl) {
        try {
            URI uri = URI.create(apiUrl);
            return uri.getHost(); // <- ВАЖНО: без порта
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid VPN apiUrl: " + apiUrl);
        }
    }
}
