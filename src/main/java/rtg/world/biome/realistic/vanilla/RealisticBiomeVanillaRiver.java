package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.vanilla.SurfaceVanillaRiver;
import rtg.world.gen.terrain.vanilla.TerrainVanillaRiver;

public class RealisticBiomeVanillaRiver extends RealisticBiomeVanillaBase {

    public static Biome vanillaBiome = Biomes.RIVER;
    public static IBlockState topBlock = vanillaBiome.topBlock;
    public static IBlockState fillerBlock = vanillaBiome.fillerBlock;

    public RealisticBiomeVanillaRiver(BiomeConfig config) {

        super(config,
            vanillaBiome,
            Biomes.RIVER,
            new TerrainVanillaRiver(),
            new SurfaceVanillaRiver(config)
        );

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
