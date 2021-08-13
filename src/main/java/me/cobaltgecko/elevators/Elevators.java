package me.cobaltgecko.elevators;

import me.cobaltgecko.elevators.events.PlayerJumpEvent;
import me.cobaltgecko.elevators.events.PlayerSneakEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Elevators extends JavaPlugin {

    public static Plugin instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        getServer().getPluginManager().registerEvents(new PlayerJumpEvent(), instance);
        getServer().getPluginManager().registerEvents(new PlayerSneakEvent(), instance);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getInstance() {
        return instance;
    }
}
