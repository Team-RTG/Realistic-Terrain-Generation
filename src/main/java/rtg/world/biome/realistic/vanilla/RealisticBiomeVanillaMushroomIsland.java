package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.vanilla.SurfaceVanillaMushroomIsland;
import rtg.world.gen.terrain.vanilla.TerrainVanillaMushroomIsland;

public class RealisticBiomeVanillaMushroomIsland extends RealisticBiomeVanillaBase {

    public static IBlockState topBlock = BiomeGenBase.mushroomIsland.topBlock;
    public static IBlockState fillerBlock = BiomeGenBase.mushroomIsland.fillerBlock;

    public RealisticBiomeVanillaMushroomIsland(BiomeConfig config) {

        super(config,
            BiomeGenBase.mushroomIsland,
            BiomeGenBase.river,
            new TerrainVanillaMushroomIsland(),
            new SurfaceVanillaMushroomIsland(config, topBlock, fillerBlock, 67, topBlock, 0f)
        );

        this.noLakes = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
