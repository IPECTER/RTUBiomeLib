package com.github.ipecter.rtu.nms;

import org.bukkit.Location;

import java.util.*;

public interface NMSInterface {
    String getBiomeName(Location location);
    List<String> getBiomesName();
    List<String> getBiomesNameByFabricTag(String tag);
}
