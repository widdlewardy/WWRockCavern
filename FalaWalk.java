package WWRockCavern;

import java.lang.Runnable;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class FalaWalk extends Strategy implements Runnable {

	@Override
	public void run()
	{
		Cons.userHP = Players.getLocal().getHpPercent();
		SceneObject Door = SceneEntities.getNearest(Cons.falaDoorID);
		SceneObject Stairs = SceneEntities.getNearest(Cons.falaStairsID);
		for (Item i : Inventory.getItems())
	    {
			if (i.getId() != 15263 && i.getId() != 15270 && i.getId() != 444 && i.getId() != 453 && i.getId() != 14664 && i.getId() != 11640)
			{
				Cons.stats = "Put Items On";
				i.getWidgetChild().click(true);
				Time.sleep(350, 550);
			}
	    }
		if (Cons.falaDoorArea.contains(Players.getLocal().getLocation()))
		{
			if (!Door.isOnScreen())
			{
				if (Stairs != null)
				{
					if (Stairs.isOnScreen())
					{
						Cons.stats = "Climb Down Stairs.";
						Stairs.interact("Climb-down");
						Time.sleep(1450, 1650);
					} else
					Camera.turnTo(Stairs);
				}
			} else
			{
				if (Door != null)
				{
					if (Door.isOnScreen())
					{
						Cons.stats = "Opening Door.";
						Door.interact("Open");
						Time.sleep(1450, 1650);
					} else
					Camera.turnTo(Door);
				}
			}
		} else
		{
			Cons.stats = "Walking To Falador Area.";
			Walking.newTilePath(Cons.falaWalkToDoor).traverse();
			Time.sleep(50, 150);
		}
	}

	@Override
	public boolean validate() 
	{
		return Cons.start && Cons.faladorArea.contains(Players.getLocal().getLocation());
	}

}
