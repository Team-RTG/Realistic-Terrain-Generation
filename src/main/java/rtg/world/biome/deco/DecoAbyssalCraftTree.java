package rtg.world.biome.deco;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.minecraftforge.event.terraingen.TerrainGen;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE;

import com.shinoow.abyssalcraft.api.block.ACBlocks;

import rtg.api.world.RTGWorld;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.tree.abyssalcraft.WorldGenTreeACDarkwood;

/**
 * @author WhichOnesPink
 */
public class DecoAbyssalCraftTree extends DecoTree {

    private TreeType treeType;

    public DecoAbyssalCraftTree() {

        super();

        /**
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.setLoops(1);
        this.setStrengthFactorForLoops(0f);
        this.setStrengthNoiseFactorForLoops(false);
        this.setStrengthNoiseFactorXForLoops(false);
        this.setTreeType(TreeType.DARKWOOD);
        this.setDistribution(new DecoTree.Distribution(100f, 5f, 0.8f));
        this.setTreeCondition(TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE);
        this.setTreeConditionNoise(0f);
        this.setTreeConditionChance(1);
        this.setMinY(63); // No underwater trees by default.
        this.setMaxY(230); // Sensible upper height limit by default.
        this.setLogBlock(Blocks.LOG.getDefaultState());
        this.setLeavesBlock(Blocks.LEAVES.getDefaultState());
        this.setMinSize(2);
        this.setMaxSize(4);

        this.addDecoTypes(DecoType.TREE);
    }

    @Override
    public boolean properlyDefined() {

        return true;
    }

    @Override
    public void generate(RealisticBiomeBase biome, RTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.allowed) {

            if (TerrainGen.decorate(rtgWorld.world, rand, new BlockPos(worldX, 0, worldZ), TREE)) {

                float noise = rtgWorld.simplex.noise2(worldX / this.distribution.noiseDivisor, worldZ / this.distribution.noiseDivisor) * this.distribution.noiseFactor + this.distribution.noiseAddend;

                int loopCount = this.loops;
                loopCount = (this.strengthFactorForLoops > 0f) ? (int) (this.strengthFactorForLoops * strength) : loopCount;
                loopCount = (this.strengthNoiseFactorForLoops) ? (int) (noise * strength) : loopCount;
                loopCount = (this.strengthNoiseFactorXForLoops) ? (int) (noise * this.strengthFactorForLoops * strength) : loopCount;
                for (int i = 0; i < loopCount; i++) {
                    int intX = worldX + rand.nextInt(16) + 8;
                    int intZ = worldZ + rand.nextInt(16) + 8;
                    int intY = rtgWorld.world.getHeight(new BlockPos(intX, 0, intZ)).getY();

                    switch (this.treeType) {

                        case DARKWOOD:

                            if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand, strength)) {

                                WorldGenerator worldgenerator = new WorldGenTreeACDarkwood(6 + rand.nextInt(6), 10 + rand.nextInt(10), ACBlocks.darklands_oak_wood.getDefaultState(), ACBlocks.darklands_oak_leaves.getDefaultState());
                                worldgenerator.generate(rtgWorld.world, rand, new BlockPos(intX, intY, intZ));
                            }

                            break;

                        default:
                            break;
                    }
                }
            }
        }
    }

    public enum TreeType {
        DARKWOOD;
    }

    public DecoAbyssalCraftTree setTreeType(TreeType treeType) {

        this.treeType = treeType;
        return this;
    }
}
