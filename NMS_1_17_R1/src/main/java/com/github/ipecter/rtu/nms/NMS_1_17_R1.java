package com.github.ipecter.rtu.nms;

import net.minecraft.core.BlockPosition;
import net.minecraft.core.IRegistry;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.level.biome.BiomeBase;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;

import java.util.List;
import java.util.stream.Collectors;

public class NMS_1_17_R1 implements NMSInterface {

    private final DedicatedServer dedicatedServer = ((CraftServer) Bukkit.getServer()).getServer();
    private final ResourceKey<IRegistry<BiomeBase>> resourceKey = IRegistry.aO;
    private final IRegistry<BiomeBase> registry = dedicatedServer.getCustomRegistry().d(resourceKey);

    @Override
    public String getBiomeName(Location location) {
        return getMinecraftKey(getBiomeBase(location)).toString();
    }

    @Override
    public List<String> getBiomesAsString() {
        return registry.d().stream().map(Object::toString).collect(Collectors.toList());
    }

    @Override
    public List<String> getBiomeTag(String tag) {
        return List.of();
    }

    private MinecraftKey getMinecraftKey(BiomeBase biomeBase) {
        return registry.getKey(biomeBase);
    }

    private BiomeBase getBiomeBase(Location location) {
        BlockPosition pos = new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        WorldServer nmsWorld = ((CraftWorld) location.getWorld()).getHandle();
        if (nmsWorld != null) {
            return nmsWorld.getBiome(pos.getX() >> 2, pos.getY() >> 2, pos.getZ() >> 2);
        }
        return null;
    }
}
