package fr.ixsou.lobby;

import fr.ixsou.lobby.commands.LobbyAdmin;
import fr.ixsou.lobby.commands.SetLobby;
import fr.ixsou.lobby.commands.TpLobby;
import fr.ixsou.lobby.utils.LobbyTabCompleter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Lobby extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        getLogger().info(getMessage("plugin-enable"));
        getCommand("setlobby").setExecutor(new SetLobby(this));
        getCommand("lobby").setExecutor(new TpLobby(this));
        getCommand("lobbyadmin").setExecutor(new LobbyAdmin(this));
        getCommand("lobbyadmin").setTabCompleter(new LobbyTabCompleter());
    }


    @Override
    public void onDisable() {
        saveDefaultConfig();

        getLogger().info(getMessage("plugin-disable"));
    }
    public String getMessage(String path) {
        if (path.equals("no-found-message")) {
            return ChatColor.translateAlternateColorCodes('&', "&cMessage introuvable !");
        }

        Object messageObject = getConfig().get("messages." + path);

        if (messageObject instanceof String) {
            return ChatColor.translateAlternateColorCodes('&', (String) messageObject);
        }

        return getMessage("no-found-message");
    }

    public String helpMessage(){
        return getMessage("help.header") + "\n" +
                getMessage("help.1") + "\n" +
                getMessage("help.2") + "\n" +
                getMessage("help.3") + "\n" +
                getMessage("help.footer");
    }


}
