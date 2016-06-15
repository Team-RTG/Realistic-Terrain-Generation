package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaTaigaHills;
import rtg.world.biome.deco.collection.DecoCollectionTaiga;
import rtg.world.gen.surface.vanilla.SurfaceVanillaTaigaHills;
import rtg.world.gen.terrain.vanilla.TerrainVanillaTaigaHills;

public class RealisticBiomeVanillaTaigaHills extends RealisticBiomeVanillaBase
{
    
    public static Block topBlock = BiomeGenBase.taigaHills.topBlock;
    public static Block fillerBlock = BiomeGenBase.taigaHills.fillerBlock;
    
    public RealisticBiomeVanillaTaigaHills(BiomeConfig config)
    {
    
        super(config, 
            BiomeGenBase.taigaHills,
            BiomeGenBase.river,
            new TerrainVanillaTaigaHills(),
            new SurfaceVanillaTaigaHills(config, Blocks.grass, Blocks.dirt, true, Blocks.gravel, 0.2f)
        );
        this.noLakes=true;
        
        this.addDecoCollection(new DecoCollectionTaiga(this.config._boolean(BiomeConfigVanillaTaigaHills.decorationLogsId), 10f));
    }
}
