package me.sousa222.smegafone.commands;

import me.sousa222.smegafone.SMegafone;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class MegafoneCommand implements CommandExecutor {
    private final HashMap<UUID, Long> cooldown;

    public MegafoneCommand(){
        this.cooldown = new HashMap<>();
    }
    final FileConfiguration config = SMegafone.getPlugin().getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission("smegafone.megafone")){
                if(args.length > 0) {
                    if(!this.cooldown.containsKey(player.getUniqueId()) || System.currentTimeMillis() - cooldown.get(player.getUniqueId()) >= config.getLong("cooldown") * 1000) {
                        this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                        // anuncio = mensagem da config yml
                        for (String anuncio : config.getStringList("anuncio")) {
                            String msg = "";
                            // msg = mensagem que o jogador escreve
                            for (int i = 0; i < args.length; i++) {
                                msg = msg + args[i] + " ";
                            }
                            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', anuncio).replace("%jogador%", player.getDisplayName()).replace("%anuncio%", msg));
                        }
                    }else{
                        long timeElapsed = System.currentTimeMillis() - cooldown.get(player.getUniqueId());
                        int timeElapsedSeconds = (int) (timeElapsed / 1000);
                        int p1 = (int) ((config.getLong("cooldown") - timeElapsedSeconds) % 60);
                        int p2 = (int) ((config.getLong("cooldown") - timeElapsedSeconds) / 60);
                        int p3 = p2 % 60;
                        p2 = p2 / 60;
                        if(p3 == 0 && p2 == 0){
                            player.sendMessage("§cVocê tem que esperar mais " + p1 + " segundo(s) para executar esse comando novamente.");
                            return true;
                        } else if(p2 > 0) {
                            player.sendMessage("§cVocê tem que esperar mais " +p2+ " hora(s), " + p3 + " minuto(s), " + p1 + " segundo(s) para executar esse comando novamente.");
                            return true;
                        }else if(p2 == 0) {
                            player.sendMessage("§cVocê tem que esperar mais " + p3 + " minuto(s), " + p1 + " segundo(s) para executar esse comando novamente.");
                            return true;
                        }
                    }
                }else{
                    String semArgumentosMsg = SMegafone.getPlugin().getConfig().getString("erro_sem_argumentos");
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', semArgumentosMsg));
                }
            }else{
                String semPermissaoMsg = config.getString("erro_sem_permissao");
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', semPermissaoMsg));
            }
        } else {
            String consoleErroMsg = config.getString("erro_console");
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', consoleErroMsg));
        }

        return true;
    }
}
