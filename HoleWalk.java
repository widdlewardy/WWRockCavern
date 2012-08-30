package WWRockCavern;

import java.lang.Runnable;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class HoleWalk extends Strategy implements Runnable {

	@Override
	public void run()
	{
		Cons.userHP = Players.getLocal().getHpPercent();
		SceneObject Rope = SceneEntities.getNearest(Cons.holeRopeID);
		if (Cons.holeRopeArea.contains(Players.getLocal().getLocation()))
		{
			if (Rope != null)
			{
				if (Rope.isOnScreen())
				{
					Cons.stats = "Climb Down Rope.";
					Rope.interact("Climb");
					Time.sleep(1450, 1650);
				} else
				Camera.turnTo(Rope);
			}
		} else
		{
			Cons.stats = "Walking To Hole Area.";
			Walking.newTilePath(Cons.holeWalkToRope).traverse();
			Time.sleep(50, 150);
		}
	}

	@Override
	public boolean validate() 
	{
		return Cons.start && Cons.holeArea.contains(Players.getLocal().getLocation());
	}

}