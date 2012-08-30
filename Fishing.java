package WWRockCavern;

import java.lang.Runnable;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.powerbot.game.api.wrappers.node.Item;

public class Fishing extends Strategy implements Runnable {

	public static int oldX = 0, oldY = 0;
	
	@Override
	public void run()
	{
		if (!Widgets.get(548).getChild(38).isOnScreen() && Integer.parseInt(Widgets.get(748, 8).getText()) > 0)
		{
			oldX = Players.getLocal().getLocation().getX();
			oldY = Players.getLocal().getLocation().getY();
		}
		if (!Cons.fishfoodArea.contains(Players.getLocal().getLocation()))
		{
			Cons.stats = "Walking To Fish Area. 1";
			Walking.walk(new Tile(Random.nextInt(3625, 3637), 5086, 0));
			Time.sleep(750, 1350);
		} else
		{
			if (Inventory.getItem(15263) != null && Inventory.getItem(15263).getStackSize() > 5)
			{
				if (!Players.getLocal().isMoving())
				{
					getFishs = null;
					getFishs = NPCs.getNearest(Cons.fishID);
				}
				if (!Cons.fishfoodArea.contains(getFishs.getLocation()))
				{
					Cons.stats = "Walking To Fish Area. 2";
					Walking.walk(new Tile(Random.nextInt(3625, 3637), 5086, 0));
					Time.sleep(750, 1350);
				} else
				{
					if (Cons.userHP == Players.getLocal().getHpPercent())
					{
						if (getFishs != null)
						{
							if (getFishs.isOnScreen())
							{
								if (Players.getLocal().getAnimation() != -1)
								{
									Cons.stats = "Waiting On Fish.";
									waitTime = System.currentTimeMillis();
									for (Item i : Inventory.getItems())
								    {
										if (i.getId() == 24154)
										{
											Cons.stats = "Claim Spin Now";
											i.getWidgetChild().interact("Claim spin");
											Time.sleep(250, 275);
										}
										if (!Cons.keepGifts)
										{
											if (i.getId() != 444 && i.getId() != 453 && i.getId() != 15263 && i.getId() != 15270)
											{
												Cons.stats = "Drop Items Now";
												i.getWidgetChild().interact("drop");
												Time.sleep(250, 275);
											}
										} else
										{
											if (i.getId() != 444 && i.getId() != 453 && i.getId() != 15263 && i.getId() != 15270 && i.getId() != 14664 && i.getId() != 11640)
											{
												Cons.stats = "Drop Items Now";
												i.getWidgetChild().interact("drop");
												Time.sleep(250, 275);
											}
										}
								    }
									if (System.currentTimeMillis() >= waitTime2 + 95000)
									{
										Cons.stats = "Clicking On Fish.";
										if (!getFishs.interact("Bait"))
										getFishs.click(true);
										waitTime2 = System.currentTimeMillis();
									}
								} else
								{
									if (System.currentTimeMillis() >= waitTime + 2750)
									{
										Cons.stats = "Clicking On Fish.";
										if (!getFishs.interact("Bait"))
										getFishs.click(true);
										waitTime = System.currentTimeMillis();
									}
								}
							} else
							{
								Cons.stats = "Move To Next Fish.";
								if (!Players.getLocal().isMoving())
								Walking.walk(new Tile(Random.nextInt(3625, 3637), 5086, 0));
								else
								Walking.walk(getFishs);
								Time.sleep(750, 1350);
							}
						}
					} else
					{
						if (Cons.timesInCombat < 1)
						{
							Cons.timesInCombat++;
							Cons.stats = "In Combat. '"+Cons.timesInCombat+"'";
							waitTime = System.currentTimeMillis();
						} else
						Cons.goBankNow = true;
						Cons.userHP = Players.getLocal().getHpPercent();
					}
				}
			} else
			{
				loot = null;
				loot = GroundItems.getNearest(15263);
				if (loot != null && loot.isOnScreen())
				{
					Cons.stats = "Pick Up My Loot";
					if (loot.interact("Take"))
					loot.click(true);
				} else
				{
					Cons.stats = "Walking To Loot";
					Walking.walk(new Tile(oldX, oldY, 0));
					Time.sleep(750, 1350);
				}
			}
		}
	}

	@Override
	public boolean validate() 
	{
		if (Cons.canFish && !Cons.goBankNow)
		{
			return Game.getClientState() != 12 && Cons.start
			&& Cons.mineArea.contains(Players.getLocal().getLocation());
		}
		return false;
	}

	public long waitTime, waitTime2;
	public NPC getFishs = null;
	public GroundItem loot = null;
}
