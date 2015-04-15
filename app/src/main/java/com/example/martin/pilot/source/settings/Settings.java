package com.example.martin.pilot.source.settings;

/**
 * Created by Martin on 04/04/2015.
 */
public class Settings {
    public Settings(String serverIp, Integer serverPort, String deviceName) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        this.deviceName = deviceName;
    }

    public String serverIp;
    public Integer serverPort;
    public String deviceName;
}
