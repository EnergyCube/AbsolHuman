package fr.energycube.absolhuman;

import fr.energycube.absolhuman.utils.Grade;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;

public class AbsolPlayer {

    private static HashMap<Player, AbsolPlayer> regListHM = new HashMap<>();
    FileConfiguration config = AbsolHuman.getInstance().getConfig();

    private Player p;
    private Grade grade;
    private int money;
    private int xp;
    //private List<Quest> questList;

    /* Static Access */
    public static AbsolPlayer getAPlayer(Player p){
        if(regListHM.containsKey(p)){
            return regListHM.get(p);
        }else{
            AbsolPlayer ap = new AbsolPlayer(p);
            regListHM.put(p, ap);
            return regListHM.get(p);
        }
    }

    public static Collection<AbsolPlayer> getAPlayers(){
        return regListHM.values();
    }
    /* Fin Static Access */

    public void unload(){
        regListHM.remove(p);
    }

    public AbsolPlayer(Player p){
        this.p = p;
        if(!regListHM.containsKey(p)) {
            reload();
        }
    }

    public Player getPlayer(){
        return p;
    }

    public Grade getGrade(){
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
        confGradeSave();
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
        confMoneySave();
    }

    public void addMoney(int money){
        this.money += money;
        confMoneySave();
    }

    public void remMoney(int money){
        this.money -= money;
        confMoneySave();
    }

    public int getXP() {
        return xp;
    }

    private void confMoneySave(){
        config.set("player." + p.getName() + ".money", money);
        AbsolHuman.getInstance().saveConfig();
    }

    private void confGradeSave(){
        config.set("player." + p.getName() + ".grade", this.grade.getUUID());
        AbsolHuman.getInstance().saveConfig();
    }

    /*
    public List<Quest> getQuestList() {
        return questList;
    }

    public List<Quest> getActiveQuestList() {
        List<Quest> active_quest = new ArrayList<>();
        questList.forEach(quest -> {
            if(!quest.isComplete()){
                active_quest.add(quest);
            };
        });
        return active_quest;
    }

    public int getMoney() {
        return money;
    }*/

    // Peut être call pour mettre à jour les infos
    // La fonction prend en charge la création des données du joueurs si elles sont ne pas existantes
    public void reload(){
        AbsolHuman.getInstance().reloadConfig();

        /* Grade */
        String grade_uuid;
        grade_uuid = config.getString("player." + p.getName() + ".grade");
        if(grade_uuid == null) {
            // Set default grade
            grade_uuid = Grade.Membre.getUUID();
            config.set("player." + p.getName() + ".grade", Grade.Membre.getUUID());
        }
        grade = Grade.getGradeByUUID(grade_uuid);

        /* Money */
        int money_read;
        String money_read_string = config.getString("player." + p.getName() + ".money");
        if(money_read_string == null) {
            // Set default money
            config.set("player." + p.getName() + ".money", 100);
            money_read = 100;
        }else{
            money_read = Integer.parseInt(money_read_string);
        }
        money = money_read;

        /* XP */
        int xp_read;
        String xp_read_string = config.getString("player." + p.getName() + ".xp");
        if(xp_read_string == null) {
            // Set default money
            config.set("player." + p.getName() + ".xp", 0);
            xp_read = 1;
        }else{
            xp_read = Integer.parseInt(xp_read_string);
        }
        xp = xp_read;

        /* Quest */
        /*
        List<String> questList_read;
        questList_read = AbsolHuman.getInstance().getConfig().getStringList("player." + p.getName() + ".quests");
        if(questList_read.size() == 0){
            // Set default quests
            Quest.getDefaultQuests().forEach(quest -> AbsolHuman.getInstance().getConfig().set("player." + p.getName() + ".quests." + quest.getUUID(), false));
        }else{
            for (String quest_uuid : questList_read){
                questList.add(Quest.getQuestFromUUID(quest_uuid));
                System.out.println(quest_uuid);
            }
        }
        */


        AbsolHuman.getInstance().saveConfig();
    }


}
