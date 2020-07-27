package fr.energycube.absolhuman;

import fr.energycube.absolhuman.commands.*;
import fr.energycube.absolhuman.events.*;
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
        //getServer().getPluginManager().registerEvents(new RewardXP(), this);


        getServer().getPluginManager().registerEvents(new ClaimInteract(), this);
        getServer().getPluginManager().registerEvents(new ClaimInv(), this);

        getServer().getPluginManager().registerEvents(new Move(), this);

        getServer().getPluginManager().registerEvents(new WaitLogin(), this);

        getCommand("money").setExecutor(new MoneyCommand());
        getCommand("grade").setExecutor(new GradeCommand());
        getCommand("lvl").setExecutor(new LvlCommand());

        getCommand("chunk").setExecutor(new ChunkCommand());

        /* AUTH */
        getCommand("login").setExecutor(new LoginCommand());
        getCommand("register").setExecutor(new RegisterCommand());
        getCommand("changepass").setExecutor(new ChangePassCommand());

        new MessagesRunnable().runTaskTimerAsynchronously(this, 24000, 24000); // S * 20 (20 = 1s)

        System.out.println("[AbsolHuman] " + getConfig().getStringList("claimindex").size() + " claim detected !");
        AbsolChunk.loadAll();
        System.out.println("[AbsolHuman] " + AbsolChunk.getAbsolChunks().size() + " claim loaded !");

        initSpawn();
    }

    @Override
    public void onDisable() {

        // saveConfig(); Détruit les modifications manuels
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
            saveConfig();
        }
    }

    public static AbsolHuman getInstance(){
        return instance;
    }
}
