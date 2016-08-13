package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.vanilla.SurfaceVanillaRiver;
import rtg.world.gen.terrain.vanilla.TerrainVanillaRiver;

public class RealisticBiomeVanillaRiver extends RealisticBiomeVanillaBase {

    public static Biome vanillaBiome = Biome.river;
    public static IBlockState topBlock = vanillaBiome.topBlock;
    public static IBlockState fillerBlock = vanillaBiome.fillerBlock;

    public RealisticBiomeVanillaRiver(BiomeConfig config) {

        super(config,
            vanillaBiome,
            Biome.river,
            new TerrainVanillaRiver(),
            new SurfaceVanillaRiver(config)
        );

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
