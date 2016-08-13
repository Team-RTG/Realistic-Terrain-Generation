package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.vanilla.SurfaceVanillaMushroomIslandShore;
import rtg.world.gen.terrain.vanilla.TerrainVanillaMushroomIslandShore;

public class RealisticBiomeVanillaMushroomIslandShore extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.MUSHROOM_ISLAND_SHORE;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaMushroomIslandShore(BiomeConfig config) {

        super(config, biome, river,
            new TerrainVanillaMushroomIslandShore(),
            new SurfaceVanillaMushroomIslandShore(config, biome.topBlock, biome.fillerBlock, 67, biome.topBlock, 0f)
        );

        this.noLakes = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
