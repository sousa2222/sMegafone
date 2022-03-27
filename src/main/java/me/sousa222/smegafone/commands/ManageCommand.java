package me.sousa222.smegafone.commands;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import me.sousa222.smegafone.SMegafone;
import org.bukkit.entity.Player;

public class ManageCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            if (args.length < 1 || !(args[0].equalsIgnoreCase("reload"))) {
                sender.sendMessage("§cUse: /smegafone reload");
                return true;
            }
            if (args[0].equalsIgnoreCase("reload")) {
                SMegafone.getPlugin().reloadConfig();
                sender.sendMessage("§asMegafone recarregado com sucesso!");
                return true;
            }
        }
        Player player = (Player) sender;
        if (args.length < 1) {
            if (player.hasPermission("smegafone.admin")) {
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 100);
                player.sendMessage("\n§e§lsMegafone §f- §81.1.0\n§7/smegafone reload §f- §eRecarrega os ficheiros de configuração.\n");
                return true;
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',SMegafone.getPlugin().getConfig().getString("erro_sem_permissao")));
                player.playSound(player.getLocation(), Sound.VILLAGER_HAGGLE, 100, 100);
                return true;
            }
        }
        if(args[0].equalsIgnoreCase("reload")){
            if(player.hasPermission("smegafone.admin")){
                SMegafone.getPlugin().reloadConfig();
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', SMegafone.getPlugin().getConfig().getString("mensagem_reload")));
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);

                return true;
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',SMegafone.getPlugin().getConfig().getString("erro_sem_permissao")));
                player.playSound(player.getLocation(), Sound.VILLAGER_HAGGLE, 100, 100);
                return true;
            }
        }
        return true;
    }
}
