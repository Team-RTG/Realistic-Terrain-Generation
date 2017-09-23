package rtg.api.world.deco;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

import net.minecraftforge.event.terraingen.TerrainGen;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS;

import rtg.api.world.IRTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.gen.feature.WorldGenGrass;

/*
 * @author WhichOnesPink
 */
public class DecoGrass extends DecoBase {

    private IBlockState[] randomGrassBlocks;
    private byte[] randomGrassMetas;

    private boolean useRandomGrass;
    private static final int MAX_LOOPS = 10;

    private IBlockState block;
    private int meta;
    private WorldGenGrass grassGenerator;

    public DecoGrass() {

        super();

        /*
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.block = Blocks.TALLGRASS.getStateFromMeta(1);
        this.meta = 1;
        this.randomGrassBlocks = new IBlockState[]{};
        this.randomGrassMetas = new byte[]{};
        this.useRandomGrass = (this.randomGrassBlocks.length > 0 && this.randomGrassBlocks.length == this.randomGrassMetas.length);

        this.addDecoTypes(DecoType.GRASS);
        grassGenerator = new WorldGenGrass(block, meta);
    }

    public DecoGrass(int meta) {

        this();
        this.meta = meta;
        grassGenerator = new WorldGenGrass.SingleType(block, meta);
    }

    public DecoGrass(IBlockState block) {

        this();
        this.block = block;
        grassGenerator = new WorldGenGrass.SingleType(block, meta);
    }

    public DecoGrass(IBlockState[] randomBlocks, byte[] randomMetas) {

        this();
        if (randomBlocks.length != randomMetas.length) {
            throw new RuntimeException("Mismatch in block and metadata arrays for DecoGrass");
        }
        this.randomGrassBlocks = randomBlocks;
        this.randomGrassMetas = randomMetas;
        grassGenerator = new WorldGenGrass.RandomType(randomGrassBlocks, randomGrassMetas);
    }

    @Override
    public String friendlyName() {
        return "Grass";
    }

    @Override
    public void initConfig() {
        this.config().addProperty(this.config().MIN_Y).set(63);
        this.config().addProperty(this.config().MAX_Y).set(255);
        this.config().addProperty(this.config().LOOPS).set(1);
        this.config().addProperty(this.config().NOT_EQUALS_ZERO_CHANCE).set(1);
        this.config().addProperty(this.config().CHANCE).set(1);
        this.config().addProperty(this.config().STRENGTH_FACTOR).set(0f);
    }

    @Override
    public void generate(IRealisticBiome biome, IRTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.config().ALLOW.get()) {

            if (TerrainGen.decorate(rtgWorld.world(), rand, new BlockPos(worldX, 0, worldZ), GRASS)) {

                int loops = (this.config().STRENGTH_FACTOR.get() > 0f) ? (int) (this.config().STRENGTH_FACTOR.get() * strength) : this.config().LOOPS.get();
                loops = (loops > this.MAX_LOOPS) ? this.MAX_LOOPS : loops;
                for (int i = 0; i < loops * 64; i++) {
                    int intX = worldX + rand.nextInt(16);// + 8;
                    int intY = this.config().MIN_Y.get() + (rand.nextInt(this.config().MAX_Y.get() - this.config().MIN_Y.get()) + 1);
                    int intZ = worldZ + rand.nextInt(16);// + 8;

                    //Do we want to choose a random grass?
                    if (this.useRandomGrass) {

                        //this.block = this.randomGrassBlocks[rand.nextInt(this.randomGrassBlocks.length)];
                        //this.meta = this.randomGrassMetas[rand.nextInt(this.randomGrassMetas.length)];
                    }

                    if (this.config().NOT_EQUALS_ZERO_CHANCE.get() > 1) {

                        if (intY >= this.config().MIN_Y.get() && intY <= this.config().MAX_Y.get() && rand.nextInt(this.config().NOT_EQUALS_ZERO_CHANCE.get()) != 0) {
                            grassGenerator.generate(rtgWorld.world(), rand, new BlockPos(intX, intY, intZ));
                        }
                    }
                    else {

                        if (intY >= this.config().MIN_Y.get() && intY <= this.config().MAX_Y.get() && rand.nextInt(this.config().CHANCE.get()) == 0) {
                            grassGenerator.generate(rtgWorld.world(), rand, new BlockPos(intX, intY, intZ));
                        }
                    }
                }
            }
        }
    }

    public IBlockState[] getRandomGrassBlocks() {

        return randomGrassBlocks;
    }

    public DecoGrass setRandomGrassBlocks(IBlockState[] randomGrassBlocks) {

        this.randomGrassBlocks = randomGrassBlocks;
        return this;
    }

    public byte[] getRandomGrassMetas() {

        return randomGrassMetas;
    }

    public DecoGrass setRandomGrassMetas(byte[] randomGrassMetas) {

        this.randomGrassMetas = randomGrassMetas;
        return this;
    }
}
