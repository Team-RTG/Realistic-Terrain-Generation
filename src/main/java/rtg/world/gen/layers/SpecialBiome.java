package rtg.world.gen.layers;

public class SpecialBiome // extends //BOPOverworldBiome redirects to the bop api dunno where this needs to be then...
{
	 /**Smaller numbers zoom in the noise field (biomes are less common)*/
	public double zoom;
	/**The strength the field must reach to replace the biome. Larger numbers result in smaller patches.*/
	public double threshold;
	
	public SpecialBiome(int biomeID) 
	{
	//	super(biomeID);  //turned off cause it otherwise give error cause it doesnt do anything yet
	}
}
