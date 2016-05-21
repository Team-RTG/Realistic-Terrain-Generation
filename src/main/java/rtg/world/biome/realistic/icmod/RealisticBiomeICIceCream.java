package rtg.world.biome.realistic.icmod;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.icmod.SurfaceICIceCream;
import rtg.world.gen.terrain.icmod.TerrainICIceCream;

/**
 * Created by VelocityRa on 16/4/2016.
 */
public class RealisticBiomeICIceCream extends RealisticBiomeICBase {

    public RealisticBiomeICIceCream(BiomeGenBase icBiome, BiomeConfig config) {
        super(config,
                icBiome,
                BiomeGenBase.frozenRiver,
                new TerrainICIceCream(),
                new SurfaceICIceCream(config, icBiome.topBlock, icBiome.fillerBlock, icBiome.topBlock, icBiome.topBlock)
        );
        this.waterSurfaceLakeChance = 1;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}