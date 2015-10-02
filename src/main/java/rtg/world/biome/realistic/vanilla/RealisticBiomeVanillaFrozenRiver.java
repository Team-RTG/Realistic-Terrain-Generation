package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigRTG;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeGenManager;
import rtg.world.gen.surface.vanilla.SurfaceVanillaFrozenRiver;
import rtg.world.gen.terrain.vanilla.TerrainVanillaFrozenRiver;

public class RealisticBiomeVanillaFrozenRiver extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.frozenRiver.topBlock;
	public static Block fillerBlock = BiomeGenBase.frozenRiver.fillerBlock;
	
	public RealisticBiomeVanillaFrozenRiver()
	{
		super(
			BiomeGenBase.frozenRiver,
			BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.ICE),
			new TerrainVanillaFrozenRiver(),
			new SurfaceVanillaFrozenRiver(Blocks.snow, Blocks.snow, Blocks.snow, Blocks.snow, (byte)0, 0)
		);
		
		this.setRealisticBiomeName("Vanilla Frozen River");
		BiomeGenManager.addFrozenBiome(this, ConfigRTG.weightVanillaFrozenRiver);
	}	
}
