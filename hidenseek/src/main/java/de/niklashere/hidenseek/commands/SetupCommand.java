package de.niklashere.hidenseek.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.niklashere.inventorys.SetupInventory;

public class SetupCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		SetupInventory.openInventory(p);
		return false;
		
	}
}