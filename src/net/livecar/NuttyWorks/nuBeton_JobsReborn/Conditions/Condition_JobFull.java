package net.livecar.NuttyWorks.nuBeton_JobsReborn.Conditions;

import java.util.List;

import org.bukkit.entity.Player;

import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.container.Job;
import com.gamingmesh.jobs.container.JobProgression;

import pl.betoncraft.betonquest.InstructionParseException;
import pl.betoncraft.betonquest.api.Condition;
import pl.betoncraft.betonquest.utils.PlayerConverter;

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
