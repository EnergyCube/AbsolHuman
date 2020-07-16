package fr.energycube.absolhuman.events;

import fr.energycube.absolhuman.AbsolPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class WaitLogin implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        AbsolPlayer ap = AbsolPlayer.getAPlayer(e.getPlayer());
        if(!ap.isLogged()){
            e.setCancelled(true);
            ap.sendAuthMessage();
        }
    }

    @EventHandler
    public void onPlace(PlayerInteractEvent e) {
        AbsolPlayer ap = AbsolPlayer.getAPlayer(e.getPlayer());
        if(!ap.isLogged()){
            e.setCancelled(true);
            ap.sendAuthMessage();
        }
    }

    @EventHandler
    public void onPlace(PlayerDropItemEvent e) {
        AbsolPlayer ap = AbsolPlayer.getAPlayer(e.getPlayer());
        if(!ap.isLogged()){
            e.setCancelled(true);
            ap.sendAuthMessage();
        }
    }

    @EventHandler
    public void onPlace(PlayerMoveEvent e) {
        AbsolPlayer ap = AbsolPlayer.getAPlayer(e.getPlayer());
        if(!ap.isLogged()){
            e.setCancelled(true);
            ap.sendAuthMessage();
        }
    }

    @EventHandler
    public void onPlace(PlayerInteractEntityEvent e) {
        AbsolPlayer ap = AbsolPlayer.getAPlayer(e.getPlayer());
        if(!ap.isLogged()){
            e.setCancelled(true);
            ap.sendAuthMessage();
        }
    }

}
