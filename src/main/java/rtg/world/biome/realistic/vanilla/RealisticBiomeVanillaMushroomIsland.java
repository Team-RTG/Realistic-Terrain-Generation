package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.vanilla.SurfaceVanillaMushroomIsland;
import rtg.world.gen.terrain.vanilla.TerrainVanillaMushroomIsland;

public class RealisticBiomeVanillaMushroomIsland extends RealisticBiomeVanillaBase {

    public static IBlockState topBlock = Biomes.MUSHROOM_ISLAND.topBlock;
    public static IBlockState fillerBlock = Biomes.MUSHROOM_ISLAND.fillerBlock;

    public RealisticBiomeVanillaMushroomIsland(BiomeConfig config) {

        super(config,
            Biomes.MUSHROOM_ISLAND,
            Biomes.RIVER,
            new TerrainVanillaMushroomIsland(),
            new SurfaceVanillaMushroomIsland(config, topBlock, fillerBlock, 67, topBlock, 0f)
        );

        this.noLakes = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
