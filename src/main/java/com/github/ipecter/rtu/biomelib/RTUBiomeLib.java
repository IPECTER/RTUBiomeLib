package com.github.ipecter.rtu.biomelib;

import com.github.ipecter.rtu.nms.*;
import com.iridium.iridiumcolorapi.IridiumColorAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class RTUBiomeLib extends JavaPlugin implements CommandExecutor {

    private static NMSInterface nmsInterface;
    private final String prefix = IridiumColorAPI.process("<GRADIENT:39cc1f>[ RTUBiomeLib ]</GRADIENT:a3a3a3> ");

    public static NMSInterface getInterface() {
        return nmsInterface;
    }

    @Override
    public void onEnable() {
        Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', prefix + "&aEnable&f!"));
        loadNMS();
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', prefix + "&cDisable&f!"));
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String s, String[] args) {
        sender.sendMessage("Here: " + getInterface().getBiomeName(((Player) sender).getLocation()));
        return true;
    }

    private void loadNMS() {
        String nms = "ERROR";
        switch (VersionUtil.getVersion().getVersion()) {
            case 1170, 1171 -> {
                nms = "v1_17_R1";
                nmsInterface = new NMS_1_17_R1();
            }
            case 1180, 1181 -> {
                nms = "v1_18_R1";
                nmsInterface = new NMS_1_18_R1();
            }
            case 1182 -> {
                nms = "v1_18_R2";
                nmsInterface = new NMS_1_18_R2();
            }
            case 1190, 1191, 1192 -> {
                nms = "v1_19_R1";
                nmsInterface = new NMS_1_19_R1();
            }
            case 1193 -> {
                nms = "v1_19_R2";
                nmsInterface = new NMS_1_19_R2();
            }
            case 1194 -> {
                nms = "v1_19_R3";
                nmsInterface = new NMS_1_19_R3();
            }
            case 1200, 1201 -> {
                nms = "v1_20_R1";
                nmsInterface = new NMS_1_20_R1();
            }
            case 1202 -> {
                nms = "v1_20_R2";
                nmsInterface = new NMS_1_20_R2();
            }
            default -> {
                Bukkit.getLogger().warning("[ RTUBiomeLib ] Server version is unsupported version, Disabling RTUBiomeLib...");
                this.getServer().getPluginManager().disablePlugin(this);
            }
        }
        Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', prefix + "&fNMS: " + nms));
    }
}
