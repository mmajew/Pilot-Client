package com.example.martin.pilot.source.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.martin.pilot.R;


public class SettingsManager {
    private Context m_Context;
    private final String FILE_NAME = "MainPreferences";
    private final String SERVER_IP = "ServerIp";
    private final String TCP_PORT = "TcpPort";
    private final String UDP_PORT = "UdpPort";
    private final String DEVICE_NAME = "DeviceName";
    private final String ALLOW_UDP = "AllowUdp";

    static private SettingsManager m_self;

    private SettingsManager(Context context){
        m_Context = context;
    }

    public static void initialize(Context context) {
        if(m_self == null)
            m_self = new SettingsManager(context);
    }

    public static SettingsManager getInstance() {
        if(m_self == null) {
            Log.e("SettingsManager", "Uninitialized reference to SettingsManager");
        }
        return m_self;
    }

    public boolean isConfigurationPresent(){
        return getPreferences().contains(SERVER_IP) && getPreferences().contains(TCP_PORT);
    }

    public String getServerIp(){
        return getPreferences().getString(SERVER_IP, "");
    }

    public Integer getTcpPort(){
        return getPreferences().getInt(TCP_PORT, R.string.default_tcp_port);
    }

    public Integer getUdpPort(){
        return getPreferences().getInt(UDP_PORT, R.string.default_udp_port);
    }

    public String getDeviceName(){
        return getPreferences().getString(DEVICE_NAME, android.os.Build.MODEL);
    }

    public boolean getIsUdpEnabled() {
        return getPreferences().getBoolean(ALLOW_UDP, false);
    }

    public void saveServerIp(String newIp){
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putString(SERVER_IP, newIp);
        editor.apply();
    }

    public void saveTcpPort(Integer newPort){
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putInt(TCP_PORT, newPort);
        editor.apply();
    }

    public void saveUdpPort(Integer newPort){
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putInt(UDP_PORT, newPort);
        editor.apply();
    }

    public void saveDeviceName(String newName){
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putString(DEVICE_NAME, newName);
        editor.apply();
    }

    public void saveIsUdpAllowed(boolean useUdp) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putBoolean(ALLOW_UDP, useUdp);
        editor.apply();
    }

    private SharedPreferences getPreferences(){
        return m_Context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }
}
