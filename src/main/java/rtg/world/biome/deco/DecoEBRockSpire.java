package rtg.world.biome.deco;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;
import enhancedbiomes.world.gen.WorldGenRockSpire;

/**
 * 
 * @author WhichOnesPink
 *
 */
public class DecoEBRockSpire extends DecoBase
{

	public float strengthFactor;
	public int maxY;
	public int loops;
	public int chance;
	public int notEqualsZeroChance;
	public Block[] materials;
	public byte[] meta;
	public int height;
	
	public DecoEBRockSpire()
	{
		super();
		
		/**
		 * Default values.
		 * These can be overridden when configuring the Deco object in the realistic biome.
		 */
		this.maxY = 255; // No height limit by default.
		this.strengthFactor = 0f; // Not sure why it was done like this, but... the higher the value, the more there will be.
		this.loops = 1;
		this.chance = 1;
		this.notEqualsZeroChance = 1;
		this.materials = new Block[]{};
		this.meta = new byte[]{};
		this.height = 10;
		
		this.addDecoTypes(DecoType.ROCK_SPIRE);
	}
	
	@Override
	public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
	{
		if (this.allowed) {

			WorldGenerator worldGenerator = new WorldGenRockSpire(this.materials, this.meta, this.height);
			
			this.loops = (this.strengthFactor > 0f) ? (int)(this.strengthFactor * strength) : this.loops;
            for (int i = 0; i < this.loops; i++)
            {
                int intX = chunkX + rand.nextInt(16) + 8;
                int intZ = chunkY + rand.nextInt(16) + 8;
                int intY = world.getTopSolidOrLiquidBlock(intX, intZ);
				
                if (this.notEqualsZeroChance > 1) {
                	
	                if (intY <= this.maxY && rand.nextInt(this.notEqualsZeroChance) != 0) {
	                	
	                    worldGenerator.generate(world, rand, intX, intY, intZ);
	                }
                }
                else {
                	
	                if (intY <= this.maxY && rand.nextInt(this.chance) == 0) {

	                    worldGenerator.generate(world, rand, intX, intY, intZ);
	                }
                }
            }
		}
	}
}
