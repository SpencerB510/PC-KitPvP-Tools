package com.parkourcraft.KitPvPTools.configuration.lcoal;

public class FileLoader {

    public static void startUp() {
        FileManager.initializeFile("config");
        FileManager.initializeFile("messages");
    }

    public static void load() {
        FileManager.load("config");
        FileManager.load("messages");

    }

    public static void save() {
        FileManager.save("config");
        FileManager.save("messages");
    }

}
