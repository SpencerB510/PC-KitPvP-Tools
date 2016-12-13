package com.parkourcraft.KitPvPTools.configuration;

import com.parkourcraft.KitPvPTools.configuration.local.FileManager;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigReader {

    public static String getMessage(String key, boolean translated) {
        FileConfiguration messagesConfig = FileManager.getFileConfig("messages");

        String message = messagesConfig.getString(key);

        if (translated)
            return ChatColor.translateAlternateColorCodes('&', message);
        return message;
    }

}
