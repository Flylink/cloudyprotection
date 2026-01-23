package pro.cloudyprotection.vpn.threexui.dto;

import java.util.UUID;

public class AddClientRequest {

    private UUID id;
    private String email;
    private long expiryTime;
    private boolean enable;

    public AddClientRequest(UUID id, String email, long expiryTime) {
        this.id = id;
        this.email = email;
        this.expiryTime = expiryTime;
        this.enable = true;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public long getExpiryTime() {
        return expiryTime;
    }

    public boolean isEnable() {
        return enable;
    }
}