package fr.energycube.absolhuman.events;

import fr.energycube.absolhuman.AbsolPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Random;

public class RewardXP implements Listener {

    @EventHandler
    public void onKill(EntityDeathEvent e) {
        Random random = new Random();
        if (e.getEntity().getKiller() instanceof Player) {
            Player p = (Player) e.getEntity().getKiller();
            AbsolPlayer ap = AbsolPlayer.getAPlayer(p);
            switch (e.getEntity().getType()) {

                //random.nextInt(max + 1 - min) + min;

                case CREEPER:
                    ap.addXP(random(20, 10));
                    break;
                case ENDERMAN:
                    ap.addXP(random(50, 20));
                    break;
                case BLAZE:
                    ap.addXP(random(40, 20));
                    break;
                case GHAST:
                    ap.addXP(random(250, 100));
                    break;
                case ZOMBIE:
                    ap.addXP(random(5, 0));
                    break;
                case SKELETON:
                    ap.addXP(random(10, 0));
                    break;
                case SPIDER:
                    ap.addXP(random(8, 0));
                    break;
                case GUARDIAN:
                    ap.addXP(random(500, 200));
                    break;
                case WITHER_SKELETON:
                    ap.addXP(random(300, 150));
                    break;

                case WITHER:
                    ap.addXP(random(6000, 4000));
                    break;
                case ENDER_DRAGON:
                    ap.addXP(random(8000, 6000));
                    break;
            }
        }
    }

    public static int random(int max, int min) {
        Random random = new Random();
        return random.nextInt(max + 1 - min) + min;
    }

}
