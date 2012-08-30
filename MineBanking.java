package WWRockCavern;

import java.lang.Runnable;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.methods.widget.DepositBox;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class MineBanking extends Strategy implements Runnable {

	@Override
	public void run()
	{
		Cons.timesInCombat = 0;
		Cons.userHP = Players.getLocal().getHpPercent();
		if (!Cons.BankOreArea.contains(Players.getLocal().getLocation()))
		{
			Cons.stats = "Walking To Bank Area.";
			Walking.walk(new Tile(3654, 5113, 0));
			Time.sleep(50, 100);
		} else
		{
			SceneObject Bank = SceneEntities.getNearest(Cons.pulleyLiftID);
			if (Bank != null)
			{
				if (Bank.isOnScreen())
				{
					Cons.stats = "Banking.";
					if (!DepositBox.isOpen())
					{
						Bank.interact("Deposit");
						Time.sleep(1450, 1650);
					} else
					{
						if (!Cons.canFish)
						{
							DepositBox.deposit(453, 28);
							Time.sleep(250, 550);
							DepositBox.deposit(444, 28);
						}
						if (Cons.canFish)
						DepositBox.deposit(15270, 28);
						DepositBox.close();
						Cons.goBankNow = false;
					}
				} else
				Camera.turnTo(Bank);
			}
		}
	}

	@Override
	public boolean validate() 
	{
		return Cons.start && Cons.mineArea.contains(Players.getLocal().getLocation()) && Cons.goBankNow;
	}

}