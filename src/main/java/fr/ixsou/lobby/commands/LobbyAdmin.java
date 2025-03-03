package fr.ixsou.lobby.commands;

import fr.ixsou.lobby.Lobby;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class LobbyAdmin implements CommandExecutor{

    private final Lobby plugin;

    public LobbyAdmin(Lobby plugin){
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(plugin.getMessage("no-player"));
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            sender.sendMessage(plugin.getMessage("wrong-command-usage"));
            return false;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (player.hasPermission("lobby.reload")) {
                plugin.reloadConfig();
                plugin.saveDefaultConfig();
                player.sendMessage(plugin.getMessage("config-reload"));
            } else {
                sender.sendMessage(plugin.getMessage("no-permission"));
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("help")) {
            if (player.hasPermission("lobby.help")) {
                player.sendMessage(plugin.helpMessage());
            } else {
                sender.sendMessage(plugin.getMessage("no-permission"));
            }
            return true;
        }

        sender.sendMessage(plugin.getMessage("wrong-command-usage"));
        return false;
    }
}
