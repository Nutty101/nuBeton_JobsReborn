package net.livecar.NuttyWorks.nuBeton_JobsReborn_V1_8.Conditions;

import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.container.Job;

import pl.betoncraft.betonquest.InstructionParseException;
import pl.betoncraft.betonquest.api.Condition;

public class Condition_JobFull extends Condition
{
	private String sJobName;
	
	public Condition_JobFull(String packName, String instruction) throws InstructionParseException
	{
		super(packName, instruction);
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

	public boolean check(String playerID)
	{
		for (Job job : Jobs.getJobs()) 
		{
			if (job.getName().equalsIgnoreCase(sJobName))
			{
				if (job.getMaxSlots() == null)
					return false;
				if (job.getTotalPlayers() >= job.getMaxSlots())
					return true;
			}
		}
		return false;
	}
}
