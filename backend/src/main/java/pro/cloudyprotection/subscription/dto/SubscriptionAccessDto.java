package pro.cloudyprotection.subscription.dto;

import java.time.Instant;

public class SubscriptionAccessDto {

    private String vlessUri;
    private Instant expiresAt;
    private String serverName;

    public SubscriptionAccessDto(
            String vlessUri,
            Instant expiresAt,
            String serverName
    ) {
        this.vlessUri = vlessUri;
        this.expiresAt = expiresAt;
        this.serverName = serverName;
    }

    public String getVlessUri() {
        return vlessUri;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public String getServerName() {
        return serverName;
    }
}