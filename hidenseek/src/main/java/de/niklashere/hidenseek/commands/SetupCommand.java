package de.niklashere.hidenseek.commands;

import de.niklashere.hidenseek.inventorys.SetupInventory;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Setup command which allows admin to setup this plugin.
 *
 * @author Niklashere
 * @since 01.08.2021
 */
public class SetupCommand implements CommandExecutor {

  @Override
  public final boolean onCommand(final CommandSender sender, final Command cmd,
      final String label, final String[] args) {
    Player p = (Player) sender;
    SetupInventory.openInventory(p);
    return false;
  }
}
