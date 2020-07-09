package fr.energycube.absolhuman.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.util.Random;

public class Weather implements Listener {

    @EventHandler(ignoreCancelled = true)
    private void onWeatherChange(WeatherChangeEvent e) {

        if (e.toWeatherState()) {
            int r = new Random().nextInt(10);
            if(r != 5) {
                e.getWorld().getPlayers().forEach(player -> player.sendMessage(ChatColor.YELLOW + "Pluie annulée !"));
                e.setCancelled(true);
                e.getWorld().setWeatherDuration(0);
                e.getWorld().setThundering(false);
            }else{
                e.getWorld().getPlayers().forEach(player -> player.sendMessage(ChatColor.GOLD + "Pluie non annulée !"));
            }
        }

    }

}
