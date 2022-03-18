package com.github.ipecter.rtu.nms;

import net.minecraft.core.IRegistry;
import net.minecraft.core.IRegistryWritable;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.world.level.biome.BiomeBase;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_18_R2.CraftServer;

import java.util.Collection;
import java.util.List;

public class NMS_1_18_R2 implements NMSInterface{
    @Override
    public String getBiomeName(Location location) {

    }

    @Override
    public List<String> getBiomesName() {
        return getBiomeKey().stream().map(minecraftKey -> minecraftKey.a()).toList();
    }

    public Collection<MinecraftKey> getBiomeKey() {
        DedicatedServer dedicatedServer = ((CraftServer) Bukkit.getServer()).getServer();

        IRegistryWritable<BiomeBase> registry = dedicatedServer.aV().b(IRegistry.aR);

        return registry.d();
    }
}
