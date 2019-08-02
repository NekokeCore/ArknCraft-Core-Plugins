package net.emtips.mfp;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WorldListener implements Listener {
    @EventHandler
    private void onWeatherChange(WeatherChangeEvent event)
    {
            Bukkit.broadcastMessage("§c[ArknCraft-OS]天气变了呢！");
    }
}
