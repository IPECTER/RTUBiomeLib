package com.github.ipecter.rtu.biomelib;

import com.github.ipecter.rtu.nms.NMSInterface;
import com.github.ipecter.rtu.nms.NMS_1_18_R1;
import com.github.ipecter.rtu.nms.NMS_1_18_R2;
import com.github.ipecter.rtu.nms.NMS_1_19_R1;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class RTUBiomeLib extends JavaPlugin implements CommandExecutor {
    private static NMSInterface nmsInterface;

    public static NMSInterface getInterface() {
        return nmsInterface;
    }

    private final static String VERSION = Bukkit.getServer().getClass().getPackage().getName().replace("org.bukkit.craftbukkit", "").replace(".", "");

    @Override
    public void onEnable() {

        if (!NMSVersion.loadNMS(VERSION)) {
            Bukkit.getLogger().warning("[ RTUBiomeLib ] Server version is unsupported version, Disabling RTUBiomeLib...");
            this.getServer().getPluginManager().disablePlugin(this);
        }
        Bukkit.getLogger().info("RTUBiomeLib Enable");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("RTUBiomeLib Disable");
    }

    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String s, String[] args) {
        sender.sendMessage(getInterface().getBiomeName(((Player) sender).getLocation()));
        return true;
    }

    private void loadNMS() {

    }

    private enum NMSVersion {

        v1_18_R1(new NMS_1_18_R1()),
        v1_18_R2(new NMS_1_18_R2()),
        v1_19_R1(new NMS_1_19_R1());

        NMSInterface nmsInterface;

        NMSVersion(NMSInterface nmsInterface) {
            this.nmsInterface = nmsInterface;
        }

        public static boolean loadNMS(String version) {
            NMSVersion nmsVersion = NMSVersion.valueOf(version);
            if (nmsVersion == null) return false;
            RTUBiomeLib.nmsInterface = nmsVersion.nmsInterface;
            return true;
        }
    }
}
