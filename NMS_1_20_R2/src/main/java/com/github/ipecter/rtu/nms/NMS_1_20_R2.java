package com.github.ipecter.rtu.nms;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R2.CraftServer;
import org.bukkit.craftbukkit.v1_20_R2.CraftWorld;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NMS_1_20_R2 implements NMSInterface {

    private final DedicatedServer dedicatedServer = ((CraftServer) Bukkit.getServer()).getServer();
    private final ResourceKey<Registry<Biome>> resourceKey = Registries.BIOME;
    private final Registry<Biome> registry = dedicatedServer.registries().compositeAccess().registryOrThrow(resourceKey);

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
        Optional<HolderSet.Named<Biome>> holders = registry.getTag(TagKey.create(resourceKey, new ResourceLocation(tag)));
        return holders.map(biomeNamed -> biomeNamed.stream().map(biomeBaseHolder -> getResourceLocation(biomeBaseHolder.value()).toString()).collect(Collectors.toList())).orElseGet(List::of);
    }

    private ResourceLocation getResourceLocation(Biome biome) {
        return registry.getKey(biome);
    }

    private Biome getNMSBiome(Location location) {
        BlockPos pos = new BlockPos(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        CraftWorld world = (CraftWorld) location.getWorld();
        if (world != null)
            return world.getHandle().getChunk(pos).getNoiseBiome(pos.getX() >> 2, pos.getY() >> 2, pos.getZ() >> 2).value();
        return null;
    }
}
