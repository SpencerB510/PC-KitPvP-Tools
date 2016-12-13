package com.parkourcraft.KitPvPTools.configuration.lcoal;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;

public class FilesObject {

    private String fileName;
    private static FileConfiguration fileConfig;
    private static File file;

    public FilesObject(String fileNameIn, FileConfiguration fileConfigIn, File fileIn) {
        fileName = fileNameIn;
        fileConfig = fileConfigIn;
        file = fileIn;
        System.out.println("new object! name: " + fileNameIn + " path: " + fileIn.getPath());
    }

    public String getName() {
        return fileName;
    }

    public FileConfiguration getFileConfig() {
        return fileConfig;
    }

    public File getFile() {
        return file;
    }

    public void setName(String nameIn) {
        fileName = nameIn;
    }

    public void setFileConfig(FileConfiguration fileConfigIn) {
        fileConfig = fileConfigIn;
    }

    public void setFile(File fileIn) {
        file = fileIn;
    }

}
