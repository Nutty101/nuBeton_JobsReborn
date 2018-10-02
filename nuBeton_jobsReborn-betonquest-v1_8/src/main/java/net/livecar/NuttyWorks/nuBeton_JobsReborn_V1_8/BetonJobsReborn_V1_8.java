package net.livecar.NuttyWorks.nuBeton_JobsReborn_V1_8;

import java.util.logging.Level;

import net.livecar.NuttyWorks.BetonQuestPlugin;
import org.bukkit.Bukkit;

import net.livecar.NuttyWorks.nuBeton_JobsReborn_V1_8.Conditions.*;
import net.livecar.NuttyWorks.nuBeton_JobsReborn_V1_8.Events.*;
import net.livecar.NuttyWorks.nuBeton_JobsReborn_V1_8.Objectives.*;
import org.bukkit.plugin.java.JavaPlugin;
import pl.betoncraft.betonquest.BetonQuest;

public class BetonJobsReborn_V1_8 extends BetonQuestPlugin
{
	public BetonJobsReborn_V1_8(JavaPlugin plugin)
	{
	    super(plugin);

		//Register conditions with beton
		BetonQuest.getInstance().registerConditions("nujobs_canlevel", Condition_CanLevel.class);
		BetonQuest.getInstance().registerConditions("nujobs_hasjob", Condition_HasJob.class);
		BetonQuest.getInstance().registerConditions("nujobs_jobfull", Condition_JobFull.class);
		BetonQuest.getInstance().registerConditions("nujobs_joblevel", Condition_JobLevel.class);
		Bukkit.getServer().getLogger().log(Level.INFO, "Registered Conditions [nujobs_canlevel,nujobs_hasjob,nujobs_jobfull,nujobs_joblevel]");
		
		//register events
		BetonQuest.getInstance().registerEvents("nujobs_addexp", Event_AddExp.class);
		BetonQuest.getInstance().registerEvents("nujobs_addlevel", Event_AddLevel.class);
		BetonQuest.getInstance().registerEvents("nujobs_dellevel", Event_DelLevel.class);
		BetonQuest.getInstance().registerEvents("nujobs_joinjob", Event_JoinJob.class);
		BetonQuest.getInstance().registerEvents("nujobs_leavejob", Event_LeaveJob.class);
		BetonQuest.getInstance().registerEvents("nujobs_setlevel", Event_SetLevel.class);
		Bukkit.getServer().getLogger().log(Level.INFO, "Registered Events [nujobs_addexp,nujobs_addlevel,nujobs_dellevel,nujobs_joinjob,nujobs_leavejob,nujobs_setlevel]");
		
		//register objectives
		BetonQuest.getInstance().registerObjectives("nujobs_joinjob", Objective_JoinJob.class);
		BetonQuest.getInstance().registerObjectives("nujobs_leavejob", Objective_LeaveJob.class);
		BetonQuest.getInstance().registerObjectives("nujobs_levelup", Objective_LevelUpEvent.class);
		BetonQuest.getInstance().registerObjectives("nujobs_payment", Objective_PaymentEvent.class);
		Bukkit.getServer().getLogger().log(Level.INFO, "Registered Objectives [nujobs_joinjob,nujobs_leavejob,nujobs_levelup,nujobs_payment]");
		
	}
}
