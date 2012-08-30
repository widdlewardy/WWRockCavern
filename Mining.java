package WWRockCavern;

import java.lang.Runnable;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class Mining extends Strategy implements Runnable {

	@Override
	public void run()
	{
		if (!Cons.mineOreArea.contains(Players.getLocal().getLocation()))
		{
			Cons.stats = "Walking To Mine Area. 1";
			if (!Players.getLocal().isMoving())
			Walking.walk(new Tile(Random.nextInt(3651, 3670), Random.nextInt(5088, 5095), 0));
			Time.sleep(750, 1350);
		} else
		{
			if (!Players.getLocal().isMoving())
			{
				RockOres = null;
				if (Cons.startLevel >= 77)
				RockOres = SceneEntities.getNearest(5999);
				if (Cons.startLevel >= 80)
				RockOres = SceneEntities.getNearest(Cons.mineOreID);
			}
			if (!Cons.mineOreArea.contains(RockOres.getLocation()))
			{
				Cons.stats = "Walking To Mine Area. 2";
				if (!Players.getLocal().isMoving())
				Walking.walk(new Tile(Random.nextInt(3651, 3670), Random.nextInt(5088, 5095), 0));
				Time.sleep(750, 1350);
			} else
			{
				if (Cons.userHP == Players.getLocal().getHpPercent())
				{
					if (RockOres != null)
					{
						if (Cons.mineRock1.contains(RockOres.getLocation())
						&& !Cons.mineRock1.contains(Players.getLocal().getLocation()))
						{
							Cons.stats = "Walking To Gold Mine Area 1";
							Walking.walk(new Tile(3640, 5094, 0));
							Time.sleep(750, 1350);
						} else
						if (Cons.mineRock2.contains(RockOres.getLocation())
						&& !Cons.mineRock2.contains(Players.getLocal().getLocation()))
						{
							Cons.stats = "Walking To Gold Mine Area 2";
							Walking.walk(new Tile(3667, 5078, 0));
							Time.sleep(750, 1350);
						} else
						if (Cons.mineRock3.contains(RockOres.getLocation())
						&& !Cons.mineRock3.contains(Players.getLocal().getLocation()))
						{
							Cons.stats = "Walking To Coal Mine Area 1";
							Walking.walk(new Tile(3665, 5092, 0));
							Time.sleep(750, 1350);
						} else
						if (Cons.mineRock4.contains(RockOres.getLocation())
						&& !Cons.mineRock4.contains(Players.getLocal().getLocation()))
						{
							Cons.stats = "Walking To Coal Mine Area 2";
							Walking.walk(new Tile(3673, 5099, 0));
							Time.sleep(750, 1350);
						} else
						if (Cons.mineRock5.contains(RockOres.getLocation())
						&& !Cons.mineRock5.contains(Players.getLocal().getLocation()))
						{
							Cons.stats = "Walking To Coal Mine Area 3";
							Walking.walk(new Tile(3689, 5106, 0));
							Time.sleep(750, 1350);
						} else
						{
							if (Players.getLocal().getAnimation() != -1)
							{
								Cons.stats = "Waiting On Mine.";
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
										if (i.getId() != 444 && i.getId() != 453 && i.getId() != 15263 && i.getId() != 15270
										&&  i.getId() != 1275 && i.getId() != 1265 && i.getId() != 1267 && i.getId() != 1269
										&&  i.getId() != 1273 && i.getId() != 1271 && i.getId() != 1623 && i.getId() != 1619
										&&  i.getId() != 1617 && i.getId() != 1621 && i.getId() != 15259 && i.getId() != 13661)
										{
											Cons.stats = "Drop Items Now";
											i.getWidgetChild().interact("drop");
											Time.sleep(250, 275);
										}
									} else
									{
										if (i.getId() != 444 && i.getId() != 453 && i.getId() != 15263 && i.getId() != 15270
										&&  i.getId() != 1275 && i.getId() != 1265 && i.getId() != 1267 && i.getId() != 1269
										&&  i.getId() != 1273 && i.getId() != 1271 && i.getId() != 1623 && i.getId() != 1619
										&&  i.getId() != 1617 && i.getId() != 1621 && i.getId() != 15259 && i.getId() != 13661
										&&  i.getId() != 14664 && i.getId() != 11640)
										{
											Cons.stats = "Drop Items Now";
											i.getWidgetChild().interact("drop");
											Time.sleep(250, 275);
										}
									}
							    }
						    } else
						    {
								if (System.currentTimeMillis() >= waitTime + 2750)
								{
									Cons.stats = "Clicking On Mine.";
									if (!RockOres.interact("Mine"))
									RockOres.click(true);
									waitTime2 = System.currentTimeMillis();
									waitTime = System.currentTimeMillis();
								}
						    }
						}
					}
				} else
				{
					if (Cons.timesInCombat < 4)
					{
						if (System.currentTimeMillis() >= waitTime2 + 3150)
						{
							Cons.timesInCombat++;
							waitTime2 = System.currentTimeMillis();
							Cons.stats = "In Combat. '"+Cons.timesInCombat+"'";
							if (Players.getLocal().getLocation().getX() >= 3662
							&&  Players.getLocal().getLocation().getX() <= 3669
							&&  Players.getLocal().getLocation().getY() >= 5088
							&&  Players.getLocal().getLocation().getY() <= 5093)
							{
								if (Players.getLocal().getLocation().getX() >= 3664
								&&  Players.getLocal().getLocation().getX() <= 3666
								&&  Players.getLocal().getLocation().getY() == 5092)
								Walking.getClosestOnMap(new Tile(3667, 5090, 0)).interact("Walk here");
								if (Players.getLocal().getLocation().getX() == 3667
								&&  Players.getLocal().getLocation().getY() == 5090)
								Walking.getClosestOnMap(new Tile(Random.nextInt(3664, 3665), 5089, 0)).interact("Walk here");
								if (Players.getLocal().getLocation().getX() >= 3664
								&&  Players.getLocal().getLocation().getX() <= 3666
								&&  Players.getLocal().getLocation().getY() == 5089)
								Walking.getClosestOnMap(new Tile(3663, 5091, 0)).interact("Walk here");
								if (Players.getLocal().getLocation().getX() == 3663
								&&  Players.getLocal().getLocation().getY() == 5091)
								Walking.getClosestOnMap(new Tile(Random.nextInt(3665, 3666), 5092, 0)).interact("Walk here");
							}
							if (Players.getLocal().getLocation().getX() >= 3672
							&&  Players.getLocal().getLocation().getX() <= 3677
							&&  Players.getLocal().getLocation().getY() >= 5096
							&&  Players.getLocal().getLocation().getY() <= 5102)
							{
								if (Players.getLocal().getLocation().getX() == 3673
								&&  Players.getLocal().getLocation().getY() >= 5098
								&&  Players.getLocal().getLocation().getY() <= 5100)
								Walking.getClosestOnMap(new Tile(3675, 5101, 0)).interact("Walk here");
								if (Players.getLocal().getLocation().getX() >= 3674
								&&  Players.getLocal().getLocation().getX() <= 3675
								&&  Players.getLocal().getLocation().getY() == 5101)
								Walking.getClosestOnMap(new Tile(3676, Random.nextInt(5098, 5099), 0)).interact("Walk here");
								if (Players.getLocal().getLocation().getX() == 3676
								&&  Players.getLocal().getLocation().getY() >= 5098
								&&  Players.getLocal().getLocation().getY() <= 5100)
								Walking.getClosestOnMap(new Tile(3674, 5097, 0)).interact("Walk here");
								if (Players.getLocal().getLocation().getX() >= 3674
								&&  Players.getLocal().getLocation().getX() <= 3675
								&&  Players.getLocal().getLocation().getY() == 5097)
								Walking.getClosestOnMap(new Tile(3673, Random.nextInt(5099, 5100), 0)).interact("Walk here");
							}
							if (Players.getLocal().getLocation().getX() >= 3685
							&&  Players.getLocal().getLocation().getX() <= 3692
							&&  Players.getLocal().getLocation().getY() >= 5105
							&&  Players.getLocal().getLocation().getY() <= 5111)
							{
								if (Players.getLocal().getLocation().getX() != 3689
								&&  Players.getLocal().getLocation().getY() != 5106)
								Walking.getClosestOnMap(new Tile(3689, 5106, 0)).interact("Walk here");
								else
								Cons.goBankNow = true;
							}
							if (Players.getLocal().getLocation().getX() >= 3670
							&&  Players.getLocal().getLocation().getX() <= 3666
							&&  Players.getLocal().getLocation().getY() >= 5073
							&&  Players.getLocal().getLocation().getY() <= 5073)
							{
								if (Players.getLocal().getLocation().getX() != 3669
								&&  Players.getLocal().getLocation().getY() != 5076)
								Walking.getClosestOnMap(new Tile(3669, 5076, 0)).interact("Walk here");
								else
								Cons.goBankNow = true;
							}
							if  (Players.getLocal().getLocation().getX() >= 3635
							&&  Players.getLocal().getLocation().getX() <= 3641
							&&  Players.getLocal().getLocation().getY() >= 5092
							&&  Players.getLocal().getLocation().getY() <= 5097)
							{
								if (Players.getLocal().getLocation().getX() == 3636
								&&  Players.getLocal().getLocation().getY() >= 5094
								&&  Players.getLocal().getLocation().getY() <= 5095)
								Walking.getClosestOnMap(new Tile(Random.nextInt(3638, 3639), 5096, 0)).interact("Walk here");
								if (Players.getLocal().getLocation().getX() >= 3637
								&&  Players.getLocal().getLocation().getX() <= 3639
								&&  Players.getLocal().getLocation().getY() == 5096)
								Walking.getClosestOnMap(new Tile(3640, 5094, 0)).interact("Walk here");
								if (Players.getLocal().getLocation().getX() == 3640
								&&  Players.getLocal().getLocation().getY() >= 5094
								&&  Players.getLocal().getLocation().getY() <= 5095)
								Walking.getClosestOnMap(new Tile(Random.nextInt(3637, 3638), 5093, 0)).interact("Walk here");
								if (Players.getLocal().getLocation().getX() >= 3637
								&&  Players.getLocal().getLocation().getX() <= 3639
								&&  Players.getLocal().getLocation().getY() == 5093)
								Walking.getClosestOnMap(new Tile(3636, 5095, 0)).interact("Walk here");
							}
							waitTime = System.currentTimeMillis();
						}
					} else
					Cons.goBankNow = true;
					Cons.userHP = Players.getLocal().getHpPercent();
				}
			}
		}
	}

	@Override
	public boolean validate() 
	{
		return Cons.start && Cons.mineArea.contains(Players.getLocal().getLocation()) && !Cons.canFish && !Cons.goBankNow;
	}

	public long waitTime, waitTime2;
	public SceneObject RockOres = null;
}