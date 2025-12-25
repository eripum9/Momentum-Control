package com.Pumpgun.MomentumControl.listeners;

import com.Pumpgun.MomentumControl.MomentumControlPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class NoFallListener implements Listener {
    private final MomentumControlPlugin plugin;

    public NoFallListener(MomentumControlPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onFallDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (event.getCause() == EntityDamageEvent.DamageCause.FALL &&
                plugin.getNoFallPlayers().contains(player.getUniqueId())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerLand(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (!plugin.getNoFallPlayers().contains(player.getUniqueId())) return;

        // Avoid deprecated isOnGround: detect support block just beneath the player
        boolean onGround = player.getLocation().subtract(0, 0.1, 0).getBlock().getType().isSolid();
        if (!onGround) {
            // Consider landing in fluids as a valid landing to clear the flag
            onGround = player.getLocation().getBlock().isLiquid();
        }

        if (onGround) {
            plugin.getNoFallPlayers().remove(player.getUniqueId());
            player.setVelocity(player.getVelocity().setY(0));
            player.setFallDistance(0f);
        }
    }
}
