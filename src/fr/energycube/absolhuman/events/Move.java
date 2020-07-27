package fr.energycube.absolhuman.events;

import fr.energycube.absolhuman.AbsolChunk;
import fr.energycube.absolhuman.AbsolHuman;
import fr.energycube.absolhuman.AbsolOfflinePlayer;
import fr.energycube.absolhuman.utils.TitleMessage;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;

public class Move implements Listener {

    HashMap<Player, String> lastChunkOwner = new HashMap<>();

    @EventHandler(priority = EventPriority.LOWEST)
    private void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        boolean debug = false;
        if(debug){
            System.out.println("======= DEBUG ======");
            System.out.println(event.getPlayer().getName());
            System.out.println(event.getPlayer().getLocation().getChunk());
            System.out.println(AbsolChunk.contains(event.getPlayer().getLocation().getChunk()));
            try{
                System.out.println(AbsolChunk.getAbsolChunk(event.getPlayer().getLocation().getChunk()).getOwner());
            }catch (Exception ex){}

        }

        if(AbsolChunk.contains(player.getLocation().getChunk())){
            String owner = AbsolChunk.getAbsolChunk(player.getLocation().getChunk()).getOwner();
            String lastowner = lastChunkOwner.get(player);
            if(lastowner != null){
                if(!lastowner.equalsIgnoreCase(owner)){
                    lastChunkOwner.remove(player);
                    lastChunkOwner.put(player, owner);
                    if(AbsolHuman.getInstance().getServer().getPlayerExact(owner) != null)
                        new TitleMessage("", AbsolHuman.getInstance().getServer().getPlayerExact(owner).getDisplayName()).send(player, 10 ,15, 10);
                    else {
                        AbsolOfflinePlayer owner_ap = new AbsolOfflinePlayer(owner);
                        new TitleMessage("", owner_ap.getGrade().getChatColor() + owner_ap.getGrade().getChatName() + " " + owner).send(player, 10, 15, 10);
                    }

                }
            }else{
                lastChunkOwner.put(player, owner);
            }
        }else{
            String owner = "Sauvage";
            String lastowner = lastChunkOwner.get(player);
            if(lastowner != null){
                if(!lastowner.equalsIgnoreCase(owner)){
                    lastChunkOwner.remove(player);
                    lastChunkOwner.put(player, owner);
                    new TitleMessage("",ChatColor.DARK_GREEN + owner).send(player, 10,10, 10);
                }
            }else{
                lastChunkOwner.put(player, owner);
            }
        }

    }
}
