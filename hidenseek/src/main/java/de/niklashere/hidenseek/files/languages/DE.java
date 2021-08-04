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
   * -
   * Prefixes: %pc%: chat prefix %pi%: item prefix %pii%: inv-item prefix %pin%:
   * inv-name
   * -
   * Colors: &: § %cp%: primary chat color %cs%: secondary chat color %cw%:
   * warning color
   * -
   * Players: %pp%: player %pk%: killer
   * -
   * Stats: %sc%: coughts %sf%: founds %sw%: wins %spl%: plays %spo%: points
   * -
   * Countdown: %t%: time %s%: second
   */
  public static void loadMessages() {

    // Chatmessages
    addMessage(Variablelist.chat_joinMessage, "%pc%%cs%%pp%%cp% hat den Server betreten.");
    addMessage(Variablelist.chat_quitMessage, "%pc%%cs%%pp%%cp% hat den Server verlassen.");
    addMessage(Variablelist.chat_stopServer, "%pc%Server schaltet sich ab");
    addMessage(Variablelist.chat_countdownLobby, "%pc%Das Spiel startet in %cs%%t% %s%%cp%.");
    addMessage(Variablelist.chat_countdownWarmup, "%pc%Das Spiel startet in %cs%%t% %s%%cp%.");
    addMessage(Variablelist.chat_countdownIngame, "%pc%Das Spiel startet in %cs%%t% %s%%cp%.");
    addMessage(Variablelist.chat_countdownEnd, "%pc%Das Spiel startet in %cs%%t% %s%%cp%.");
    addMessage(Variablelist.chat_skiplobby, "%pc%Maximale Spielerzahl erreicht.");
    addMessage(Variablelist.chat_secondSingular, "Sekunde");
    addMessage(Variablelist.chat_secondPlural, "Sekunden");
    addMessage(Variablelist.chat_missingPermission, "%pc%Du hast nicht genug Rechte.");
    addMessage(Variablelist.chat_saveError, "%pc%Speichern der Änderungen sind fehlgeschlagen.");
    addMessage(Variablelist.chat_found, "%pc%%cs%%pp%%cp% wurde von %cs%%pk%%cp% gefunden.");
    addMessage(Variablelist.chat_stunned, "%pc%%cs%%pp%%cp% wurde von %cs%%pk%%cp% gelähmt.");

    // Commands

    // Stats Command
    addMessage(Variablelist.command_stats_m1, "%pc%%cs%=====================");
    addMessage(Variablelist.command_stats_m2, "%pc%wurde Gefangen: %cs%%sc%");
    addMessage(Variablelist.command_stats_m3, "%pc%hat Gefunden: %cs%%sf%");
    addMessage(Variablelist.command_stats_m4, "%pc%Gewonnen: %cs%%sw%");
    addMessage(Variablelist.command_stats_m5, "%pc%Spiele: %cs%%spl%");
    addMessage(Variablelist.command_stats_m6, "%pc%Punkte: %cs%%spo%");
    addMessage(Variablelist.command_stats_m7, "%pc%%cs%=====================");
    addMessage(Variablelist.command_stats_usage, "%pc%%cw%/stats <player>");

    // Language Command
    addMessage(Variablelist.command_lang_languages,
        "%pc%Zu foldenden Sprachen kann gewechselt werden: ");
    addMessage(Variablelist.command_lang_switch, "%pc%Folgende Sprache wurde ausgewählt: ");
    addMessage(Variablelist.command_lang_usage, "%pc%/language <language-code>");

    // Inventory

    // Setup Inventory
    addMessage(Variablelist.inv_setup_name, "%pin%Setup Inventar");
    addMessage(Variablelist.inv_setup_spawnpointLobby, "%pii%setzte Lobby Spawnpoint");
    addMessage(Variablelist.inv_setup_spawnpointSeeker, "%pii%setzte Sucher Spawnpoint");
    addMessage(Variablelist.inv_setup_spawnpointHider, "%pii%setzte Verstecker Spawnpoint");
    addMessage(Variablelist.inv_setup_minPlayers, "%pii%min. Spieler um das Spiel zu starten: ");
    addMessage(Variablelist.inv_setup_maxPlayers, "%pii%max. Spieler: ");
    addMessage(Variablelist.inv_setup_maxSeeker, "%pii%max. Sucher: ");
    addMessage(Variablelist.inv_setup_inGameTime, "%pii%Spielzeit: ");
    addMessage(Variablelist.inv_setup_instruction1, "Linksklick: + 1 / Rechtsklick: -1");
    addMessage(Variablelist.inv_setup_instruction2,
        "Shift + Linksklick: + 10 / Rechtsklick + Shift: -10");

    // Hint Inventory
    addMessage(Variablelist.inv_hint_name, "%pin%Tipp geben");
    addMessage(Variablelist.inv_hint_Firework, "%pii%Feuerwerk");
    addMessage(Variablelist.inv_hint_Meow, "%pii%Meow");
    addMessage(Variablelist.inv_hint_Explosion, "%pii%Explosion");

    // Role Inventory
    addMessage(Variablelist.inv_role_name, "%pin%Rolle wählen");
    addMessage(Variablelist.inv_role_hider, "%pii%Verstecker");
    addMessage(Variablelist.inv_role_seeker, "%pii%Sucher");

    // Mapvoting Inventory
    addMessage(Variablelist.inv_mapvoting_name, "%pin%Mapvoting");

    // Items

    // Lobbyitems
    addMessage(Variablelist.items_lobby_chest, "%pii%Karte wählen");
    addMessage(Variablelist.items_lobby_redBanner, "%pii%Rolle wählen");

    // Seekeritems
    addMessage(Variablelist.items_seeker_sword, "%pii%Schwert");
    addMessage(Variablelist.items_seeker_bow, "%pii%Bogen");

    // Hideritems
    addMessage(Variablelist.items_hider_stun, "%pii%Stunner");
    addMessage(Variablelist.items_hider_hint, "%pii%Tipps");

    // Console Output
    addMessage(Variablelist.console_languages, "Erfolgreich folgende Sprachen geladen: ");
    addMessage(Variablelist.console_save_error, "Änderungen konnten nicht gespeichert werden");
    addMessage(Variablelist.console_mysql_connected, "MySQL Verbindung erfolgreich hergestellt.");
    addMessage(Variablelist.console_mysql_closed, "MySQL Verbindung erfolgreich geschlossen.");
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
