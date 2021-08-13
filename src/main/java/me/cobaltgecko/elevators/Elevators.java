package me.cobaltgecko.elevators;

import me.cobaltgecko.elevators.events.SneakEvent;
import me.cobaltgecko.elevators.events.JumpEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Elevators extends JavaPlugin {

    public static Plugin instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        getServer().getPluginManager().registerEvents(new JumpEvent(), instance);
        getServer().getPluginManager().registerEvents(new SneakEvent(), instance);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getInstance() {
        return instance;
    }
}
