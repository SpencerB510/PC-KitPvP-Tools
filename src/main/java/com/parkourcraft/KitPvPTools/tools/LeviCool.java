package com.parkourcraft.KitPvPTools.tools;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.parkourcraft.KitPvPTools.Effects;
import org.bukkit.scheduler.BukkitRunnable;

public class LeviCool extends BukkitRunnable {
    private static Map<String, Long> cooldown = new HashMap();

    public LeviCool(Effects effects) {}

    public void run() {
        Iterator<Map.Entry<String, Long>> it = cooldown.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Long> pair = (Map.Entry)it.next();
            if (System.currentTimeMillis() - ((Long)pair.getValue()).longValue() >= 20000L) {
                it.remove();
            }
        }
    }

    public static boolean isonCD(String p) {
        return cooldown.containsKey(p);
    }

    public static void add(String p) {
        cooldown.put(p, Long.valueOf(System.currentTimeMillis()));
    }
}
