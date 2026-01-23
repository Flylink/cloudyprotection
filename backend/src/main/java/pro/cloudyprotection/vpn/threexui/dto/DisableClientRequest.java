package pro.cloudyprotection.vpn.threexui.dto;

public class DisableClientRequest {

    private String id;
    private boolean enable;

    public DisableClientRequest(String id, boolean enable) {
        this.id = id;
        this.enable = enable;
    }

    public String getId() {
        return id;
    }

    public boolean isEnable() {
        return enable;
    }
}