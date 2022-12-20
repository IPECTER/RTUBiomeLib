package com.github.ipecter.rtu.nms;

import net.minecraft.core.BlockPosition;
import net.minecraft.core.IRegistry;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.world.level.biome.BiomeBase;
import net.minecraft.world.level.chunk.Chunk;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_19_R1.CraftServer;
import org.bukkit.craftbukkit.v1_19_R1.CraftWorld;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NMS_1_19_R1 implements NMSInterface{

    private DedicatedServer dedicatedServer = ((CraftServer) Bukkit.getServer()).getServer();
    private IRegistry<BiomeBase> registry = dedicatedServer.N.b(IRegistry.aR);

    @Override
    public String getBiomeName(Location location) {
        return registry.b(getBiomeBase(location)).toString();
    }

    @Override
    public List<String> getBiomesAsString() {
        return registry.d().stream().map(minecraftKey -> minecraftKey.toString()).collect(Collectors.toList());
    }

    @Override
    public List<String> getBiomesAsStringByFabricTag(String fabricTag) {
        switch (fabricTag) {
            case "is_badlands":
                return Arrays.asList("minecraft:badlands", "minecraft:eroded_badlands", "minecraft:wooded_badlands");
            case "is_beach":
                return Arrays.asList("minecraft:beach", "minecraft:snowy_beach");
            case "is_deep_ocean":
                return Arrays.asList("minecraft:deep_frozen_ocean", "minecraft:deep_cold_ocean", "minecraft:deep_ocean", "minecraft:deep_lukewarm_ocean");
            case "is_end":
                return Arrays.asList("minecraft:the_end", "minecraft:end_highlands", "minecraft:end_midlands", "minecraft:small_end_islands", "minecraft:end_barrens");
            case "is_forest":
                return Arrays.asList("minecraft:forest", "minecraft:flower_forest", "minecraft:birch_forest", "minecraft:old_growth_birch_forest", "minecraft:dark_forest", "minecraft:grove");
            case "is_hill":
                return Arrays.asList("minecraft:windswept_hills", "minecraft:windswept_forest", "minecraft:windswept_gravelly_hills");
            case "is_jungle":
                return Arrays.asList("minecraft:bamboo_jungle", "minecraft:jungle", "minecraft:sparse_jungle");
            case "is_mountain":
                return Arrays.asList("minecraft:meadow", "minecraft:frozen_peaks", "minecraft:jagged_peaks", "minecraft:stony_peaks", "minecraft:snowy_slopes");
            case "is_nether":
                return Arrays.asList("minecraft:nether_wastes", "minecraft:soul_sand_valley", "minecraft:crimson_forest", "minecraft:warped_forest", "minecraft:basalt_deltas");
            case "is_ocean":
                return Arrays.asList("minecraft:deep_frozen_ocean", "minecraft:deep_cold_ocean", "minecraft:deep_ocean", "minecraft:deep_lukewarm_ocean", "minecraft:frozen_ocean", "minecraft:ocean", "minecraft:cold_ocean", "minecraft:lukewarm_ocean", "minecraft:warm_ocean");
            case "is_overworld":
                return Arrays.asList("minecraft:mushroom_fields", "minecraft:deep_frozen_ocean", "minecraft:frozen_ocean", "minecraft:deep_cold_ocean", "minecraft:cold_ocean", "minecraft:deep_ocean", "minecraft:ocean", "minecraft:deep_lukewarm_ocean", "minecraft:lukewarm_ocean", "minecraft:warm_ocean", "minecraft:stony_shore", "minecraft:swamp", "minecraft:mangrove_swamp", "minecraft:snowy_slopes", "minecraft:snowy_plains", "minecraft:snowy_beach", "minecraft:windswept_gravelly_hills", "minecraft:grove", "minecraft:windswept_hills", "minecraft:snowy_taiga", "minecraft:windswept_forest", "minecraft:taiga", "minecraft:plains", "minecraft:meadow", "minecraft:beach", "minecraft:forest", "minecraft:old_growth_spruce_taiga", "minecraft:flower_forest", "minecraft:birch_forest", "minecraft:dark_forest", "minecraft:savanna_plateau", "minecraft:savanna", "minecraft:jungle", "minecraft:badlands", "minecraft:desert", "minecraft:wooded_badlands", "minecraft:jagged_peaks", "minecraft:stony_peaks", "minecraft:frozen_river", "minecraft:river", "minecraft:ice_spikes", "minecraft:old_growth_pine_taiga", "minecraft:sunflower_plains", "minecraft:old_growth_birch_forest", "minecraft:sparse_jungle", "minecraft:bamboo_jungle", "minecraft:eroded_badlands", "minecraft:windswept_savanna", "minecraft:frozen_peaks", "minecraft:dripstone_caves", "minecraft:lush_caves", "minecraft:deep_dark");
            case "is_savanna":
                return Arrays.asList("minecraft:savanna", "minecraft:savanna_plateau", "minecraft:windswept_savanna");
            case "is_taiga":
                return Arrays.asList("minecraft:taiga", "minecraft:snowy_taiga", "minecraft:old_growth_pine_taiga", "minecraft:old_growth_spruce_taiga");
            case "is_river":
                return Arrays.asList("minecraft:river", "minecraft:frozen_river");
            default:
                return null;
        }
    }

    private BiomeBase getBiomeBase(Location location) {
        BlockPosition pos = new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        Chunk nmsChunk = ((CraftWorld) location.getWorld()).getHandle().l(pos);
        if (nmsChunk != null) {
            return nmsChunk.getNoiseBiome(pos.u() >> 2, pos.v() >> 2, pos.w() >> 2).a();
        }
        return null;
    }
}
