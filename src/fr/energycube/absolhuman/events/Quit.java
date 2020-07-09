package fr.energycube.absolhuman.events;

import fr.energycube.absolhuman.AbsolPlayer;
import fr.energycube.absolhuman.utils.Grade;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Quit implements Listener {

    @EventHandler
    private void onQuit(PlayerQuitEvent e){
        AbsolPlayer ap = AbsolPlayer.getAPlayer(e.getPlayer());
        Grade grade = ap.getGrade();

        if(ap.getGrade() != Grade.Admin) {
            e.setQuitMessage(ChatColor.GRAY + "Le " + grade.getChatColor() + ap.getGrade().getChatName().replace("[", "").replace("]", "") + " " + ap.getPlayer().getName() + ChatColor.AQUA + " vient de se déconnecter du serveur !");
        }else{
            e.setQuitMessage(ChatColor.GRAY + "L'" + grade.getChatColor() + ap.getGrade().getChatName().replace("[", "").replace("]", "") + " " + ap.getPlayer().getName() + ChatColor.AQUA + " vient de se déconnecter du serveur !");
        }

        ap.unload();
    }

}
