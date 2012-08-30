package WWRockCavern;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.bot.event.MessageEvent;
import org.powerbot.game.bot.event.listener.MessageListener;
import org.powerbot.game.bot.event.listener.PaintListener;

@Manifest(authors = { "widdlewardy" }, name = "WW-RockCavern", description = "To Mine Coal|Gold Or Fish RockTail. Script In BETA",
version = 0.1, website = "")
public class WWRockCavern extends ActiveScript implements PaintListener, MouseListener, MessageListener {

	/*
	* ToDo: need to fix walking to ores.. like make an area around them.. same for fishing and banking.
	*/
	
	@Override
	protected void setup()
	{
		if (Game.isLoggedIn())
		{
			timer.reset();
			try
			{ rc1 = ImageIO.read(new URL("http://i1253.photobucket.com/albums/hh600/widdlewardy/a9c88992.jpg"));
			} catch (MalformedURLException e)
			{ e.printStackTrace(); }
			catch (IOException e)
			{ e.printStackTrace(); }
			try
			{ rc2 = ImageIO.read(new URL("http://i1253.photobucket.com/albums/hh600/widdlewardy/151da6e4.jpg"));
			} catch (MalformedURLException e)
			{ e.printStackTrace(); }
			catch (IOException e)
			{ e.printStackTrace(); }
			try
			{ rc3 = ImageIO.read(new URL("http://i1253.photobucket.com/albums/hh600/widdlewardy/0798d405.jpg"));
			} catch (MalformedURLException e)
			{ e.printStackTrace(); }
			catch (IOException e)
			{ e.printStackTrace(); }
			try
			{ rc4 = ImageIO.read(new URL("http://i1253.photobucket.com/albums/hh600/widdlewardy/29cca2e6.jpg"));
			} catch (MalformedURLException e)
			{ e.printStackTrace(); }
			catch (IOException e)
			{ e.printStackTrace(); }
			try
			{ rc5 = ImageIO.read(new URL("http://i1253.photobucket.com/albums/hh600/widdlewardy/f9d74cd9.jpg"));
			} catch (MalformedURLException e)
			{ e.printStackTrace(); }
			catch (IOException e)
			{ e.printStackTrace(); }
			Mouse.click(Widgets.get(751).getChild(7).getCentralPoint(), true);
			Cons.userHP = Players.getLocal().getHpPercent();
			provide(new GetWidgets());
			provide(new FalaWalk());
			provide(new HoleWalk());
			provide(new MineBanking());
			provide(new Mining());
			provide(new Fishing());
		}
	}

