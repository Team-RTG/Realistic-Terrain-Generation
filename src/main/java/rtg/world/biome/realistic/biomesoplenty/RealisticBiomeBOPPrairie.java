package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPPrairie;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPPrairie;

public class RealisticBiomeBOPPrairie extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.prairie.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPPrairie(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPPrairie(65f, 80f, 25f),
            new SurfaceBOPPrairie(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
