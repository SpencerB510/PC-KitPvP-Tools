package com.parkourcraft.KitPvPTools.configuration.lcoal;

public class FileLoader {

    public static void startUp() {
        FileManager.initializeFile("config");
    }

    public static void load() {
        FileManager.load("config");
    }

    public static void save() {
        FileManager.save("config");
    }

}
