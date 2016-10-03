package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.vanilla.SurfaceVanillaStoneBeach;
import rtg.world.gen.terrain.vanilla.TerrainVanillaStoneBeach;

public class RealisticBiomeVanillaStoneBeach extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.STONE_BEACH;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaStoneBeach(BiomeConfig config) {

        super(config, biome, river,
            new TerrainVanillaStoneBeach(),
            new SurfaceVanillaStoneBeach(config, Blocks.GRAVEL.getDefaultState(), biome.fillerBlock, 1f, 1.5f, 85f, 20f, 4f)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
