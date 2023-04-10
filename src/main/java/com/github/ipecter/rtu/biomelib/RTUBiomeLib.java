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

    private final static String VERSION = Bukkit.getServer().getClass().getPackage().getName().replace("org.bukkit.craftbukkit", "").replace(".", "");
    private static NMSInterface nmsInterface;
    private final String prefix = IridiumColorAPI.process("<GRADIENT:39cc1f>[ RTUBiomeLib ]</GRADIENT:a3a3a3> ");

    public static NMSInterface getInterface() {
        return nmsInterface;
    }

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
        sender.sendMessage("Here: " + getInterface().getBiomeName(((Player) sender).getLocation()));
        sender.sendMessage("Biome Tag (is_overworld): " + getInterface().getBiomeTag("is_overworld"));
        return true;
    }

    private void loadNMS(String version) {
        switch (version) {
            case "v1_17_R1": {
                nmsInterface = new NMS_1_17_R1();
                break;
            }
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
            case "v1_19_R2": {
                nmsInterface = new NMS_1_19_R2();
                break;
            }
            case "v1_19_R3": {
                nmsInterface = new NMS_1_19_R3();
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
