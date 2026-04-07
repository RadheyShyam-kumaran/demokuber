package com.catalog.model;

public class PodInfo {
    private String podName;
    private String version;
    private String status;
    private String message;

    public PodInfo(String podName, String version, String status, String message) {
        this.podName = podName;
        this.version = version;
        this.status = status;
        this.message = message;
    }

    public String getPodName() { return podName; }
    public String getVersion() { return version; }
    public String getStatus() { return status; }
    public String getMessage() { return message; }
}
