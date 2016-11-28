package rtg.world.biome.deco;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.minecraftforge.event.terraingen.TerrainGen;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS;

import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenGrass;

/**
 * @author WhichOnesPink
 */
public class DecoGrassDoubleTallgrass extends DecoBase {

    public float strengthFactor;
    public int maxY;
    public int loops;
    public int grassChance;
    public int doubleGrassChance;

    public DecoGrassDoubleTallgrass() {

        super();

        /*
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.maxY = 255; // No height limit by default.
        this.strengthFactor = 0f; // Not sure why it was done like this, but... the higher the value, the more there will be.
        this.loops = 1;
        this.grassChance = 0; // 50% chance for both grass & double grass by default.
        this.doubleGrassChance = 0; // 50% chance for both grass & double grass by default. (If set, overrides grass chance.)

        this.addDecoTypes(DecoType.GRASS, DecoType.GRASS_DOUBLE);
    }

    @Override
    public void generate(RealisticBiomeBase biome, RTGWorld rtgWorld, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.allowed) {

            World world = rtgWorld.world;
            Random rand = rtgWorld.rand;
            OpenSimplexNoise simplex = rtgWorld.simplex;

            if (TerrainGen.decorate(world, rand, new BlockPos(worldX, 0, worldZ), GRASS)) {

                WorldGenerator worldGenerator = null;
                if (this.doubleGrassChance > 0) {

                    if (rand.nextInt(this.doubleGrassChance) == 0) {

                        worldGenerator = new WorldGenGrass(Blocks.DOUBLE_PLANT.getStateFromMeta(2), 2);
                    }
                    else {

                        worldGenerator = new WorldGenGrass(Blocks.TALLGRASS.getStateFromMeta(1), 1);
                    }
                }
                else if (this.grassChance > 0) {

                    if (rand.nextInt(this.grassChance) == 0) {

                        worldGenerator = new WorldGenGrass(Blocks.TALLGRASS.getStateFromMeta(1), 1);
                    }
                    else {

                        worldGenerator = new WorldGenGrass(Blocks.DOUBLE_PLANT.getStateFromMeta(2), 2);
                    }
                }
                else {

                    if (rand.nextBoolean()) {

                        worldGenerator = new WorldGenGrass(Blocks.TALLGRASS.getStateFromMeta(1), 1);
                    }
                    else {

                        worldGenerator = new WorldGenGrass(Blocks.DOUBLE_PLANT.getStateFromMeta(2), 2);
                    }
                }

                this.loops = (this.strengthFactor > 0f) ? (int) (this.strengthFactor * strength) : this.loops;
                for (int i = 0; i < this.loops; i++) {
                    int intX = worldX + rand.nextInt(16) + 8;
                    int intY = rand.nextInt(this.maxY);
                    int intZ = worldZ + rand.nextInt(16) + 8;

                    if (intY <= this.maxY) {

                        worldGenerator.generate(world, rand, new BlockPos(intX, intY, intZ));
                    }
                }
            }
        }
    }
}
