package com.github.ipecter.rtu.nms;

import net.minecraft.core.BlockPosition;
import net.minecraft.core.IRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.BiomeBase;
import net.minecraft.world.level.chunk.Chunk;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_19_R3.CraftServer;
import org.bukkit.craftbukkit.v1_19_R3.CraftWorld;

import java.util.List;
import java.util.stream.Collectors;

public class NMS_1_19_R3 implements NMSInterface {

    private final DedicatedServer dedicatedServer = ((CraftServer) Bukkit.getServer()).getServer();
    private final ResourceKey<IRegistry<BiomeBase>> resourceKey = Registries.an;
    private final IRegistry<BiomeBase> registry = dedicatedServer.aX().d(resourceKey);

    @Override
    public String getBiomeName(Location location) {
        return getMinecraftKey(getBiomeBase(location)).toString();
    }

    @Override
    public List<String> getBiomesAsString() {
        return registry.e().stream().map(MinecraftKey::toString).collect(Collectors.toList());
    }

    @Override
    public List<String> getBiomeTag(String tag) {
        return registry.a(TagKey.a(resourceKey, new MinecraftKey(tag))).a().map(biomeBaseHolder -> getMinecraftKey(biomeBaseHolder.a()).toString()).collect(Collectors.toList());
    }

    private MinecraftKey getMinecraftKey(BiomeBase biomeBase) {
        return registry.b(biomeBase);
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
