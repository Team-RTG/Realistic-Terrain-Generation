package rtg.api.world.deco;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.fml.common.eventhandler.Event;
import rtg.RTGConfig;
import rtg.api.event.DecorateBiomeEventRTG;
import rtg.api.util.BlockUtil;
import rtg.api.util.ChunkInfo;
import rtg.api.util.Logger;
import rtg.api.util.BlockUtil.MatchType;
import rtg.api.world.RTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.gen.feature.WorldGenShrubRTG;
import rtg.api.world.gen.feature.tree.rtg.TreeMaterials;
import rtg.api.world.gen.feature.tree.rtg.TreeDensityLimiter;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;

/**
 * Variable Trees
 * This class make trees of variable type and height based on a noise parameter from ChunkInfo
 * 
 */

abstract public class DecoVariableTree extends DecoTree {
	
	protected TreeRTG tallTree;
	protected TreeRTG mediumTree;
	protected TreeRTG smallTree;
	protected final TreeMaterials.Picker materialsPicker = new TreeMaterials.Picker();
	protected TreeMaterials materials ;
	
	protected int tallTreeMinimumHeight = 21; // shortest allowed tall tree
	protected int tallTreeMinimumVariability = 9; // this less 1 (Random.nextInt()) added to minimum for largest allowed medium tree
	protected int mediumTreeMinimumHeight = 12; // etc.
	protected int mediumTreeMinimumVariability = 5;
	protected int smallTreeMinimumHeight = 7;
	protected int smallTreeMinimumVariability = 3;
	protected int vanillaTreeMinimumHeight = 2;
	protected int vanillaTreeMinimumVariability = 2;
			
	protected float averageHeightSqrt = 4.4f; // average tree height square root; trees vary
	protected float heightNoiseVariability = 2f; // maximum change in average height up or down from noise
	                                               // can go up or down so range is twice this number
	protected float localHeightSqrtVariability = 0.25f; // similar but tree to tree;
	
	protected float saplingChance = .1f; // chance a tree will be shorter than expected
	
	public DecoVariableTree() {
		
	}
	
	public void  changeAverageHeightSqrt(float change) {
		averageHeightSqrt += change;
	}
	
	public void  changeHeightNoiseVariability(float change) {
		heightNoiseVariability += change;
	}
	
