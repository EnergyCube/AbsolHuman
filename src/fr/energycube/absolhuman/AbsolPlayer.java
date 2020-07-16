package fr.energycube.absolhuman;

import fr.energycube.absolhuman.utils.Grade;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;

public class AbsolPlayer {

    private static HashMap<Player, AbsolPlayer> regListHM = new HashMap<>();
    FileConfiguration config = AbsolHuman.getInstance().getConfig();

    private boolean logged;

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
        p.setDisplayName(grade.getChatColor() + getGrade().getChatName() + " " + getPlayer().getName());
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

    public void setXP() {
        this.xp =  xp;
        confXPSave();
    }

    public void addXP(int xp){
        this.xp += xp;
        confXPSave();
    }

    public void remXP(int xp){
        this.xp -= xp;
        confXPSave();
    }

    private void confMoneySave(){
        config.set("player." + p.getName() + ".money", money);
        AbsolHuman.getInstance().saveConfig();
    }

    private void confGradeSave(){
        config.set("player." + p.getName() + ".grade", grade.getUUID());
        AbsolHuman.getInstance().saveConfig();
    }

    private void confXPSave(){
        config.set("player." + p.getName() + ".xp", xp);
        AbsolHuman.getInstance().saveConfig();
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public void sendAuthMessage(){
        if(AbsolHuman.getInstance().getConfig().get("player." + p.getName() + ".password") == null){
            p.sendMessage(ChatColor.RED + "MERCI DE VOUS ENREGISTRER !");
            p.sendMessage(ChatColor.YELLOW + "Merci de vous enregistrer avec /register UnMotDePasse");
        }else{
            p.sendMessage(ChatColor.RED + "MERCI DE VOUS IDENTIFIER !");
            p.sendMessage(ChatColor.YELLOW + "Merci de vous enregistrer avec /login VotreMotDePasse");
        }
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
            xp_read = 0;
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


        p.setDisplayName(grade.getChatColor() + getGrade().getChatName() + " " + getPlayer().getName());
        logged = false;
        AbsolHuman.getInstance().saveConfig();
    }


}
