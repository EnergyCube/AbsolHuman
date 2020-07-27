package fr.energycube.absolhuman.commands;

import fr.energycube.absolhuman.AbsolChunk;
import fr.energycube.absolhuman.AbsolHuman;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ChunkCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(sender instanceof Player) {
            Player p = (Player) sender;

            switch (args.length) {
                case 0:

                    if(AbsolChunk.contains(p.getLocation().getChunk())) {
                        if(AbsolChunk.getAbsolChunk(p.getLocation().getChunk()).getOwner().equalsIgnoreCase(p.getName())) {
                            Inventory pre_chunkinv = AbsolHuman.getInstance().getServer().createInventory(null, 27, ChatColor.BLUE + "Gestionnaire de Chunk");

                            ItemStack background = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                            ItemMeta backgroundM = background.getItemMeta();
                            backgroundM.setDisplayName(" ");
                            background.setItemMeta(backgroundM);

                            for (int i = 0; i < 27; i++) {
                                pre_chunkinv.setItem(i, background);
                            }

                            ItemStack currentclaim = new ItemStack(Material.CHEST);
                            ItemMeta currentclaimM = currentclaim.getItemMeta();
                            currentclaimM.setDisplayName(ChatColor.GREEN + "Modifier ce Chunk");
                            currentclaim.setItemMeta(currentclaimM);

                            ItemStack allclaim = new ItemStack(Material.ENDER_CHEST);
                            ItemMeta allclaimM = allclaim.getItemMeta();
                            allclaimM.setDisplayName(ChatColor.GREEN + "Modifier tous vos Chunks (En Dev...)");
                            allclaim.setItemMeta(allclaimM);

                            pre_chunkinv.setItem(11, currentclaim);
                            pre_chunkinv.setItem(15, allclaim);


                            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2, 1);
                            p.openInventory(pre_chunkinv);
                        }else{
                            Inventory pre_chunkinv = AbsolHuman.getInstance().getServer().createInventory(null, 27, ChatColor.BLUE + "Gestionnaire de Chunk");

                            ItemStack background = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                            ItemMeta backgroundM = background.getItemMeta();
                            backgroundM.setDisplayName(" ");
                            background.setItemMeta(backgroundM);

                            for (int i = 0; i < 27; i++) {
                                pre_chunkinv.setItem(i, background);
                            }

                            ItemStack currentclaim = new ItemStack(Material.IRON_BARS);
                            ItemMeta currentclaimM = currentclaim.getItemMeta();
                            currentclaimM.setDisplayName(ChatColor.GOLD + "Propriétaire : " + AbsolChunk.getAbsolChunk(p.getLocation().getChunk()).getOwner());
                            currentclaim.setItemMeta(currentclaimM);

                            ItemStack allclaim = new ItemStack(Material.ENDER_CHEST);
                            ItemMeta allclaimM = allclaim.getItemMeta();
                            allclaimM.setDisplayName(ChatColor.GREEN + "Modifier tous vos Chunks (En Dev...)");
                            allclaim.setItemMeta(allclaimM);

                            pre_chunkinv.setItem(11, currentclaim);
                            pre_chunkinv.setItem(15, allclaim);


                            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2, 1);
                            p.openInventory(pre_chunkinv);
                        }
                    }else{
                        Inventory buy_chunkinv = AbsolHuman.getInstance().getServer().createInventory(null, 27, ChatColor.BLUE + "Gestionnaire de Chunk");

                        ItemStack background = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                        ItemMeta backgroundM = background.getItemMeta();
                        backgroundM.setDisplayName(" ");
                        background.setItemMeta(backgroundM);

                        ItemStack allclaim = new ItemStack(Material.ENDER_CHEST);
                        ItemMeta allclaimM = allclaim.getItemMeta();
                        allclaimM.setDisplayName(ChatColor.GREEN + "Modifier tous vos Chunks (En Dev...)");
                        allclaim.setItemMeta(allclaimM);

                        for (int i = 0; i < 27; i++) {
                            buy_chunkinv.setItem(i, background);
                        }

                        ItemStack currentclaim = new ItemStack(Material.GRASS_BLOCK);
                        ItemMeta currentclaimM = currentclaim.getItemMeta();
                        currentclaimM.setDisplayName(ChatColor.GREEN + "Revendiquer ce chunk");
                        currentclaim.setItemMeta(currentclaimM);

                        buy_chunkinv.setItem(11, currentclaim);
                        buy_chunkinv.setItem(15, allclaim);


                        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2, 1);
                        p.openInventory(buy_chunkinv);
                    }

                    break;
                case 1:

                    if (args[0].equalsIgnoreCase("claim")) {
                        if(!AbsolChunk.contains(p.getLocation().getChunk())){
                            AbsolChunk.claim(p.getLocation().getChunk(), p.getName());
                            p.sendMessage(ChatColor.GREEN + "Vous avez réussi à récupérer ce chunk !");
                            p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES, 2, 1);
                        } else {
                            p.sendMessage(ChatColor.RED + "Ce chunk est déjà revendiqué !");
                        }
                    }else if (args[0].equalsIgnoreCase("unclaim")) {
                        if(AbsolChunk.contains(p.getLocation().getChunk())) {
                            AbsolChunk absolChunk = AbsolChunk.getAbsolChunk(p.getLocation().getChunk());
                            if(absolChunk.getOwner().equalsIgnoreCase(p.getName())){
                                absolChunk.unclaim();
                                p.sendMessage(ChatColor.DARK_GREEN + "Ce terrain n'est désormais plus le vôtre !");
                                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 2, 1);
                            }else {
                                p.sendMessage(ChatColor.RED + "Vous n'êtes pas sur un de vos terrain !");
                            }
                        }else {
                            p.sendMessage(ChatColor.RED + "Vous n'êtes pas sur un de vos terrain!");
                        }
                    }else if(args[0].equalsIgnoreCase("allow")){
                        p.sendMessage(ChatColor.RED + "Merci de faire /chunk allow NomDuJoueur");
                    }else if(args[0].equalsIgnoreCase("info")){
                        if(AbsolChunk.contains(p.getLocation().getChunk())) {
                            AbsolChunk absolChunk = AbsolChunk.getAbsolChunk(p.getLocation().getChunk());
                            p.sendMessage(ChatColor.BLUE + "Propriétaire : " + absolChunk.getOwner());
                            p.sendMessage(ChatColor.BLUE + "Autorisé : " + absolChunk.getAllowed());
                            p.sendMessage(ChatColor.BLUE + "Exclu : " + absolChunk.getBanned());
                        }else {
                            p.sendMessage(ChatColor.RED + "Aucune information disponible pour ce terrain !");
                        }
                    }else if(args[0].equalsIgnoreCase("list")){
                        p.sendMessage(ChatColor.GREEN + "======== " + ChatColor.BLUE + "Chunks Possédés" + ChatColor.GREEN + " ========");
                        for(AbsolChunk ac : AbsolChunk.getAbsolChunks()){
                            if(ac.getOwner().equalsIgnoreCase(p.getName())){
                                p.sendMessage(ChatColor.DARK_GREEN + "" + ac.getChunk().getX() + " | " + ac.getChunk().getZ()
                                        + "    " + ChatColor.GREEN + "" + (ac.getChunk().getX() << 4) + " | " + (ac.getChunk().getZ() << 4));
                            }
                        }
                        p.sendMessage(ChatColor.GREEN + "================================");
                    }else if(args[0].equalsIgnoreCase("explosion")){
                        if(AbsolChunk.contains(p.getLocation().getChunk())) {
                            AbsolChunk absolChunk = AbsolChunk.getAbsolChunk(p.getLocation().getChunk());
                            if(absolChunk.getOwner().equalsIgnoreCase(p.getName())){
                                if(absolChunk.getExplosion()){
                                    p.sendMessage(ChatColor.DARK_GREEN + "Les explosions sont désormais interdites !");
                                    absolChunk.setExplosion(false);
                                }else{
                                    p.sendMessage(ChatColor.DARK_GREEN + "Les explosions sont désormais autorisées !");
                                    absolChunk.setExplosion(true);
                                }
                                p.playSound(p.getLocation(), Sound.BLOCK_HONEY_BLOCK_HIT, 2, 1);
                            }else {
                                p.sendMessage(ChatColor.RED + "Vous n'êtes pas sur un de vos terrain !");
                            }
                        }else {
                            p.sendMessage(ChatColor.RED + "Vous n'êtes pas sur un de vos terrain!");
                        }
                    }else if(args[0].equalsIgnoreCase("interact")){
                        if(AbsolChunk.contains(p.getLocation().getChunk())) {
                            AbsolChunk absolChunk = AbsolChunk.getAbsolChunk(p.getLocation().getChunk());
                            if(absolChunk.getOwner().equalsIgnoreCase(p.getName())){
                                if(absolChunk.getInteract()){
                                    p.sendMessage(ChatColor.DARK_GREEN + "Les interactions sont désormais interdites !");
                                    absolChunk.setInteract(false);
                                }else{
                                    p.sendMessage(ChatColor.DARK_GREEN + "Les interactions sont désormais autorisées !");
                                    absolChunk.setInteract(true);
                                }
                                p.playSound(p.getLocation(), Sound.BLOCK_HONEY_BLOCK_HIT, 2, 1);
                            }else {
                                p.sendMessage(ChatColor.RED + "Vous n'êtes pas sur un de vos terrain !");
                            }
                        }else {
                            p.sendMessage(ChatColor.RED + "Vous n'êtes pas sur un de vos terrain!");
                        }
                    }else{
                        sendHelp(sender);
                    }

                    break;

                case 2:
                    if(args[0].equals("allow")){
                        Player newAllowedPlayer = AbsolHuman.getInstance().getServer().getPlayer(args[1]);
                        if(newAllowedPlayer != null) {
                            if(AbsolChunk.contains(p.getLocation().getChunk())){
                                AbsolChunk absolChunk = AbsolChunk.getAbsolChunk(p.getLocation().getChunk());
                                if(absolChunk.getOwner().equalsIgnoreCase(p.getName())){
                                    if(newAllowedPlayer != p) {
                                        absolChunk.addAllowed(newAllowedPlayer.getName());
                                        p.sendMessage(ChatColor.GREEN + "Le Joueur " + newAllowedPlayer.getName() + " est désormais autorisé sur ce terrain !");
                                    }else {
                                        p.sendMessage(ChatColor.RED + "Impossible de vous autoriser vous-même !");
                                    }
                                }else{
                                    p.sendMessage(ChatColor.RED + "Vous n'êtes pas sur un de vos terrain !");
                                }
                            }else{
                                p.sendMessage(ChatColor.RED + "Vous n'êtes pas sur un terrain valide !");
                            }
                        }else {
                            p.sendMessage(ChatColor.RED + "Le Joueur " + args[1] + " n'est pas en ligne !");
                        }
                    }else if(args[0].equals("disallow")){
                        Player removePlayer = AbsolHuman.getInstance().getServer().getPlayer(args[1]);
                        if(removePlayer != null) {
                            if(AbsolChunk.contains(p.getLocation().getChunk())){
                                AbsolChunk absolChunk = AbsolChunk.getAbsolChunk(p.getLocation().getChunk());
                                if(absolChunk.getOwner().equalsIgnoreCase(p.getName())){
                                    if(absolChunk.getAllowed().contains(removePlayer.getName())) {
                                        absolChunk.removeAllowed(removePlayer.getName());
                                        p.sendMessage(ChatColor.DARK_GREEN + "Le Joueur " + removePlayer.getName() + " n'est plus autorisé sur ce terrain !");
                                    }else{
                                        p.sendMessage(ChatColor.RED + "Le Joueur " + removePlayer.getName() + " n'est déjà pas autorisé !");
                                    }
                                }else{
                                    p.sendMessage(ChatColor.RED + "Vous n'êtes pas sur un de vos terrain !");
                                }
                            }else{
                                p.sendMessage(ChatColor.RED + "Vous n'êtes pas sur un terrain valide !");
                            }
                        }else {
                            p.sendMessage(ChatColor.RED + "Le Joueur " + args[1] + " n'est pas en ligne !");
                        }
                    }else{
                        sendHelp(sender);
                    }
                    break;

                default:
                    sendHelp(sender);

            }
        }



        return false;
    }

    private void sendHelp(CommandSender sender){
        sender.sendMessage(ChatColor.GREEN + "======== " + ChatColor.BLUE + "Aide Chunks" + ChatColor.GREEN + " ========");
        sender.sendMessage(ChatColor.AQUA + "/chunk : Affiche l'interface simplifiée du gestionnaire de chunk");
        sender.sendMessage(ChatColor.AQUA + "/chunk claim : Permets d'obtenir l'exclusivité du terrain");
        sender.sendMessage(ChatColor.AQUA + "/chunk allow NomDuJoueur : Permets d'autoriser un joueur sur votre terrain");
        sender.sendMessage(ChatColor.AQUA + "/chunk disallow : Permets de ne plus autoriser un joueur sur votre terrain");
        sender.sendMessage(ChatColor.AQUA + "/chunk explosion : Permets d'autoriser ou pas les explosions");
        sender.sendMessage(ChatColor.AQUA + "/chunk interact :  Permets d'autoriser ou pas les interactions des joueurs inconnus");
        sender.sendMessage(ChatColor.AQUA + "/chunk unclaim : Permets de rendre son chunk à la nature");
        sender.sendMessage(ChatColor.GREEN + "==============================");
    }
}
