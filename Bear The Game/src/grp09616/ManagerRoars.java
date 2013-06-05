package grp09616;

import java.util.Random;

public class ManagerRoars
{
	public static final char[] ROAR_KEYS = {'q', 'a', 'e', 'd'}; 
	public static final char NULL_CHAR = ' ';
	
	private static Random r;
	private static int roarLevel;
	private static char roarChar;
	
	public static void init()
	{
		r = new Random();
		roarLevel = 0;
		roarChar = NULL_CHAR;
	}
	
	public static boolean roar(char ch)
	{
		if(roarChar == NULL_CHAR)
		{
			roarChar = getRandomRoarChar();
		}
		if(ch == roarChar)
		{
			roarLevel++;
			roarChar = getRandomRoarChar();
			return true;
		}
		return false;
	}
	
	private static char getRandomRoarChar()
	{
		return ROAR_KEYS[r.nextInt(ROAR_KEYS.length)];
	}
	
	public static char getRoarChar()
	{
		return roarChar;
	}
	
	public static int getRoarLevel()
	{
		return roarLevel;
	}
	
	public static int finishRoar()
	{
		int output = roarLevel;
		roarLevel = 0;
		roarChar = NULL_CHAR;
		return output;
	}
}
