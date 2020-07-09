package fr.energycube.absolhuman.commands;

import fr.energycube.absolhuman.AbsolHuman;
import fr.energycube.absolhuman.AbsolPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Money implements CommandExecutor {
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

                    if(args[0].equals("help") || args[0].equals("?") || args[0].equals("aide")){
                        p.sendMessage(ChatColor.AQUA + "============= " + ChatColor.YELLOW + "Commande /money" + ChatColor.AQUA + " =============");
                        p.sendMessage(ChatColor.YELLOW + "/money send <Joueur> <Argent> :  Envoyer de l'argent à un joueur !");
                    }else if(args[0].equals("send")){
                        p.sendMessage(ChatColor.RED + "Commande invalide ! Merci de faire /money send " + ChatColor.UNDERLINE + "<Joueur> <Argent>");
                    }
                    break;

                case 2:
                    if(args[0].equals("send")){
                        p.sendMessage(ChatColor.RED + "Commande invalide ! Merci de faire /money send <Joueur> <Argent>");
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

                                p.sendMessage("Vous avez donnez " + money_send_read + "€ à " + args[1] + " !");
                            }else{
                                p.sendMessage("Vous n'avez pas une tel somme !");
                            }

                        }else {
                            p.sendMessage(ChatColor.RED + "Le Joueur " + args[1] + " n'est pas en ligne !");
                        }
                    }
                    break;
            }
        }

        return false;
    }
}
