package me.sousa222.smegafone.commands;

import me.sousa222.smegafone.SMegafone;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;


import java.util.HashMap;
import java.util.UUID;

public class MegafoneCommand implements CommandExecutor {

    private final HashMap<UUID, Long> cooldown;

    public MegafoneCommand() {
        this.cooldown = new HashMap<>();
    }

    final double quantia = SMegafone.getPlugin().getConfig().getDouble("quantia");
    final Economy econ = SMegafone.getEconomy();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(SMegafone.getPlugin().getConfig().getString("erro_console"));
            return true;
        }

        Player p = (Player) sender;
        if (!(p.hasPermission("megafone.usar"))) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', SMegafone.getPlugin().getConfig().getString("erro_sem_permissao")));
            return true;
        }

        if (!(args.length > 0)) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', SMegafone.getPlugin().getConfig().getString("erro_sem_argumentos")));
            return true;
        }

        if (!this.cooldown.containsKey(p.getUniqueId()) || System.currentTimeMillis() - cooldown.get(p.getUniqueId()) >= SMegafone.getPlugin().getConfig().getLong("cooldown") * 1000) {
            EconomyResponse withdraw = econ.withdrawPlayer(p, quantia);
            if (withdraw.transactionSuccess()) {
                if (!(p.hasPermission("smegafone.bypass"))){
                    cooldown.put(p.getUniqueId(), System.currentTimeMillis());
                }
                for (String anuncio : SMegafone.getPlugin().getConfig().getStringList("anuncio")) {
                    String msg = "";
                    for (int i = 0; i < args.length; i++) {
                        msg = msg + args[i] + " ";
                    }
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', anuncio).replace("%jogador%", p.getDisplayName()).replace("%anuncio%", msg));
                }
                String titleMessage = ChatColor.translateAlternateColorCodes('&', SMegafone.getPlugin().getConfig().getString("title.linha1").replace("%player%", p.getDisplayName()));
                String subTitleMessage = ChatColor.translateAlternateColorCodes('&', SMegafone.getPlugin().getConfig().getString("title.linha2").replace("%player%", p.getDisplayName()));
                for (Player global : Bukkit.getOnlinePlayers()) {
                    PlayerConnection connection = ((CraftPlayer) global.getPlayer()).getHandle().playerConnection;
                    IChatBaseComponent title = IChatBaseComponent.ChatSerializer.a("{'text': ' " + titleMessage + "'}");
                    IChatBaseComponent subTitle = IChatBaseComponent.ChatSerializer.a("{'text': ' " + subTitleMessage + "'}");
                    PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, title);
                    PacketPlayOutTitle subTitlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, subTitle);
                    connection.sendPacket(subTitlePacket);
                    connection.sendPacket(titlePacket);
                    global.playSound(p.getLocation(), Sound.valueOf(SMegafone.getPlugin().getConfig().getString("som_anuncio").toUpperCase()), 100, 100);
                }
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', SMegafone.getPlugin().getConfig().getString("sem_money")));
            }
            return true;
        } else {
            long timeElapsed = System.currentTimeMillis() - cooldown.get(p.getUniqueId());
            int timeElapsedSeconds = (int) (timeElapsed / 1000);
            int p1 = (int) ((SMegafone.getPlugin().getConfig().getLong("cooldown") - timeElapsedSeconds) % 60);
            int p2 = (int) ((SMegafone.getPlugin().getConfig().getLong("cooldown") - timeElapsedSeconds) / 60);
            int p3 = p2 % 60;
            p2 = p2 / 60;
            if (p3 == 0 && p2 == 0) {
                p.sendMessage("§cVocê tem que esperar mais " + p1 + " segundo(s) para executar esse comando novamente.");
                return true;
            } else if (p2 > 0) {
                p.sendMessage("§cVocê tem que esperar mais " + p2 + " hora(s), " + p3 + " minuto(s), " + p1 + " segundo(s) para executar esse comando novamente.");
                return true;
            } else if (p2 == 0) {
                p.sendMessage("§cVocê tem que esperar mais " + p3 + " minuto(s), " + p1 + " segundo(s) para executar esse comando novamente.");
                return true;
            }
        }
        return false;
    }
}