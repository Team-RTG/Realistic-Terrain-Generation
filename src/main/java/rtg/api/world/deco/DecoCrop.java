package rtg.api.world.deco;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import rtg.api.world.RTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.gen.feature.WorldGenCrops;


/**
 * @author lightningo7
 */
public class DecoCrop extends DecoBase {

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
    public void generate(IRealisticBiome biome, RTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.allowed) {

            if (this.chance > 1 && rand.nextInt(this.chance) == 0) {

                for (int i = 0; i < this.strengthFactor * strength; ++i) {

                    int x = worldX + rand.nextInt(16) + 8;
                    int z = worldZ + rand.nextInt(16) + 8;
                    int y = rtgWorld.world().getHeight(new BlockPos(x, 0, z)).getY();
                    if (y >= this.minY && y <= this.maxY) {
                        new WorldGenCrops(type, size, density, height, water).generate(rtgWorld.world(), rand, new BlockPos(x, y, z));
                    }
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
