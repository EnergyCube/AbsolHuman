package fr.energycube.absolhuman.utils;

import net.minecraft.server.v1_16_R1.IChatBaseComponent;
import net.minecraft.server.v1_16_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_16_R1.PlayerConnection;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class TitleMessage {

    private String title_;
    private String subtitle_;

    public TitleMessage(@Nullable String title, @Nullable String subtitle) {
        this.title_ = title;
        this.subtitle_ = subtitle;
    }

    public void send(Player player, int fadein, int stay, int fadeout) {
        PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;

        IChatBaseComponent title = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title_ + "\"}");
        IChatBaseComponent subtitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle_ + "\"}");

        PacketPlayOutTitle titleTime = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, title, fadein, stay, fadeout);
        PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, title);

        PacketPlayOutTitle subtitleTime = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, subtitle, fadein, stay, fadeout);
        PacketPlayOutTitle subtitlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, subtitle);

        connection.sendPacket(subtitleTime);
        connection.sendPacket(subtitlePacket);

        connection.sendPacket(titleTime);
        connection.sendPacket(titlePacket);

    }

}
