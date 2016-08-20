package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoPond;
import rtg.world.biome.deco.helper.DecoHelperBorder;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPCrag;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPCrag;

public class RealisticBiomeBOPCrag extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.crag.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPCrag(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPCrag(90f),
            new SurfaceBOPCrag(config, biome.topBlock, biome.fillerBlock, biome.topBlock)
        );

        this.generatesEmeralds = true;
        this.noLakes = true;
        this.noWaterFeatures = true;

        DecoPond decoPond = new DecoPond();
        decoPond.chunksPerPond = 3;// very high because most are blocked by topography
        DecoHelperBorder borderedPond = new DecoHelperBorder(decoPond, 0.8f, 0.7f);
        this.addDeco(borderedPond);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
