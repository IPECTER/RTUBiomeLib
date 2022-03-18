package com.github.ipecter.rtu.nms;

import net.minecraft.core.BlockPosition;
import net.minecraft.core.IRegistry;
import net.minecraft.core.IRegistryWritable;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.world.level.biome.BiomeBase;
import net.minecraft.world.level.biome.WorldChunkManager;
import net.minecraft.world.level.chunk.Chunk;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_18_R1.CraftServer;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;

import java.util.Collection;
import java.util.List;

public class NMS_1_18_R1 implements NMSInterface{
    DedicatedServer dedicatedServer = ((CraftServer) Bukkit.getServer()).getServer();

    @Override
    public String getBiomeName(Location location) {
        
    }
    @Override
    public List<String> getBiomesName() {
        return getBiomeKey().stream().map(minecraftKey -> minecraftKey.a()).toList();
    }

    private Collection<MinecraftKey> getBiomeKey() {

        IRegistryWritable<BiomeBase> registry = dedicatedServer.aV().b(IRegistry.aR);
        
        return registry.d();
    }
   private BiomeBase getBiomeBase(Location location) {
        // NMS position
        BlockPosition pos = new BlockPosition(location.getBlockX(), 0, location.getBlockZ());
    
        // NMS chunk from pos
        Chunk nmsChunk = ((CraftWorld)location.getWorld()).getHandle().l(pos);
    
        if (nmsChunk != null) {
            return nmsChunk.getBiomeIndex().getBiome(pos.getX(), pos.get, pos.getZ());
        }
        return null;
    }
}
