package fr.energycube.absolhuman.events;

import fr.energycube.absolhuman.AbsolPlayer;
import fr.energycube.absolhuman.utils.Grade;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat implements Listener {

    @EventHandler
    private void onSpeak(AsyncPlayerChatEvent e){
        AbsolPlayer ap = AbsolPlayer.getAPlayer(e.getPlayer());
        Grade grade = ap.getGrade();
        e.setFormat(ap.getPlayer().getDisplayName() + " : " + grade.getChatColor() + e.getMessage());
    }
}
