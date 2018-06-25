package rtg.world.gen.structure;

import java.util.Random;

import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraft.world.gen.structure.WoodlandMansion;

import rtg.api.world.gen.RTGChunkGenSettings;

public class WoodlandMansionRTG extends WoodlandMansion
{
    private final int spacing;
    private final int separation;

    public WoodlandMansionRTG(ChunkGeneratorOverworld providerIn, RTGChunkGenSettings settings) {
        super(providerIn);
        this.spacing    = settings.mansionSpacing;
        this.separation = settings.mansionSeparation;
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
        int i = chunkX;
        int j = chunkZ;
        if (chunkX < 0) { i = chunkX - (this.spacing - 1); }
        if (chunkZ < 0) { j = chunkZ - (this.spacing - 1); }
        int x = i / this.spacing;
        int z = j / this.spacing;
        Random random = this.world.setRandomSeed(x, z, 10387319);
        x = x * this.spacing;
        z = z * this.spacing;
        x = x + (random.nextInt(this.spacing - this.separation) + random.nextInt(this.spacing - this.separation)) / 2;
        z = z + (random.nextInt(this.spacing - this.separation) + random.nextInt(this.spacing - this.separation)) / 2;
        return chunkX == x && chunkZ == z && this.world.getBiomeProvider().areBiomesViable(chunkX * 16 + 8, chunkZ * 16 + 8, 32, ALLOWED_BIOMES);
    }
}
