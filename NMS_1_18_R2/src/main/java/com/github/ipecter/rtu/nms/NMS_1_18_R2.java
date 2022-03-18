package com.github.ipecter.rtu.nms;

import net.minecraft.core.BlockPosition;
import net.minecraft.core.IRegistry;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.world.level.biome.BiomeBase;
import net.minecraft.world.level.chunk.Chunk;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_18_R2.CraftServer;
import org.bukkit.craftbukkit.v1_18_R2.CraftWorld;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class NMS_1_18_R2 implements NMSInterface {
    DedicatedServer dedicatedServer = ((CraftServer) Bukkit.getServer()).getServer();

    @Override
    public String getBiomeName(Location location) {
        IRegistry<BiomeBase> registry = dedicatedServer.Q.b(IRegistry.aP);
        return registry.b(getBiomeBase(location)).a(); // getBiomeBase() from above
    }

    @Override
    public List<String> getBiomesName() {
        return getBiomeKey().stream().map(minecraftKey -> minecraftKey.a()).collect(Collectors.toList());
    }

    private Collection<MinecraftKey> getBiomeKey() {
        IRegistry<BiomeBase> registry = dedicatedServer.Q.b(IRegistry.aP);
        return registry.d();
    }

    private BiomeBase getBiomeBase(Location location) {
        BlockPosition pos = new BlockPosition(location.getBlockX(), 0, location.getBlockZ());
        Chunk nmsChunk = ((CraftWorld) location.getWorld()).getHandle().l(pos);
        if (nmsChunk != null) {
            return nmsChunk.getNoiseBiome(pos.u(), pos.v(), pos.w()).a();
        }
        return null;
    }
}
