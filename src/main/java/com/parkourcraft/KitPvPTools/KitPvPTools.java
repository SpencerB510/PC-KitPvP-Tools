package com.parkourcraft.KitPvPTools;

import com.parkourcraft.KitPvPTools.tools.*;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class KitPvPTools extends JavaPlugin implements Listener {

    private Cooldown a;
    private Reload b;
    private BoltAction c;
    private CoolBash d;
    private Shield e;
    private Cool f;
    private LeviCool g;
    private FireCool h;
    public static KitPvPTools plugin = null;

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);

        this.a = new Cooldown(this);
        this.a.runTaskTimer(this, 10L, 10L);
        this.b = new Reload(this);
        this.b.runTaskTimer(this, 10L, 10L);
        this.c = new BoltAction(this);
        this.c.runTaskTimer(this, 10L, 10L);
        this.d = new CoolBash(this);
        this.d.runTaskTimer(this, 10L, 10L);
        this.e = new Shield(this);
        this.e.runTaskTimer(this, 10L, 10L);
        this.f = new Cool(this);
        this.f.runTaskTimer(this, 10L, 10L);
        this.g = new LeviCool(this);
        this.g.runTaskTimer(this, 10L, 10L);
        this.h = new FireCool(this);
        this.h.runTaskTimer(this, 10L, 10L);
        plugin = this;
    }

    public void onDisable() {
        plugin = null;
    }
}
