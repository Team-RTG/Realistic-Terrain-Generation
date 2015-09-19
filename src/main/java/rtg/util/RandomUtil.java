package rtg.util;

public class RandomUtil
{
	public static int getRandomInt(int intStart, int intEnd)
	{
		return (int)( (Math.random() * intEnd) + intStart );
	}
}