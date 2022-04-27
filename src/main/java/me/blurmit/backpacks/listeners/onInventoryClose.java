package me.blurmit.backpacks.listeners;

import me.blurmit.backpacks.BackPacks;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class onInventoryClose implements Listener {

    private final BackPacks plugin;

    public onInventoryClose(BackPacks plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        Inventory inventory = event.getInventory();
        ItemStack[] inventoryContents = event.getInventory().getContents();

        if (plugin.getBackpackManager().isBackpack(player, inventory)) {
            plugin.getBackpackManager().getBackpackStorage().put(player.getUniqueId(), inventoryContents);
            plugin.getBackpackManager().saveBackpacks();
        }
    }

}
