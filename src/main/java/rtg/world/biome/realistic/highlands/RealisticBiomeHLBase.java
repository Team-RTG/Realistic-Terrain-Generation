package rtg.world.biome.realistic.highlands;

import net.minecraft.world.biome.BiomeGenBase;

import net.minecraftforge.fml.common.Loader;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.highlands.config.BiomeConfigHL;
import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeHLBase extends RealisticBiomeBase {

    public static RealisticBiomeBase hlAdirondacks;
    public static RealisticBiomeBase hlAdirondacksFoothills;
    public static RealisticBiomeBase hlAlps;
    public static RealisticBiomeBase hlAlpsFoothills;
    public static RealisticBiomeBase hlBadlands;
    public static RealisticBiomeBase hlBadlandsFoothills;
    public static RealisticBiomeBase hlBaldHill;
    public static RealisticBiomeBase hlBambooForest;
    public static RealisticBiomeBase hlDryForest;
    public static RealisticBiomeBase hlDunes;
    public static RealisticBiomeBase hlGreyMountains;
    public static RealisticBiomeBase hlGreyMountainsFoothills;
    public static RealisticBiomeBase hlHighlands;
    public static RealisticBiomeBase hlLake;
    public static RealisticBiomeBase hlLowlands;
    public static RealisticBiomeBase hlMeadow;
    public static RealisticBiomeBase hlMojave;
    public static RealisticBiomeBase hlPinelands;
    public static RealisticBiomeBase hlPoplarHills;
    public static RealisticBiomeBase hlRedwoodForest;
    public static RealisticBiomeBase hlTropicalHills;
    public static RealisticBiomeBase hlTropicalIslands;


    public RealisticBiomeHLBase(BiomeConfig config, BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s) {

        super(config, b, riverbiome, t, s);

        this.lavaSurfaceLakeChance = 0;
    }

    public static void addBiomes() {

        if (Loader.isModLoaded("highlands")) {
            BiomeGenBase[] b = BiomeGenBase.getBiomeGenArray();

            for (int i = 0; i < 256; i++) {
                if (b[i] != null) {
                    if (b[i].biomeName == null) {
                        Logger.warn("Biome ID %d has no name.", b[i].biomeID);
                        continue;
                    }

                    BiomeGenBase hlBiome = b[i];
                    int biomeId = b[i].biomeID;
                    String biomeName = b[i].biomeName;
                    String biomeClass = b[i].getBiomeClass().getName();

                    if (biomeName.equalsIgnoreCase("Adirondacks") && biomeClass.equalsIgnoreCase("com.sdj64.highlands.biome.BiomeGenAdirondacks")) {
                        hlAdirondacks = new RealisticBiomeHLAdirondacks(hlBiome, BiomeConfigHL.biomeConfigHLAdirondacks);
                    }
                    else if (biomeName.equalsIgnoreCase("Adirondacks foothills") && biomeClass.equalsIgnoreCase("com.sdj64.highlands.biome.BiomeGenAdirondacks")) {
                        hlAdirondacksFoothills = new RealisticBiomeHLAdirondacksFoothills(hlBiome, BiomeConfigHL.biomeConfigHLAdirondacksFoothills);
                    }
                    else if (biomeName.equalsIgnoreCase("Alps") && biomeClass.equalsIgnoreCase("com.sdj64.highlands.biome.BiomeGenAlps")) {
                        hlAlps = new RealisticBiomeHLAlps(hlBiome, BiomeConfigHL.biomeConfigHLAlps);
                    }
                    else if (biomeName.equalsIgnoreCase("Alps foothills") && biomeClass.equalsIgnoreCase("com.sdj64.highlands.biome.BiomeGenAlps")) {
                        hlAlpsFoothills = new RealisticBiomeHLAlpsFoothills(hlBiome, BiomeConfigHL.biomeConfigHLAlpsFoothills);
                    }
                    else if (biomeName.equalsIgnoreCase("Badlands") && biomeClass.equalsIgnoreCase("com.sdj64.highlands.biome.BiomeGenBadlands")) {
                        hlBadlands = new RealisticBiomeHLBadlands(hlBiome, BiomeConfigHL.biomeConfigHLBadlands);
                    }
                    else if (biomeName.equalsIgnoreCase("Badlands foothills") && biomeClass.equalsIgnoreCase("com.sdj64.highlands.biome.BiomeGenBadlands")) {
                        hlBadlandsFoothills = new RealisticBiomeHLBadlandsFoothills(hlBiome, BiomeConfigHL.biomeConfigHLBadlandsFoothills);
                    }
                    else if (biomeName.equalsIgnoreCase("Bald Hill") && biomeClass.equalsIgnoreCase("com.sdj64.highlands.biome.BiomeGenBaldHill")) {
                        hlBaldHill = new RealisticBiomeHLBaldHill(hlBiome, BiomeConfigHL.biomeConfigHLBaldHill);
                    }
                    else if (biomeName.equalsIgnoreCase("Bamboo Forest") && biomeClass.equalsIgnoreCase("com.sdj64.highlands.biome.BiomeGenBambooForest")) {
                        hlBambooForest = new RealisticBiomeHLBambooForest(hlBiome, BiomeConfigHL.biomeConfigHLBambooForest);
                    }
                    else if (biomeName.equalsIgnoreCase("Dry Forest") && biomeClass.equalsIgnoreCase("com.sdj64.highlands.biome.BiomeGenDryForest")) {
                        hlDryForest = new RealisticBiomeHLDryForest(hlBiome, BiomeConfigHL.biomeConfigHLDryForest);
                    }
                    else if (biomeName.equalsIgnoreCase("Dunes") && biomeClass.equalsIgnoreCase("com.sdj64.highlands.biome.BiomeGenDunes")) {
                        hlDunes = new RealisticBiomeHLDunes(hlBiome, BiomeConfigHL.biomeConfigHLDunes);
                    }
                    else if (biomeName.equalsIgnoreCase("Grey Mountains") && biomeClass.equalsIgnoreCase("com.sdj64.highlands.biome.BiomeGenGreyMountains")) {
                        hlGreyMountains = new RealisticBiomeHLGreyMountains(hlBiome, BiomeConfigHL.biomeConfigHLGreyMountains);
                    }
                    else if (biomeName.equalsIgnoreCase("Grey Mountains foothills") && biomeClass.equalsIgnoreCase("com.sdj64.highlands.biome.BiomeGenGreyMountains")) {
                        hlGreyMountainsFoothills = new RealisticBiomeHLGreyMountainsFoothills(hlBiome, BiomeConfigHL.biomeConfigHLGreyMountainsFoothills);
                    }
                    else if (biomeName.equalsIgnoreCase("Highlands") && biomeClass.equalsIgnoreCase("com.sdj64.highlands.biome.BiomeGenHighlands")) {
                        hlHighlands = new RealisticBiomeHLHighlands(hlBiome, BiomeConfigHL.biomeConfigHLHighlands);
                    }
                    else if (biomeName.equalsIgnoreCase("Lake") && biomeClass.equalsIgnoreCase("com.sdj64.highlands.biome.BiomeGenLake")) {
                        hlLake = new RealisticBiomeHLLake(hlBiome, BiomeConfigHL.biomeConfigHLLake);
                    }
                    else if (biomeName.equalsIgnoreCase("Lowlands") && biomeClass.equalsIgnoreCase("com.sdj64.highlands.biome.BiomeGenLowlands")) {
                        hlLowlands = new RealisticBiomeHLLowlands(hlBiome, BiomeConfigHL.biomeConfigHLLowlands);
                    }
                    else if (biomeName.equalsIgnoreCase("Meadow") && biomeClass.equalsIgnoreCase("com.sdj64.highlands.biome.BiomeGenMeadow")) {
                        hlMeadow = new RealisticBiomeHLMeadow(hlBiome, BiomeConfigHL.biomeConfigHLMeadow);
                    }
                    else if (biomeName.equalsIgnoreCase("Mojave") && biomeClass.equalsIgnoreCase("com.sdj64.highlands.biome.BiomeGenMojave")) {
                        hlMojave = new RealisticBiomeHLMojave(hlBiome, BiomeConfigHL.biomeConfigHLMojave);
                    }
                    else if (biomeName.equalsIgnoreCase("Pinelands") && biomeClass.equalsIgnoreCase("com.sdj64.highlands.biome.BiomeGenPinelands")) {
                        hlPinelands = new RealisticBiomeHLPinelands(hlBiome, BiomeConfigHL.biomeConfigHLPinelands);
                    }
                    else if (biomeName.equalsIgnoreCase("Poplar Hills") && biomeClass.equalsIgnoreCase("com.sdj64.highlands.biome.BiomeGenPoplarHills")) {
                        hlPoplarHills = new RealisticBiomeHLPoplarHills(hlBiome, BiomeConfigHL.biomeConfigHLPoplarHills);
                    }
                    else if (biomeName.equalsIgnoreCase("Redwood Forest") && biomeClass.equalsIgnoreCase("com.sdj64.highlands.biome.BiomeGenRedwoodForest")) {
                        hlRedwoodForest = new RealisticBiomeHLRedwoodForest(hlBiome, BiomeConfigHL.biomeConfigHLRedwoodForest);
                    }
                    else if (biomeName.equalsIgnoreCase("Tropical Hills") && biomeClass.equalsIgnoreCase("com.sdj64.highlands.biome.BiomeGenTropHills")) {
                        hlTropicalHills = new RealisticBiomeHLTropicalHills(hlBiome, BiomeConfigHL.biomeConfigHLTropicalHills);
                    }
                    else if (biomeName.equalsIgnoreCase("Tropical Islands") && biomeClass.equalsIgnoreCase("com.sdj64.highlands.biome.BiomeGenTropicalIslands")) {
                        hlTropicalIslands = new RealisticBiomeHLTropicalIslands(hlBiome, BiomeConfigHL.biomeConfigHLTropicalIslands);
                    }
                }
            }
        }
    }
}
