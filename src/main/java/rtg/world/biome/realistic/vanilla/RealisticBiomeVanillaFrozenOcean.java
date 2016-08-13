package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.vanilla.SurfaceVanillaFrozenOcean;
import rtg.world.gen.terrain.vanilla.TerrainVanillaFrozenOcean;

public class RealisticBiomeVanillaFrozenOcean extends RealisticBiomeVanillaBase {

    public static IBlockState topBlock = Biomes.FROZEN_OCEAN.topBlock;
    public static IBlockState fillerBlock = Biomes.FROZEN_OCEAN.fillerBlock;

    public RealisticBiomeVanillaFrozenOcean(BiomeConfig config) {

        super(config,
            Biomes.FROZEN_OCEAN,
            Biomes.RIVER,
            new TerrainVanillaFrozenOcean(),
            new SurfaceVanillaFrozenOcean(config, Blocks.SAND.getDefaultState(), Blocks.SAND.getDefaultState(), Blocks.GRAVEL.getDefaultState(), 20f, 0.2f)
        );

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
        this.noLakes = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
