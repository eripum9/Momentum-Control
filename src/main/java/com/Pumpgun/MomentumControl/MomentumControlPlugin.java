package com.Pumpgun.MomentumControl;

import org.bukkit.plugin.java.JavaPlugin;
import com.Pumpgun.MomentumControl.commands.ChangeVelocityCommand;
import com.Pumpgun.MomentumControl.commands.ChangeHorizontalVelocityCommand;
import com.Pumpgun.MomentumControl.listeners.NoFallListener;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class MomentumControlPlugin extends JavaPlugin {

    private final Set<UUID> noFallPlayers = new HashSet<>();

    public Set<UUID> getNoFallPlayers() {
        return noFallPlayers;
    }

    @Override
    public void onEnable() {
        if (this.getCommand("changevelocity") != null) {
            this.getCommand("changevelocity").setExecutor(new ChangeVelocityCommand(this));
        }
        if (this.getCommand("changehorizontalvelocity") != null) {
            this.getCommand("changehorizontalvelocity").setExecutor(new ChangeHorizontalVelocityCommand(this));
        }
        getServer().getPluginManager().registerEvents(new NoFallListener(this), this);
    }

    @Override
    public void onDisable() {
        noFallPlayers.clear();
    }
}
