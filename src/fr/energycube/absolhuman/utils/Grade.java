package fr.energycube.absolhuman.utils;

import org.bukkit.ChatColor;

public enum Grade {

    Membre(ChatColor.GRAY + "[Membre]", ChatColor.GRAY.toString(), "ff3126cd-7fcc-4887-b2e8-8e352664f85b"),
    MembreActif(ChatColor.GREEN + "[Membre Actif]", ChatColor.GRAY.toString(), "e43d66d0-988a-4319-a91b-642b190bea06"),
    Moderateur(ChatColor.GOLD + "[Modérateur]", ChatColor.WHITE.toString(), "03920967-7283-4149-afcf-7a217005a605"),
    Admin(ChatColor.DARK_PURPLE + "[Administrateur]", ChatColor.WHITE.toString(), "ccc626d9-f57d-4f11-b4df-dbe5334e356f");

    private String chat_name;
    private String chat_color;
    private String uuid;

    Grade(String chat_name, String chat_color, String uuid) {
        this.chat_name = chat_name;
        this.chat_color = chat_color;
        this.uuid = uuid;
    }

    public String getChatName(){
        return chat_name;
    }

    public String getChatColor(){
        return chat_color;
    }

    public String getUUID() {
        return uuid;
    }

    public boolean isMod(){
        if(this == Admin || this == Moderateur){
            return true;
        }
        return false;
    }

    public static Grade getGradeByUUID(String uuid){
        for(Grade grades : values()){
            if(grades.getUUID().contains(uuid)){
                return grades;
            }
        }
        return null;
    }
}


