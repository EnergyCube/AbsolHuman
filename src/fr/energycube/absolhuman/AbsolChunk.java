package fr.energycube.absolhuman;

import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class AbsolChunk {

    private static HashMap<Chunk, AbsolChunk> absolChunkHashMap = new HashMap<>();
    private static FileConfiguration config = AbsolHuman.getInstance().getConfig();

    private Chunk chunk;
    private String owner = null;
    private World world = null;
    private List<String> allowed = new ArrayList<>();
    private List<String> banned = new ArrayList<>();
    private boolean interact = false;
    private boolean explosion = false;

    public AbsolChunk(Chunk chunk){
        this.chunk = chunk;
        if(!absolChunkHashMap.containsKey(chunk)){
            reload();
            absolChunkHashMap.put(chunk, this);
        }
    }
    
    public static AbsolChunk claim(Chunk chunk, String owner){
        int x = chunk.getX();
        int z = chunk.getZ();
        config.set("claim." + x + ":" + z + ".world", chunk.getWorld().getName());
        config.set("claim." + x + ":" + z + ".interact", false);
        config.set("claim." + x + ":" + z + ".explosion", false);
        config.set("claim." + x + ":" + z + ".owner", owner);
        config.set("claim." + x + ":" + z + ".allowed", new ArrayList<>());
        config.set("claim." + x + ":" + z + ".banned", new ArrayList<>());

        List<String> index = config.getStringList("claimindex");
        index.add(x + ":" + z);
        config.set("claimindex", index);

        AbsolHuman.getInstance().saveConfig();

        for(Location corner : getChunkCorners(chunk)) {
            corner.setY(corner.getY()+1);
            corner.getBlock().setType(Material.CYAN_WOOL);
            corner.setY(corner.getY()+1);
            corner.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, corner, 15);
        }

        return new AbsolChunk(chunk);
    }

    public void unclaim(){

        config.set("claim." + chunk.getX() + ":" + chunk.getZ(), null);

        List<String> index = config.getStringList("claimindex");
        index.remove(chunk.getX() + ":" + chunk.getZ());
        config.set("claimindex", index);

        AbsolHuman.getInstance().saveConfig();

        absolChunkHashMap.remove(chunk);
        for(Location corner : getChunkCorners(chunk)) {
            corner.setY(corner.getY()+1);
            corner.getBlock().setType(Material.RED_WOOL);
            corner.setY(corner.getY()+1);
            corner.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, corner, 15);
        }
    }

    public static Collection<AbsolChunk> getAbsolChunks(){
        return absolChunkHashMap.values();
    }

    private static List<Location> getChunkCorners(Chunk wanted_corners_chunk) {
        int x = wanted_corners_chunk.getX() << 4;
        int z = wanted_corners_chunk.getZ() << 4;

        Location loc1 = new Location(wanted_corners_chunk.getWorld(), x, wanted_corners_chunk.getWorld().getHighestBlockAt(x, z).getY(), z);
        Location loc2 = new Location(wanted_corners_chunk.getWorld(), x + 15, wanted_corners_chunk.getWorld().getHighestBlockAt(x + 15, z + 15).getY(), z + 15);
        Location loc3 = new Location(wanted_corners_chunk.getWorld(), x + 15, wanted_corners_chunk.getWorld().getHighestBlockAt(x + 15, z).getY(), z);
        Location loc4 = new Location(wanted_corners_chunk.getWorld(), x, wanted_corners_chunk.getWorld().getHighestBlockAt(x, z + 15).getY(), z + 15);

        List<Location> corners = new ArrayList<>();

        corners.add(loc1);
        corners.add(loc2);
        corners.add(loc3);
        corners.add(loc4);
        return corners;
    }

    public Chunk getChunk() {
        return chunk;
    }

    //getChunks().contains([...]) ne fonctionne pas !
    // Pour une raison obscure si on est pas connecté il y arrive pas
    /*
    public static Collection<Chunk> getChunks(){
        return absolChunkHashMap.keySet();
    }*/

    public static boolean contains(Chunk chunk){
        int x = chunk.getX();
        int z = chunk.getZ();
        String world = chunk.getWorld().getName();
        for(Chunk chunk_tmp : absolChunkHashMap.keySet()){
            if (chunk.getWorld().getName().equalsIgnoreCase(world) && x == chunk_tmp.getX() && z == chunk_tmp.getZ()) {
                return true;
            }
        }
        return false;
    }

    public static AbsolChunk getAbsolChunk(Chunk chunk){
        int x = chunk.getX();
        int z = chunk.getZ();
        String world = chunk.getWorld().getName();
        for(AbsolChunk achunk : absolChunkHashMap.values()){
            if (chunk.getWorld().getName().equalsIgnoreCase(world) && x == achunk.getChunk().getX() && z == achunk.getChunk().getZ()) {
                return achunk;
            }
        }
        return null;
    }

    public void setOwner(String owner) {
        this.owner = owner;
        config.set("claim." + chunk.getX() + ":" + chunk.getZ() + ".owner", owner);
        AbsolHuman.getInstance().saveConfig();
    }

    public void addAllowed(String name){
        if(!allowed.contains(name)){
            allowed.add(name);
        }
        config.set("claim." + chunk.getX() + ":" + chunk.getZ() + ".allowed", allowed);
        AbsolHuman.getInstance().saveConfig();
    }

    public void removeAllowed(String name){
        if(allowed.contains(name)){
            allowed.remove(name);
        }
        config.set("claim." + chunk.getX() + ":" + chunk.getZ() + ".allowed", allowed);
        AbsolHuman.getInstance().saveConfig();
    }

    public void setAllowed(List<String> allowed) {
        this.allowed = allowed;
        config.set("claim." + chunk.getX() + ":" + chunk.getZ() + ".allowed", allowed);
        AbsolHuman.getInstance().saveConfig();
    }

    public void setBanned(List<String> banned) {
        this.banned = banned;
        config.set("claim." + chunk.getX() + ":" + chunk.getZ() + ".banned", banned);
        AbsolHuman.getInstance().saveConfig();
    }

    public void setInteract(boolean interact) {
        this.interact = interact;
        config.set("claim." + chunk.getX() + ":" + chunk.getZ() + ".interact", interact);
        AbsolHuman.getInstance().saveConfig();
    }

    public boolean getInteract(){
        return interact;
    }

    public boolean getExplosion(){
        return explosion;
    }

    public void setExplosion(boolean explosion) {
        this.explosion = explosion;
        config.set("claim." + chunk.getX() + ":" + chunk.getZ() + ".explosion", explosion);
        AbsolHuman.getInstance().saveConfig();
    }

    public String getOwner() {
        return owner;
    }

    public List<String> getAllowed() {
        return allowed;
    }

    public List<String> getBanned() {
        return banned;
    }

    public boolean allowInteract() {
        return interact;
    }

    public boolean allowExplosion() {
        return explosion;
    }

    public static void loadAll(){
        for(String index : config.getStringList("claimindex")){
            World world_tmp = Bukkit.getWorld(config.getString("claim." + index.split(":")[0] + ":" + index.split(":")[1] + ".world"));
            new AbsolChunk(world_tmp.getChunkAt(Integer.parseInt(index.split(":")[0]), Integer.parseInt(index.split(":")[1])));
        }
    }

    private void reload(){
        this.world = AbsolHuman.getInstance().getServer().getWorld(config.getString("claim." + chunk.getX() + ":" + chunk.getZ() + ".world"));
        this.interact = config.getBoolean("claim." + chunk.getX() + ":" + chunk.getZ() + ".interact");
        this.explosion = config.getBoolean("claim." + chunk.getX() + ":" + chunk.getZ() + ".explosion");
        this.owner = config.getString("claim." + chunk.getX() + ":" + chunk.getZ() + ".owner");
        this.allowed = config.getStringList("claim." + chunk.getX() + ":" + chunk.getZ() + ".allowed");
        this.banned = config.getStringList("claim." + chunk.getX() + ":" + chunk.getZ() + ".banned");
    }




}