    @Override
    public void generate(final IRealisticBiome biome, final RTGWorld rtgWorld, final Random rand, final ChunkPos chunkPos, final float river, final boolean hasVillage, ChunkInfo chunkInfo) {
    	// duped from DecoTree to add debuggers

        final BlockPos offsetPos = getOffsetPos(chunkPos);
        /*
         * Determine how many trees we're going to try to generate (loopCount).
         * The actual number of trees that end up being generated could be *less* than this value,
         * depending on environmental conditions.
         */
        // TODO: [1.12] What is the point of deriving a noise value from static BlockPos within a chunk (population origin) and then applying
        //              it to a feature taking place at some other arbitrary place in the chunk. This seems nonsensical and makes needless
        //              calls to the noise generator. This should be replaced by a random amount.
        // Zeno: Noise generation is essential to make different regions have different characteristics. Why are people trying to
        // "simplify" out core aspects of RTG (here that unlike vanilla, every forest is NOT the same) without paying attention 
        // to the code and design intent?
        float noise = distribution.getValue(offsetPos, rtgWorld.treeDistributionNoise());
        int loopCount = (this.strengthFactorForLoops > 0f) ? (int) this.strengthFactorForLoops : this.loops;
        loopCount = (this.strengthNoiseFactorForLoops) ? (int) noise : loopCount;
        loopCount = (this.strengthNoiseFactorXForLoops) ? (int) (noise * this.strengthFactorForLoops) : loopCount;

        if (loopCount < 1) {
            return;
        }

        // Now let's check the configs to see if we should increase/decrease this value.
        loopCount = this.applyConfigMultipliers(loopCount, biome);

        if (loopCount < 1) {
            return;
        }

        /*
         * Since RTG posts a TREE event for each batch of trees it tries to generate (instead of one event per chunk),
         * we post this custom event so that we can pass the number of trees RTG expects to generate in each batch.
         *
         * This provides more contextual information to mods like Recurrent Complex, which can use the info to better
         * determine how to handle each batch of trees.
         *
         * Because the custom event extends DecorateBiomeEvent.Decorate, it still works with mods that don't need
         * the additional context.
         */
        //TODO [1.12] Trees should just generate how they do in the vanilla BiomeDecorator::genDecorations and use the Forge event.
        DecorateBiomeEventRTG.DecorateRTG event = new DecorateBiomeEventRTG.DecorateRTG(rtgWorld.world(), rand, offsetPos, Decorate.EventType.TREE, loopCount);
        MinecraftForge.TERRAIN_GEN_BUS.post(event);

        if (event.getResult() != Event.Result.DENY) {

            loopCount = event.getModifiedAmount();
            if (loopCount < 1) { return; }

            // TODO: [1.12] This should be done in #setLeavesBlock.
            DecoBase.tweakTreeLeaves(this, false, true);

            TreeDensityLimiter treesRemaining = new TreeDensityLimiter(loopCount);
            while (treesRemaining.notDone()) {
                final BlockPos pos = offsetPos.add(rand.nextInt(16), 0, rand.nextInt(16));
                int y = rtgWorld.world().getHeight(pos).getY();
                if (y <= this.maxY && y >= this.minY && isValidTreeCondition(noise, rand)) {

                    // If we're in a village, check to make sure the tree has extra room to grow to avoid corrupting the village.
                    if (hasVillage) {
                        if (BlockUtil.checkVerticalBlocks(MatchType.ALL, rtgWorld.world(), pos, -1, Blocks.FARMLAND) ||
                            !BlockUtil.checkAreaBlocks(MatchType.ALL_IGNORE_REPLACEABLE, rtgWorld.world(), pos, 2)) {
                            return;
                        }
                    }
                    // get a suitable tree Type
                    doVariableGenerate(rand,  chunkInfo, pos,y, treesRemaining);
                } else {
                	treesRemaining.allowed(1f, rand);
                }

            }
        }
        else if (RTGConfig.enableDebugging()) {
        }
    }


	public void doVariableGenerate(Random rand, ChunkInfo chunkInfo, BlockPos column, int y, TreeDensityLimiter treesRemaining) { 
		// TODO Auto-generated method stub
		
		float averageHeightIndex = chunkInfo.treeHeightNoiseValue();
		
		float noise = averageHeightIndex;
		// value is -1 to 1, so adjust to be in [2.5,5.5], the targeted range of height square *root*
		averageHeightIndex *= heightNoiseVariability;
		averageHeightIndex += averageHeightSqrt;
		// a little tree to tree variability. Math is different from noise math because noises are [-1,1] and randoms [0,1]
		float actualHeightIndex = averageHeightIndex + rand.nextFloat()*localHeightSqrtVariability*2f - localHeightSqrtVariability;
		// shrink down if high
		if (y>70) {
			actualHeightIndex -= ((float)(y-70))/20f;
			// no negatives!
			if (averageHeightIndex <0) {
				treesRemaining.occupy(0.3f);// use up a little to avoid infinite loops
				return;  //too small, no tree generated.
			}
		}
		// square for actual height
		int actualHeight = (int)(actualHeightIndex*actualHeightIndex);
		// occasional smaller saplings
		if (rand.nextFloat()<saplingChance&&actualHeight>vanillaTreeMinimumHeight) {
			actualHeight = vanillaTreeMinimumHeight + rand.nextInt(actualHeight-vanillaTreeMinimumHeight);
		}
		// the generate step is separated out because 
		doGenerate(chunkInfo.world(),rand,column.up(y),actualHeight,treesRemaining);
		return;
	}
	
