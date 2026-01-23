package pro.cloudyprotection.subscription.dto;

import pro.cloudyprotection.subscription.Subscription;

import java.time.Instant;

public class SubscriptionResponse {

    private Long id;
    private String clientUuid;
    private Instant expiresAt;
    private String status;
    private String serverName;

    public SubscriptionResponse(
            Long id,
            String clientUuid,
            Instant expiresAt,
            String status,
            String serverName
    ) {
        this.id = id;
        this.clientUuid = clientUuid;
        this.expiresAt = expiresAt;
        this.status = status;
        this.serverName = serverName;
    }

    public static SubscriptionResponse from(Subscription subscription) {
        return new SubscriptionResponse(
                subscription.getId(),
                subscription.getClientUuid().toString(),
                subscription.getExpiresAt(),
                subscription.getStatus().name(),
                subscription.getVpnServer().getName()
        );
    }

    public Long getId() {
        return id;
    }

    public String getClientUuid() {
        return clientUuid;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public String getStatus() {
        return status;
    }

    public String getServerName() {
        return serverName;
    }
}
