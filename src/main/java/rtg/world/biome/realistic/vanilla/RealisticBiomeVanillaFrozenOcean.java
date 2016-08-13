package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.vanilla.SurfaceVanillaFrozenOcean;
import rtg.world.gen.terrain.vanilla.TerrainVanillaFrozenOcean;

public class RealisticBiomeVanillaFrozenOcean extends RealisticBiomeVanillaBase {

    public static IBlockState topBlock = Biome.frozenOcean.topBlock;
    public static IBlockState fillerBlock = Biome.frozenOcean.fillerBlock;

    public RealisticBiomeVanillaFrozenOcean(BiomeConfig config) {

        super(config,
            Biome.frozenOcean,
            Biome.river,
            new TerrainVanillaFrozenOcean(),
            new SurfaceVanillaFrozenOcean(config, Blocks.sand.getDefaultState(), Blocks.sand.getDefaultState(), Blocks.gravel.getDefaultState(), 20f, 0.2f)
        );

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
        this.noLakes = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
