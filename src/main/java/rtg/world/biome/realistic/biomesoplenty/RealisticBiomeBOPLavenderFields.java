package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPLavenderFields;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPLavenderFields;

public class RealisticBiomeBOPLavenderFields extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.lavender_fields.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPLavenderFields(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPLavenderFields(),
            new SurfaceBOPLavenderFields(config, biome.topBlock, biome.fillerBlock, 0f, 1.5f, 60f, 65f, 1.5f, biome.topBlock, 0.05f)
        );

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 110;
        decoShrub.chance = 10;
        decoShrub.strengthFactor = 4f;
        this.addDeco(decoShrub);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 128;
        decoGrass.strengthFactor = 6f;
        this.addDeco(decoGrass);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
