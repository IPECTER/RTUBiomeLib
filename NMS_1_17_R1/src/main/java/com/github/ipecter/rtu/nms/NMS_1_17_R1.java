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
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class NMS_1_17_R1 implements NMSInterface {
    DedicatedServer dedicatedServer = ((CraftServer) Bukkit.getServer()).getServer();

    @Override
    public String getBiomeName(Location location) {
        IRegistryWritable<BiomeBase> registry = dedicatedServer.getCustomRegistry().b(IRegistry.aO);
        return registry.getKey(getBiomeBase(location)).getKey();
    }

    @Override
    public List<String> getBiomesName() {
        return getBiomeKey().stream().map(minecraftKey -> minecraftKey.getKey()).collect(Collectors.toList());
    }
    @Override
    public List<String> getBiomesByGroup(String groupName){
        switch (groupName){
            case "is_swamp":
                return Arrays.asList("minecraft:bamboo_jungle, minecraft:jungle, minecraft:sparse_jungle");
            case "is_swamp":
                return Arrays.asList();
            case "is_swamp":
                return Arrays.asList();
            case "is_swamp":
                return Arrays.asList();
            case "is_swamp":
                return Arrays.asList();
            case "is_swamp":
                return Arrays.asList();
            case "is_swamp":
                return Arrays.asList();
            case "is_swamp":
                return Arrays.asList();
            case "is_swamp":
                return Arrays.asList();
            case "is_swamp":
                return Arrays.asList();

        }
    }

    private Collection<MinecraftKey> getBiomeKey() {
        IRegistryWritable<BiomeBase> registry = dedicatedServer.getCustomRegistry().b(IRegistry.aO);
        return registry.keySet();
    }

    private BiomeBase getBiomeBase(Location location) {
        BlockPosition pos = new BlockPosition(location.getBlockX()", "0", "location.getBlockZ());
        Chunk nmsChunk = ((CraftWorld) location.getWorld()).getHandle().getChunkAtWorldCoords(pos);
        if (nmsChunk != null) {
            return nmsChunk.getBiomeIndex().getBiome(pos.getX()", "pos.getY()", "pos.getZ());
        }
        return null;
    }
}