	public void doGenerate(World world, Random rand, BlockPos pos, int actualHeight, TreeDensityLimiter treesRemaining) {
		if (actualHeight >tallTreeMinimumHeight+rand.nextInt(tallTreeMinimumVariability)) {
			this.generateTallTree(world, rand, pos, actualHeight, materials,treesRemaining);
		} else if (actualHeight >mediumTreeMinimumHeight+rand.nextInt(mediumTreeMinimumVariability)) {
			this.generateMediumTree(world, rand, pos, actualHeight, materials,treesRemaining);
		} else if (actualHeight >smallTreeMinimumHeight+rand.nextInt(smallTreeMinimumVariability)) {
			this.generateSmallTree(world, rand, pos, actualHeight, materials,treesRemaining);
		} else if (actualHeight >vanillaTreeMinimumHeight+rand.nextInt(vanillaTreeMinimumVariability)) {
			if (treesRemaining.allowed(0.5f, rand))  {
			    vanillaTree().generate(world, rand, pos);
			}
		} else {
			if (treesRemaining.allowed(0.7f, rand))  {
			    new WorldGenShrubRTG(actualHeight,materials.log,materials.leaves,false).generate(world, rand, pos);
			}
		}
		if (treesRemaining.notDone()==false&&treesRemaining.treesSoFar==0) {
			Logger.info("Empty: Height {} type {} ", actualHeight,this.getClass().getName());
		}
	return;
}
    @Override
    @Deprecated
    public boolean properlyDefined() {

        // override DecoTree because we don't have just one tree.
        return true;
    }
    
	private void generateTallTree(World world, Random random, BlockPos pos,int actualHeight, TreeMaterials materials, TreeDensityLimiter treesRemaining) {
		float proportionTrunk  = tallTree.getLowestVariableTrunkProportion() + random.nextFloat()*tallTree.getTrunkProportionVariability();
		int trunkHeight = (int)(proportionTrunk*(actualHeight-tallTree.getTrunkReserve()))+tallTree.getTrunkReserve();
		if (trunkHeight < 4) trunkHeight = 4;
		
		tallTree.setLogBlock(materials.log);
        tallTree.setLeavesBlock(materials.leaves);
        tallTree.setBranchBlock(materials.branches);
        tallTree.setTrunkSize(trunkHeight);
        tallTree.setCrownSize(actualHeight-trunkHeight);
        tallTree.setNoLeaves(false);
        if (treesRemaining.allowed(tallTree.estimatedSize(), random)) {
		    tallTree.generate(world, random, pos);
        }
	}
	
	private void generateMediumTree(World world, Random random, BlockPos pos,int actualHeight, TreeMaterials materials, TreeDensityLimiter treesRemaining) {
		float proportionTrunk  = mediumTree.getLowestVariableTrunkProportion() + random.nextFloat()*mediumTree.getTrunkProportionVariability();
		int trunkHeight = (int)(proportionTrunk*(actualHeight-mediumTree.getTrunkReserve()))+mediumTree.getTrunkReserve();
		if (trunkHeight < 4) trunkHeight = 4;
		
		mediumTree.setLogBlock(materials.log);
		mediumTree.setLeavesBlock(materials.leaves);
		mediumTree.setBranchBlock(materials.branches);
        mediumTree.setTrunkSize(trunkHeight);
        mediumTree.setCrownSize(actualHeight-trunkHeight);
        mediumTree.setNoLeaves(false);
        if (treesRemaining.allowed(mediumTree.estimatedSize(), random)) {
             mediumTree.generate(world, random, pos);
        }
	}
	
	private void generateSmallTree(World world, Random random, BlockPos pos,int actualHeight, TreeMaterials materials, TreeDensityLimiter treesRemaining) {		
		float proportionTrunk  = smallTree.getLowestVariableTrunkProportion() + random.nextFloat()*smallTree.getTrunkProportionVariability();
		int trunkHeight = (int)(proportionTrunk*(actualHeight-smallTree.getTrunkReserve()))+smallTree.getTrunkReserve();
		if (trunkHeight < 4) trunkHeight = 4;
	
	    smallTree.setLogBlock(materials.log);
		smallTree.setLeavesBlock(materials.leaves);
		smallTree.setBranchBlock(materials.branches);
		smallTree.setTrunkSize(trunkHeight);
	    smallTree.setCrownSize(actualHeight-trunkHeight+2);// need a bit more crown for this algo
	    smallTree.setNoLeaves(false);
	    if (treesRemaining.allowed(smallTree.estimatedSize(), random)){
		    smallTree.generate(world, random, pos);
	    }
	}
	
	public int largestVanillaTree() {return this.smallTreeMinimumHeight + this.smallTreeMinimumVariability -1;}

	protected WorldGenAbstractTree vanillaTree() {
		return new WorldGenTrees(false,4,materials.log,materials.leaves,false);
	}
}
