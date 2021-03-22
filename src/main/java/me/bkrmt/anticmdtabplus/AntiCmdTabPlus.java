package me.bkrmt.anticmdtabplus;

import me.bkrmt.bkcore.BkPlugin;

public final class AntiCmdTabPlus extends BkPlugin {
    public static BkPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        start();
        getServer().getPluginManager().registerEvents(new BlockCommand(), this);
        getServer().getPluginManager().registerEvents(new CustomTab(), this);
        getServer().getPluginManager().registerEvents(new CustomCommandsTab(), this);
    }
}
