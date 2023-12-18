package rtg.api.world.deco;

/*
 * Author @Zeno410
 * This class chooses a oak material or spruce material for a conifer tree using the materials picker and then calls the appropriate variable treee
 */
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
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
import rtg.api.world.gen.feature.tree.rtg.TreeMaterials;
import rtg.api.world.gen.feature.tree.rtg.TreeDensityLimiter;

public class DecoVariableTaigaTree extends DecoTree {
	
	protected DecoVariableSpruce oakTree = new DecoVariableSpruce();
	protected DecoVariableSpruce spruceTree = new DecoVariableSpruce();
	protected final TreeMaterials.Picker materialsPicker = new TreeMaterials.Picker();
	protected final TreeMaterials.Chooser chooser = TreeMaterials.inSpruceForest;
	
	public DecoVariableTaigaTree() {
		oakTree.materials = materialsPicker.oak;
		// increase tree variability
		oakTree.localHeightSqrtVariability = 1f;
		spruceTree.localHeightSqrtVariability = 1f;
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
        float loopCount = (this.strengthFactorForLoops > 0f) ? (int) this.strengthFactorForLoops : this.loops;
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
        DecorateBiomeEventRTG.DecorateRTG event = new DecorateBiomeEventRTG.DecorateRTG(rtgWorld.world(), rand, offsetPos, Decorate.EventType.TREE, (int)loopCount);
        MinecraftForge.TERRAIN_GEN_BUS.post(event);

        if (event.getResult() != Event.Result.DENY) {

            int newLoopCount = event.getModifiedAmount();
            if ((int)loopCount != newLoopCount) {
            	loopCount = newLoopCount;
            }
            if (loopCount < 0.3) { 
            	Logger.info("FractionalTree","");
            	return; 
            }

            // TODO: [1.12] This should be done in #setLeavesBlock.
            DecoBase.tweakTreeLeaves(this, false, true);

            int tries = 0;
            TreeDensityLimiter treesRemaining = new TreeDensityLimiter(loopCount);
            while (treesRemaining.notDone()) {
                final BlockPos pos = offsetPos.add(rand.nextInt(16), 0, rand.nextInt(16));
                tries ++;
            	if (tries>100) {
            		throw new RuntimeException();
            	}
                int y = rtgWorld.world().getHeight(pos).getY();
                if (y <= this.maxY && y >= this.minY && isValidTreeCondition(noise, rand)) {

                    // If we're in a village, check to make sure the tree has extra room to grow to avoid corrupting the village.
                	
                    if (hasVillage) {
                        if (BlockUtil.checkVerticalBlocks(MatchType.ALL, rtgWorld.world(), pos, -1, Blocks.FARMLAND)) {// ||
                            //!BlockUtil.checkAreaBlocks(MatchType.ALL_IGNORE_REPLACEABLE, rtgWorld.world(), pos, 2)) {
                            return;
                        }
                    }
                    // get a suitable tree Type
                    TreeMaterials materials = chooser.materialFor(pos,rtgWorld,rand);
                    // using spruce design for the oak
                    if (materials.equals(materialsPicker.oak)) {
                        oakTree.doVariableGenerate(rand, chunkInfo, pos,y, treesRemaining);
                    } else if (materials.equals(materialsPicker.spruce)) {
                        spruceTree.doVariableGenerate(rand, chunkInfo, pos,y, treesRemaining);
                    } else  {
                    	throw new RuntimeException();
                    }
                } else {
                	treesRemaining.allowed(1f, rand);
                }

            }
        }
        else if (RTGConfig.enableDebugging()) {
            Logger.debug("Tree generation was cancelled @ ChunkPos{}", chunkPos);
        }
    }
    @Override
    @Deprecated
    public boolean properlyDefined() {

        // override DecoTree because we don't have just one tree.
        return true;
    }
    
    public void changeAvgHeightSqrt(float change) {
    	oakTree.averageHeightSqrt += change;
    	spruceTree.averageHeightSqrt += change;
    }
    
    public void changeHeightVariability(float change) {
    	oakTree.heightNoiseVariability += change;
    	spruceTree.heightNoiseVariability += change;
    }

}

