package fr.energycube.absolhuman;

import fr.energycube.absolhuman.commands.Money;
import fr.energycube.absolhuman.events.Chat;
import fr.energycube.absolhuman.events.Join;
import fr.energycube.absolhuman.events.Quit;
import fr.energycube.absolhuman.events.Weather;
import fr.energycube.absolhuman.runnables.MessagesRunnable;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class AbsolHuman extends JavaPlugin {

    private static AbsolHuman instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        saveConfig();


        getServer().getPluginManager().registerEvents(new Join(), this);
        getServer().getPluginManager().registerEvents(new Chat(), this);
        getServer().getPluginManager().registerEvents(new Quit(), this);
        getServer().getPluginManager().registerEvents(new Weather(), this);

        getCommand("money").setExecutor(new Money());

        new MessagesRunnable().runTaskTimerAsynchronously(this, 6000, 6000); // S * 20 (20 = 1s)

        initSpawn();

    }

    @Override
    public void onDisable() {
        reloadConfig();
        saveConfig();
    }

    private void initSpawn(){
        if(getConfig().getLocation("spawn") != null) {
            Location location = getConfig().getLocation("spawn");
            if(location.getY() != 0) {
                getServer().getWorld("world").setSpawnLocation(location);
                getServer().getConsoleSender().sendMessage("Spawn du monde principal " + location.getX() + "," + location.getY() + "," + location.getZ() + " (config)");
            }else{
                getServer().getConsoleSender().sendMessage(ChatColor.RED + "!! Merci de configurer le spawn dans le fichier de config !!");
            }
        }else{
            getConfig().set("spawn", new Location(getServer().getWorld("world"), 0, 0, 0, 0, 0));
            getServer().getConsoleSender().sendMessage(ChatColor.RED + "!! Merci de configurer le spawn dans le fichier de config !!");
        }
    }

    public static AbsolHuman getInstance(){
        return instance;
    }
}
