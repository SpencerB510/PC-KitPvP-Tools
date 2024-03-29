package com.parkourcraft.KitPvPTools;

import com.parkourcraft.KitPvPTools.configuration.ConfigReader;
import com.parkourcraft.KitPvPTools.tools.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class Events implements Listener {

    @EventHandler
    public void onDragonShadow(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK) || (e.getAction() == Action.PHYSICAL)) &&
                (e.getMaterial().equals(Material.END_ROD))) {
            e.getPlayer().launchProjectile(DragonFireball.class);
            p.playSound(p.getLocation(), Sound.ENTITY_ENDERDRAGON_FIREBALL_EXPLODE, 2.0F, 2.0F);
        }
    }

    @EventHandler
    public void onBash(EntityDamageByEntityEvent e) {
        if (((e.getDamager() instanceof Player)) &&
                ((e.getEntity() instanceof Player))) {
            Player damager = (Player)e.getDamager();
            Player victim = (Player)e.getEntity();
            ItemStack weapon = damager.getInventory().getItemInMainHand();
            if ((weapon.getType().equals(Material.COAL)) &&
                    (weapon.getItemMeta().getDisplayName().equals(ChatColor.RED + "" + ChatColor.BOLD + ChatColor.ITALIC + "Bash"))) {
                if (CoolBash.isonCD(damager.getName())) {
                    damager.sendMessage(ConfigReader.getMessage("basic-cooldown", true));
                    e.setCancelled(true);
                    return;
                }
                victim.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 2, false));
                victim.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 2, false));
                damager.playSound(damager.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
                victim.playSound(damager.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1.0F, 1.0F);
                CoolBash.add(damager.getName());
            }
        }
    }

    @EventHandler
    public void onSmack(EntityDamageByEntityEvent e) {
        if (((e.getDamager() instanceof Player)) &&
                ((e.getEntity() instanceof Player))) {
            Player damager = (Player)e.getDamager();
            Player victim = (Player)e.getEntity();
            ItemStack weapon = damager.getInventory().getItemInMainHand();
            if ((weapon.getType().equals(Material.DIAMOND)) &&
                    (weapon.getItemMeta().getDisplayName().equals(ChatColor.BLUE + "" + ChatColor.BOLD + ChatColor.ITALIC + "Slam")))
            {
                if (Shield.isonCD(damager.getName())) {
                    damager.sendMessage(ConfigReader.getMessage("basic-cooldown", true));
                    e.setCancelled(true);
                    return;
                }
                victim.setVelocity(damager.getLocation().getDirection().multiply(5.0D));
                damager.playSound(damager.getLocation(), Sound.ENTITY_FIREWORK_BLAST, 3.0F, 3.0F);
                victim.playSound(damager.getLocation(), Sound.ENTITY_FIREWORK_BLAST, 3.0F, 3.0F);
                Shield.add(damager.getName());
            }
        }
    }

    @EventHandler
    public void onClickEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK) || (e.getAction() == Action.PHYSICAL)) &&
                (e.getMaterial().equals(Material.BLAZE_POWDER)) &&
                (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "" + ChatColor.ITALIC + ChatColor.BOLD + "Levitation")))
        {
            if (LeviCool.isonCD(p.getName())) {
                p.sendMessage(ConfigReader.getMessage("basic-cooldown", true));
                e.setCancelled(true);
                return;
            }
            p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 100, 1, false));
            p.playEffect(p.getLocation(), Effect.SMOKE, 2);
            p.playSound(p.getLocation(), Sound.BLOCK_FIRE_AMBIENT, 2.0F, 2.0F);
            LeviCool.add(p.getName());
        }
    }

    @EventHandler
    public void onGhast(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (((p instanceof Player)) &&
                (e.getAction() == Action.LEFT_CLICK_AIR) &&
                (p.getInventory().getItemInMainHand().getType().equals(Material.GHAST_TEAR)) &&
                (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GRAY + "" + ChatColor.BOLD + ChatColor.ITALIC + "Blaster"))) {
            if (Reload.isonCD(p.getName())) {
                p.sendMessage(ConfigReader.getMessage("basic-cooldown", true));
                e.setCancelled(true);
                return;
            }
            p.launchProjectile(LargeFireball.class);
            p.playSound(p.getLocation(), Sound.ENTITY_GHAST_SHOOT, 2.0F, 2.0F);
            Reload.add(p.getName());
        }
    }

    @EventHandler
    public void onFireball(EntityDamageByEntityEvent e) {
        if (((e.getDamager() instanceof LargeFireball)) &&
                ((e.getEntity() instanceof Player))) {
            LargeFireball f = (LargeFireball)e.getDamager();
            if ((f.getShooter() instanceof Player)) {
                Player st = (Player)f.getShooter();
                if ((st.getInventory().getItemInMainHand().getType() == Material.GHAST_TEAR) &&
                        (st.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GRAY + "" + ChatColor.BOLD + ChatColor.ITALIC + "Blaster"))) {
                    e.setDamage(12.0D);
                }
            }
        }
    }

    public static void shootFireballDelayed(Player player, long delayInTicks) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(KitPvPTools.plugin, new Runnable() {
            public void run() {
                player.launchProjectile(SmallFireball.class);
                player.playSound(player.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 2.0F, 2.0F);
            }
        }, delayInTicks);
    }

    @EventHandler
    public void onFlameEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK) || (e.getAction() == Action.PHYSICAL)) &&
                (e.getMaterial().equals(Material.BLAZE_ROD)) &&
                (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "" + ChatColor.ITALIC + ChatColor.BOLD + "Burst Fire"))) {
            if (FireCool.isonCD(p.getName())) {
                p.sendMessage(ConfigReader.getMessage("basic-cooldown", true));
                e.setCancelled(true);
                return;
            }
            shootFireballDelayed(p, 0L);
            shootFireballDelayed(p, 5L);
            shootFireballDelayed(p, 10L);
            FireCool.add(p.getName());
        }
    }

    @EventHandler
    public void onArrowEvent(EntityShootBowEvent e) {
        if ((e.getEntity() instanceof Player)) {
            Player p = (Player)e.getEntity();
            if ((e.getBow().hasItemMeta()) &&
                    (p.getInventory().getItemInMainHand().getType().equals(Material.BOW)) &&
                    (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + ChatColor.ITALIC + "The Wilt"))) {
                if (Cooldown.isonCD(p.getName())) {
                    p.sendMessage(ConfigReader.getMessage("basic-cooldown", true));
                    e.setCancelled(true);
                    return;
                }
                e.setCancelled(true);
                ((WitherSkull)p.launchProjectile(WitherSkull.class)).setVelocity(e.getProjectile().getVelocity().multiply(0.3D));
                p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SHOOT, 2.0F, 2.0F);
                Cooldown.add(p.getName());
            }
        }
    }

    @EventHandler
    public void onLeftClickRod(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (((e.getAction() == Action.LEFT_CLICK_AIR) || (e.getAction() == Action.LEFT_CLICK_BLOCK) || (e.getAction() == Action.PHYSICAL)) &&
                (e.getMaterial().equals(Material.END_ROD)) &&
                (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + ChatColor.ITALIC + "Fire Strike"))) {
            p.launchProjectile(EnderPearl.class);
            p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_SHOOT, 2.0F, 2.0F);
        }
    }

    @EventHandler
    public void onEnderPearl(PlayerTeleportEvent e) {
        Player player = e.getPlayer();
        if ((player.getInventory().getItemInMainHand().getType().equals(Material.END_ROD)) &&
                (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + ChatColor.ITALIC + "The Dragon's Breath"))) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onlightning(EntityDamageByEntityEvent e) {
        if (((e.getDamager() instanceof Arrow)) &&
                ((((Arrow)e.getDamager()).getShooter() instanceof Player)) &&
                ((e.getEntity() instanceof Player))) {
            Player damager = (Player)((Arrow)e.getDamager()).getShooter();
            Player victim = (Player)e.getEntity();
            ItemStack weapon = damager.getInventory().getItemInMainHand();
            if ((weapon.hasItemMeta()) &&
                    (weapon.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "" + ChatColor.BOLD + ChatColor.ITALIC + "The Lightning Stirke"))) {
                Random rand = new Random();
                int numb = rand.nextInt(100) + 1;
                if (numb <= 20) {
                    victim.getWorld().strikeLightning(victim.getLocation());
                }
            }
        }
    }

    @EventHandler
    public void onThorHammer(EntityDamageByEntityEvent e) {
        if (((e.getDamager() instanceof Player)) &&
                ((e.getEntity() instanceof Player))) {
            Player damager = (Player)e.getDamager();
            Player vic = (Player)e.getEntity();
            if ((damager.getInventory().getItemInMainHand().hasItemMeta()) &&
                    (damager.getInventory().getItemInMainHand().getType().equals(Material.STONE_AXE)) &&
                    (damager.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GRAY + "" + ChatColor.BOLD + ChatColor.ITALIC + "The Mjolnir"))) {
                Random rand = new Random();
                int numb = rand.nextInt(100) + 1;
                if (numb <= 25) {
                    vic.getWorld().strikeLightning(vic.getLocation());
                }
            }
        }
    }

    @EventHandler
    public void onSniperFire(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (((e.getAction() == Action.LEFT_CLICK_AIR) || (e.getAction() == Action.LEFT_CLICK_BLOCK) || (e.getAction() == Action.PHYSICAL)) &&
                (p.getInventory().getItemInMainHand().getType().equals(Material.IRON_HOE)) &&
                (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "" + ChatColor.BOLD + ChatColor.ITALIC + "Snip")) &&
                ((p instanceof Player))) {
            if (BoltAction.isonCD(p.getName())) {
                p.sendMessage(ConfigReader.getMessage("rifle-reload-cooldown", true));
                e.setCancelled(true);
                return;
            }
            Snowball g = (Snowball)p.launchProjectile(Snowball.class);
            g.setVelocity(g.getVelocity().multiply(10));
            p.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, 1.0F, 1.0F);
            BoltAction.add(p.getName());
        }
    }

    @EventHandler
    public void onSnowball(EntityDamageByEntityEvent e) {
        if ((e.getDamager() instanceof Snowball)) {
            Snowball f = (Snowball)e.getDamager();
            if ((f.getShooter() instanceof Player)) {
                Player shooter = (Player)f.getShooter();
                if (shooter.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "" + ChatColor.BOLD + ChatColor.ITALIC + "Snip")) {
                    e.setDamage(19.0D);
                }
            }
        }
    }

    @EventHandler
    public void onFang(EntityDamageByEntityEvent e) {
        if (((e.getDamager() instanceof Player)) &&
                ((e.getEntity() instanceof Player))) {
            Player damager = (Player)e.getDamager();
            Player victim = (Player)e.getEntity();
            if ((damager.getInventory().getItemInMainHand().getType().equals(Material.QUARTZ)) &&
                    (damager.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + ChatColor.ITALIC + "Fangs"))) {
                if (Fangs.isonCD(damager.getName())) {
                    damager.sendMessage(ConfigReader.getMessage("basic-cooldown", true));
                    e.setCancelled(true);
                    return;
                }
                victim.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 1, 1));
                damager.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 1));
                Fangs.add(e.getDamager().getName());
            }
        }
    }

}
