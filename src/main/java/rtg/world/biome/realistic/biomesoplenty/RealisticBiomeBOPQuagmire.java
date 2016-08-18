package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPQuagmire;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPQuagmire;

public class RealisticBiomeBOPQuagmire extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.quagmire.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPQuagmire(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPQuagmire(),
            new SurfaceBOPQuagmire(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
