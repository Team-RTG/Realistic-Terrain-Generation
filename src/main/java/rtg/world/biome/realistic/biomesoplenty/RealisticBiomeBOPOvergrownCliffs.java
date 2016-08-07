package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.BiomeGenBase;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPOvergrownCliffs;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPOvergrownCliffs;

public class RealisticBiomeBOPOvergrownCliffs extends RealisticBiomeBOPBase {

    public static BiomeGenBase bopBiome = BOPBiomes.overgrown_cliffs.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPOvergrownCliffs(BiomeConfig config) {

        super(config,
            bopBiome, BiomeGenBase.river,
            new TerrainBOPOvergrownCliffs(300f, 100f, 0f),
            new SurfaceBOPOvergrownCliffs(config, topBlock, fillerBlock, false, null, 0.95f)
        );

        this.generatesEmeralds = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