	@Override
	public void onRepaint(Graphics g)
	{
		int chatBoxX = Widgets.get(137).getChild(50).getAbsoluteX()-6;
		int chatBoxY = Widgets.get(137).getChild(50).getAbsoluteY()-7;
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (Widgets.get(748).getChild(3).getAbsoluteX() == 691
				&& Widgets.get(748).getChild(3).getAbsoluteY() == 65)
		{
			if (startExp == 0 || Cons.startLevel == 0)
			{
				if (Cons.canFish)
				{
					startExp = Skills.getExperience(Skills.FISHING);
					Cons.startLevel = Skills.getLevel(Skills.FISHING);
				}
				if (!Cons.canFish)
				{
					startExp = Skills.getExperience(Skills.MINING);
					Cons.startLevel = Skills.getLevel(Skills.MINING);
				}
			}
			if (Cons.canFish)
			{
				stat = timer.toElapsedString();
				skillLevel = Skills.getLevel(Skills.FISHING);
				if (skillLevel != 99)
				{
					skillLevelGained = Skills.getLevel(Skills.FISHING) - Cons.startLevel;
					skillExpLevel = Skills.getExperienceToLevel(Skills.FISHING, Skills.getLevel(Skills.FISHING)+1);
					skillExpAnHour = (int) ((skillExpGained) * 3600000D / (System.currentTimeMillis() - startTime));
					skillExpGained = Skills.getExperience(Skills.FISHING) - startExp;
					skillExpHour = (int) ((skillExpGained) * 3600000D / (System.currentTimeMillis() - startTime));
					if (skillExpAnHour > 0)
					{
						double hour = (int) Math.floor(skillExpLevel / skillExpHour);
						double minutes = ((skillExpLevel / skillExpHour) - hour) * 60;
						double seconds = (int) ((minutes - Math.floor(minutes)) * 60);
						skillNextLevel = (int) hour + ":" + (int) minutes + ":" + (int) seconds;
					}
				}
			}
			if (!Cons.canFish)
			{
				stat = timer.toElapsedString();
				skillLevel = Skills.getLevel(Skills.MINING);
				if (skillLevel != 99)
				{
					skillLevelGained = Skills.getLevel(Skills.MINING) - Cons.startLevel;
					skillExpLevel = Skills.getExperienceToLevel(Skills.MINING, Skills.getLevel(Skills.MINING)+1);
					skillExpAnHour = (int) ((skillExpGained) * 3600000D / (System.currentTimeMillis() - startTime));
					skillExpGained = Skills.getExperience(Skills.MINING) - startExp;
					skillExpHour = (int) ((skillExpGained) * 3600000D / (System.currentTimeMillis() - startTime));
					if (skillExpAnHour > 0)
					{
						double hour = (int) Math.floor(skillExpLevel / skillExpHour);
						double minutes = ((skillExpLevel / skillExpHour) - hour) * 60;
						double seconds = (int) ((minutes - Math.floor(minutes)) * 60);
						skillNextLevel = (int) hour + ":" + (int) minutes + ":" + (int) seconds;
					}
				}
			}
			long millis = System.currentTimeMillis() - startTime;
	        long hours = millis / (1000 * 60 * 60);
	        millis -= hours * (1000 * 60 * 60);
	        long minutes = millis /(1000 *60);
	        millis -= minutes * (1000 * 60);
	        long seconds = millis / 1000;
	        int profit = (coalOreCount * coalOrePrice) + (goldOreCount * goldOrePrice) + (rockTailCount * rockTailPrice);
	        int profitPerHour = 0;
	        float profitsec = 0;
	        if ((minutes > 0 || hours > 0 || seconds > 0) && profit > 0)
	        {
	        	profitsec = ((float) profit)/(float)(seconds + (minutes*60) + (hours*60*60));
	        }
	        float profitmin = profitsec * 60;
	        profitPerHour = Math.round(profitmin * 60);
	        g.setFont(font1);
	        g.setColor(color);
			if (Cons.scriptPart == 0)
			{
				if(rc1 != null)
				{
					g.drawImage(rc1, chatBoxX, chatBoxY, null);
				} else
				{
					try
					{ rc1 = ImageIO.read(new URL("http://i1253.photobucket.com/albums/hh600/widdlewardy/a9c88992.jpg"));
					} catch (MalformedURLException e)
					{ e.printStackTrace(); }
					catch (IOException e)
					{ e.printStackTrace(); }
				}
			}
			if (Cons.scriptPart == 1)
			{
				if(rc2 != null)
				{
					g.drawImage(rc2, chatBoxX, chatBoxY, null);
				} else
				{
					try
					{ rc2 = ImageIO.read(new URL("http://i1253.photobucket.com/albums/hh600/widdlewardy/151da6e4.jpg"));
					} catch (MalformedURLException e)
					{ e.printStackTrace(); }
					catch (IOException e)
					{ e.printStackTrace(); }
				}
				if (skillLevel != 99)
				{
			        g.drawString(""+skillNextLevel+"", 349, 488);
			        g.drawString(""+skillExpAnHour+"", 102, 486);
			        g.drawString(""+skillExpGained+"", 102, 475);
				} else
				{
			        g.drawString("MAX LEVEL", 349, 488);
			        g.drawString("MAX LEVEL", 102, 486);
			        g.drawString("MAX LEVEL", 102, 475);
				}
		        g.drawString(""+skillLevel+"", 349, 476);
		        g.drawString(""+profitPerHour+"", 102, 508);
		        g.drawString(""+profit+"", 102, 497);
		        //g.drawString(""+oreCount+"("+orePerHour+")", 138, 451);
			}
			if (Cons.scriptPart == 2)
			{
				if(rc3 != null)
				{
					g.drawImage(rc3, chatBoxX, chatBoxY, null);
				} else
				{
					try
					{ rc3 = ImageIO.read(new URL("http://i1253.photobucket.com/albums/hh600/widdlewardy/0798d405.jpg"));
					} catch (MalformedURLException e)
					{ e.printStackTrace(); }
					catch (IOException e)
					{ e.printStackTrace(); }
				}
		        g.drawString(""+skillLevel+"", 349, 476);
				if (skillLevel != 99)
			    g.drawString(""+skillNextLevel+"", 349, 488);
				else
			    g.drawString("MAX LEVEL", 349, 488);
				g.drawString(""+profitPerHour+"", 102, 508);
			}
			if (Cons.scriptPart == 3)
			{
				if(rc4 != null)
				{
					g.drawImage(rc4, chatBoxX, chatBoxY, null);
				} else
				{
					try
					{ rc4 = ImageIO.read(new URL("http://i1253.photobucket.com/albums/hh600/widdlewardy/29cca2e6.jpg"));
					} catch (MalformedURLException e)
					{ e.printStackTrace(); }
					catch (IOException e)
					{ e.printStackTrace(); }
				}
				if (skillLevel != 99)
				{
			        g.drawString(""+skillExpAnHour+"", 102, 486);
			        g.drawString(""+skillExpGained+"", 102, 475);
				} else
				{
			        g.drawString("MAX LEVEL", 102, 486);
			        g.drawString("MAX LEVEL", 102, 475);
				}
		        g.drawString(""+profitPerHour+"", 102, 508);
		        g.drawString(""+profit+"", 102, 497);
			}
			if (Cons.scriptPart == 4)
			{
				if(rc5 != null)
				{
					g.drawImage(rc5, chatBoxX, chatBoxY, null);
				} else
				{
					try
					{ rc5 = ImageIO.read(new URL("http://i1253.photobucket.com/albums/hh600/widdlewardy/f9d74cd9.jpg"));
					} catch (MalformedURLException e)
					{ e.printStackTrace(); }
					catch (IOException e)
					{ e.printStackTrace(); }
				}
		        g.setFont(font1);
				g.setColor(Color.red);
				if (!Cons.keepGifts)
				{
					g.drawLine(114, 465, 165, 465);
					g.drawLine(114, 470, 165, 470);
					g.drawLine(114, 475, 165, 475);
					g.drawLine(114, 480, 165, 480);
					g.drawLine(114, 485, 165, 485);
				}
		        g.setColor(color);
			}
	        g.drawString(""+Cons.stats+"", 54, 523);
	        g.drawString(""+stat+"", 420, 523);
		} else
		{
			g.setColor(Color.YELLOW); g.setFont(font);
			g.drawString("SETTING UP....IN FIXED MODE.",
			Widgets.get(137).getChild(50).getAbsoluteX()-4, Widgets.get(137).getChild(50).getAbsoluteY()-7);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		p = e.getPoint();
		if (Cons.scriptPart == 0)
		{
			if(fish.contains(p) || mine.contains(p))
			{
				if (fish.contains(p))
				{
					Cons.scriptPart = 1;
					Cons.canFish = true;
					Cons.start = true;
					return;
				}
				if (mine.contains(p))
				{
					Cons.scriptPart = 1;
					Cons.canFish = false;
					Cons.start = true;
					return;
				}
			}
		}
		if (Cons.scriptPart == 1)
		{
			if(fontColour.contains(p) || setting.contains(p) || openShopNow.contains(p) || hide.contains(p))
			{
				if (fontColour.contains(p))
				{
					Cons.scriptPart = 2;
					return;
				}
				if (setting.contains(p))
				{
					Cons.scriptPart = 4;
					return;
				}
				if (openShopNow.contains(p))
				{
					Cons.scriptPart = 3;
					return;
				}
				if (hide.contains(p))
				{
					//hidePrint = true;
				}
			}
		}
		if (Cons.scriptPart == 2)
		{
			if(fontColour.contains(p) || setting.contains(p) || openShopNow.contains(p) || hide.contains(p)
			|| colour1.contains(p) || colour2.contains(p) || colour3.contains(p)
			|| colour4.contains(p) || colour5.contains(p))
			{
				if (fontColour.contains(p))
				{ 
					Cons.scriptPart = 1;
					return;
				}
				if (setting.contains(p))
				{ 
					Cons.scriptPart = 4;
					return;
				}
				if (openShopNow.contains(p))
				{ 
					Cons.scriptPart = 3;
					return;
				}
				if (hide.contains(p))
				{ 
					//hidePrint = true;
				}
				if (colour1.contains(p))
				{ 
					color = new Color(255, 0, 0);
					Cons.scriptPart = 1;
					return;
				}
				if (colour2.contains(p))
				{
					color = new Color(255, 255, 0);
					Cons.scriptPart = 1;
					return;
				}
				if (colour3.contains(p))
				{
					color = new Color(0, 255, 0);
					Cons.scriptPart = 1;
					return;
				}
				if (colour4.contains(p))
				{ 
					color = new Color(0, 0, 255);
					Cons.scriptPart = 1;
					return;
				}
				if (colour5.contains(p))
				{
					color = new Color(255, 105, 180);
					Cons.scriptPart = 1;
					return;
				}
			}
		}
		if (Cons.scriptPart == 3)
		{
			if(fontColour.contains(p) || setting.contains(p) || openShopNow.contains(p)
			|| hide.contains(p) || doWhat1.contains(p) || doWhat2.contains(p))
			{
				if (fontColour.contains(p))
				{ 
					Cons.scriptPart = 2;
					return;
				}
				if (setting.contains(p))
				{ 
					Cons.scriptPart = 4;
					return;
				}
				if (openShopNow.contains(p))
				{ 
					Cons.scriptPart = 1;
					return;
				}
				if (hide.contains(p))
				{ 
					//hidePrint = true;
				}
				if (doWhat1.contains(p))
				{
					Cons.canFish = true;
					Cons.scriptPart = 1;
					return;
				}
				if (doWhat2.contains(p))
				{
					Cons.canFish = false;
					Cons.scriptPart = 1;
					return;
				}
			}
		}
		if (Cons.scriptPart == 4)
		{
			if(fontColour.contains(p) || setting.contains(p) || openShopNow.contains(p)
			|| hide.contains(p) || keep1.contains(p) || doWhat2.contains(p))
			{
				if (fontColour.contains(p))
				{ 
					Cons.scriptPart = 2;
					return;
				}
				if (setting.contains(p))
				{ 
					Cons.scriptPart = 1;
					return;
				}
				if (openShopNow.contains(p))
				{ 
					Cons.scriptPart = 3;
					return;
				}
				if (hide.contains(p))
				{ 
					//hidePrint = true;
				}
				if (keep1.contains(p))
				{
					if (Cons.keepGifts)
					{
						Cons.keepGifts = false;
					}
					else
					{
						Cons.keepGifts = true;
					}
					Cons.scriptPart = 1;
					return;
				}
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
	@Override
	public void messageReceived(MessageEvent e)
	{
		if (e.getMessage().contains("mine some"))
		{
			if (e.getMessage().contains("coal"))
			coalOreCount += 1;
			if (e.getMessage().contains("gold"))
			goldOreCount += 1;
		}
		if (e.getMessage().contains("mine two"))
		{
			if (e.getMessage().contains("coal"))
			coalOreCount += 2;
			if (e.getMessage().contains("gold"))
			goldOreCount += 2;
		}
		if (e.getMessage().contains("You catch a"))
		{
			rockTailCount += 1;
		}
	}
	
	public static Point p;
	public static double skillExpHour;
	public static final Timer timer = new Timer(0);
	public static long skillExpLevel, startTime = System.currentTimeMillis();
	public static String stat = "00:00:00:00", skillNextLevel = "00:00:00";
	public static int startExp, skillExpAnHour, skillLevel, skillLevelGained, skillExpGained;
	public static final Font font = new Font("Arial", 1, 30);
	public static Color color = new Color(255, 0, 0);
    private final Font font1 = new Font("Arial", 0, 12);
	private Rectangle fontColour = new Rectangle(86, 428, 71, 24);
	private Rectangle setting = new Rectangle(218, 428, 70, 23);
	private Rectangle openShopNow = new Rectangle(349, 428, 72, 24);
	private Rectangle colour1 = new Rectangle(49, 454, 49, 33);
	private Rectangle colour2 = new Rectangle(103, 453, 49, 34);
	private Rectangle colour3 = new Rectangle(158, 453, 50, 35);
	private Rectangle colour4 = new Rectangle(213, 453, 49, 34);
	private Rectangle colour5 = new Rectangle(266, 453, 50, 34);
	private Rectangle doWhat1 = new Rectangle(231, 454, 109, 38);
	private Rectangle doWhat2 = new Rectangle(372, 453, 113, 40);
	private Rectangle keep1 = new Rectangle(117, 458, 47, 40);
	public static Rectangle fish = new Rectangle(60, 451, 175, 54);
	public static Rectangle mine = new Rectangle(282, 450, 177, 56);
	public static Rectangle hide = new Rectangle(505, 387, 14, 17);
	public static BufferedImage rc1, rc2, rc3, rc4, rc5;
	public static int coalOrePrice = 268, coalOreCount, goldOrePrice = 249, goldOreCount,
	rockTailPrice = 1792, rockTailCount;
	
}
