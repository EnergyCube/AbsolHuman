package fr.energycube.absolhuman.commands;

import fr.energycube.absolhuman.AbsolHuman;
import fr.energycube.absolhuman.AbsolPlayer;
import fr.energycube.absolhuman.utils.Grade;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GradeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(sender instanceof Player){
            AbsolPlayer ap = AbsolPlayer.getAPlayer((Player)sender);
            if(ap.getGrade().isMod()){
                if(args.length == 2){
                    String name = args[0];
                    String grade_name = args[1];

                    if(AbsolHuman.getInstance().getServer().getPlayer(name) != null){
                        AbsolPlayer wanted_ah = AbsolPlayer.getAPlayer(AbsolHuman.getInstance().getServer().getPlayer(name));

                        Grade wanted_grade;
                        try {
                            wanted_grade = Grade.valueOf(grade_name);
                            if(wanted_grade != Grade.Admin) {
                                wanted_ah.setGrade(wanted_grade);
                                sender.sendMessage(ChatColor.GREEN + "Le joueur " + AbsolHuman.getInstance().getServer().getPlayer(name).getName() + " est désormais " + wanted_grade.getChatColor() + wanted_grade.getChatName() + ChatColor.GREEN + " !");
                                return false;
                            }else{
                                sender.sendMessage(ChatColor.DARK_RED + "nop");
                                return false;
                            }
                        }catch (Exception ex){
                            sender.sendMessage(ChatColor.RED + "Impossible de trouver le grade !");
                            return false;
                        }

                    }else{
                        sender.sendMessage(ChatColor.RED + "Impossible de trouver le joueur !");
                    }

                } if(args.length == 1){
                    if(args[0].equals("list")) {
                        for (Grade grade : Grade.values()) {
                            sender.sendMessage(grade.name());
                        }
                    }else{
                        sender.sendMessage(ChatColor.RED + "Merci de faire /grade NomJoueur Grade");
                        sender.sendMessage(ChatColor.RED + "Pour avoir la liste des grades : /grade list");
                    }
                }else{
                    sender.sendMessage(ChatColor.RED + "Merci de faire /grade NomJoueur Grade");
                    sender.sendMessage(ChatColor.RED + "Pour avoir la liste des grades : /grade list");
                }
            }
        }else{
            if(args.length == 2){
                String name = args[0];
                String grade_name = args[1];

                if(AbsolHuman.getInstance().getServer().getPlayer(name) != null){
                    AbsolPlayer wanted_ah = AbsolPlayer.getAPlayer(AbsolHuman.getInstance().getServer().getPlayer(name));

                    Grade wanted_grade;
                    try {
                        wanted_grade = Grade.valueOf(grade_name);
                        wanted_ah.setGrade(wanted_grade);
                        sender.sendMessage(ChatColor.GREEN + "Le joueur " + sender.getName() + " est désormais " + wanted_grade.getChatColor() + wanted_grade.getChatName() + ChatColor.GREEN + " !");
                        return false;
                    }catch (Exception ex){
                        sender.sendMessage(ChatColor.RED + "Impossible de trouver le grade !");
                        return false;
                    }

                }else{
                    sender.sendMessage(ChatColor.RED + "Impossible de trouver le joueur !");
                }

            } if(args.length == 1){
                if(args[0].equals("list")) {
                    for (Grade grade : Grade.values()) {
                        sender.sendMessage(grade.name());
                    }
                }else{
                    sender.sendMessage(ChatColor.RED + "Merci de faire /grade NomJoueur Grade");
                    sender.sendMessage(ChatColor.RED + "Pour avoir la liste des grades : /grade list");
                }
            }else{
                sender.sendMessage(ChatColor.RED + "Merci de faire /grade NomJoueur Grade");
                sender.sendMessage(ChatColor.RED + "Pour avoir la liste des grades : /grade list");
            }
        }


        return false;
    }
}
