package fr.energycube.absolhuman.events;

import fr.energycube.absolhuman.AbsolPlayer;
import fr.energycube.absolhuman.utils.Grade;
import fr.energycube.absolhuman.utils.MathXP;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat implements Listener {

    @EventHandler
    private void onSpeak(AsyncPlayerChatEvent e){
        AbsolPlayer ap = AbsolPlayer.getAPlayer(e.getPlayer());
        Grade grade = ap.getGrade();
        int lvl = MathXP.getLVLfromXP(ap.getXP());
        e.setFormat(ap.getPlayer().getDisplayName() + " " + getColorFromLVL(lvl) + "{" + lvl + "}" + ChatColor.RESET + " : " + grade.getChatColor() + e.getMessage());
    }


    private ChatColor getColorFromLVL(int lvl){
        if(lvl <= 2){
            return ChatColor.GRAY;
        }else if(lvl <= 5){
            return ChatColor.YELLOW;
        }else if(lvl <= 8){
            return ChatColor.BLUE;
        } else if(lvl <= 10){
            return ChatColor.RED;
        }else{
            return ChatColor.DARK_PURPLE;
        }

    }
}
