package me.blurmit.backpacks.backpacks;

import me.blurmit.backpacks.BackPacks;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BackPackManager {

    private final BackPacks plugin;
    private final Map<UUID, ItemStack[]> backpackStorage;
    private final Map<UUID, Inventory> backpackList;

    public BackPackManager(BackPacks plugin) {
        this.plugin = plugin;
        this.backpackStorage = new HashMap<>();
        this.backpackList = new HashMap<>();
    }

    public void loadBackpacks() {
        if (backpackStorage.isEmpty()) {
            return;
        }

        plugin.getConfig().getConfigurationSection("Backpacks").getKeys(false).forEach(backpack -> {
            backpackStorage.put(UUID.fromString(backpack), (ItemStack[]) plugin.getConfig().get("Backpacks." + backpack));
        });
    }

    public void saveBackpacks() {
        backpackStorage.keySet().forEach(backpack -> {
            plugin.getConfig().set("Backpacks." + backpack.toString(), getBackpack(backpack));
            plugin.saveConfig();
        });
    }

    public ItemStack[] getBackpack(UUID uuid) {
        return getBackpackStorage().get(uuid);
    }

    public Map<UUID, ItemStack[]> getBackpackStorage() {
        return backpackStorage;
    }

    public void getBackpackInventory(Player player) {
        Inventory backpack = Bukkit.createInventory(player, 54, player.getName() + "'s Backpack");
        backpack.setContents(getBackpack(player.getUniqueId()));

        player.openInventory(backpack);
        setBackpack(player, backpack);
    }

    public void createBackpack(Player player) {
        getBackpackStorage().put(player.getUniqueId(), new ItemStack[] {});
    }

    public boolean hasBackpack(Player player) {
        return getBackpackStorage().containsKey(player.getUniqueId());
    }

    public void setBackpack(Player player, Inventory inventory) {
        backpackList.put(player.getUniqueId(), inventory);
    }

    public boolean isBackpack(Player player, Inventory inventory) {
        return backpackList.get(player.getUniqueId()).equals(inventory);
    }

}
