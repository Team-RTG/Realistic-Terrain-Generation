package rtg.world.biome.deco;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.RandomUtil;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenBlock;

/**
 * 
 * @author WhichOnesPink
 *
 */
public class DecoCobwebs extends DecoBase
{
    
	public float strengthFactor; // Higher = more/bigger boulders.
	public int minY; // Lower height restriction.
	public int maxY; // Upper height restriction.
	public int chance; // Higher = more rare.
	public Block adjacentBlock;
	public byte adjacentBlockMeta;
	public int minAdjacents;
	
	public DecoCobwebs()
	{
		super();
		
		/**
		 * Default values.
		 * These can be overridden when configuring the Deco object in the realistic biome.
		 */
		this.strengthFactor = 2f;
		this.minY = 1; // No lower height limit by default.
		this.maxY = 255; // No upper height limit by default.
		this.chance = 10;
		this.adjacentBlock = Blocks.air;
		this.adjacentBlockMeta = (byte)0;
		this.minAdjacents = 1;
		
		this.addDecoTypes(DecoType.COBWEB);
	}
	
	@Override
	public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river, boolean hasPlacedVillageBlocks)
	{
		if (this.allowed) {
			
			WorldGenerator worldGenerator = new WorldGenBlock(Blocks.web, (byte)0, Blocks.air, (byte)0, this.adjacentBlock, this.adjacentBlockMeta, this.minAdjacents);
			
            for (int l1 = 0; l1 < this.strengthFactor * strength; ++l1)
            {
                int i1 = chunkX + rand.nextInt(16);// + 8;
                int j1 = chunkY + rand.nextInt(16);// + 8;
                int k1 = RandomUtil.getRandomInt(rand, this.minY, this.maxY);
                
                if (rand.nextInt(this.chance) == 0) {
                	worldGenerator.generate(world, rand, i1, k1, j1);
                }
            }
		}
	}
}
