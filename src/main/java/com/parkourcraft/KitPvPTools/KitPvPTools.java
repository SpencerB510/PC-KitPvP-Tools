package com.parkourcraft.KitPvPTools;

import com.parkourcraft.KitPvPTools.tools.*;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class KitPvPTools extends JavaPlugin implements Listener {

    public static KitPvPTools plugin = null;

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new Events(), this);

        Cooldown a = new Cooldown(this);
        a.runTaskTimer(this, 10L, 10L);
        Reload b = new Reload(this);
        b.runTaskTimer(this, 10L, 10L);
        BoltAction c = new BoltAction(this);
        c.runTaskTimer(this, 10L, 10L);
        CoolBash d = new CoolBash(this);
        d.runTaskTimer(this, 10L, 10L);
        Shield e = new Shield(this);
        e.runTaskTimer(this, 10L, 10L);
        Cool f = new Cool(this);
        f.runTaskTimer(this, 10L, 10L);
        LeviCool g = new LeviCool(this);
        g.runTaskTimer(this, 10L, 10L);
        FireCool h = new FireCool(this);
        h.runTaskTimer(this, 10L, 10L);
        plugin = this;
    }

    public void onDisable() {
        plugin = null;
    }
}
