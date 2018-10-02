package net.livecar.NuttyWorks.nuBeton_JobsReborn_V1_8.Events;

import java.util.List;

import org.bukkit.entity.Player;

import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.container.Job;
import com.gamingmesh.jobs.container.JobProgression;

import pl.betoncraft.betonquest.InstructionParseException;
import pl.betoncraft.betonquest.api.QuestEvent;
import pl.betoncraft.betonquest.utils.PlayerConverter;

public class Event_AddExp extends QuestEvent
{
	private String sJobName;
	private double nAddExperience;
	
    public Event_AddExp(String packName, String instructions) throws InstructionParseException 
    {
        super(packName, instructions);
        String[] sParts = instructions.split(" ");
		if (sParts.length < 3) {
			throw new InstructionParseException("Not enough arguments");
		}
		for (Job job : Jobs.getJobs()) 
		{
			if (job.getName().equalsIgnoreCase(sParts[1]))
			{
				sJobName = job.getName();
				try {
					this.nAddExperience = Double.parseDouble(sParts[2]);
				} catch (Exception err)
				{
					throw new InstructionParseException("NUJobs_AddExp: Unable to parse the experience amount" );					
				}
				return;
			}
		}
		throw new InstructionParseException("Jobs Reborn job " + sParts[1] + " does not exist" );
    }

    @Override
    public void run(String playerID) 
    {
		Player oPlayer =  PlayerConverter.getPlayer(playerID);
		
		List<JobProgression> oJobs = Jobs.getPlayerManager().getJobsPlayer(oPlayer).getJobProgression();
		for (JobProgression oJob : oJobs) 
		{
			if (oJob.getJob().getName().equalsIgnoreCase(sJobName))
			{
				//User has the job, return true
				oJob.addExperience(nAddExperience);
			}
		}
    }
}
