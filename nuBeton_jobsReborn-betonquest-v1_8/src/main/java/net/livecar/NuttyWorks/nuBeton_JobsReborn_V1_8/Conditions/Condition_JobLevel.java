package net.livecar.NuttyWorks.nuBeton_JobsReborn_V1_8.Conditions;

import java.util.List;

import org.bukkit.entity.Player;

import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.container.Job;
import com.gamingmesh.jobs.container.JobProgression;

import pl.betoncraft.betonquest.InstructionParseException;
import pl.betoncraft.betonquest.api.Condition;
import pl.betoncraft.betonquest.utils.PlayerConverter;

public class Condition_JobLevel extends Condition
{
	private String sJobName;
	private int nMinLevel;
	private int nMaxLevel;
	
	public Condition_JobLevel(String packName, String instruction) throws InstructionParseException
	{
		super(packName, instruction);
		String[] sParts = instructions.split(" ");
		if (sParts.length < 4) {
			throw new InstructionParseException("Not enough arguments");
		}
		for (Job job : Jobs.getJobs()) 
		{
			if (job.getName().equalsIgnoreCase(sParts[1]))
			{
				sJobName = job.getName();
				try {
					this.nMinLevel = Integer.parseInt(sParts[2]);
					this.nMaxLevel = Integer.parseInt(sParts[3]);
				} catch (Exception err)
				{
					throw new InstructionParseException("NUJobs_Joblevel: Unable to parse the min or max level" );					
				}
				return;
			}
		}
		throw new InstructionParseException("Jobs Reborn job " + sParts[1] + " does not exist" );
	}

	public boolean check(String playerID)
	{
		Player oPlayer =  PlayerConverter.getPlayer(playerID);
		
		List<JobProgression> oJobs = Jobs.getPlayerManager().getJobsPlayer(oPlayer).getJobProgression();
		for (JobProgression oJob : oJobs) 
		{
			if (oJob.getJob().getName().equalsIgnoreCase(sJobName))
			{
				//User has the job, return true
				if (oJob.getLevel() >= nMinLevel && oJob.getLevel() <= nMaxLevel)
					return true;
			}
		}
		return false;
	}
}
