package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.vanilla.SurfaceVanillaFrozenRiver;
import rtg.world.gen.terrain.vanilla.TerrainVanillaFrozenRiver;

public class RealisticBiomeVanillaFrozenRiver extends RealisticBiomeVanillaBase {

    public static IBlockState topBlock = BiomeGenBase.frozenRiver.topBlock;
    public static IBlockState fillerBlock = BiomeGenBase.frozenRiver.fillerBlock;

    public RealisticBiomeVanillaFrozenRiver(BiomeConfig config) {

        super(config,
            BiomeGenBase.frozenRiver,
            BiomeGenBase.frozenRiver,
            new TerrainVanillaFrozenRiver(),
            new SurfaceVanillaFrozenRiver(config)
        );

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
