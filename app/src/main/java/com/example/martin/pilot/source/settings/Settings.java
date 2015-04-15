package com.example.martin.pilot.source.settings;

/**
 * Created by Martin on 04/04/2015.
 */
public class Settings {
    public Settings(String serverIp, String deviceName, Integer tcpPort, Integer udpPort) {
        this.serverIp = serverIp;
        this.deviceName = deviceName;
        this.tcpPort = tcpPort;
        this.udpPort = udpPort;
    }

    public String serverIp;
    public String deviceName;
    public Integer tcpPort;
    public Integer udpPort;
}
