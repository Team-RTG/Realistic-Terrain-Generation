package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.vanilla.SurfaceVanillaMushroomIsland;
import rtg.world.gen.terrain.vanilla.TerrainVanillaMushroomIsland;

public class RealisticBiomeVanillaMushroomIsland extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.MUSHROOM_ISLAND;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaMushroomIsland(BiomeConfig config) {

        super(config, biome, river,
            new TerrainVanillaMushroomIsland(),
            new SurfaceVanillaMushroomIsland(config, biome.topBlock, biome.fillerBlock, 0f)
        );

        this.noLakes = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
