package me.bkrmt.anticmdtabplus;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;

import static me.bkrmt.anticmdtabplus.AntiCmdTabPlus.plugin;

public class
BlockCommand implements Listener {

    @EventHandler
            (
                    priority = EventPriority.HIGHEST
            )
    public void onPreprocess(PlayerCommandPreprocessEvent event) {
        String command = "";
        for (String arg : event.getMessage().split(" ")) {
            if (arg.contains("/")) {
                command = arg;
            }
        }
        ArrayList<String> blockedCommands = (ArrayList<String>) plugin.getConfig().getStringList("blocked-commands");
        for (String blockedCommand : blockedCommands) {
            if (command.equalsIgnoreCase(blockedCommand)) {
                if (plugin.getConfig().getBoolean("blocked-command-message"))
                    event.getPlayer().sendMessage(plugin.getLangFile().get("error.command-blocked"));
                event.setCancelled(true);
            }
        }

        ArrayList<String> blockedArgs = (ArrayList<String>) plugin.getConfig().getStringList("blocked-arguments");
        ArrayList<String> commandWhitelist = (ArrayList<String>) plugin.getConfig().getStringList("blocked-arguments");

        String[] args = event.getMessage().split(" ");
        for (String arg : args) {
            boolean isWhitelisted = false;
            if (arg.contains("/")) {
                for (String whitelistedCmd : commandWhitelist) {
                    if (arg.equalsIgnoreCase("/" + whitelistedCmd)) {
                        isWhitelisted = true;
                        break;
                    }
                }
            }
            if (!isWhitelisted) {
                if (!arg.contains("/")) {
                    for (String blockedArg : blockedArgs) {
                        if (arg.equalsIgnoreCase(blockedArg)) {
                            if (plugin.getConfig().getBoolean("blocked-arg-message"))
                                event.getPlayer().sendMessage(plugin.getLangFile().get("error.argument-blocked"));
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
    }
}
