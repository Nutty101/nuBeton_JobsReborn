package net.livecar.NuttyWorks.nuBeton_JobsReborn;

import org.bukkit.ChatColor;

import net.livecar.NuttyWorks.nuBeton_JobsReborn_V1_8.BetonJobsReborn_V1_8;
import net.livecar.NuttyWorks.nuBeton_JobsReborn_V1_9.BetonJobsReborn_V1_9;

public class BetonJobsReborn extends org.bukkit.plugin.java.JavaPlugin implements org.bukkit.event.Listener 
{
	public static BetonJobsReborn Instance;
	
	@SuppressWarnings("unused")
	public void onEnable() 
	{
		//Get version 1.8.x or later
		if (getServer().getPluginManager().getPlugin("BetonQuest").getDescription().getVersion().startsWith("1.8"))
		{
			BetonJobsReborn_V1_8 bqPlugin8 = new BetonJobsReborn_V1_8();
		} else
		{
			BetonJobsReborn_V1_9 bqPlugin9 = new BetonJobsReborn_V1_9();
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
