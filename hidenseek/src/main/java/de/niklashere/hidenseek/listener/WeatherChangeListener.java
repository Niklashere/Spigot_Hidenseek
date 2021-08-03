package de.niklashere.hidenseek.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

/**
 * Listener for the WeatherChangeEvent.
 *
 * @author Niklashere
 * @since 31.07.2021
 */
public class WeatherChangeListener implements Listener {

  /**
   * Called when the event occurs.
   * 
   * @param e event
   */
  @EventHandler
  public void onWeatherChange(WeatherChangeEvent e) {
    e.setCancelled(true);
  }
}
