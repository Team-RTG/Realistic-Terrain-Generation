package rtg.world.gen;

import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenCaves;

public class MapGenCavesRTG extends MapGenCaves
{

    private final int caveDensity;
    private final int caveChance;

    MapGenCavesRTG(int caveChance, int caveDensity) {
        this.caveChance  = caveChance;
        this.caveDensity = caveDensity;
    }

    @Override
    protected void recursiveGenerate(World worldIn, int chunkX, int chunkZ, int originalX, int originalZ, ChunkPrimer chunkPrimerIn) {

        int density = this.caveDensity;
        int chance = this.caveChance;

        density = (density < 1) ? 1 : ((density > 39) ? 39 : density);
        density += 1;

        int i = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(density) + 1) + 1);

        chance = (chance < 1) ? 1 : ((chance > 39) ? 39 : chance);
        chance += 1;

        if (this.rand.nextInt(chance) != 0) i = 0;

        for (int j = 0; j < i; ++j) {
            double x = (chunkX * 16 + this.rand.nextInt(16));
            double y = this.rand.nextInt(this.rand.nextInt(120) + 8);
            double z = (chunkZ * 16 + this.rand.nextInt(16));
            int k = 1;

            if (this.rand.nextInt(4) == 0) {
                super.addRoom(this.rand.nextLong(), originalX, originalZ, chunkPrimerIn, x, y, z);
                k += this.rand.nextInt(4);
            }

            for (int l = 0; l < k; ++l) {
                float f = this.rand.nextFloat() * ((float) Math.PI * 2F);
                float f1 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
                float f2 = this.rand.nextFloat() * 2.0F + this.rand.nextFloat();

                if (this.rand.nextInt(10) == 0) {
                    f2 *= this.rand.nextFloat() * this.rand.nextFloat() * 3.0F + 1.0F;
                }
                super.addTunnel(this.rand.nextLong(), originalX, originalZ, chunkPrimerIn, x, y, z, f2, f, f1, 0, 0, 1.0D);
            }
        }
    }
}
