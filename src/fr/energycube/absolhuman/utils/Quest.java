package fr.energycube.absolhuman.utils;

import fr.energycube.absolhuman.AbsolHuman;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Deprecated
public class Quest {

    private String title;
    private String description;
    private String uuid;
    private Material icon;
    private boolean complete;

    private HashMap<String, Quest> questCache = new HashMap<>();

    private static FileConfiguration config = AbsolHuman.getInstance().getConfig();

    public Quest(String title, String description, String uuid, String icon_material_name){
        this.title = title;
        this.description = description;
        this.uuid = uuid;
        this.icon = Material.valueOf(icon_material_name);
        if(!questCache.containsKey(uuid)){

        }
    }

    public static List<Quest> getDefaultQuests(){
        List<Quest> questList = new ArrayList<>();
        questList.add(new Quest("Bloquez 100 dégats","Votre armure ne vous veut aucun mal !","830b645b-5ce4-4788-a13f-5a83e49b8ef7", Material.IRON_CHESTPLATE.name()));
        questList.add(new Quest("Faites 300 dégats","Votre arme est votre meilleure ami !","4f0300aa-196e-4015-a178-cf534bd27d5f", Material.IRON_SWORD.name()));
        return questList;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUUID() {
        return uuid;
    }

    public Material getIcon() {
        return icon;
    }

    public boolean isComplete(){
        return false;
    }

    public static Quest getQuestFromUUID(String uuid){
        config.get("quests." + uuid);
        return new Quest(config.get("quests." + uuid + ".title").toString(), config.get("quests." + uuid + ".description").toString(), uuid , config.get("quests." + uuid + ".icon").toString());
    }

    public void checkConf(){
        if(config.getList("quests." + uuid) == null){
            config.set("quests." + uuid + "title", title);
            config.set("quests." + uuid + "description", description);
            config.set("quests." + uuid + "icon", icon.name());
        }
    }

}
