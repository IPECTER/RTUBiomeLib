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
            String major = Objects.requireNonNullElse(version.split("\\.")[0], "0");
            String minor = Objects.requireNonNullElse(version.split("\\.")[1], "0");
            String patch = Objects.requireNonNullElse(version.split("\\.")[2], "0");
            this.version = Integer.parseInt(major + minor + patch);
        }

    }
}
