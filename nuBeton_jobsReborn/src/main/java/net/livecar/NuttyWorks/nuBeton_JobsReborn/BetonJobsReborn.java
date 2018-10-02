package net.livecar.NuttyWorks.nuBeton_JobsReborn;

import net.livecar.NuttyWorks.BetonQuestPlugin;
import org.bukkit.ChatColor;

import net.livecar.NuttyWorks.nuBeton_JobsReborn_V1_8.BetonJobsReborn_V1_8;
import net.livecar.NuttyWorks.nuBeton_JobsReborn_V1_9.BetonJobsReborn_V1_9;

public class BetonJobsReborn extends org.bukkit.plugin.java.JavaPlugin implements org.bukkit.event.Listener 
{
	public static BetonJobsReborn Instance;
	private BetonQuestPlugin bqPlugin;
	
	@SuppressWarnings("unused")
	public void onEnable() 
	{
		//Get version 1.8.x or 1.9x
		if (getServer().getPluginManager().getPlugin("BetonQuest").getDescription().getVersion().startsWith("1.8"))
		{
			bqPlugin = new BetonJobsReborn_V1_8(this);
		} else if (getServer().getPluginManager().getPlugin("BetonQuest").getDescription().getVersion().startsWith("1.9") || getServer().getPluginManager().getPlugin("BetonQuest").getDescription().getVersion().startsWith("1.10"))
		{
			bqPlugin = new BetonJobsReborn_V1_9(this);
		}


		BetonJobsReborn.Instance = this;

		getLogger().log(java.util.logging.Level.ALL,ChatColor.GREEN + this.getDescription().getName() + " [V "+ this.getDescription().getVersion() + "] initialized");

		try {
			MCStatsMetrics metrics = new MCStatsMetrics(this);
			metrics.start();
		} catch (Exception e) {
			// Wheee no stats, oh well.
		}
	}
}
