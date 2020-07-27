package fr.energycube.absolhuman.commands;

import fr.energycube.absolhuman.AbsolHuman;
import fr.energycube.absolhuman.AbsolPlayer;
import fr.energycube.absolhuman.utils.Security;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class RegisterCommand implements CommandExecutor {

    List<String> blacklist = Arrays.asList(new String[]{"123", "motdepasse", "321", "abc", "azerty", "qwerty", "1234", "12345", "123456"});

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            if(AbsolHuman.getInstance().getConfig().get("player." + sender.getName() + ".password") == null) {
                if (args.length == 1) {
                    String wantedpass = args[0];
                    if (!blacklist.contains(wantedpass)) {
                        if(wantedpass.length() >= 3) {
                            if (!wantedpass.contains(sender.getName())) {
                                boolean finalcheck = true;
                                for(OfflinePlayer offp : AbsolHuman.getInstance().getServer().getOfflinePlayers()){
                                    if(wantedpass.equals(offp.getName())){
                                        finalcheck = false;
                                    }
                                }
                                if(finalcheck) {
                                    sender.sendMessage(ChatColor.YELLOW + "Enregistrement du Mot de Passe...");
                                    AbsolHuman.getInstance().getConfig().set("player." + sender.getName() + ".password", Security.getSHA512SecurePassword(wantedpass));
                                    AbsolHuman.getInstance().saveConfig();
                                    sender.sendMessage(ChatColor.GREEN + "Enregistrement réussi ! Votre Mot de Passe a été défini !");
                                    AbsolPlayer.getAPlayer((Player)sender).setLogged(true);
                                }else {
                                    sender.sendMessage(ChatColor.DARK_RED + "!! Mot de passe non autorisé !!");
                                }
                            } else {
                                sender.sendMessage(ChatColor.DARK_RED + "!!?? Votre Pseudo !!?? Mot de passe non autorisé !!");
                            }
                        }else {
                            sender.sendMessage(ChatColor.DARK_RED + "!! Mot de passe trop court !!");
                        }
                    } else {
                        sender.sendMessage(ChatColor.DARK_RED + "!! Mot de passe non autorisé !!");
                    }

                } else {
                    sender.sendMessage(ChatColor.RED + "Merci de vous enregistrer avec /register UnMotDePasse");
                }
            }else{
                sender.sendMessage(ChatColor.RED + "Impossible ! " + ChatColor.AQUA + "Vous avez déjà un mot de passe !");
                sender.sendMessage(ChatColor.YELLOW + "Pour vous connecter faites " + ChatColor.AQUA + "/login VotreMotDePasse");
                sender.sendMessage(ChatColor.YELLOW + "Ou pour changer de mot de passe faites " + ChatColor.AQUA + "/changepass VotreMotDePasseActuel VotreNouveauMotDePasse");
            }
        }else{
            sender.sendMessage("t'es rigolo hein");
        }

        return false;
    }
}
