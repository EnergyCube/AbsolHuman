package fr.energycube.absolhuman.commands;

import fr.energycube.absolhuman.AbsolHuman;
import fr.energycube.absolhuman.AbsolPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MoneyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(sender instanceof Player) {
            Player p = (Player)sender;
            AbsolPlayer ap = AbsolPlayer.getAPlayer(p);
            switch (args.length){
                case 0:
                    p.sendMessage(ChatColor.GREEN + "Vous avez " + ap.getMoney() + "€" + " !");
                    break;

                case 1:

                    if(args[0].equals("send")){
                        p.sendMessage(ChatColor.RED + "Commande invalide ! Merci de faire /money send " + ChatColor.UNDERLINE + "<Joueur> <Argent>");
                    }else if(args[0].equals("set")){
                        p.sendMessage(ChatColor.RED + "Commande invalide ! Merci de faire /money set " + ChatColor.UNDERLINE + "<Joueur> <Argent>");
                    }else {

                        p.sendMessage(ChatColor.AQUA + "============= " + ChatColor.YELLOW + "Commande /money" + ChatColor.AQUA + " =============");
                        if(!ap.getGrade().isMod()){
                            p.sendMessage(ChatColor.YELLOW + "/money send <Joueur> <Argent> :  Envoyer de l'argent à un joueur !");
                        }else {
                            p.sendMessage(ChatColor.YELLOW + "/money send/set <Joueur> <Argent> :  Définir l'argent d'un joueur !");
                        }
                    }
                    break;

                case 3:
                    if(args[0].equals("send")){
                        if(AbsolHuman.getInstance().getServer().getPlayer(args[1]) != null){
                            int money_send_read;
                            try {
                                money_send_read = Integer.parseInt(args[2]);
                            }catch (Exception ex){
                                p.sendMessage(ChatColor.RED + "Le montant n'est pas valide !");
                                break;
                            }
                            if(ap.getMoney() >= money_send_read){
                                ap.remMoney(money_send_read);

                                Player rec_p = AbsolHuman.getInstance().getServer().getPlayer(args[1]);
                                AbsolPlayer rec = AbsolPlayer.getAPlayer(rec_p);
                                rec.addMoney(money_send_read);
                                rec_p.sendMessage(ChatColor.GREEN + "Vous avez reçu " + money_send_read + "€ de " + p.getName());

                                p.sendMessage(ChatColor.DARK_GREEN + "Vous avez donnez " + money_send_read + "€ à " + args[1] + " !");
                            }else{
                                p.sendMessage("Vous n'avez pas une telle somme !");
                            }

                        }else {
                            p.sendMessage(ChatColor.RED + "Le Joueur " + args[1] + " n'est pas en ligne !");
                        }
                    }else if(args[0].equals("set")){
                        if(ap.getGrade().isMod()){
                            if(AbsolHuman.getInstance().getServer().getPlayer(args[1]) != null){
                                int money_send_read;
                                try {
                                    money_send_read = Integer.parseInt(args[2]);
                                }catch (Exception ex){
                                    p.sendMessage(ChatColor.RED + "Le montant n'est pas valide !");
                                    break;
                                }

                                Player rec_p = AbsolHuman.getInstance().getServer().getPlayer(args[1]);
                                AbsolPlayer rec = AbsolPlayer.getAPlayer(rec_p);
                                rec.setMoney(money_send_read);
                                p.sendMessage(ChatColor.DARK_GREEN + "Vous avez définit à " + money_send_read + "€ l'argent de " + args[1] + " !");

                            }else {
                                p.sendMessage(ChatColor.RED + "Le Joueur " + args[1] + " n'est pas en ligne !");
                            }
                        } else {
                          p.sendMessage(ChatColor.RED + "Ben non...");
                        }
                    }
                    break;

                default:
                    p.sendMessage(ChatColor.RED + "Commande invalide ! Merci de faire /money send <Joueur> <Argent>");
                    break;
            }
        }

        return false;
    }
}
