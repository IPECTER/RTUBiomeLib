package com.github.ipecter.rtu.biomelib;

import lombok.Getter;
import org.bukkit.Bukkit;

import java.util.List;
import java.util.Objects;

public class VersionUtil {

    private static final Version VERSION = new Version(fromAPI(Bukkit.getVersion()));

    private static String fromAPI(String version) {
        return version.split("-")[0];
    }

    public static boolean isSupportVersion(String maxVersion, String minVersion) {
        Version max = new Version(fromAPI(maxVersion));
        Version min = new Version(fromAPI(minVersion));
        return max.getVersion() >= VERSION.getVersion() && min.getVersion() <= VERSION.getVersion();
    }

    public static Version getVersion() {
        return VERSION;
    }

    @Getter
    public static class Version {
        private final int version;

        public Version(String version) {
            String[] numbers = version.split("\\.");
            String majorStr = numbers.length == 0 ? "0" : numbers[0];
            String minorStr = numbers.length <= 1 ? "0" : numbers[1];
            String patchStr = numbers.length <= 2 ? "0" : numbers[2];
            this.version = Integer.parseInt(majorStr + minorStr + patchStr);
        }

        public Version(String major, String minor, String patch) {
            String majorStr = (String)Objects.requireNonNullElse(major, "0");
            String minorStr = (String)Objects.requireNonNullElse(minor, "0");
            String patchStr = (String)Objects.requireNonNullElse(patch, "0");
            this.version = Integer.parseInt(majorStr + minorStr + patchStr);
        }

    }
}
