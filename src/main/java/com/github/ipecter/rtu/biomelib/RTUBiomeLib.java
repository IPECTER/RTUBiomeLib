package com.github.ipecter.rtu.biomelib;

import com.github.ipecter.rtu.nms.NMSInterface;
import com.github.ipecter.rtu.nms.NMS_1_18_R1;
import com.github.ipecter.rtu.nms.NMS_1_18_R2;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class RTUBiomeLib extends JavaPlugin{
    private static NMSInterface nmsInterface;
    public static NMSInterface getNmsInterface() {
        return nmsInterface;
    }
    @Override
    public void onEnable() {

        Bukkit.getLogger().info("RTUBiomeLib Enable");
        if (Bukkit.getVersion().contains("1.18.2")){
            nmsInterface = new NMS_1_18_R2();
        }else if(Bukkit.getVersion().contains("1.18.1")){
            nmsInterface = new NMS_1_18_R1();
        }else if (Bukkit.getVersion().contains("1.18")){
            nmsInterface = new NMS_1_18_R1();
        }


    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("RTUBiomeLib Disable");
    }

}
