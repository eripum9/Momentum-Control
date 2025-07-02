package com.example.changevelocity.commands;

import com.example.changevelocity.ChangeVelocityPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;

import java.util.Collection;

public class ChangeVelocityCommand implements CommandExecutor {

    private final ChangeVelocityPlugin plugin;

    public ChangeVelocityCommand(ChangeVelocityPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Only allow command blocks
        if (!(sender instanceof BlockCommandSender)) {
            sender.sendMessage("This command can only be run by a command block.");
            return true;
        }

        if (args.length != 2) {
            sender.sendMessage("Usage: /changevelocity <player> <value>");
            return true;
        }

        Collection<? extends Entity> targets = Bukkit.selectEntities(sender, args[0]);
        if (targets.isEmpty()) {
            sender.sendMessage("No players found.");
            return true;
        }

        double value;
        try {
            value = Double.parseDouble(args[1]);
            if (value < 0.1 || value > 10) {
                sender.sendMessage("Value must be between 0.1 and 10.");
                return true;
            }
        } catch (NumberFormatException e) {
            sender.sendMessage("Usage: /changevelocity <player> <value>");
            return true;
        }

        for (Entity entity : targets) {
            if (entity instanceof Player target) {
                // Multiply the player's current velocity by the value (no flinging)
                target.setVelocity(target.getVelocity().multiply(value));
                plugin.getNoFallPlayers().add(target.getUniqueId());
            }
        }
        return true;
    }
}