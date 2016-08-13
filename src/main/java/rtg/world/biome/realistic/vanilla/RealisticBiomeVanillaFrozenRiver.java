package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.vanilla.SurfaceVanillaFrozenRiver;
import rtg.world.gen.terrain.vanilla.TerrainVanillaFrozenRiver;

public class RealisticBiomeVanillaFrozenRiver extends RealisticBiomeVanillaBase {

    public static IBlockState topBlock = Biomes.FROZEN_RIVER.topBlock;
    public static IBlockState fillerBlock = Biomes.FROZEN_RIVER.fillerBlock;

    public RealisticBiomeVanillaFrozenRiver(BiomeConfig config) {

        super(config,
            Biomes.FROZEN_RIVER,
            Biomes.FROZEN_RIVER,
            new TerrainVanillaFrozenRiver(),
            new SurfaceVanillaFrozenRiver(config)
        );

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
