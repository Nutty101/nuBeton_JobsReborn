package net.livecar.NuttyWorks.nuBeton_JobsReborn_V1_8.Objectives;

import net.livecar.NuttyWorks.nuBeton_JobsReborn_V1_8.BetonJobsReborn_V1_8;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.api.JobsLevelUpEvent;
import com.gamingmesh.jobs.container.Job;

import pl.betoncraft.betonquest.InstructionParseException;
import pl.betoncraft.betonquest.api.Objective;
import pl.betoncraft.betonquest.utils.PlayerConverter;

public class Objective_LevelUpEvent extends Objective implements Listener 
{
	private final String sJobName;
	
	public Objective_LevelUpEvent(String packName, String label, String instructions) throws InstructionParseException 
	{
        super(packName, label, instructions);
        template = ObjectiveData.class;
		String[] sParts = instructions.split(" ");
		if (sParts.length < 2) {
			throw new InstructionParseException("Not enough arguments");
		}
		for (Job job : Jobs.getJobs()) 
		{
			if (job.getName().equalsIgnoreCase(sParts[1]))
			{
				sJobName = job.getName();
				return;
			}
		}
		throw new InstructionParseException("Jobs Reborn job " + sParts[1] + " does not exist" );
    }
	
	@EventHandler
    public void onJobsLevelUpEvent(JobsLevelUpEvent event) 
	{
		if (event.getJobName().equalsIgnoreCase(this.sJobName))
		{
			String playerID = PlayerConverter.getID(event.getPlayer().getPlayer().getPlayer());
            if (containsPlayer(playerID) && checkConditions(playerID)) {
                completeObjective(playerID);
            }
		}
    }
    
    @Override
    public void start() {
        Bukkit.getPluginManager().registerEvents(this, BetonJobsReborn_V1_8.getPlugin());
    }

    @Override
    public void stop() {
        HandlerList.unregisterAll(this);
    }

    @Override
    public String getDefaultDataInstruction() {
        return "";
    }

}
