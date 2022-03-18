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
    public List<String> getBiomesByFabricTag(String groupName){
        switch (groupName){
            case "is_badlands":
                return Arrays.asList("minecraft:badlands", "minecraft:eroded_badlands", "minecraft:wooded_badlands");
            case "is_beach":
                return Arrays.asList("minecraft:beach", "minecraft:snowy_beach");
            case "is_deep_ocean":
                return Arrays.asList("minecraft:deep_frozen_ocean", "minecraft:deep_cold_ocean", "minecraft:deep_ocean", "minecraft:deep_lukewarm_ocean");
            case "is_forest":
                return Arrays.asList("minecraft:forest", "minecraft:flower_forest", "minecraft:birch_forest", "minecraft:old_growth_birch_forest", "minecraft:dark_forest", "minecraft:grove");
            case "is_hill":
                return Arrays.asList("minecraft:windswept_hills", "minecraft:windswept_forest", "minecraft:windswept_gravelly_hills");
            case "is_jungle":
                return Arrays.asList("minecraft:bamboo_jungle", "minecraft:jungle", "minecraft:sparse_jungle");
            case "is_mountain":
                return Arrays.asList("minecraft:meadow", "minecraft:frozen_peaks", "minecraft:jagged_peaks", "minecraft:stony_peaks", "minecraft:snowy_slopes");
            case "is_nether":
                return Arrays.asList("minecraft:nether_wastes", "minecraft:basalt_deltas", "minecraft:soul_sand_valley", "minecraft:crimson_forest", "minecraft:warped_forest");
            case "is_river":
                return Arrays.asList("minecraft:river", "minecraft:frozen_river");
            case "is_taiga":
                return Arrays.asList("minecraft:taiga", "minecraft:snowy_taiga", "minecraft:old_growth_pine_taiga", "minecraft:old_growth_spruce_taiga");
            case "is_ocean":
                return Arrays.asList("minecraft:deep_frozen_ocean", "minecraft:deep_cold_ocean", "minecraft:deep_ocean", "minecraft:deep_lukewarm_ocean", "minecraft:frozen_ocean", "minecraft:ocean", "minecraft:cold_ocean", "minecraft:lukewarm_ocean", "minecraft:warm_ocean");
            default:
                return null;   
        }
    }

    private Collection<MinecraftKey> getBiomeKey() {
        IRegistryWritable<BiomeBase> registry = dedicatedServer.getCustomRegistry().b(IRegistry.aO);
        return registry.keySet();
    }

    private BiomeBase getBiomeBase(Location location) {
        BlockPosition pos = new BlockPosition(location.getBlockX()"", ""0"", ""location.getBlockZ());
        Chunk nmsChunk = ((CraftWorld) location.getWorld()).getHandle().getChunkAtWorldCoords(pos);
        if (nmsChunk != null) {
            return nmsChunk.getBiomeIndex().getBiome(pos.getX()"", ""pos.getY()"", ""pos.getZ());
        }
        return null;
    }
}
