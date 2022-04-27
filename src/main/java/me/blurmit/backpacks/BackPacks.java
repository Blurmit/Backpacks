package me.blurmit.backpacks;

import me.blurmit.backpacks.backpacks.BackPackManager;
import me.blurmit.backpacks.commands.BackPackCommand;
import me.blurmit.backpacks.listeners.onInventoryClose;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class BackPacks extends JavaPlugin {

    private BackPackManager backPackManager;

    @Override
    public void onEnable() {
        Bukkit.getLogger().log(Level.INFO, "BackPacks has been successfully enabled!");
        saveDefaultConfig();

        getCommand("backpack").setExecutor(new BackPackCommand(this));
        Bukkit.getPluginManager().registerEvents(new onInventoryClose(this), this);

        this.backPackManager = new BackPackManager(this);
        getBackpackManager().loadBackpacks();
    }

    public BackPackManager getBackpackManager() {
        return backPackManager;
    }

}
