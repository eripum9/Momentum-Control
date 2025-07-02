package com.example.changevelocity.listeners;

import com.example.changevelocity.ChangeVelocityPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class NoFallListener implements Listener {
    private final ChangeVelocityPlugin plugin;

    public NoFallListener(ChangeVelocityPlugin plugin) {
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

        // Check if the player is on the ground (without using deprecated isOnGround())
        if (player.getLocation().subtract(0, 0.1, 0).getBlock().getType().isSolid()) {
            plugin.getNoFallPlayers().remove(player.getUniqueId());
            // Message removed as requested
            player.setVelocity(player.getVelocity().setY(0));
        }
    }
}