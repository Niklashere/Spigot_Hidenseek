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
    addMessage(Variablelist.chat_joinMessage, "%pc%%cs%%pp%%cp% hat den Server betreten.");
    addMessage(Variablelist.chat_quitMessage, "%pc%%cs%%pp%%cp% hat den Server verlassen.");
    addMessage(Variablelist.chat_stopServer, "%pc%Server schaltet sich ab");
    addMessage(Variablelist.chat_countdownLobby, "%pc%Das Spiel startet in %cs%%t% %s%%cp%.");
    addMessage(Variablelist.chat_countdownWarmup, "%pc%Das Spiel startet in %cs%%t% %s%%cp%.");
    addMessage(Variablelist.chat_countdownIngame, "%pc%Das Spiel startet in %cs%%t% %s%%cp%.");
    addMessage(Variablelist.chat_countdownEnd, "%pc%Das Spiel startet in %cs%%t% %s%%cp%.");
    addMessage(Variablelist.chat_skiplobby, "%pc%Der Lobbycountdown wird übersprungen.");
    addMessage(Variablelist.chat_already_skipped, 
        "%pc%Der Lobbycountdown wurde bereits übersprungen.");
    addMessage(Variablelist.chat_secondSingular, "Sekunde");
    addMessage(Variablelist.chat_secondPlural, "Sekunden");
    addMessage(Variablelist.chat_missingPermission, "%pc%Du hast nicht genug Rechte.");
    addMessage(Variablelist.chat_saveError, "%pc%Speichern der Änderungen sind fehlgeschlagen.");
    addMessage(Variablelist.chat_found, "%pc%%cs%%pp%%cp% wurde von %cs%%pk%%cp% gefunden.");
    addMessage(Variablelist.chat_stunned, "%pc%%cs%%pp%%cp% wurde von %cs%%pk%%cp% gelähmt.");
    addMessage(Variablelist.chat_point, "%pc%Du hast %cs%%hp% Punkte%cp% für diesen Tipp.");
    addMessage(Variablelist.chat_wait, "%pc%%cw%Du hast noch %cs%%t% %s%%cw% zu warten.");
    addMessage(Variablelist.chat_wait_ended, "%pc%Du kannst %cs%%name%%cp% wieder benutzen.");
    addMessage(Variablelist.chat_mapvoting, "%pc%Du hast für die Map %cs%%name%%cp% gevoted.");

    String[] won = { "%pc%%cs%=====================",
        "%pc%Die Karte %cs%%name%%cp% hat das Mapvoting gewonnen.",
        "%pc%Die Karte wurde von %cs%%author%%cp% gebaut.", "%pc%%cs%=====================" };
    addMessageList(Variablelist.chat_mapvoting_won, won);

    addMessage(Variablelist.chat_role, "%pc%Du bist der Rolle %cs%%name%%cp% beigetreten.");
    addMessage(Variablelist.chat_role_full, "%pc%%cw%Die Rolle %cs%%name%%cw% ist voll.");
    addMessage(Variablelist.chat_location, "%pc%Du hast die Location %cs%%name%%cp% gespeichert.");
    addMessage(Variablelist.chat_hider, "Verstecker");
    addMessage(Variablelist.chat_seeker, "Sucher");
    addMessage(Variablelist.chat_spectator, "Spectator");
    addMessage(Variablelist.chat_voted_mode, "%pc%Du hast für %cs%%name%%cp% gestimmt.");
    addMessage(Variablelist.chat_voted_classic, "Klassik");
    addMessage(Variablelist.chat_voted_prophunt, "Prophunt");

    // Commands

    // Stats Command
    String[] stats = { "%pc%%cs%=====================", "%pc%wurde Gefangen: %cs%%sc%",
        "%pc%hat Gefunden: %cs%%sf%", "%pc%Gewonnen: %cs%%sw%", "%pc%Spiele: %cs%%spl%",
        "%pc%Punkte: %cs%%spo%", "%pc%%cs%=====================" };
    addMessageList(Variablelist.command_stats, stats);
    addMessage(Variablelist.command_stats_usage, "%pc%%cw%/stats <player>");

    // Language Command
    addMessage(Variablelist.command_lang_languages,
        "%pc%Zu foldenden Sprachen kann gewechselt werden: ");
    addMessage(Variablelist.command_lang_switch, "%pc%Folgende Sprache wurde ausgewählt: ");
    addMessage(Variablelist.command_lang_usage, "%pc%/language <language-code>");

    // Leaderboard Command
    String[] lead = { "%pc%%cs%=====================", "%pc%#%pos% %cs%%pk%%cp% - %spo%",
        "%pc%#%pos% %cs%%pk%%cp% - %spo%", "%pc%#%pos% %cs%%pk%%cp% - %spo%",
        "%pc%#%pos% %cs%%pk%%cp% - %spo%", "%pc%#%pos% %cs%%pk%%cp% - %spo%",
        "%pc%#%pos% %cs%%pk%%cp% - %spo%", "%pc%#%pos% %cs%%pk%%cp% - %spo%",
        "%pc%#%pos% %cs%%pk%%cp% - %spo%", "%pc%#%pos% %cs%%pk%%cp% - %spo%",
        "%pc%#%pos% %cs%%pk%%cp% - %spo%", "%pc%%cs%=====================" };
    addMessageList(Variablelist.command_lead, lead);

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

    // Chooseprop Inventory
    addMessage(Variablelist.inv_chooseprop_name, "%pin%Wähle Prop");
    addMessage(Variablelist.chat_choosedprop,
        "%pc%Du hast %cs%%name%%cp% als dein Prop ausgewählt.");
    
    // Choose Gamemode Inventory
    addMessage(Variablelist.inv_gamemode_name, "%pin%Wähle Spielmodus");
    addMessage(Variablelist.inv_gamemode_classic, "%pin%Klassik");
    addMessage(Variablelist.inv_gamemode_prop, "%pin%Prophunt");

    // Items

    // Lobbyitems
    addMessage(Variablelist.items_lobby_chest, "%pii%Karte wählen");
    addMessage(Variablelist.items_lobby_redBanner, "%pii%Rolle wählen");
    addMessage(Variablelist.items_lobby_slimeball, "%pii%Wähle Prop");
    addMessage(Variablelist.items_lobby_fireworkstar, "%pii%Wähle Spielmodus");

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

  /**
   * Adds a list of messages to a config, if not already exist.
   *
   * @param string  List of strings under wich the message should be saved
   * @param message The content that should be saved behind string
   */
  private static void addMessageList(String string, String[] message) {
    File file = new File("plugins/hidenseek/languages", language + ".yml");
    if (Fileaccess.getStringList(string, file) == null
        || Fileaccess.getStringList(string, file).size() == 0) {
      Fileaccess.setStringList(string, file, message);

    }
  }
}
