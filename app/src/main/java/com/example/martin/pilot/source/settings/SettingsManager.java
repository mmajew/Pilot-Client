package com.example.martin.pilot.source.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


public class SettingsManager {
    private Context m_Context;
    private final String m_FileName = "MainPreferences";
    private final String m_SeverIp = "ServerIp";
    private final String m_SeverPort = "ServerPort";
    private final String m_DeviceName = "DeviceName";

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
        return getPreferences().contains(m_SeverIp) && getPreferences().contains(m_SeverPort);
    }

    public String getServerIp(){
        return getPreferences().getString(m_SeverIp, null);
    }

    public Integer getServerPort(){
        return getPreferences().getInt(m_SeverPort, 4444);
    }

    public String getDeviceName(){
        return getPreferences().getString(m_DeviceName, android.os.Build.MODEL);
    }

    public Settings getSettings(){
        return new Settings(getServerIp(), getServerPort(), getDeviceName());
    }

    public void saveServerIp(String newIp){
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putString(m_SeverIp, newIp);
        editor.commit();
    }

    public void saveSeverPort(Integer newPort){
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putInt(m_SeverPort, newPort);
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
