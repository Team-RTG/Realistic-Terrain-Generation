package rtg.world.biome.realistic.itd;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.*;
import rtg.world.gen.surface.sushicraft.SurfaceSCSakuraForest;
import rtg.world.gen.terrain.sushicraft.TerrainSCSakuraForest;


public class RealisticBiomeITDDarkForest extends RealisticBiomeITDBase {

    public RealisticBiomeITDDarkForest(BiomeGenBase itdBiome, BiomeConfig config) {

            super(config,
                    itdBiome, BiomeGenBase.river,
                    new TerrainSCSakuraForest(),
                    new SurfaceSCSakuraForest(config, itdBiome.topBlock, itdBiome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.dirt, (byte)2, 0.15f)
            );

            DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
            decoBaseBiomeDecorations.allowed = true;
            this.addDeco(decoBaseBiomeDecorations);

            DecoBoulder decoBoulder = new DecoBoulder();
            decoBoulder.boulderBlock = Blocks.cobblestone;
            decoBoulder.chance = 24;
            decoBoulder.maxY = 95;
            decoBoulder.strengthFactor = 1f;
            this.addDeco(decoBoulder);

            DecoShrub decoShrub = new DecoShrub();
            decoShrub.maxY = 110;
            decoShrub.notEqualsZerochance = 4;
            decoShrub.strengthFactor = 2f;
            this.addDeco(decoShrub);

            DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
            decoFlowersRTG.flowers = new int[] {6, 8};
            decoFlowersRTG.maxY = 128;
            decoFlowersRTG.heightType = DecoFlowersRTG.HeightType.NEXT_INT;
            decoFlowersRTG.notEqualsZeroChance = 4;
            decoFlowersRTG.strengthFactor = 8f;
            this.addDeco(decoFlowersRTG);

            DecoGrass decoGrass = new DecoGrass();
            decoGrass.maxY = 128;
            decoGrass.notEqualsZerochance = 4;
            decoGrass.strengthFactor = 10f;
            this.addDeco(decoGrass);
    }
}
