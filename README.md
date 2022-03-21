# RTUBiomeLib 1.0.0
[ Minecraft Plugin ] 1.18 ~ 1.18.2 Biome Library, Get Custom Biome!
(Can Only Get String Now)

**RTUBiomeLib.getInterface()**

- String **getBiomeName(Location)**

  ex) new:where

  ex) minecraft:birch_forest

- List<String> **getBiomesName()**
  
  return list of all the biomes name in server (including custom biome, like "new:where")

- List<String> **getBiomesNameByFabricTag(String)**
  
  getBiomesNameByFabric("is_nether")
  
  return {"minecraft:nether_wastes", "minecraft:basalt_deltas", "minecraft:soul_sand_valley", "minecraft:crimson_forest", "minecraft:warped_forest"}

# Gradle&Maven
maven repo soon maybe...!
