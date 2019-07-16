package rtg.api.world.deco;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.ChunkPos;

import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.event.terraingen.TerrainGen;

import rtg.api.world.RTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.gen.feature.WorldGenBlock;


/**
 * @author WhichOnesPink
 */
// TODO: [1.12] This Deco is only used in Roofed Forest and only generates with Roofed Forest Trees. It might be prudent to just add random cobwebs in the tree generator.
public class DecoCobwebs extends DecoBase {

    private static final IBlockState WEB = Blocks.WEB.getDefaultState();
    private static final IBlockState AIR = Blocks.AIR.getDefaultState();

    private float strengthFactor; // Higher = more/bigger boulders.
    private int minY; // Lower height restriction.
    private int maxY; // Upper height restriction.
    private int chance; // Higher = more rare.
    private IBlockState adjacentBlock;
    private int minAdjacents;

    public DecoCobwebs() {

        super();

        /**
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.setStrengthFactor(2f);
        this.setMinY(1); // No lower height limit by default.
        this.setMaxY(255); // No upper height limit by default.
        this.setChance(10);
        this.setAdjacentBlock(Blocks.AIR.getDefaultState());
        this.setMinAdjacents(1);

        this.addDecoTypes(DecoType.COBWEB);
    }

    @Override
    public void generate(final IRealisticBiome biome, final RTGWorld rtgWorld, final Random rand, final ChunkPos chunkPos, final float river, final boolean hasVillage) {

        if (TerrainGen.decorate(rtgWorld.world(), rand, chunkPos, Decorate.EventType.CUSTOM)) {
            for (int i = 0; i < this.strengthFactor * strength; ++i) {
                if (rand.nextInt(this.chance) == 0) {
                    new WorldGenBlock(WEB, AIR, this.adjacentBlock, this.minAdjacents)
                        .generate(rtgWorld.world(), rand, getOffsetPos(chunkPos).add(rand.nextInt(16), getRangedRandom(rand, this.minY, this.maxY), rand.nextInt(16)));
                }
            }
        }
    }

    public float getStrengthFactor() {

        return strengthFactor;
    }

    public DecoCobwebs setStrengthFactor(float strengthFactor) {

        this.strengthFactor = strengthFactor;
        return this;
    }

    public int getMinY() {

        return minY;
    }

    public DecoCobwebs setMinY(int minY) {

        this.minY = minY;
        return this;
    }

    public int getMaxY() {

        return maxY;
    }

    public DecoCobwebs setMaxY(int maxY) {

        this.maxY = maxY;
        return this;
    }

    public int getChance() {

        return chance;
    }

    public DecoCobwebs setChance(int chance) {

        this.chance = chance;
        return this;
    }

    public IBlockState getAdjacentBlock() {

        return adjacentBlock;
    }

    public DecoCobwebs setAdjacentBlock(IBlockState adjacentBlock) {

        this.adjacentBlock = adjacentBlock;
        return this;
    }

    public int getMinAdjacents() {

        return minAdjacents;
    }

    public DecoCobwebs setMinAdjacents(int minAdjacents) {

        this.minAdjacents = minAdjacents;
        return this;
    }
}
