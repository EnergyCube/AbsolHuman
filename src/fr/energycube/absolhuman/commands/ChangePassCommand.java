package fr.energycube.absolhuman.commands;

import fr.energycube.absolhuman.AbsolHuman;
import fr.energycube.absolhuman.utils.Security;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class ChangePassCommand implements CommandExecutor {

    List<String> blacklist = Arrays.asList(new String[]{"123", "motdepasse", "321", "abc", "azerty", "qwerty", "1234", "12345", "123456"});

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            if(AbsolHuman.getInstance().getConfig().get("player." + sender.getName() + ".password") != null) {
                if (args.length == 2) {
                    String actualpass = args[0];
                    String newpass = args[1];
                    if(Security.getSHA512SecurePassword(actualpass).equals(AbsolHuman.getInstance().getConfig().get("player." + sender.getName() + ".password"))){
                        if(!blacklist.contains(newpass)){
                            if(newpass.length() >= 3) {
                                if (!newpass.contains(sender.getName())) {
                                    sender.sendMessage(ChatColor.YELLOW + "Enregistrement du nouveau Mot de Passe...");
                                    AbsolHuman.getInstance().getConfig().set("player." + sender.getName() + ".password", Security.getSHA512SecurePassword(newpass));
                                    sender.sendMessage(ChatColor.GREEN + "Enregistrement réussi ! Votre Mot de Passe a changé !");
                                } else {
                                    sender.sendMessage(ChatColor.DARK_RED + "!!?? Votre Pseudo !!?? Mot de passe non autorisé !!");
                                }
                            }else{
                                sender.sendMessage(ChatColor.DARK_RED + "!! Mot de passe trop court !!");
                            }
                        }else{
                            sender.sendMessage(ChatColor.DARK_RED + "!! Mot de passe non autorisé !!");
                        }
                    }else{
                        sender.sendMessage(ChatColor.RED + "Mot de passe actuel invalide !");
                        sender.sendMessage(ChatColor.RED + "Merci de faire /changepass VotreMotDePasseActuel VotreNouveauMotDePasse");
                    }

                } else {
                    sender.sendMessage(ChatColor.RED + "Merci de faire /changepass VotreMotDePasseActuel VotreNouveauMotDePasse");
                }
            }else{
                sender.sendMessage(ChatColor.RED + "Impossible de changer votre Mot de Passe ! Car vous n'avez pas Mot de Passe !");
                sender.sendMessage(ChatColor.YELLOW + "Pour vous connecter faites /register UnMotDePasse");
            }
        }else{
            sender.sendMessage("t'es rigolo hein");
        }

        return false;
    }
}
