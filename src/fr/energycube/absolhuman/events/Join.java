package fr.energycube.absolhuman.events;

import fr.energycube.absolhuman.AbsolPlayer;
import fr.energycube.absolhuman.utils.Grade;
import fr.energycube.absolhuman.utils.MathXP;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {

    @EventHandler
    private void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        AbsolPlayer ap = AbsolPlayer.getAPlayer(p);
        Grade grade = ap.getGrade();

        if(ap.getGrade() != Grade.Admin) {
            e.setJoinMessage(ChatColor.AQUA + "Le " + grade.getChatColor() + ap.getGrade().getChatName().replace("[", "").replace("]", "") + " " + ap.getPlayer().getName() + ChatColor.AQUA + " a rejoint le serveur !");
        }else{
            e.setJoinMessage(ChatColor.AQUA + "L'" + grade.getChatColor() + ap.getGrade().getChatName().replace("[", "").replace("]", "") + " " + ap.getPlayer().getName() + ChatColor.AQUA + " a rejoint le serveur !");
        }

        p.sendMessage(ChatColor.GREEN + "Vous avez " + ap.getXP() + " XP (LVL " + MathXP.getLVLfromXP(ap.getXP()) + ") !");
        p.sendMessage(ChatColor.GREEN + "Il vous faut " + MathXP.getNextXPNeeded(ap.getXP()) + " XP pour atteindre le prochain niveau ! ");

    }


}
