package rtg.world.biome.realistic.floricraft;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;

import net.minecraftforge.fml.common.Loader;

import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;

@SuppressWarnings("WeakerAccess")
public abstract class RealisticBiomeFLORIBase extends RealisticBiomeBase
{
    private static final String FLORICRAFT_MODID    = "floricraft";
    private static final String ROSE_LAND_REG_NAME  = "Rose Land";
    private static final String TULIP_LAND_REG_NAME = "Tulip Land";

    public static RealisticBiomeBase roseLand;
    public static RealisticBiomeBase tulipLand;

    public RealisticBiomeFLORIBase(Biome b, Biome riverbiome) {

        super(b, riverbiome);

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
    }

    @Override public String modSlug() {
        return FLORICRAFT_MODID;
    }

    public static void addBiomes() {

        if (Loader.isModLoaded(FLORICRAFT_MODID)) {

            for (Biome biome : Biome.REGISTRY) {
                ResourceLocation loc = biome.getRegistryName();
                if (loc == null) {
                    Logger.error("ResourceLocation is NULL for: \"{}\"[{}]", biome.getBiomeName(), biome.getBiomeClass().getName());
                    continue;
                }

                if (loc.getResourceDomain().equals(FLORICRAFT_MODID) && loc.getResourcePath().equals( ROSE_LAND_REG_NAME)) { setRoseLand(biome); }
                if (loc.getResourceDomain().equals(FLORICRAFT_MODID) && loc.getResourcePath().equals(TULIP_LAND_REG_NAME)) { setTulipLand(biome);}
            }
        }
    }
    private static void setRoseLand (Biome biome) { roseLand  = new RealisticBiomeFLORIRoseLand(biome); }
    private static void setTulipLand(Biome biome) { tulipLand = new RealisticBiomeFLORITulipLand(biome);}
}
