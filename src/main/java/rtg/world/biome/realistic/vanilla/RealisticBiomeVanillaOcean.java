package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.vanilla.SurfaceVanillaOcean;
import rtg.world.gen.terrain.vanilla.TerrainVanillaOcean;

public class RealisticBiomeVanillaOcean extends RealisticBiomeVanillaBase {

    public static IBlockState topBlock = Biome.ocean.topBlock;
    public static IBlockState fillerBlock = Biome.ocean.fillerBlock;

    public RealisticBiomeVanillaOcean(BiomeConfig config) {

        super(config,
            Biome.ocean,
            Biome.river,
            new TerrainVanillaOcean(),
            new SurfaceVanillaOcean(config, Blocks.sand.getDefaultState(), Blocks.sand.getDefaultState(), Blocks.gravel.getDefaultState(), 20f, 0.2f)
        );

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
        this.noLakes = true;
        this.noWaterFeatures = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
