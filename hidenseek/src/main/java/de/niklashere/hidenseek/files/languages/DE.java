package de.niklashere.hidenseek.files.languages;

import de.niklashere.hidenseek.libary.Fileaccess;

import java.io.File;

/**
 * Creates a German language file.
 *
 * @author Niklashere
 * @since 03.08.2021
 */
public class DE {

  private static String language = "de";

  /**
   * Creates a config and adds all Strings, if their arn't already added.
   */
  public static void loadMessages() {

    // Chatmessages
    addMessage(Variablelist.chat_joinMessage, "%p hat den Server betreten.");
    addMessage(Variablelist.chat_quitMessage, "%p hat den Server verlassen.");
    addMessage(Variablelist.chat_stopServer, "Server schaltet sich ab");
    addMessage(Variablelist.chat_countdownLobby, "Das Spiel startet in %s sekunden.");
    addMessage(Variablelist.chat_countdownWarmup, "Das Spiel startet in %s sekunden.");
    addMessage(Variablelist.chat_countdownIngame, "Das Spiel startet in %s sekunden.");
    addMessage(Variablelist.chat_countdownEnd, "Das Spiel startet in %s sekunden.");
    addMessage(Variablelist.chat_secondSingular, "Sekunde");
    addMessage(Variablelist.chat_secondPlural, "Sekunden");
    addMessage(Variablelist.chat_missingPermission, "Du hast nicht genug Rechte.");
    addMessage(Variablelist.chat_saveError, "Speichern der Änderungen sind fehlgeschlagen.");
    addMessage(Variablelist.chat_found, "%p wurde von %k gefunden.");
    addMessage(Variablelist.chat_stunned, "%p wurde von %k gelähmt.");

    // Commands

    // Stats Command
    addMessage(Variablelist.command_stats_m1, "=====================");
    addMessage(Variablelist.command_stats_m2, "wurde Gefangen: %sc");
    addMessage(Variablelist.command_stats_m3, "hat Gefunden: %sf");
    addMessage(Variablelist.command_stats_m4, "Gewonnen: %sw");
    addMessage(Variablelist.command_stats_m5, "Spiele: %spl");
    addMessage(Variablelist.command_stats_m6, "Punkte: %spo");
    addMessage(Variablelist.command_stats_m7, "=====================");
    addMessage(Variablelist.command_stats_usage, "/stats <player>");

    // Inventory

    // Setup Inventory
    addMessage(Variablelist.inv_setup_name, "Setup Inventar");
    addMessage(Variablelist.inv_setup_spawnpointLobby, "setzte Lobby Spawnpoint");
    addMessage(Variablelist.inv_setup_spawnpointSeeker, "setzte Sucher Spawnpoint");
    addMessage(Variablelist.inv_setup_spawnpointHider, "setzte Verstecker Spawnpoint");
    addMessage(Variablelist.inv_setup_minPlayers, "min. Spieler um das Spiel zu starten: ");
    addMessage(Variablelist.inv_setup_maxPlayers, "max. Spieler: ");
    addMessage(Variablelist.inv_setup_maxSeeker, "max. Sucher: ");
    addMessage(Variablelist.inv_setup_inGameTime, "Spielzeit: ");
    addMessage(Variablelist.inv_setup_instruction1, "Linksklick: + 1 / Rechtsklick: -1");
    addMessage(Variablelist.inv_setup_instruction2,
        "Shift + Linksklick: + 10 / Rechtsklick + Shift: -10");

    // Hint Inventory
    addMessage(Variablelist.inv_hint_name, "Tipp geben");
    addMessage(Variablelist.inv_hint_Firework, "Feuerwerk");
    addMessage(Variablelist.inv_hint_Meow, "Meow");
    addMessage(Variablelist.inv_hint_Explosion, "Explosion");

    // Role Inventory
    addMessage(Variablelist.inv_role_name, "Rolle wählen");
    addMessage(Variablelist.inv_role_hider, "Verstecker");
    addMessage(Variablelist.inv_role_seeker, "Sucher");

    // Mapvoting Inventory
    addMessage(Variablelist.inv_mapvoting_Name, "Mapvoting");

    // Items

    // Lobbyitems
    addMessage(Variablelist.items_lobby_chest, "Karte wählen");
    addMessage(Variablelist.items_lobby_redBanner, "Rolle wählen");

    // Seekeritems
    addMessage(Variablelist.items_seeker_sword, "Schwert");
    addMessage(Variablelist.items_seeker_bow, "Bogen");

    // Hideritems
    addMessage(Variablelist.items_hider_stun, "Stunner");
    addMessage(Variablelist.items_hider_hint, "Tipps");

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
