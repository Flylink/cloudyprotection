package pro.cloudyprotection.vpn.threexui;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pro.cloudyprotection.vpn.VpnServer;
import pro.cloudyprotection.vpn.threexui.dto.AddClientRequest;

@Component
public class ThreeXuiClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public void addVlessClient(VpnServer server, AddClientRequest request) {

        String url = server.getApiUrl()
                             + "/panel/api/inbounds/addClient";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(
                server.getApiUsername(),
                server.getApiPassword()
        );

        HttpEntity<AddClientRequest> entity =
                new HttpEntity<>(request, headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity(url, entity, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new IllegalStateException("3X-UI client creation failed");
        }
    }
}