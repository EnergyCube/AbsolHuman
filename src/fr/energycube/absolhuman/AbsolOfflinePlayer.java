package fr.energycube.absolhuman;

import fr.energycube.absolhuman.utils.Grade;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public class AbsolOfflinePlayer {

    FileConfiguration config = AbsolHuman.getInstance().getConfig();
    private static HashMap<String, AbsolOfflinePlayer> absolOfflinePlayerHashMap = new HashMap<>();

    private String name;
    private Grade grade;
    private int money;
    private int xp;

    public AbsolOfflinePlayer(String name){
        this.name = name;
        if(absolOfflinePlayerHashMap.containsKey(name)){
            grade = absolOfflinePlayerHashMap.get(name).getGrade();
            money = absolOfflinePlayerHashMap.get(name).getMoney();
            xp = absolOfflinePlayerHashMap.get(name).getXP();
        }else {
            load();
            absolOfflinePlayerHashMap.put(name, this);
        }
    }

    public void unload(){
        if(absolOfflinePlayerHashMap.containsKey(name))
            absolOfflinePlayerHashMap.remove(name);
    }

    public static void tryUnload(String name){
        if(absolOfflinePlayerHashMap.containsKey(name))
            absolOfflinePlayerHashMap.remove(name);
    }

    public String getName() {
        return name;
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
        config.set("player." + name + ".money", money);
        AbsolHuman.getInstance().saveConfig();
    }

    private void confGradeSave(){
        config.set("player." + name + ".grade", grade.getUUID());
        AbsolHuman.getInstance().saveConfig();
    }

    private void confXPSave(){
        config.set("player." + name + ".xp", xp);
        AbsolHuman.getInstance().saveConfig();
    }


    public void load(){

        /* Grade */
        String grade_uuid;
        grade_uuid = config.getString("player." + name + ".grade");
        grade = Grade.getGradeByUUID(grade_uuid);

        /* Money */
        int money_read;
        String money_read_string = config.getString("player." + name + ".money");
        money_read = Integer.parseInt(money_read_string);
        money = money_read;

        /* XP */
        int xp_read;
        String xp_read_string = config.getString("player." + name + ".xp");
        xp_read = Integer.parseInt(xp_read_string);
        xp = xp_read;

    }

}
