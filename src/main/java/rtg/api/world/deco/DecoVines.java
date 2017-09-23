package rtg.api.world.deco;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockVine;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.minecraftforge.event.terraingen.TerrainGen;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS;

import rtg.api.world.IRTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.gen.feature.WorldGenVinesRTG;

/**
 * @author WhichOnesPink
 */
public class DecoVines extends DecoBase {

    private int loops;
    private float strengthFactor;
    private Block vineBlock;
    private int minY;
    private int maxY;
    private PropertyBool propNorth;
    private PropertyBool propEast;
    private PropertyBool propSouth;
    private PropertyBool propWest;

    protected WorldGenerator worldGenerator;

    public DecoVines() {

        super();

        this.setLoops(1);
        this.setStrengthFactor(0f);
        this.setMinY(63);
        this.setMaxY(200);
        this.vineBlock = Blocks.VINE;
        this.propNorth = BlockVine.NORTH;
        this.propEast = BlockVine.EAST;
        this.propSouth = BlockVine.SOUTH;
        this.propWest = BlockVine.WEST;

        this.addDecoTypes(DecoType.VINE);
    }

    @Override
    public boolean properlyDefined() {

        try {
            IBlockState vineTest = this.vineBlock.getDefaultState()
                .withProperty(this.propNorth, true)
                .withProperty(this.propEast, false)
                .withProperty(this.propSouth, false)
                .withProperty(this.propWest, false);
        }
        catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public void generate(IRealisticBiome biome, IRTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.allowed) {

            if (TerrainGen.decorate(rtgWorld.world(), rand, new BlockPos(worldX, 0, worldZ), GRASS)) {

                this.worldGenerator = new WorldGenVinesRTG(this.vineBlock, this.maxY, this.propNorth, this.propEast, this.propSouth, this.propWest);

                this.setLoops((this.strengthFactor > 0f) ? (int) (this.strengthFactor * strength) : this.loops);
                for (int i = 0; i < this.loops; i++) {

                    int intX = worldX + rand.nextInt(16);// + 8;
                    int intZ = worldZ + rand.nextInt(16);// + 8;
                    int intY = this.minY;

                    worldGenerator.generate(rtgWorld.world(), rand, new BlockPos(intX, intY, intZ));
                }
            }
        }
    }

    public int getLoops() {

        return loops;
    }

    public DecoVines setLoops(int loops) {

        this.loops = loops;
        return this;
    }

    public float getStrengthFactor() {

        return strengthFactor;
    }

    public DecoVines setStrengthFactor(float strengthFactor) {

        this.strengthFactor = strengthFactor;
        return this;
    }

    public Block getVineBlock() {

        return vineBlock;
    }

    public DecoVines setVineBlock(Block vineBlock) {

        this.vineBlock = vineBlock;
        return this;
    }

    public int getMinY() {

        return minY;
    }

    public DecoVines setMinY(int minY) {

        this.minY = minY;
        return this;
    }

    public int getMaxY() {

        return maxY;
    }

    public DecoVines setMaxY(int maxY) {

        this.maxY = maxY;
        return this;
    }

    public PropertyBool getPropNorth() {

        return propNorth;
    }

    public DecoVines setPropNorth(PropertyBool propNorth) {

        this.propNorth = propNorth;
        return this;
    }

    public PropertyBool getPropEast() {

        return propEast;
    }

    public DecoVines setPropEast(PropertyBool propEast) {

        this.propEast = propEast;
        return this;
    }

    public PropertyBool getPropSouth() {

        return propSouth;
    }

    public DecoVines setPropSouth(PropertyBool propSouth) {

        this.propSouth = propSouth;
        return this;
    }

    public PropertyBool getPropWest() {

        return propWest;
    }

    public DecoVines setPropWest(PropertyBool propWest) {

        this.propWest = propWest;
        return this;
    }
}
