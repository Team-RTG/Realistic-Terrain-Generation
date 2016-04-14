package enhancedbiomes.helpers;

import net.minecraft.world.ColorizerFoliage;

public class ColorizerFoliageBirch extends ColorizerFoliage
{
    public static int birchColour = 8431445;
	
/* FIXME: STFU, Travis!
    public ColorizerFoliageBirch()
    {
    	
    }
*/

	/**
     * Gets the foliage color for birch type (metadata 2) trees
     */
    public static int getFoliageColorBirch()
    {
        return birchColour;
    }
}
