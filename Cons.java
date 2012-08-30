package WWRockCavern;

import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;

public class Cons {
	
	public static String stats = "Loading";
	public static boolean start = false, canFish = false, keepGifts = false;
	public static int scriptPart = 0, startLevel = 0;
	/*
	 * Falador Stuff 
	*/
	public static final int falaDoorID = 11714;
	public static final int falaStairsID = 30944;
	public static final Area faladorArea = new Area(new Tile[]
	{
		new Tile(2934, 3394, 0), new Tile(3065, 3392, 0),
		new Tile(3065, 3349, 0), new Tile(2933, 3349, 0)
	});
	public static final Area falaDoorArea = new Area(new Tile[]
	{
		new Tile(3055, 3383, 0), new Tile(3062, 3383, 0),
		new Tile(3062, 3372, 0), new Tile(3055, 3372, 0)
	});
	public static final Tile[] falaWalkToDoor = new Tile[]
	{
		new Tile(2964, 3379, 0), new Tile(2978, 3377, 0),
		new Tile(2988, 3370, 0), new Tile(2998, 3364, 0),
		new Tile(3011, 3363, 0), new Tile(3023, 3365, 0), 
		new Tile(3033, 3368, 0), new Tile(3045, 3369, 0),
		new Tile(3055, 3369, 0), new Tile(3060, 3374, 0)
	};
	/*
	 * Hole Stuff 
	*/
	public static final int holeRopeID = 45077;
	public static final Area holeArea = new Area(new Tile[]
	{
		new Tile(2996, 9836, 0), new Tile(3060, 9836, 0),
		new Tile(3060, 9757, 0), new Tile(2996, 9757, 0)
	});
	public static final Area holeRopeArea = new Area(new Tile[]
	{
		new Tile(3006, 9836, 0), new Tile(3019, 9836, 0),
		new Tile(3019, 9825, 0), new Tile(3006, 9825, 0)
	});
	public static final Tile[] holeWalkToRope = new Tile[]
	{
		new Tile(3059, 9776, 0), new Tile(3048, 9784, 0),
		new Tile(3042, 9792, 0), new Tile(3042, 9800, 0),
		new Tile(3038, 9808, 0), new Tile(3038, 9816, 0), 
		new Tile(3039, 9824, 0), new Tile(3038, 9832, 0),
		new Tile(3031, 9829, 0), new Tile(3018, 9830, 0),
		new Tile(3012, 9831, 0)
	};
	/*
	 * Mine/Fish Stuff 
	*/
	public static final int mineOreID[] = { 5999,45076 };
	public static final int fishID = 8842;
	public static int userHP = 0, timesInCombat = 0, walkWays = 0;
	public static final Area mineArea = new Area(new Tile[]
	{
		new Tile(3606, 5162, 0), new Tile(3695, 5162, 0),
		new Tile(3695, 5076, 0), new Tile(3606, 5076, 0)
	});
	public static final Area mineOreArea = new Area(new Tile[]
	{
		new Tile(3631, 5124, 0), new Tile(3693, 5124, 0),
		new Tile(3693, 5076, 0), new Tile(3631, 5076, 0)
	});
	public static final Area mineRock1 = new Area(new Tile[]
	{
		new Tile(3634, 5099, 0), new Tile(3642, 5099, 0),
		new Tile(3642, 5091, 0), new Tile(3634, 5091, 0)
	});
	public static final Area mineRock2 = new Area(new Tile[]
	{
		new Tile(3664, 5080, 0), new Tile(3671, 5080, 0),
		new Tile(3671, 5073, 0), new Tile(3664, 5073, 0)
	});
	public static final Area mineRock3 = new Area(new Tile[]
	{
		new Tile(3661, 5094, 0), new Tile(3670, 5094, 0),
		new Tile(3670, 5087, 0), new Tile(3661, 5087, 0)
	});
	public static final Area mineRock4 = new Area(new Tile[]
	{
		new Tile(3671, 5103, 0), new Tile(3678, 5103, 0),
		new Tile(3678, 5095, 0), new Tile(3671, 5095, 0)
	});
	public static final Area mineRock5 = new Area(new Tile[]
	{
		new Tile(3684, 5111, 0), new Tile(3692, 5111, 0),
		new Tile(3692, 5104, 0), new Tile(3684, 5104, 0)
	});
	public static final Area fishfoodArea = new Area(new Tile[]
	{
		new Tile(3616, 5095, 0), new Tile(3656, 5095, 0),
		new Tile(3656, 5079, 0), new Tile(3616, 5079, 0)
	});
	public static final Tile[] mineWalkToOre = new Tile[]
	{
		new Tile(3651, 5122, 0), new Tile(3657, 5119, 0),
		new Tile(3658, 5115, 0), new Tile(3658, 5111, 0),
		new Tile(3659, 5107, 0), new Tile(3660, 5103, 0), 
		new Tile(3659, 5099, 0), new Tile(3659, 5095, 0),
		new Tile(3658, 5093, 0)
	};
	
	public static final Tile[] fishWalkWay1 = new Tile[]
	{
		new Tile(3618, 5089, 0), new Tile(3626, 5090, 0),
		new Tile(3634, 5090, 0), new Tile(3642, 5092, 0),
		new Tile(3650, 5094, 0), new Tile(3657, 5102, 0), 
		new Tile(3657, 5109, 0), new Tile(3654, 5113, 0)
	};
	public static final Tile[] fishWalkWay2 = new Tile[]
	{
		new Tile(3629, 5084, 0), new Tile(3640, 5091, 0),
		new Tile(3649, 5094, 0), new Tile(3659, 5102, 0),
		new Tile(3657, 5109, 0), new Tile(3654, 5113, 0)
	};
	public static final Tile[] fishWalkWay3 = new Tile[]
	{
		new Tile(3644, 5083, 0), new Tile(3646, 5090, 0),
		new Tile(3656, 5096, 0), new Tile(3657, 5103, 0),
		new Tile(3657, 5110, 0), new Tile(3654, 5113, 0)
	};
	/*
	 * Bank Stuff 
	*/
	public static final int pulleyLiftID = 45079;
	public static boolean goBankNow = false;
	public static final Area BankOreArea = new Area(new Tile[]
	{
		new Tile(3648, 5118, 0), new Tile(3660, 5118, 0),
		new Tile(3660, 5108, 0), new Tile(3648, 5108, 0)
	});
}
