package com.github.ipecter.rtu.nms;

import net.minecraft.core.BlockPosition;
import net.minecraft.core.IRegistry;
import net.minecraft.core.IRegistryWritable;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.world.level.biome.BiomeBase;
import net.minecraft.world.level.chunk.Chunk;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_18_R1.CraftServer;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class NMS_1_18_R1 implements NMSInterface {
    DedicatedServer dedicatedServer = ((CraftServer) Bukkit.getServer()).getServer();

    @Override
    public String getBiomeName(Location location) {
        IRegistryWritable<BiomeBase> registry = dedicatedServer.aV().b(IRegistry.aR);
        return registry.b(getBiomeBase(location)).a();
    }

    @Override
    public List<String> getBiomesName() {
        return getBiomeKey().stream().map(minecraftKey -> minecraftKey.a()).collect(Collectors.toList());
    }

    private Collection<MinecraftKey> getBiomeKey() {
        IRegistryWritable<BiomeBase> registry = dedicatedServer.aV().b(IRegistry.aR);
        return registry.d();
    }

    private BiomeBase getBiomeBase(Location location) {
        BlockPosition pos = new BlockPosition(location.getBlockX(), 0, location.getBlockZ());
        Chunk nmsChunk = ((CraftWorld) location.getWorld()).getHandle().l(pos);
        if (nmsChunk != null) {
            return nmsChunk.getNoiseBiome(pos.u(), pos.v(), pos.w());
        }
        return null;
    }
}
