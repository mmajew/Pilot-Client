package com.example.martin.pilot.source.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


public class SettingsManager {
    private Context m_Context;
    private final String m_FileName = "MainPreferences";
    private final String m_SeverIp = "ServerIp";
    private final String m_TcpPort = "TcpPort";
    private final String m_UdpPort = "UdpPort";
    private final String m_DeviceName = "DeviceName";
    private final String m_UseUdp = "UseUdp";

    private SettingsManager(Context context){
        m_Context = context;
    }

    public static void initialize(Context context) {
        if(m_self == null)
            m_self = new SettingsManager(context);
    }

    public static SettingsManager getInstance() {
        if(m_self == null)
            Log.e("SettingsManager", "Uninitialized reference to SettingsManager"); //TODO
        return m_self;
    }

    public boolean isConfigurationPresent(){
        return getPreferences().contains(m_SeverIp) && getPreferences().contains(m_TcpPort);
    }

    public String getServerIp(){
        return getPreferences().getString(m_SeverIp, null);
    }

    public Integer getTcpPort(){
        return getPreferences().getInt(m_TcpPort, 4444);
    }

    public Integer getUdpPort(){
        return getPreferences().getInt(m_UdpPort, 4443);
    }

    public String getDeviceName(){
        return getPreferences().getString(m_DeviceName, android.os.Build.MODEL);
    }

    public Settings getSettings(){
        return new Settings(getServerIp(), getDeviceName(), getTcpPort(), getUdpPort());
    }

    public void saveServerIp(String newIp){
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putString(m_SeverIp, newIp);
        editor.commit();
    }

    public void saveTcpPort(Integer newPort){
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putInt(m_TcpPort, newPort);
        editor.commit();
    }

    public void saveUdpPort(Integer newPort){
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putInt(m_UdpPort, newPort);
        editor.commit();
    }

    public void saveDeviceName(String newName){
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putString(m_DeviceName, newName);
        editor.commit();
    }

    private SharedPreferences getPreferences(){
        return m_Context.getSharedPreferences(m_FileName, Context.MODE_PRIVATE);
    }

    static private SettingsManager m_self;
}
