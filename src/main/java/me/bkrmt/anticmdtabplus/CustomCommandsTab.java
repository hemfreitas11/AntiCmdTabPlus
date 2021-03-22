package me.bkrmt.anticmdtabplus;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;

public class CustomCommandsTab implements Listener {

    @EventHandler(
            priority = EventPriority.HIGHEST
    )
    public void onCommandSend(PlayerCommandSendEvent event) {
        //event.getCommands().clear();

        /*List<String> customCommandsTab = plugin.getConfig().getStringList("custom-commands-tab");
        if (!customCommandsTab.isEmpty()) {
            System.out.println(event.getCommands().size());
            event.getCommands().clear();
            System.out.println(event.getCommands().size());
            for (String customCommand : customCommandsTab) {
                System.out.println(customCommand.replace("/", ""));
                event.getCommands().add(customCommand.replace("/", ""));
            }
            System.out.println(event.getCommands().size());
        }*/
    }
}
