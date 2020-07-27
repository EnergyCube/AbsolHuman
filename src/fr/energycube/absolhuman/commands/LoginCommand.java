package fr.energycube.absolhuman.commands;

import fr.energycube.absolhuman.AbsolHuman;
import fr.energycube.absolhuman.AbsolPlayer;
import fr.energycube.absolhuman.utils.Security;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LoginCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(sender instanceof Player){
            if(args.length == 1){
                String supposedpass = args[0];
                AbsolPlayer ap = AbsolPlayer.getAPlayer((Player)sender);
                if(!ap.isLogged()) {
                    String pass = AbsolHuman.getInstance().getConfig().getString("player." + sender.getName() + ".password");
                    if(pass == null){
                        sender.sendMessage(ChatColor.RED + "Vous devez vous créer un compte avant de pouvoir vous connecter !");
                    }else if (Security.getSHA512SecurePassword(supposedpass).equals(pass)) {
                        ap.setLogged(true);
                        sender.sendMessage(ChatColor.GREEN + "Identification réussite !");
                    } else {
                        ((Player) sender).kickPlayer(ChatColor.RED + "Mot de passe invalide !\nContactez un Administrateur si vous avez oublié votre mot de passe");
                    }
                }else{
                    sender.sendMessage(ChatColor.RED + "Vous êtes déjà connecté !");
                }
            }else{
                sender.sendMessage(ChatColor.RED + "Merci de vous connecter avec /login VotreMotDePasse");
            }
        }else{
            sender.sendMessage("T'es rigolo hein");
        }

        return false;
    }
}
