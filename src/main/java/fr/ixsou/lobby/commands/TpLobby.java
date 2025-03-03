package fr.ixsou.lobby.commands;

import fr.ixsou.lobby.Lobby;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TpLobby implements CommandExecutor {

    private final Lobby plugin;

    public TpLobby(Lobby plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(plugin.getMessage("console-only-player"));
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("lobby.lobby")) {
            player.sendMessage(plugin.getMessage("no-permission"));
            return true;
        }

        FileConfiguration config = plugin.getConfig();
        String nomMonde = config.getString("lobby.world");

        if (nomMonde == null) {
            player.sendMessage(plugin.getMessage("no-lobby-set"));
            return true;
        }

        if (Bukkit.getWorld(nomMonde) == null) {
            player.sendMessage(plugin.getMessage("world-not-found").replace("%world%", nomMonde));
            return true;
        }

        double x = config.getDouble("lobby.x");
        double y = config.getDouble("lobby.y");
        double z = config.getDouble("lobby.z");
        float yaw = (float) config.getDouble("lobby.yaw");
        float pitch = (float) config.getDouble("lobby.pitch");

        Location lieu = new Location(Bukkit.getWorld(nomMonde), x, y, z, yaw, pitch);

        player.teleport(lieu);

        if (config.getBoolean("messages.teleport-message.enable", true)) {
            player.sendMessage(plugin.getMessage("teleport-message.message"));
        }

        return true;
        }
}

