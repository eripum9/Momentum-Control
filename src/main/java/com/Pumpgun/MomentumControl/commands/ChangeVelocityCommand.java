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

public class ChangeVelocityCommand implements CommandExecutor {

    private final MomentumControlPlugin plugin;

    public ChangeVelocityCommand(MomentumControlPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 2) {
            sender.sendMessage("Usage: /changevelocity <player|selector> <value>");
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
            sender.sendMessage("Usage: /changevelocity <player|selector> <value>");
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
                org.bukkit.util.Vector direction = target.getLocation().getDirection().normalize();
                org.bukkit.util.Vector current = target.getVelocity().clone();

                // Add directional push and override vertical component so standing players still move
                org.bukkit.util.Vector applied;
                // Fallback if direction was zero-length
                if (direction.lengthSquared() < 0.0001) {
                    applied = new org.bukkit.util.Vector(0, value, 0);
                } else {
                    applied = current.add(direction.multiply(value));
                    applied.setY(value);
                }

                target.setVelocity(applied);
                target.setFallDistance(0f);
                plugin.getNoFallPlayers().add(target.getUniqueId());
            }
        }
        return true;
    }
}
