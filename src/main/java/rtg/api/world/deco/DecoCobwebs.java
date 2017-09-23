package rtg.api.world.deco;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenerator;

import rtg.api.util.RandomUtil;
import rtg.api.world.IRTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.gen.feature.WorldGenBlock;

/**
 * @author WhichOnesPink
 */
public class DecoCobwebs extends DecoBase {

    public DecoCobwebs() {

        super();

        this.addDecoTypes(DecoType.COBWEB);
    }

    @Override
    public String friendlyName() {
        return "Cobwebs";
    }

    @Override
    public void initConfig() {
        this.config().addProperty(this.config().MIN_Y).set(62);
        this.config().addProperty(this.config().MAX_Y).set(255);
        this.config().addProperty(this.config().CHANCE).set(10);
        this.config().addProperty(this.config().STRENGTH_FACTOR).set(2f);
        this.config().addProperty(this.config().ADJACENT_BLOCK).set(Blocks.AIR.getDefaultState());
        this.config().addProperty(this.config().MIN_ADJACENTS).set(1);
    }

    @Override
    public void generate(IRealisticBiome biome, IRTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.config().ALLOW.get()) {

            WorldGenerator worldGenerator = new WorldGenBlock(Blocks.WEB.getDefaultState(), Blocks.AIR.getDefaultState(), this.config().ADJACENT_BLOCK.get(), this.config().MIN_ADJACENTS.get());

            for (int l1 = 0; l1 < this.config().STRENGTH_FACTOR.get() * strength; ++l1) {
                int i1 = worldX + rand.nextInt(16);// + 8;
                int j1 = worldZ + rand.nextInt(16);// + 8;
                int k1 = RandomUtil.getRandomInt(rand, this.config().MIN_Y.get(), this.config().MAX_Y.get());

                if (rand.nextInt(this.config().CHANCE.get()) == 0) {
                    worldGenerator.generate(rtgWorld.world(), rand, new BlockPos(i1, k1, j1));
                }
            }
        }
    }
}
