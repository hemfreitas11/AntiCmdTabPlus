package me.bkrmt.anticmdtabplus;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static me.bkrmt.anticmdtabplus.AntiCmdTabPlus.plugin;

public class CustomTab implements Listener {

    @EventHandler(
            priority = EventPriority.HIGHEST
    )
    public void onTabComplete(TabCompleteEvent event) {
        String[] args = event.getCompletions().toArray(new String[0]);
        String command = event.getBuffer().trim();

        if (plugin.getNmsVer().number < 13) {
            List<String> customCommandsTab = plugin.getConfig().getStringList("custom-commands-tab");
            if (!customCommandsTab.isEmpty()) {
                event.setCompletions(customCommandsTab);
            }
        }

        ConfigurationSection section = plugin.getConfig().getConfigurationSection("custom-args-tab");
        for (String key : section.getKeys(false)) {
            if (command.equalsIgnoreCase(key)) {
                event.setCompletions(plugin.getConfig().getStringList("custom-args-tab." + key));
                break;
            }
        }
        /*ArrayList<String> commandsWithCustomTab = new ArrayList<>();

        for (String key : section.getKeys(false)) []

        Set<String> configKeys = plugin.getConfig().getKeys(true);
        for (String key : configKeys) {
            if (key.contains("custom-args-tab")) {
                commandsWithCustomTab.add("/" + key.replace("custom-args-tab", ""));
            }
        }

        for (String commandWithCustomTab : commandsWithCustomTab) {
            if (command.equalsIgnoreCase(commandWithCustomTab)) {
                List<String> customTabs = plugin.getConfig().getStringList("custom-args-tab." + command);
                if (args.length >= 2) {
                    event.setCompletions(partialCompletion(args, customTabs));
                } else {
                    event.setCompletions(customTabs);
                }
            }
        }*/

    }

    private List<String> partialCompletion(String[] args, List<String> passedCompletions) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            String partialCommand = args[0];
            StringUtil.copyPartialMatches(partialCommand, passedCompletions, completions);
        }

        Collections.sort(completions);

        return completions;
    }
}
