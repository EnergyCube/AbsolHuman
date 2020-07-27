package fr.energycube.absolhuman.events;

import fr.energycube.absolhuman.AbsolPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.*;

public class WaitLogin implements Listener {


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommandPreprocess(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        AbsolPlayer ap = AbsolPlayer.getAPlayer(p);

        if(!ap.isLogged() && !(e.getMessage().startsWith("/login") || e.getMessage().startsWith("/register"))){
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + "MERCI DE VOUS IDENTIFIER !");
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockPlace(BlockPlaceEvent e) {
        AbsolPlayer ap = AbsolPlayer.getAPlayer(e.getPlayer());
        if(!ap.isLogged()){
            e.setCancelled(true);
            ap.sendAuthMessage();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteract(PlayerInteractEvent e) {
        AbsolPlayer ap = AbsolPlayer.getAPlayer(e.getPlayer());
        if(!ap.isLogged()){
            e.setCancelled(true);
            ap.sendAuthMessage();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDropItem(PlayerDropItemEvent e) {
        AbsolPlayer ap = AbsolPlayer.getAPlayer(e.getPlayer());
        if(!ap.isLogged()){
            e.setCancelled(true);
            ap.sendAuthMessage();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(PlayerMoveEvent e) {
        AbsolPlayer ap = AbsolPlayer.getAPlayer(e.getPlayer());
        if(!ap.isLogged()){
            e.setCancelled(true);
            ap.sendAuthMessage();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteractEntity(PlayerInteractEntityEvent e) {
        AbsolPlayer ap = AbsolPlayer.getAPlayer(e.getPlayer());
        if(!ap.isLogged()){
            e.setCancelled(true);
            ap.sendAuthMessage();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onDamage(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player){
            AbsolPlayer absolPlayer = AbsolPlayer.getAPlayer((Player)e.getDamager());
            if(!absolPlayer.isLogged())
                e.setCancelled(true);
        }
    }

}
