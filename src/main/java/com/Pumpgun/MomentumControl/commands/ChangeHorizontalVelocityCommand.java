package com.Pumpgun.MomentumControl.commands;

import com.Pumpgun.MomentumControl.MomentumControlPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;

import java.util.Collection;

public class ChangeHorizontalVelocityCommand implements CommandExecutor {

    private final MomentumControlPlugin plugin;

    public ChangeHorizontalVelocityCommand(MomentumControlPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 2) {
            sender.sendMessage("Usage: /changehorizontalvelocity <player|selector> <value>");
            return true;
        }

        // Only allow command blocks, console, or operators
        boolean isAllowed = sender instanceof BlockCommandSender || sender instanceof org.bukkit.command.ConsoleCommandSender || (sender instanceof Player p && p.isOp());
        if (!isAllowed) {
            sender.sendMessage("You must be an operator, command block, or console to use this command.");
            return true;
        }

        double value;
        try {
            value = Double.parseDouble(args[1]);
        } catch (NumberFormatException e) {
            sender.sendMessage("Usage: /changehorizontalvelocity <player|selector> <value>");
            return true;
        }

        // Support selectors (@a, @p, etc.) and player names
        Collection<? extends Entity> targets = Bukkit.selectEntities(sender, args[0]);
        if (targets.isEmpty()) {
            // Try direct player lookup if selector failed
            Player player = Bukkit.getPlayer(args[0]);
            if (player != null) {
                targets = java.util.Collections.singleton(player);
            } else {
                sender.sendMessage("No players found.");
                return true;
            }
        }

        for (Entity entity : targets) {
            if (entity instanceof Player target) {
                org.bukkit.util.Vector direction = target.getLocation().getDirection();
                direction.setY(0);
                if (direction.lengthSquared() < 0.0001) {
                    // Try using existing horizontal momentum if player is not looking anywhere
                    org.bukkit.util.Vector existing = target.getVelocity().clone();
                    existing.setY(0);
                    direction = existing;
                }
                if (direction.lengthSquared() < 0.0001) {
                    direction = new org.bukkit.util.Vector(1, 0, 0); // final fallback
                }

                org.bukkit.util.Vector horizontal = direction.normalize().multiply(value);
                horizontal.setY(target.getVelocity().getY()); // preserve vertical velocity

                target.setVelocity(horizontal);
                target.setFallDistance(0f);
                plugin.getNoFallPlayers().add(target.getUniqueId());
            }
        }
        return true;
    }
}
