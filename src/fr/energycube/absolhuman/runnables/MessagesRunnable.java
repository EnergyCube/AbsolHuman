package fr.energycube.absolhuman.runnables;

import fr.energycube.absolhuman.AbsolPlayer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MessagesRunnable extends BukkitRunnable {

    String bot_prefix = ChatColor.RED + "[" + ChatColor.AQUA + "Floky" + ChatColor.RED + "]" + ChatColor.RESET;

    List<String> msg = Arrays.asList(new String[]{
            bot_prefix + ChatColor.GREEN + " Vous jouez sur le serveur de la communauté d'Absol Human !",
            bot_prefix + ChatColor.GREEN + " Si vous jouez, n'hésitez pas à venir sur Discord !",
            bot_prefix + ChatColor.GREEN + " Envie d'un #⛏-minecraft ? Moi aussi...",
            bot_prefix + ChatColor.GREEN + " N'hésitez pas à ramener des joueurs de la communauté sur le serveur :)",
            bot_prefix + ChatColor.GREEN + " Votre PC est lent ? Minecraft utilise trop de ram ? Vous avez des chutes de FPS ? Utilisez donc Optifine !",
            bot_prefix + ChatColor.GREEN + " Vous avez un bon PC et vous voulez rendre votre jeu graphiquement plus joli ? Utilisez donc Optifine avec des shaders !",
            bot_prefix + ChatColor.GREEN + " Je vous ai déjà parlé de Red Shadow Legend ?",
            bot_prefix + ChatColor.GREEN + " Je vous ai déjà parlé de NordVPN ?",

            // Section catacombes de l'humour

            bot_prefix + ChatColor.GREEN + " Une maman Kangourou demande à son fils : Comment s’est passé ton examen ? – « C’est dans la poche »",
            bot_prefix + ChatColor.GREEN + " Une mouette mange un sandwich. Une autre arrive : On fait mouette-mouette ?",
            bot_prefix + ChatColor.GREEN + " Quelle est la ville la plus proche de l’eau ? Bordeaux",
            bot_prefix + ChatColor.GREEN + " C’est l’histoire d’un schtroumpf qui tombe, et qui se fait un bleu..."

    });


    @Override
    public void run() {
        int r = new Random().nextInt(10);

        switch (r){
            case 0:
                // 10%
                TextComponent msg_tc = new TextComponent(bot_prefix + ChatColor.GREEN + " Souvenez vous ! Vous devez avoir lu le " + ChatColor.GOLD + ChatColor.UNDERLINE + "réglement" + ChatColor.RESET + ChatColor.GOLD + " (message cliquable)" + ChatColor.RESET + ChatColor.GREEN + " et le respecter pour jouer sur le serveur !");
                msg_tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Ouvir le site").create()));
                msg_tc.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://pastebin.com/raw/j2idexgm"));
                AbsolPlayer.getAPlayers().forEach(player -> player.getPlayer().spigot().sendMessage(msg_tc));
                break;
            default:
                // 90%
                int r_ = new Random().nextInt(msg.size()); // Re Random
                AbsolPlayer.getAPlayers().forEach(player -> player.getPlayer().sendMessage(msg.get(r_)));
                break;
        }



    }

}
