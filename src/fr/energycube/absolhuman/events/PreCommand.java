package fr.energycube.absolhuman.events;

import fr.energycube.absolhuman.AbsolPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PreCommand implements Listener {

    @EventHandler
    public void onPreCommand(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        AbsolPlayer ap = AbsolPlayer.getAPlayer(p);

        if(!ap.isLogged() && !(e.getMessage().startsWith("/login") || e.getMessage().startsWith("/register"))){
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + "MERCI DE VOUS IDENTIFIER !");
        }
    }
}
