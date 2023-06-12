package com.github.ipecter.rtu.nms;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.world.level.biome.Biome;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_18_R1.CraftServer;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;

import java.util.List;
import java.util.stream.Collectors;

public class NMS_1_18_R1 implements NMSInterface {

    private final DedicatedServer dedicatedServer = ((CraftServer) Bukkit.getServer()).getServer();
    private final ResourceKey<Registry<Biome>> resourceKey = Registry.BIOME_REGISTRY;
    private final Registry<Biome> registry = dedicatedServer.registryAccess().registryOrThrow(resourceKey);

    @Override
    public String getBiomeName(Location location) {
        return getResourceLocation(getNMSBiome(location)).toString();
    }

    @Override
    public List<String> getBiomesAsString() {
        return registry.keySet().stream().map(ResourceLocation::toString).collect(Collectors.toList());
    }

    @Override
    public List<String> getBiomeTag(String tag) {
        return List.of();
    }

    private ResourceLocation getResourceLocation(Biome biome) {
        return registry.getKey(biome);
    }

    private Biome getNMSBiome(Location location) {
        BlockPos pos = new BlockPos(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        CraftWorld world = (CraftWorld) location.getWorld();
        if (world != null)
            return world.getHandle().getChunk(pos).getNoiseBiome(pos.getX() >> 2, pos.getY() >> 2, pos.getZ() >> 2);
        return null;
    }
}
