package fr.energycube.absolhuman.commands;

import fr.energycube.absolhuman.AbsolPlayer;
import fr.energycube.absolhuman.utils.MathXP;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LvlCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;
            AbsolPlayer ap = AbsolPlayer.getAPlayer(p);

            p.sendMessage(ChatColor.GREEN + "Vous avez " + ap.getXP() + " XP (LVL " + MathXP.getLVLfromXP(ap.getXP()) + ") !");
            p.sendMessage(ChatColor.GREEN + "Il vous faut " + MathXP.getNextXPNeeded(ap.getXP()) + " XP pour atteindre le prochain niveau ! ");
        }


        return false;
    }
}
