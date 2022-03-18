package com.github.ipecter.rtu.nms;

import org.bukkit.Location;

import java.util.List;

public interface NMSInterface {
    String getBiomeName(Location location);
    List<String> getBiomesName();
    List<String> getBiomesByGroup(String groupName);
}
