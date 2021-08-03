package de.niklashere.hidenseek.files.languages;

import de.niklashere.hidenseek.libary.Fileaccess;

import java.io.File;

/**
 * Creates an English language file.
 *
 * @author Niklashere
 * @since 03.08.2021
 */
public class EN {

  private static String language = "en";

  /**
   * Creates a config and adds all Strings, if their arn't already added.
   */
  public static void loadMessages() {

    // Chatmessages
    addMessage(Variablelist.chat_joinMessage, "%p has joined the server.");
    addMessage(Variablelist.chat_quitMessage, "%p has left the server.");
    addMessage(Variablelist.chat_stopServer, "Server is shuting down");
    addMessage(Variablelist.chat_countdownLobby, "The game starts in %t %s.");
    addMessage(Variablelist.chat_countdownWarmup, "The game starts in %t %s.");
    addMessage(Variablelist.chat_countdownIngame, "The game ends in %t %s.");
    addMessage(Variablelist.chat_countdownEnd, "Server reboots in %t %s.");
    addMessage(Variablelist.chat_secondSingular, "second");
    addMessage(Variablelist.chat_secondPlural, "seconds");
    addMessage(Variablelist.chat_missingPermission, "You don't have enough permission.");
    addMessage(Variablelist.chat_saveError, "Saving the changes failed.");
    addMessage(Variablelist.chat_found, "%p was found by %k");
    addMessage(Variablelist.chat_stunned, "%p was stunned by %k");

    // Commands

    // Stats Command
    addMessage(Variablelist.command_stats_m1, "=====================");
    addMessage(Variablelist.command_stats_m2, "was cought: %sc");
    addMessage(Variablelist.command_stats_m3, "has found: %sf");
    addMessage(Variablelist.command_stats_m4, "wins: %sw");
    addMessage(Variablelist.command_stats_m5, "plays: %spl");
    addMessage(Variablelist.command_stats_m6, "points: %spo");
    addMessage(Variablelist.command_stats_m7, "=====================");
    addMessage(Variablelist.command_stats_usage, "/stats <player>");

    // Inventory

    // Setup Inventory
    addMessage(Variablelist.inv_setup_name, "Setup inventory");
    addMessage(Variablelist.inv_setup_spawnpointLobby, "set Lobby Spawnpoint");
    addMessage(Variablelist.inv_setup_spawnpointSeeker, "set Seeker Spawnpoint");
    addMessage(Variablelist.inv_setup_spawnpointHider, "set Hider Spawnpoint");
    addMessage(Variablelist.inv_setup_minPlayers, "min. Players to start Game: ");
    addMessage(Variablelist.inv_setup_maxPlayers, "max. Players: ");
    addMessage(Variablelist.inv_setup_maxSeeker, "max. Seekers: ");
    addMessage(Variablelist.inv_setup_inGameTime, "Gametime: ");
    addMessage(Variablelist.inv_setup_instruction1, "Leftclick: + 1 / Rightclick: -1");
    addMessage(Variablelist.inv_setup_instruction2,
        "Shift + Leftclick: + 10 / Rightclick + Shift: -10");

    // Hint Inventory
    addMessage(Variablelist.inv_hint_name, "Hint inventory");
    addMessage(Variablelist.inv_hint_Firework, "Firework");
    addMessage(Variablelist.inv_hint_Meow, "Meow");
    addMessage(Variablelist.inv_hint_Explosion, "Explosion");

    // Role Inventory
    addMessage(Variablelist.inv_role_name, "choose Role");
    addMessage(Variablelist.inv_role_hider, "hider");
    addMessage(Variablelist.inv_role_seeker, "seeker");

    // Mapvoting Inventory
    addMessage(Variablelist.inv_mapvoting_Name, "Mapvoting");

    // Items

    // Lobbyitems
    addMessage(Variablelist.items_lobby_chest, "Mapvoting");
    addMessage(Variablelist.items_lobby_redBanner, "Choose role");

    // Seekeritems
    addMessage(Variablelist.items_seeker_sword, "Sword");
    addMessage(Variablelist.items_seeker_bow, "Bow");

    // Hideritems
    addMessage(Variablelist.items_hider_stun, "Stunner");
    addMessage(Variablelist.items_hider_hint, "Hints");

  }

  /**
   * Adds a message to a config, if not already exist.
   * 
   * @param string  String under wich the message should be saved
   * @param message The content that should be saved behind string
   */
  private static void addMessage(String string, String message) {
    File file = new File("plugins/hidenseek/languages", language + ".yml");
    if (Fileaccess.getString(string, file) == null) {
      Fileaccess.setString(string, file, message);
    }
  }
}
