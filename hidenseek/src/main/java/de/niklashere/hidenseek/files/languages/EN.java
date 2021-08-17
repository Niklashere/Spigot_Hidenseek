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
   * -
   * Prefixes:
   * %pc%: chat prefix
   * %pi%: item prefix
   * %pii%: inv-item prefix
   * %pin%: inv-name
   * -
   * Colors:
   * &: §
   * %cp%: primary chat color
   * %cs%: secondary chat color
   * %cw%: warning color
   * -
   * Players:
   * %pp%: player
   * %pk%: killer
   * -
   * Stats:
   * %sc%: coughts
   * %sf%: founds
   * %sw%: wins
   * %spl%: plays
   * %spo%: points
   * -
   * Hints:
   * %hp%: points for hint
   * -
   * Countdown:
   * %t%: time
   * %s%: second
   */
  public static void loadMessages() {

    // Chatmessages
    addMessage(Variablelist.chat_joinMessage, "%pc%%cs%%pp%%cp% has joined the server.");
    addMessage(Variablelist.chat_quitMessage, "%pc%%cs%%pp%%cp% has left the server.");
    addMessage(Variablelist.chat_stopServer, "%pc%Server is shuting down");
    addMessage(Variablelist.chat_countdownLobby, "%pc%The game starts in %cs%%t% %s%%cp%.");
    addMessage(Variablelist.chat_countdownWarmup, "%pc%The game starts in %cs%%t% %s%%cp%.");
    addMessage(Variablelist.chat_countdownIngame, "%pc%The game ends in %cs%%t% %s%%cp%.");
    addMessage(Variablelist.chat_countdownEnd, "%pc%Server reboots in %cs%%t% %s%%cp%.");
    addMessage(Variablelist.chat_skiplobby, "%pc%Max Player reachead.");
    addMessage(Variablelist.chat_secondSingular, "second");
    addMessage(Variablelist.chat_secondPlural, "seconds");
    addMessage(Variablelist.chat_missingPermission, "%pc%You don't have enough permission.");
    addMessage(Variablelist.chat_saveError, "%pc%Saving the changes failed.");
    addMessage(Variablelist.chat_found, "%pc%%cs%%pp%%cp% was found by %cs%%pk%%cp%.");
    addMessage(Variablelist.chat_stunned, "%pc%%cs%%pp%%cp% was stunned by %cs%%pk%%cp%.");
    addMessage(Variablelist.chat_point, "%pc%You recived %cs%%hp% Points %cp%for giving a hint.");
    addMessage(Variablelist.chat_wait, "%pc%%cw%You have to wait %cs%%t% %s%%cw%.");
    addMessage(Variablelist.chat_wait_ended, "%pc%You can use %cs%%name%%cp% again.");
    addMessage(Variablelist.chat_mapvoting, "%pc%You voted for the map %cs%%name%%cp%.");
    addMessage(Variablelist.chat_role, "%pc%You joined the role %cs%%name%%cp%.");
    addMessage(Variablelist.chat_role_full, "%pc%%cw%The role %cs%%name%%cw% is full.");
    addMessage(Variablelist.chat_location, "%pc%You saved the location %cs%%name%%cp%.");
    addMessage(Variablelist.chat_hider, "Hider");
    addMessage(Variablelist.chat_seeker, "Seeker");
    addMessage(Variablelist.chat_spectator, "Spectator");
    addMessage(Variablelist.chat_choosedprop, "%pc%You choosed %cs%%name%%cp% as your prop.");

    // Commands

    // Stats Command
    addMessage(Variablelist.command_stats_m1, "%pc%%cs%=====================");
    addMessage(Variablelist.command_stats_m2, "%pc%was cought: %cs%%sc%");
    addMessage(Variablelist.command_stats_m3, "%pc%has found: %cs%%sf%");
    addMessage(Variablelist.command_stats_m4, "%pc%wins: %cs%%sw%");
    addMessage(Variablelist.command_stats_m5, "%pc%plays: %cs%%spl%");
    addMessage(Variablelist.command_stats_m6, "%pc%points: %cs%%spo%");
    addMessage(Variablelist.command_stats_m7, "%pc%%cs%=====================");
    addMessage(Variablelist.command_stats_usage, "%pc%%cw%/stats <player>");

    // Language Command
    addMessage(Variablelist.command_lang_languages,
        "%pc%The following languages can be selected: ");
    addMessage(Variablelist.command_lang_switch, "%pc%You switched your language to: ");
    addMessage(Variablelist.command_lang_usage, "%pc%/language <language-code>");

    // Inventory

    // Setup Inventory
    addMessage(Variablelist.inv_setup_name, "%pin%Setup inventory");
    addMessage(Variablelist.inv_setup_spawnpointLobby, "%pii%set Lobby Spawnpoint");
    addMessage(Variablelist.inv_setup_spawnpointSeeker, "%pii%set Seeker Spawnpoint");
    addMessage(Variablelist.inv_setup_spawnpointHider, "%pii%set Hider Spawnpoint");
    addMessage(Variablelist.inv_setup_minPlayers, "%pii%min. Players to start Game: ");
    addMessage(Variablelist.inv_setup_maxPlayers, "%pii%max. Players: ");
    addMessage(Variablelist.inv_setup_maxSeeker, "%pii%max. Seekers: ");
    addMessage(Variablelist.inv_setup_inGameTime, "%pii%Gametime: ");
    addMessage(Variablelist.inv_setup_instruction1, "Leftclick: + 1 / Rightclick: -1");
    addMessage(Variablelist.inv_setup_instruction2,
        "Shift + Leftclick: + 10 / Rightclick + Shift: -10");

    // Hint Inventory
    addMessage(Variablelist.inv_hint_name, "%pin%Hint inventory");
    addMessage(Variablelist.inv_hint_Firework, "%pii%Firework");
    addMessage(Variablelist.inv_hint_Meow, "%pii%Meow");
    addMessage(Variablelist.inv_hint_Explosion, "%pii%Explosion");

    // Role Inventory
    addMessage(Variablelist.inv_role_name, "%pin%choose Role");
    addMessage(Variablelist.inv_role_hider, "%pii%hider");
    addMessage(Variablelist.inv_role_seeker, "%pii%seeker");

    // Mapvoting Inventory
    addMessage(Variablelist.inv_mapvoting_name, "%pin%Mapvoting");

    // Chooseprop Inventory
    addMessage(Variablelist.inv_chooseprop_name, "%pin%Choose prop");

    // Items

    // Lobbyitems
    addMessage(Variablelist.items_lobby_chest, "%pii%Mapvoting");
    addMessage(Variablelist.items_lobby_redBanner, "%pii%Choose role");
    addMessage(Variablelist.items_lobby_slimeball, "%pii%Choose prop");

    // Seekeritems
    addMessage(Variablelist.items_seeker_sword, "%pii%Sword");
    addMessage(Variablelist.items_seeker_bow, "%pii%Bow");

    // Hideritems
    addMessage(Variablelist.items_hider_stun, "%pii%Stunner");
    addMessage(Variablelist.items_hider_hint, "%pii%Hints");

    // Console Output
    addMessage(Variablelist.console_languages, "Successfully loaded languages: ");
    addMessage(Variablelist.console_save_error, "Changes couldn't be saved");
    addMessage(Variablelist.console_mysql_connected, "Connection to MySQL successfull.");
    addMessage(Variablelist.console_mysql_closed, "Connection to MySQL successfull closed.");
    addMessage(Variablelist.console_mysql_error, "MySQL Error: ");
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
