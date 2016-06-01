package rtg.world.biome.realistic.vanilla;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.vanilla.SurfaceVanillaRiver;
import rtg.world.gen.terrain.vanilla.TerrainVanillaRiver;

public class RealisticBiomeVanillaRiver extends RealisticBiomeVanillaBase
{
	public static BiomeGenBase vanillaBiome = BiomeGenBase.river;

	public RealisticBiomeVanillaRiver(BiomeConfig config)
	{
		super(config, 
			vanillaBiome,
			BiomeGenBase.river,
			new TerrainVanillaRiver(),
			new SurfaceVanillaRiver(config)
		);

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}

        /**
     * This method is used by DecoBaseBiomeDecorations to allow the base biome to decorate itself.
     */
    @Override
    public void rDecorateSeedBiome(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river, BiomeGenBase seedBiome) {

        if (strength > 0.3f) {
            int previousReeds = BiomeGenBase.river.theBiomeDecorator.reedsPerChunk;
            int previousLilies = BiomeGenBase.river.theBiomeDecorator.waterlilyPerChunk;
            BiomeGenBase.river.theBiomeDecorator.reedsPerChunk = 4;
            BiomeGenBase.river.theBiomeDecorator.waterlilyPerChunk = 0;
            seedBiome.decorate(world, rand, chunkX, chunkY);
            BiomeGenBase.river.theBiomeDecorator.reedsPerChunk = previousReeds;
            BiomeGenBase.river.theBiomeDecorator.waterlilyPerChunk = previousLilies;
        }
        else {
            rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, seedBiome);
        }
    }
}
