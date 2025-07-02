package com.example.changevelocity;

import org.bukkit.plugin.java.JavaPlugin;
import com.example.changevelocity.commands.ChangeVelocityCommand;
import com.example.changevelocity.listeners.NoFallListener;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ChangeVelocityPlugin extends JavaPlugin {

    private final Set<UUID> noFallPlayers = new HashSet<>();

    public Set<UUID> getNoFallPlayers() {
        return noFallPlayers;
    }

    @Override
    public void onEnable() {
        if (this.getCommand("changevelocity") != null) {
            this.getCommand("changevelocity").setExecutor(new ChangeVelocityCommand(this));
        }
        getServer().getPluginManager().registerEvents(new NoFallListener(this), this);
    }

    @Override
    public void onDisable() {
        noFallPlayers.clear();
    }
}
