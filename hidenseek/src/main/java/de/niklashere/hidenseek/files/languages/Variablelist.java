package de.niklashere.hidenseek.files.languages;

/**
 * Class that defines all strings for the language files.
 * 
 * @author Niklashere
 * @since 03.08.2021
 */
public class Variablelist {

  // Nameming Schema: <path>_<to>_<string>_<name>
  // Ex. command_stats_usage

  // Chatmessages
  private static String chat = "chat.";
  public static String chat_joinMessage = chat + "joinmessage";
  public static String chat_quitMessage = chat + "quitmessage";
  public static String chat_stopServer = chat + "stop-server";
  public static String chat_countdownLobby = chat + "countdown-lobby";
  public static String chat_countdownWarmup = chat + "countdown-warmup";
  public static String chat_countdownIngame = chat + "countdown-ingame";
  public static String chat_countdownEnd = chat + "countdown-end";
  public static String chat_secondSingular = chat + "second-singular";
  public static String chat_secondPlural = chat + "second-plural";
  public static String chat_missingPermission = chat + "missing-permission";
  public static String chat_saveError = chat + "save-error";
  public static String chat_found = chat + "found";
  public static String chat_stunned = chat + "stunned";

  // Commands
  private static String command = "commands.";

  // Stats Command
  private static String stats = command + "stats.";
  public static String command_stats_m1 = stats + "m1";
  public static String command_stats_m2 = stats + "m2";
  public static String command_stats_m3 = stats + "m3";
  public static String command_stats_m4 = stats + "m4";
  public static String command_stats_m5 = stats + "m5";
  public static String command_stats_m6 = stats + "m6";
  public static String command_stats_m7 = stats + "m7";
  public static String command_stats_usage = stats + "usage";

  // Inventory
  private static String inventory = "inventory.";

  // Setup Inventory
  private static String setup = inventory + "setup.";
  public static String inv_setup_name = setup + "m7";
  public static String inv_setup_spawnpointLobby = setup + "m7";
  public static String inv_setup_spawnpointSeeker = setup + "m7";
  public static String inv_setup_spawnpointHider = setup + "m7";
  public static String inv_setup_minPlayers = setup + "m7";
  public static String inv_setup_maxPlayers = setup + "max-players";
  public static String inv_setup_maxSeeker = setup + "max-seeker";
  public static String inv_setup_inGameTime = setup + "ingametime";
  public static String inv_setup_instruction1 = setup + "instruction-1";
  public static String inv_setup_instruction2 = setup + "instruction-2";

  // Hint Inventory
  private static String hint = inventory + "hint.";
  public static String inv_hint_name = hint + "name";
  public static String inv_hint_Firework = hint + "Firework";
  public static String inv_hint_Meow = hint + "Meow";
  public static String inv_hint_Explosion = hint + "Explosion";

  // Role Inventory
  private static String role = inventory + "role.";
  public static String inv_role_name = role + "name";
  public static String inv_role_hider = role + "hider";
  public static String inv_role_seeker = role + "seeker";

  // Mapvoting Inventory
  private static String mapvoting = inventory + "mapvoting.";
  public static String inv_mapvoting_name = mapvoting + "seeker";

  // Items
  private static String items = "items.";

  // Lobbyitems
  private static String lobby = items + "lobby.";
  public static String items_lobby_chest = lobby + "chest";
  public static String items_lobby_redBanner = lobby + "red_banner";

  // Seekeritems
  private static String seeker = items + "seeker.";
  public static String items_seeker_sword = seeker + "sword";
  public static String items_seeker_bow = seeker + "bow";

  // Hideritems
  private static String hider = items + "hider.";
  public static String items_hider_stun = hider + "stun";
  public static String items_hider_hint = hider + "hint";

}
