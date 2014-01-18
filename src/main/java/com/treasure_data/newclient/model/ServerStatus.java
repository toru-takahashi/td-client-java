package com.treasure_data.newclient.model;

public class ServerStatus {
    public static final String STATUS = "status";

    private String status;

    public ServerStatus() {
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static ServerStatus createInstance(String status) {
        ServerStatus stat = new ServerStatus();
        stat.setStatus(status);
        return stat;
    }
}
