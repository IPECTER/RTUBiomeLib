# [ RTUBiomeLib 1.4.1 ]
[ Minecraft Plugin ] 1.17.0 ~ 1.19.4 Biome Library, Get Custom Biome!

**RTUBiomeLib.getInterface()**

- String **getBiomeName(Location)**

  Get All Server Biome as String

  ex) new:where

  ex) minecraft:birch_forest

- List<String> **getBiomesName()**
  
  Get All Server Biome as String
  
  Return list of all the biomes name in server (including custom biome, like "new:where")

- List<String> **getBiomeTag(String)**

  Bukkit API can't get Biomes using Biome Tag! But RTUBiomeLib can get!
  
  Get All Server Biome as String using Biome Tag 
  
  Available Tags: is_badlands, is_beach, is_deep_ocean, is_end, is_forest, is_hill, is_jungle, is_jungle, is_mountain, is_nether, is_ocean, is_overworld, is_savanna, is_taiga,
  is_river
  
  Support Versions: 1.18.1 <

  ex) getBiomeTag("is_nether") => ["minecraft:nether_wastes", "minecraft:basalt_deltas", "minecraft:soul_sand_valley", "minecraft:crimson_forest", "
  minecraft:warped_forest"]

## JitPack

Gradle

```
repositories {
  maven { url 'https://jitpack.io' }
}

dependencies {
  implementation 'com.github.IPECTER.RTUBiomeLib:RTUBiomeLib:1.4.0'
}
```

Maven

```access transformers
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.IPECTER.RTUBiomeLib</groupId>
    <artifactId>RTUBiomeLib</artifactId>
    <version>1.4.0</version>
</dependency>
```
