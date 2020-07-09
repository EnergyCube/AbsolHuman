package fr.energycube.absolhuman.utils;

import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Random;

@Deprecated
public class BossBarUtils {

    private JavaPlugin javaPlugin;
    private List<String> texts;

        /*  CALL
        new BossBarUtils(this, Arrays.asList(new String[]{
                "Vous jouez sur le serveur de la communauté d'Absol Human !",
                "Wow la bossbar fonctionne :D"
        })).sendBossBar();
        */

    public BossBarUtils(JavaPlugin javaPlugin, List<String> texts){
        this.javaPlugin = javaPlugin;
        this.texts = texts;
    }

    public void sendBossBar(){
        int r = new Random().nextInt(texts.size());

        BossBar bb = javaPlugin.getServer().createBossBar(texts.get(r), BarColor.PURPLE, BarStyle.SOLID, new BarFlag[0]);
        bb.setVisible(true);

        new BukkitRunnable() {

            @Override
            public void run() {
                for(Player p : javaPlugin.getServer().getOnlinePlayers()){
                    bb.addPlayer(p);
                }
            }

        }.runTaskTimerAsynchronously(javaPlugin, 100, 6000);
    }

}
