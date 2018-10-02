package net.livecar.NuttyWorks.nuBeton_JobsReborn_V1_9.Objectives;

import net.livecar.NuttyWorks.nuBeton_JobsReborn_V1_9.BetonJobsReborn_V1_9;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.api.JobsLeaveEvent;
import com.gamingmesh.jobs.container.Job;

import pl.betoncraft.betonquest.Instruction;
import pl.betoncraft.betonquest.InstructionParseException;
import pl.betoncraft.betonquest.api.Objective;
import pl.betoncraft.betonquest.utils.PlayerConverter;

public class Objective_LeaveJob extends Objective implements Listener 
{
	private final String sJobName;
	
	public Objective_LeaveJob(Instruction instructions) throws InstructionParseException 
	{
        super(instructions);
        template = ObjectiveData.class;
		if (instructions.size() < 2) {
			throw new InstructionParseException("Not enough arguments");
		}
		for (Job job : Jobs.getJobs()) 
		{
			if (job.getName().equalsIgnoreCase(instructions.getPart(1)))
			{
				sJobName = job.getName();
				return;
			}
		}
		throw new InstructionParseException("Jobs Reborn job " + instructions.getPart(1) + " does not exist" );
    }
	
	@EventHandler
    public void onJobsLeaveEvent(JobsLeaveEvent event) 
	{
		if (event.getJob().getName().equalsIgnoreCase(this.sJobName))
		{
			String playerID = PlayerConverter.getID(event.getPlayer().getPlayer().getPlayer());
            if (containsPlayer(playerID) && checkConditions(playerID)) {
                completeObjective(playerID);
            }
		}
    }
    
    @Override
    public void start() {
        Bukkit.getPluginManager().registerEvents(this, BetonJobsReborn_V1_9.getPlugin());
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
