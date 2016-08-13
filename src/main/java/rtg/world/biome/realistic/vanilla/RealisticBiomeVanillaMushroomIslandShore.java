package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.vanilla.SurfaceVanillaMushroomIslandShore;
import rtg.world.gen.terrain.vanilla.TerrainVanillaMushroomIslandShore;

public class RealisticBiomeVanillaMushroomIslandShore extends RealisticBiomeVanillaBase {

    public static IBlockState topBlock = Biomes.MUSHROOM_ISLAND_SHORE.topBlock;
    public static IBlockState fillerBlock = Biomes.MUSHROOM_ISLAND_SHORE.fillerBlock;

    public RealisticBiomeVanillaMushroomIslandShore(BiomeConfig config) {

        super(config,
            Biomes.MUSHROOM_ISLAND_SHORE,
            Biomes.RIVER,
            new TerrainVanillaMushroomIslandShore(),
            new SurfaceVanillaMushroomIslandShore(config, topBlock, fillerBlock, 67, topBlock, 0f)
        );

        this.noLakes = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
