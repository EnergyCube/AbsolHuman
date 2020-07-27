package fr.energycube.absolhuman.events;

import fr.energycube.absolhuman.AbsolChunk;
import fr.energycube.absolhuman.AbsolHuman;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ClaimInv implements Listener {

    @EventHandler
    public void onClaimInv(InventoryClickEvent e){

        if(e.getCurrentItem() == null || e.getAction() == null ||
                e.getClickedInventory() == null || e.getInventory() == null || e.getClick() == null){
            return;
        }

        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.BLUE + "Gestionnaire de Chunk")){
            ItemStack current = e.getCurrentItem();
            Player p = (Player) e.getWhoClicked();

            e.setCancelled(true);

            if(current.getType() == Material.GRASS_BLOCK){
                p.performCommand("chunk claim");
                p.closeInventory();
            }

            if(current.getType() == Material.CHEST){
                if(AbsolChunk.contains(p.getLocation().getChunk())) {
                    AbsolChunk ab = AbsolChunk.getAbsolChunk(p.getLocation().getChunk());
                    if(ab.getOwner().equalsIgnoreCase(p.getName())) {
                        p.closeInventory();
                        Inventory this_chunkinv = AbsolHuman.getInstance().getServer().createInventory(null, 45, ChatColor.BLUE + "Gestionnaire de Chunk");

                        ItemStack background = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                        ItemMeta backgroundM = background.getItemMeta();
                        backgroundM.setDisplayName(" ");
                        background.setItemMeta(backgroundM);

                        for (int i = 0; i < 45; i++) {
                            this_chunkinv.setItem(i, background);
                        }

                        ItemStack addp = new ItemStack(Material.GREEN_STAINED_GLASS);
                        ItemMeta addpM = addp.getItemMeta();
                        List<String> addlore = new ArrayList<>();
                        addlore.add(ChatColor.WHITE + "Faites /chunk allow NomDuJoueur");
                        addpM.setLore(addlore);
                        addpM.setDisplayName(ChatColor.GREEN + "Autoriser un Joueur");
                        addp.setItemMeta(addpM);

                        ItemStack addlist = new ItemStack(Material.BOOKSHELF);
                        ItemMeta addlistM = addlist.getItemMeta();
                        addlistM.setLore(ab.getAllowed());
                        if(addlistM.getLore().isEmpty())
                            addlistM.setLore(new ArrayList<String>(){{add(ChatColor.GRAY + "Aucun");}});
                        addlistM.setDisplayName(ChatColor.GOLD + "Liste des Joueurs Autorisée");
                        addlist.setItemMeta(addlistM);

                        ItemStack unclaim = new ItemStack(Material.BARRIER);
                        ItemMeta unclaimM = unclaim.getItemMeta();
                        unclaimM.setDisplayName(ChatColor.RED + "Supprimer ce Chunk");
                        List<String> unclaimlore = new ArrayList<>();
                        unclaimlore.add(ChatColor.GRAY + "Cliquez pour supprimer");
                        addpM.setLore(unclaimlore);
                        unclaim.setItemMeta(unclaimM);

                        ItemStack explosion = new ItemStack(Material.CREEPER_HEAD);
                        ItemMeta explosionM = unclaim.getItemMeta();
                        explosionM.setDisplayName(ChatColor.AQUA + "Explosion");
                        List<String> explosionlore = new ArrayList<>();
                        if(ab.allowExplosion())
                            explosionlore.add(ChatColor.GOLD + "Activé");
                        else
                            explosionlore.add(ChatColor.GRAY + "Désactivé");
                        explosionM.setLore(explosionlore);
                        explosion.setItemMeta(explosionM);

                        ItemStack interact = new ItemStack(Material.LEVER);
                        ItemMeta interactM = unclaim.getItemMeta();
                        interactM.setDisplayName(ChatColor.AQUA + "Interaction");
                        List<String> interactlore = new ArrayList<>();
                        interactlore.add(ChatColor.WHITE + "Permets aux Joueurs invités d'interagir");
                        if(ab.allowInteract())
                            interactlore.add(ChatColor.GOLD + "Activé");
                        else
                            interactlore.add(ChatColor.GRAY + "Désactivé");
                        interactM.setLore(interactlore);
                        interact.setItemMeta(interactM);

                        ItemStack removep = new ItemStack(Material.RED_STAINED_GLASS);
                        ItemMeta removepM = removep.getItemMeta();
                        List<String> removelore = new ArrayList<>();
                        removelore.add(ChatColor.WHITE + "Faites /chunk disallow NomDuJoueur");
                        removepM.setLore(removelore);
                        removepM.setDisplayName(ChatColor.RED + "Ne plus autoriser un Joueur");
                        removep.setItemMeta(removepM);

                        this_chunkinv.setItem(10, addlist);
                        this_chunkinv.setItem(19, addp);
                        this_chunkinv.setItem(28, removep);
                        this_chunkinv.setItem(40, unclaim);

                        this_chunkinv.setItem(12, explosion);
                        this_chunkinv.setItem(14, interact);


                        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2, 1);
                        p.openInventory(this_chunkinv);
                    }
                }
            }

            if(current.getType() == Material.BARRIER){
                if(AbsolChunk.contains(p.getLocation().getChunk())){
                    if(AbsolChunk.getAbsolChunk(p.getLocation().getChunk()).getOwner().equalsIgnoreCase(p.getName())){
                        p.performCommand("chunk unclaim");
                    }
                }
                p.closeInventory();
            }

            if(current.getType() == Material.CREEPER_HEAD){
                if(AbsolChunk.contains(p.getLocation().getChunk())){
                    if(AbsolChunk.getAbsolChunk(p.getLocation().getChunk()).getOwner().equalsIgnoreCase(p.getName())){
                        p.performCommand("chunk explosion");
                    }
                }
                p.closeInventory();
            }

            if(current.getType() == Material.LEVER){
                if(AbsolChunk.contains(p.getLocation().getChunk())){
                    if(AbsolChunk.getAbsolChunk(p.getLocation().getChunk()).getOwner().equalsIgnoreCase(p.getName())){
                        p.performCommand("chunk interact");
                    }
                }
                p.closeInventory();
            }

        }

    }
}
