package fr.energycube.absolhuman.utils;

public class MathXP {

    /* Attention les yeux, c'est des maths */

    public static int getLVLfromXP(int xp){
        return Math.round(xp / 1000);
    }

    public static int getNextXPNeededFromLVL(int lvl){
        return (lvl + 1) * 1000;
    }

    public static int getNextXPNeeded(int xp){
        return ((getLVLfromXP(xp) + 1) * 1000) - xp;
    }


}
