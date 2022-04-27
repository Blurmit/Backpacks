package me.blurmit.backpacks.commands;

import me.blurmit.backpacks.BackPacks;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BackPackCommand implements CommandExecutor {

    private BackPacks plugin;

    public BackPackCommand(BackPacks plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Console cannot use backpacks.");
            return true;
        }

        if (!sender.hasPermission("backpacks.use")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }

        Player player = (Player) sender;

        if (!plugin.getBackpackManager().hasBackpack(player)) {
            player.sendMessage(ChatColor.GREEN + "No backpack found, creating you a new backpack...");
            plugin.getBackpackManager().createBackpack(player);
            plugin.getBackpackManager().getBackpackInventory(player);
            return true;
        }

        player.sendMessage(ChatColor.GREEN + "Opening your backpack...");
        plugin.getBackpackManager().getBackpackInventory(player);

        return true;
    }

}
