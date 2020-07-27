package fr.energycube.absolhuman.events;

import fr.energycube.absolhuman.AbsolChunk;
import fr.energycube.absolhuman.AbsolHuman;
import fr.energycube.absolhuman.AbsolPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityBreakDoorEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;

public class ClaimInteract implements Listener {

    private String notallowed = ChatColor.RED + "Vous n'avez pas le droit d'effectuer cette action sur le terrain de %owner% !";

    @EventHandler(priority = EventPriority.HIGH)
    private void onBlockPlace(BlockPlaceEvent e) {
        AbsolPlayer ap = AbsolPlayer.getAPlayer(e.getPlayer());

        Chunk chunk = e.getBlock().getChunk();
        if(AbsolChunk.contains(chunk)){
            if(!AbsolChunk.getAbsolChunk(chunk).getOwner().equalsIgnoreCase(e.getPlayer().getName()) && !AbsolChunk.getAbsolChunk(chunk).getAllowed().contains(e.getPlayer().getName())) {
                if(!e.getPlayer().isOp()) {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(notallowed.replace("%owner%", AbsolChunk.getAbsolChunk(chunk).getOwner()));
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onBlockPlace(BlockBreakEvent e) {
        AbsolPlayer ap = AbsolPlayer.getAPlayer(e.getPlayer());

        Chunk chunk = e.getBlock().getChunk();
        if(AbsolChunk.contains(chunk)){
            if(!AbsolChunk.getAbsolChunk(chunk).getOwner().equalsIgnoreCase(e.getPlayer().getName()) && !AbsolChunk.getAbsolChunk(chunk).getAllowed().contains(e.getPlayer().getName())) {
                if(!e.getPlayer().isOp()) {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(notallowed.replace("%owner%", AbsolChunk.getAbsolChunk(chunk).getOwner()));
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onBlockPistonExtend(BlockPistonExtendEvent e) {
        if(AbsolChunk.contains(e.getBlock().getRelative(e.getDirection()).getLocation().getChunk())) {
            for (Block block : e.getBlocks()) {
                Block targetBlock = block.getRelative(e.getDirection());
                if (AbsolChunk.contains((targetBlock.getChunk()))) {
                    AbsolChunk absolChunk = AbsolChunk.getAbsolChunk(targetBlock.getChunk());
                    e.setCancelled(!absolChunk.allowInteract());

                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onEntityExplode(EntityExplodeEvent event) {
        if(event.blockList() == null)
            return;

        List<Block> claimedBlocks = new ArrayList<>();
        List<String> alert = new ArrayList<>();

        for(Block block: event.blockList()) {
            if(AbsolChunk.contains(block.getChunk())) {
                if(!AbsolChunk.getAbsolChunk(block.getChunk()).allowExplosion()) {
                    claimedBlocks.add(block);
                    if(!alert.contains(AbsolChunk.getAbsolChunk(block.getChunk()).getOwner()))
                        alert.add(AbsolChunk.getAbsolChunk(block.getChunk()).getOwner());
                }
            }
        }

        if(claimedBlocks.isEmpty())
            return;

        event.blockList().removeAll(claimedBlocks);
        if(alert.size() != 0) {
            for (String name : alert) {
                Player p = AbsolHuman.getInstance().getServer().getPlayer(name);
                if (p != null)
                    AbsolHuman.getInstance().getServer().getPlayer(name).sendMessage(ChatColor.GOLD + "Une explosion a été évité sur votre territoire !");
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    private void onEntityBreakDoor(EntityBreakDoorEvent event) {
        if(event.getEntityType() == EntityType.ZOMBIE) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onInteractBlock(PlayerInteractEvent e){
        Block block = e.getClickedBlock();
        if(block != null) {
            Chunk chunk = block.getLocation().getChunk();
            if (AbsolChunk.contains(chunk)) {
                if (!AbsolChunk.getAbsolChunk(chunk).getInteract()) {
                    if (!AbsolChunk.getAbsolChunk(chunk).getOwner().equalsIgnoreCase(e.getPlayer().getName()) && !AbsolChunk.getAbsolChunk(chunk).getAllowed().contains(e.getPlayer().getName())) {
                        if (!e.getPlayer().isOp()) {
                            if (!AbsolChunk.getAbsolChunk(chunk).allowInteract()) {
                                e.setCancelled(true);
                                e.getPlayer().sendMessage(notallowed.replace("%owner%", AbsolChunk.getAbsolChunk(chunk).getOwner()));
                            }
                        }
                    }
                }
            }
        }
    }


}
