package de.niklashere.hidenseek.libary;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.UUID;

/**
 * Fetches UUID from the Mojang servers.
 *
 * @author Niklashere
 * @since 03.08.2021
 */
public final class UuidFetcher {

  private static final String UUID_URL = "https://api.mojang.com/users/profiles/minecraft/";
  private static HashMap<String, UUID> player = new HashMap<>();

  /**
   * Get the uuid from a player.
   * 
   * @param playername player whos uuid is requested
   * @return uuid of the player
   */
  public static UUID getUuid(String playername) {
    if (player.get(playername) == null) {
      try {
        String output = callUrl(UUID_URL + playername);
        StringBuilder result = new StringBuilder();
        readData(output, result);
        String u = result.toString();
        StringBuilder uuid = new StringBuilder();
        for (int i = 0; i <= 31; i++) {
          uuid.append(u.charAt(i));
          if (i == 7 || i == 11 || i == 15 || i == 19) {
            uuid.append('-');
          }
        }
        return UUID.fromString(uuid.toString());
      } catch (Exception exception) {
        return null;
      }
    } else {
      return player.get(playername);
    }
  }

  private static void readData(String toRead, StringBuilder result) {
    for (int i = toRead.length() - 3; i >= 0; i--) {
      if (toRead.charAt(i) != '"') {
        result.insert(0, toRead.charAt(i));
      } else {
        break;
      }
    }
  }

  /**
   * Calls URL.
   * 
   * @param urlStr Url wich should be called
   * @return requested url
   */
  private static String callUrl(String urlStr) {
    StringBuilder sb = new StringBuilder();
    URLConnection urlConn;
    InputStreamReader in;
    try {
      URL url = new URL(urlStr);
      urlConn = url.openConnection();
      if (urlConn != null) {
        urlConn.setReadTimeout(60 * 1000);
      }
      if (urlConn != null && urlConn.getInputStream() != null) {
        in = new InputStreamReader(urlConn.getInputStream(), Charset.defaultCharset());
        BufferedReader bufferedReader = new BufferedReader(in);
        int cp;
        while ((cp = bufferedReader.read()) != -1) {
          sb.append((char) cp);
        }
        bufferedReader.close();
        in.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return sb.toString();
  }
}