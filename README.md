# RTUBiomeLib 1.3.0
[ Minecraft Plugin ] 1.18.0 ~ 1.19.2 Biome Library, Get Custom Biome!

**RTUBiomeLib.getInterface()**

- String **getBiomeName(Location)**

  Get All Server Biome as String

  ex) new:where

  ex) minecraft:birch_forest

- List<String> **getBiomesName()**
  
  Get All Server Biome as String
  Return list of all the biomes name in server (including custom biome, like "new:where")

- List<String> **getBiomesNameByFabricTag(String)**
  
  getBiomesNameByFabric("is_nether")
  
  Get All Server Biome as String using Fabric-BiomeTag
  Available Tags: is_badlands, is_beach, is_deep_ocean, is_end, is_forest, is_hill, is_jungle, is_jungle, is_mountain, is_nether, is_ocean, is_overworld, is_savanna, is_taiga, is_river
  
  ex) {"minecraft:nether_wastes", "minecraft:basalt_deltas", "minecraft:soul_sand_valley", "minecraft:crimson_forest", "minecraft:warped_forest"}

# Gradle&Maven
maven repo soon maybe...!

## Where is ~ 1.17.X and 1.20?
WIP... Wait~
