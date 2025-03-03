package fr.ixsou.lobby.commands;

import fr.ixsou.lobby.Lobby;
import net.md_5.bungee.api.ChatColor;
import org.apache.maven.artifact.repository.metadata.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetLobby implements CommandExecutor {


    private final Lobby plugin;

    public SetLobby(Lobby plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand( CommandSender sender, Command cmd, String s, String[] args) {
        Player player = (Player) sender;
        if (sender instanceof Player);
        if (!player.hasPermission("lobby.setlobby")) {
            player.sendMessage(plugin.getMessage("no-permission"));
            return true;
        }

        Location location = player.getLocation();

        plugin.getConfig().set("lobby.world", location.getWorld().getName());
        plugin.getConfig().set("lobby.x", location.getX());
        plugin.getConfig().set("lobby.y", location.getY());
        plugin.getConfig().set("lobby.z", location.getZ());
        plugin.getConfig().set("lobby.yaw", location.getYaw());
        plugin.getConfig().set("lobby.pitch", location.getPitch());

        plugin.saveConfig();

        player.sendMessage(plugin.getMessage("lobby-set"));

        return true;
    }
}
