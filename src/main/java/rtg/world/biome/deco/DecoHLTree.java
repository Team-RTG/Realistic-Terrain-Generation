package rtg.world.biome.deco;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenTreeCanopy;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;
import enhancedbiomes.helpers.TreeGen;

/**
 * 
 * @author WhichOnesPink
 *
 */
public class DecoHLTree extends DecoTree
{
	
	public TreeType treeType;
	public boolean thickTrunk;
	
	public DecoHLTree()
	{
		super();
		
		/**
		 * Default values.
		 * These can be overridden when configuring the Deco object in the realistic biome.
		 */
		this.loops = 1;
		this.strengthFactorForLoops = 0f;
		this.strengthNoiseFactorForLoops = false;
		this.strengthNoiseFactorXForLoops = false;
		this.treeType = TreeType.HIGHLANDS_SHRUB;
		this.distribution = new DecoTree.Distribution(100f, 5f, 0.8f);
		this.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		this.treeConditionNoise = 0f;
		this.treeConditionChance = 1;
		this.minY = 1; // No lower height limit by default.
		this.maxY = 255; // No upper height limit by default.
		this.logBlock = Blocks.log;
		this.logMeta = (byte)0;
		this.leavesBlock = Blocks.leaves;
		this.leavesMeta = (byte)-1;
		this.minSize = 2;
		this.maxSize = 4;
		this.thickTrunk = false;
		
		this.addDecoTypes(DecoType.TREE);
	}

    @Override
    public boolean properlyDefined() {
        return true;
    }

	@Override
	public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river, boolean hasPlacedVillageBlocks)
	{
		if (this.allowed) {
			
			if (TerrainGen.decorate(world, rand, chunkX, chunkY, TREE)) {
				
				float noise = simplex.noise2(chunkX / this.distribution.noiseDivisor, chunkY / this.distribution.noiseDivisor) * this.distribution.noiseFactor + this.distribution.noiseAddend;

                int loopCount = this.loops;
                loopCount = (this.strengthFactorForLoops > 0f) ? (int)(this.strengthFactorForLoops * strength) : loopCount;
                loopCount = (this.strengthNoiseFactorForLoops) ? (int)(noise * strength) : loopCount;
                loopCount = (this.strengthNoiseFactorXForLoops) ? (int)(noise * this.strengthFactorForLoops * strength) : loopCount;
	            for (int i = 0; i < loopCount; i++)
	            {
	                int intX = chunkX + rand.nextInt(16) + 8;
	                int intZ = chunkY + rand.nextInt(16) + 8;
	                int intY = world.getHeightValue(intX, intZ);
	                
	            	switch (this.treeType)
	            	{
		            		
		            	case CANOPY:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand, strength)) {

		                        WorldGenerator worldgenerator = new WorldGenTreeCanopy(this.minSize, (this.maxSize - this.minSize), false, this.thickTrunk);
		                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
		                        worldgenerator.generate(world, rand, intX, intY, intZ);
		            		}
		            		
		            		break;
		            		
		            	case HIGHLANDS_SHRUB:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand, strength)) {

		                        WorldGenerator worldgenerator = new WorldGenHighlandsShrub(this.logMeta, this.leavesMeta);
		                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
		                        worldgenerator.generate(world, rand, intX, intY, intZ);
		            		}
		            		
		            		break;

		            	default:
		            		break;
	            	}
	            }
			}
		}
	}

	public enum TreeType
	{
		CANOPY,
		HIGHLANDS_SHRUB;
	}
}
