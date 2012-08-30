package WWRockCavern;

import java.lang.Runnable;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.methods.widget.DepositBox;
import org.powerbot.game.api.util.Time;

public class GetWidgets extends Strategy implements Runnable {

	@Override
	public void run()
	{
		if (Game.getClientState() != 11 && Game.getClientState() != 9)
		{
			Cons.stats = "Closeing Widgets.";
			Widgets.get(906, 186).click(true);
			Time.sleep(1450, 1650);
		} else
		if (Camera.getPitch() <= 50)
		{
			Cons.stats = "Set Camera North.";
			Camera.setNorth();
			Camera.setPitch(100);
		} else
		if (!Walking.isRunEnabled())
		{
			Cons.stats = "Set User Running.";
			Walking.setRun(true);
			Time.sleep(1450, 1650);
		} else
		if (Widgets.get(18).validate())
		{
			Cons.stats = "Closeing Widgets.";
			Widgets.get(18).getChild(36).getChild(4).click(true);
			Time.sleep(1450, 1650);
		} else
		if (Widgets.get(266).validate())
		{
			Cons.stats = "Closeing Widgets.";
			Widgets.get(266).getChild(0).click(true);
			Time.sleep(1450, 1650);
		} else
		if (Widgets.get(892).validate())
		{
			Cons.stats = "Closeing Widgets.";
			Widgets.get(892).getChild(15).click(true);
			Time.sleep(1450, 1650);
		}
		if (!DepositBox.isOpen())
		{
			if (Inventory.isFull())
			{
				Cons.goBankNow = true;
			}
		}
	}

	@Override
	public boolean validate() 
	{
		return Widgets.get(892).validate() || Widgets.get(18).validate() || Game.getClientState() != 11
		|| Game.getClientState() != 9 || Widgets.get(266).validate() || !Walking.isRunEnabled()
		|| Camera.getPitch() <= 50;
	}

}
