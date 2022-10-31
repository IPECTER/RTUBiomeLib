package com.github.ipecter.rtu.biomelib;

import com.github.ipecter.rtu.nms.NMSInterface;
import com.github.ipecter.rtu.nms.NMS_1_18_R1;
import com.github.ipecter.rtu.nms.NMS_1_18_R2;
import com.github.ipecter.rtu.nms.NMS_1_19_R1;
import com.iridium.iridiumcolorapi.IridiumColorAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class RTUBiomeLib extends JavaPlugin implements CommandExecutor {

    private String prefix = IridiumColorAPI.process("<GRADIENT:39cc1f>[ RTUBiomeLib ]</GRADIENT:a3a3a3> ");

    private static NMSInterface nmsInterface;

    public static NMSInterface getInterface() {
        return nmsInterface;
    }

    private final static String VERSION = Bukkit.getServer().getClass().getPackage().getName().replace("org.bukkit.craftbukkit", "").replace(".", "");

    @Override
    public void onEnable() {
        Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', prefix + "&aEnable&f!"));
        Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', prefix + "&fNMS: " + VERSION));
        loadNMS(VERSION);
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', prefix + "&cDisable&f!"));
    }

    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String s, String[] args) {
        sender.sendMessage(getInterface().getBiomeName(((Player) sender).getLocation()));
        return true;
    }

    private void loadNMS(String version) {
        switch (version) {
            case "v1_18_R1": {
                nmsInterface = new NMS_1_18_R1();
                break;
            }
            case "v1_18_R2": {
                nmsInterface = new NMS_1_18_R2();
                break;
            }
            case "v1_19_R1": {
                nmsInterface = new NMS_1_19_R1();
                break;
            }
            default: {
                Bukkit.getLogger().warning("[ RTUBiomeLib ] Server version is unsupported version, Disabling RTUBiomeLib...");
                this.getServer().getPluginManager().disablePlugin(this);
                break;
            }
        }
    }
}
