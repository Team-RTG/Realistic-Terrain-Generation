package rtg.world.biome.deco;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.minecraftforge.event.terraingen.TerrainGen;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CACTUS;

import rtg.api.world.RTGWorld;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenCacti;

/**
 * @author WhichOnesPink
 */
public class DecoCactus extends DecoBase {

    public int loops;
    public int chance;
    public float strengthFactor;
    public int maxY;
    public boolean sandOnly;
    public IBlockState soilBlock;

    public DecoCactus() {

        super();

        /*
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.loops = 1;
        this.chance = 1;
        this.maxY = 255; // No height limit by default.
        this.strengthFactor = 0f; // The higher the value, the more there will be.
        this.sandOnly = false;
        this.soilBlock = Blocks.SAND.getDefaultState();

        this.addDecoTypes(DecoType.CACTUS);
    }

    @Override
    public void generate(RealisticBiomeBase biome, RTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.allowed) {

            if (TerrainGen.decorate(rtgWorld.world, rand, new BlockPos(worldX, 0, worldZ), CACTUS)) {

                WorldGenerator worldGenerator = new WorldGenCacti(this.sandOnly, 0, this.soilBlock);

                int loopCount = this.loops;
                loopCount = (this.strengthFactor > 0f) ? (int) (this.strengthFactor * strength) : loopCount;
                for (int i = 0; i < loopCount * 10; i++) {
                    int intX = worldX + rand.nextInt(16);// + 8;
                    int intY = rand.nextInt(this.maxY);
                    int intZ = worldZ + rand.nextInt(16);// + 8;

                    if (intY <= this.maxY && rand.nextInt(this.chance) == 0) {
                        worldGenerator.generate(rtgWorld.world, rand, new BlockPos(intX, intY, intZ));
                    }
                }
            }
        }
    }
}
