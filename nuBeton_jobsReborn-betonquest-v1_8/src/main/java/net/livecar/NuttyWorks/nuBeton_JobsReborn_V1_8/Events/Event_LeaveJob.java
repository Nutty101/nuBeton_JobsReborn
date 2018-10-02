package net.livecar.NuttyWorks.nuBeton_JobsReborn_V1_8.Events;

import org.bukkit.entity.Player;

import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.container.Job;

import pl.betoncraft.betonquest.InstructionParseException;
import pl.betoncraft.betonquest.api.QuestEvent;
import pl.betoncraft.betonquest.utils.PlayerConverter;

public class Event_LeaveJob extends QuestEvent
{
	private String sJobName;
	
    public Event_LeaveJob(String packName, String instructions) throws InstructionParseException 
    {
        super(packName, instructions);
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

    @Override
    public void run(String playerID) 
    {
		Player oPlayer =  PlayerConverter.getPlayer(playerID);
		for (Job job : Jobs.getJobs()) 
		{
			if (job.getName().equalsIgnoreCase(sJobName))
			{
				Jobs.getPlayerManager().getJobsPlayer(oPlayer).leaveJob(job);
				return;
			}
		}
    }
}
