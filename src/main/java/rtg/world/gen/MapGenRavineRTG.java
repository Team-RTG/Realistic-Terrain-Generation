package rtg.world.gen;

import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenRavine;


public class MapGenRavineRTG extends MapGenRavine {

    private final int ravineChance;

    MapGenRavineRTG(int ravineChance) {
        this.ravineChance = ravineChance;
    }

    @Override
    protected void recursiveGenerate(World worldIn, int chunkX, int chunkZ, int originalX, int originalZ, ChunkPrimer chunkPrimerIn) {

        if (this.rand.nextInt(this.ravineChance) == 0) {

            double x = (chunkX * 16 + this.rand.nextInt(16));
            double y = (this.rand.nextInt(this.rand.nextInt(40) + 8) + 20);
            double z = (chunkZ * 16 + this.rand.nextInt(16));

            for (int i = 0; i < 1; ++i) {
                float f = this.rand.nextFloat() * ((float) Math.PI * 2F);
                float f1 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
                float f2 = (this.rand.nextFloat() * 2.0F + this.rand.nextFloat()) * 2.0F;
                this.addTunnel(this.rand.nextLong(), originalX, originalZ, chunkPrimerIn, x, y, z, f2, f, f1, 0, 0, 3.0D);
            }
        }
    }
}
