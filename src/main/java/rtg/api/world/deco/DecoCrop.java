package rtg.api.world.deco;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.event.terraingen.TerrainGen;
import rtg.api.world.RTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.gen.feature.WorldGenCrops;

import java.util.Random;


/**
 * @author lightningo7
 */
public class DecoCrop extends DecoBase {

    // TODO: [1.12] This should be an Enum
    private int type; // This can the number 0,1,2,3.
    private int size; //Higher = larger fields.
    private int density; //Higher = Crops in fields closer together.
    private int height; //Higher = Crops on more y levels - When higher tends to be less dense.
    private float strengthFactor; // Higher = More frequent spawns.
    private int minY; // Lower height restriction.
    private int maxY; // Upper height restriction.
    private int chance; // Higher = more rare.
    private boolean water;

    public DecoCrop() {

        super();

        /*
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.type = 3;
        this.size = 5;//DO NOT PUT HIGHER THAN 30
        this.density = 50;
        this.height = 2;
        this.setStrengthFactor(2f);
        this.setMinY(63); // Sensible lower height limit by default.
        this.setMaxY(255); // No upper height limit by default.
        this.setChance(10); //The higher the number the less common it will be
        this.water = true; //whether or not to spawn water with the crops

        this.addDecoTypes(DecoType.WHEAT);
    }

    @Override
    public void generate(final IRealisticBiome biome, final RTGWorld rtgWorld, final Random rand, final ChunkPos chunkPos, final float river, final boolean hasVillage) {

        if (this.chance > 1 && rand.nextInt(this.chance) == 0 && TerrainGen.decorate(rtgWorld.world(), rand, chunkPos, Decorate.EventType.CUSTOM)) {
            for (int i = 0; i < this.strengthFactor; ++i) {
                final BlockPos pos = getOffsetPos(chunkPos).add(rand.nextInt(16), 0, rand.nextInt(16));
                int y = rtgWorld.world().getHeight(pos).getY();
                if (y >= this.minY && y <= this.maxY) {
                    new WorldGenCrops(type, size, density, height, water)
                        .generate(rtgWorld.world(), rand, pos.up(y));
                }
            }
        }
    }

    public int getType() {

        return type;
    }

    public DecoCrop setType(int type) {

        this.type = type;
        return this;
    }

    public int getSize() {

        return size;
    }

    public DecoCrop setSize(int size) {

        this.size = size;
        return this;
    }

    public int getDensity() {

        return density;
    }

    public DecoCrop setDensity(int density) {

        this.density = density;
        return this;
    }

    public int getHeight() {

        return height;
    }

    public DecoCrop setHeight(int height) {

        this.height = height;
        return this;
    }

    public float getStrengthFactor() {

        return strengthFactor;
    }

    public DecoCrop setStrengthFactor(float strengthFactor) {

        this.strengthFactor = strengthFactor;
        return this;
    }

    public int getMinY() {

        return minY;
    }

    public DecoCrop setMinY(int minY) {

        this.minY = minY;
        return this;
    }

    public int getMaxY() {

        return maxY;
    }

    public DecoCrop setMaxY(int maxY) {

        this.maxY = maxY;
        return this;
    }

    public int getChance() {

        return chance;
    }

    public DecoCrop setChance(int chance) {

        this.chance = chance;
        return this;
    }

    public boolean isWater() {

        return water;
    }

    public DecoCrop setWater(boolean water) {

        this.water = water;
        return this;
    }
}
