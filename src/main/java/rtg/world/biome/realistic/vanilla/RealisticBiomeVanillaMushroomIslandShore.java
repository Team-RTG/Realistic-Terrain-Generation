package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.vanilla.SurfaceVanillaMushroomIslandShore;
import rtg.world.gen.terrain.vanilla.TerrainVanillaMushroomIslandShore;

public class RealisticBiomeVanillaMushroomIslandShore extends RealisticBiomeVanillaBase {

    public static IBlockState topBlock = Biome.mushroomIslandShore.topBlock;
    public static IBlockState fillerBlock = Biome.mushroomIslandShore.fillerBlock;

    public RealisticBiomeVanillaMushroomIslandShore(BiomeConfig config) {

        super(config,
            Biome.mushroomIslandShore,
            Biome.river,
            new TerrainVanillaMushroomIslandShore(),
            new SurfaceVanillaMushroomIslandShore(config, topBlock, fillerBlock, 67, topBlock, 0f)
        );

        this.noLakes = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
