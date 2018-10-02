package net.livecar.NuttyWorks;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class BetonQuestPlugin
{
    private static JavaPlugin plugin;

    public BetonQuestPlugin(JavaPlugin sourcePlugin) {
        plugin = sourcePlugin;
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }
}
