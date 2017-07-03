package rtg.world.biome.realistic.biomesoplenty;

import java.util.*;

import net.minecraft.world.biome.Biome;

import net.minecraftforge.fml.common.Loader;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.biome.IExtendedBiome;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.api.generation.IGenerationManager;
import biomesoplenty.api.generation.IGenerator;
import com.google.common.collect.ImmutableCollection;
import static biomesoplenty.api.generation.GeneratorStage.*;

import rtg.api.world.deco.DecoBase;
import rtg.world.biome.realistic.RealisticBiomeBase;

public abstract class RealisticBiomeBOPBase extends RealisticBiomeBase implements IRealisticBOPBiome {

    public static RealisticBiomeBase bopAlps;
    public static RealisticBiomeBase bopBambooForest;
    public static RealisticBiomeBase bopBayou;
    public static RealisticBiomeBase bopBog;
    public static RealisticBiomeBase bopBorealForest;
    public static RealisticBiomeBase bopBrushland;
    public static RealisticBiomeBase bopChaparral;
    public static RealisticBiomeBase bopCherryBlossomGrove;
    public static RealisticBiomeBase bopColdDesert;
    public static RealisticBiomeBase bopConiferousForest;
    public static RealisticBiomeBase bopCoralReef;
    public static RealisticBiomeBase bopCrag;
    public static RealisticBiomeBase bopDeadForest;
    public static RealisticBiomeBase bopDeadSwamp;
    public static RealisticBiomeBase bopEucalyptusForest;
    public static RealisticBiomeBase bopFen;
    public static RealisticBiomeBase bopFlowerField;
    public static RealisticBiomeBase bopFlowerIsland;
    public static RealisticBiomeBase bopGlacier;
    public static RealisticBiomeBase bopGrassland;
    public static RealisticBiomeBase bopGravelBeach;
    public static RealisticBiomeBase bopGrove;
    public static RealisticBiomeBase bopHeathland;
    public static RealisticBiomeBase bopHighland;
    public static RealisticBiomeBase bopKelpForest;
    public static RealisticBiomeBase bopLandOfLakes;
    public static RealisticBiomeBase bopLavenderFields;
    public static RealisticBiomeBase bopLushDesert;
    public static RealisticBiomeBase bopLushSwamp;
    public static RealisticBiomeBase bopMangrove;
    public static RealisticBiomeBase bopMapleWoods;
    public static RealisticBiomeBase bopMarsh;
    public static RealisticBiomeBase bopMeadow;
    public static RealisticBiomeBase bopMoor;
    public static RealisticBiomeBase bopMountain;
    public static RealisticBiomeBase bopMountainFoothills;
    public static RealisticBiomeBase bopMysticGrove;
    public static RealisticBiomeBase bopOasis;
    public static RealisticBiomeBase bopOminousWoods;
    public static RealisticBiomeBase bopOrchard;
    public static RealisticBiomeBase bopOriginIsland;
    public static RealisticBiomeBase bopOutback;
    public static RealisticBiomeBase bopOvergrownCliffs;
    public static RealisticBiomeBase bopPrairie;
    public static RealisticBiomeBase bopQuagmire;
    public static RealisticBiomeBase bopRainforest;
    public static RealisticBiomeBase bopRedwoodForest;
    public static RealisticBiomeBase bopSacredSprings;
    public static RealisticBiomeBase bopSeasonalForest;
    public static RealisticBiomeBase bopShield;
    public static RealisticBiomeBase bopShrubland;
    public static RealisticBiomeBase bopSnowyConiferousForest;
    public static RealisticBiomeBase bopSnowyForest;
    public static RealisticBiomeBase bopSteppe;
    public static RealisticBiomeBase bopTemperateRainforest;
    public static RealisticBiomeBase bopTropicalRainforest;
    public static RealisticBiomeBase bopTropicalIsland;
    public static RealisticBiomeBase bopTundra;
    public static RealisticBiomeBase bopVolcanicIsland;
    public static RealisticBiomeBase bopWasteland;
    public static RealisticBiomeBase bopWetland;
    public static RealisticBiomeBase bopWoodland;
    public static RealisticBiomeBase bopXericShrubland;

    public static ArrayList<RealisticBiomeBOPBase> bopBiomes = new ArrayList<RealisticBiomeBOPBase>(){};

    protected IExtendedBiome bopExtendedBiome;
    protected IGenerationManager bopGenerationManager;
    protected HashMap<GeneratorStage, ImmutableCollection<IGenerator>> bopStageGenerators = new HashMap<>();

    protected ArrayList<GeneratorStage> generatorStages = new ArrayList<GeneratorStage>(Arrays.asList(
        BIG_SHROOM, CACTUS, CLAY, DEAD_BUSH, FLOWERS, GRASS, LAKE_LAVA, LAKE_WATER, LILYPAD,
        ORE_POST, ORE_PRE, PARENT, POST, PRE, PUMPKIN, REED, SAND, SAND_PASS2, SHROOM, TREE
    ));

    public RealisticBiomeBOPBase(Biome b, Biome riverbiome) {

        super(b, riverbiome);

        bopBiomes.add(this);
        bopExtendedBiome = (IExtendedBiome) b;
        bopGenerationManager = bopExtendedBiome.getGenerationManager();

        // Add the BOP biome's generators to RTG's internal list.
        for (GeneratorStage generatorStage : generatorStages) {
            bopStageGenerators.put(generatorStage, bopGenerationManager.getGeneratorsForStage(generatorStage));
        }

        // Now remove them from the biome so we can manually call them when we want to.
        removeBOPGenerators();

        // Remove any relevant stages from decoration, as designated in the individual biome's initDecos().
        removeStagesFromBaseBiomeDecoration();
    }

    @Override
    public IExtendedBiome bopExtendedBiome() {
        return this.bopExtendedBiome;
    }

    @Override
    public IGenerationManager bopGenerationManager() {
        return this.bopGenerationManager;
    }

    @Override
    public HashMap<GeneratorStage, ImmutableCollection<IGenerator>> bopStageGenerators() {
        return this.bopStageGenerators;
    }

    @Override
    public ArrayList<GeneratorStage> generatorStages() {
        return this.generatorStages;
    }

    @Override
    public Biome baseBiome() {
        return this.baseBiome;
    }

    @Override
    public Biome riverBiome() {
        return this.riverBiome;
    }

    @Override
    public String modSlug() {
        return "biomesoplenty";
    }

    @Override
    public int waterSurfaceLakeChance() {
        return 0;
    }

    @Override
    public int lavaSurfaceLakeChance() {
        return 0;
    }

    public static void addBiomes() {

        if (Loader.isModLoaded("BiomesOPlenty")) {
            if (BOPBiomes.alps.isPresent()) {
                bopAlps = new RealisticBiomeBOPAlps();
            }
            if (BOPBiomes.bamboo_forest.isPresent()) {
                bopBambooForest = new RealisticBiomeBOPBambooForest();
            }
            if (BOPBiomes.bayou.isPresent()) {
                bopBayou = new RealisticBiomeBOPBayou();
            }
            if (BOPBiomes.bog.isPresent()) {
                bopBog = new RealisticBiomeBOPBog();
            }
            if (BOPBiomes.boreal_forest.isPresent()) {
                bopBorealForest = new RealisticBiomeBOPBorealForest();
            }
            if (BOPBiomes.brushland.isPresent()) {
                bopBrushland = new RealisticBiomeBOPBrushland();
            }
            if (BOPBiomes.chaparral.isPresent()) {
                bopChaparral = new RealisticBiomeBOPChaparral();
            }
            if (BOPBiomes.cherry_blossom_grove.isPresent()) {
                bopCherryBlossomGrove = new RealisticBiomeBOPCherryBlossomGrove();
            }
            if (BOPBiomes.cold_desert.isPresent()) {
                bopColdDesert = new RealisticBiomeBOPColdDesert();
            }
            if (BOPBiomes.coniferous_forest.isPresent()) {
                bopConiferousForest = new RealisticBiomeBOPConiferousForest();
            }
            if (BOPBiomes.coral_reef.isPresent()) {
                bopCoralReef = new RealisticBiomeBOPCoralReef();
            }
            if (BOPBiomes.crag.isPresent()) {
                bopCrag = new RealisticBiomeBOPCrag();
            }
            if (BOPBiomes.dead_forest.isPresent()) {
                bopDeadForest = new RealisticBiomeBOPDeadForest();
            }
            if (BOPBiomes.dead_swamp.isPresent()) {
                bopDeadSwamp = new RealisticBiomeBOPDeadSwamp();
            }
            if (BOPBiomes.eucalyptus_forest.isPresent()) {
                bopEucalyptusForest = new RealisticBiomeBOPEucalyptusForest();
            }
            if (BOPBiomes.fen.isPresent()) {
                bopFen = new RealisticBiomeBOPFen();
            }
            if (BOPBiomes.flower_field.isPresent()) {
                bopFlowerField = new RealisticBiomeBOPFlowerField();
            }
            if (BOPBiomes.flower_island.isPresent()) {
                bopFlowerIsland = new RealisticBiomeBOPFlowerIsland();
            }
            if (BOPBiomes.glacier.isPresent()) {
                bopGlacier = new RealisticBiomeBOPGlacier();
            }
            if (BOPBiomes.grassland.isPresent()) {
                bopGrassland = new RealisticBiomeBOPGrassland();
            }
            if (BOPBiomes.gravel_beach.isPresent()) {
                bopGravelBeach = new RealisticBiomeBOPGravelBeach();
            }
            if (BOPBiomes.grove.isPresent()) {
                bopGrove = new RealisticBiomeBOPGrove();
            }
            if (BOPBiomes.heathland.isPresent()) {
                bopHeathland = new RealisticBiomeBOPHeathland();
            }
            if (BOPBiomes.highland.isPresent()) {
                bopHighland = new RealisticBiomeBOPHighland();
            }
            if (BOPBiomes.kelp_forest.isPresent()) {
                bopKelpForest = new RealisticBiomeBOPKelpForest();
            }
            if (BOPBiomes.land_of_lakes.isPresent()) {
                bopLandOfLakes = new RealisticBiomeBOPLandOfLakes();
            }
            if (BOPBiomes.lavender_fields.isPresent()) {
                bopLavenderFields = new RealisticBiomeBOPLavenderFields();
            }
            if (BOPBiomes.lush_desert.isPresent()) {
                bopLushDesert = new RealisticBiomeBOPLushDesert();
            }
            if (BOPBiomes.lush_swamp.isPresent()) {
                bopLushSwamp = new RealisticBiomeBOPLushSwamp();
            }
            if (BOPBiomes.mangrove.isPresent()) {
                bopMangrove = new RealisticBiomeBOPMangrove();
            }
            if (BOPBiomes.maple_woods.isPresent()) {
                bopMapleWoods = new RealisticBiomeBOPMapleWoods();
            }
            if (BOPBiomes.marsh.isPresent()) {
                bopMarsh = new RealisticBiomeBOPMarsh();
            }
            if (BOPBiomes.meadow.isPresent()) {
                bopMeadow = new RealisticBiomeBOPMeadow();
            }
            if (BOPBiomes.moor.isPresent()) {
                bopMoor = new RealisticBiomeBOPMoor();
            }
            if (BOPBiomes.mountain.isPresent()) {
                bopMountain = new RealisticBiomeBOPMountainPeaks();
            }
            if (BOPBiomes.mountain_foothills.isPresent()) {
                bopMountainFoothills = new RealisticBiomeBOPMountainFoothills();
            }
            if (BOPBiomes.mystic_grove.isPresent()) {
                bopMysticGrove = new RealisticBiomeBOPMysticGrove();
            }
            if (BOPBiomes.oasis.isPresent()) {
                bopOasis = new RealisticBiomeBOPOasis();
            }
            if (BOPBiomes.ominous_woods.isPresent()) {
                bopOminousWoods = new RealisticBiomeBOPOminousWoods();
            }
            if (BOPBiomes.orchard.isPresent()) {
                bopOrchard = new RealisticBiomeBOPOrchard();
            }
            if (BOPBiomes.origin_island.isPresent()) {
                bopOriginIsland = new RealisticBiomeBOPOriginIsland();
            }
            if (BOPBiomes.outback.isPresent()) {
                bopOutback = new RealisticBiomeBOPOutback();
            }
            if (BOPBiomes.overgrown_cliffs.isPresent()) {
                bopOvergrownCliffs = new RealisticBiomeBOPOvergrownCliffs();
            }
            if (BOPBiomes.prairie.isPresent()) {
                bopPrairie = new RealisticBiomeBOPPrairie();
            }
            if (BOPBiomes.quagmire.isPresent()) {
                bopQuagmire = new RealisticBiomeBOPQuagmire();
            }
            if (BOPBiomes.rainforest.isPresent()) {
                bopRainforest = new RealisticBiomeBOPRainforest();
            }
            if (BOPBiomes.redwood_forest.isPresent()) {
                bopRedwoodForest = new RealisticBiomeBOPRedwoodForest();
            }
            if (BOPBiomes.sacred_springs.isPresent()) {
                bopSacredSprings = new RealisticBiomeBOPSacredSprings();
            }
            if (BOPBiomes.seasonal_forest.isPresent()) {
                bopSeasonalForest = new RealisticBiomeBOPSeasonalForest();
            }
            if (BOPBiomes.shield.isPresent()) {
                bopShield = new RealisticBiomeBOPShield();
            }
            if (BOPBiomes.shrubland.isPresent()) {
                bopShrubland = new RealisticBiomeBOPShrubland();
            }
            if (BOPBiomes.snowy_coniferous_forest.isPresent()) {
                bopSnowyConiferousForest = new RealisticBiomeBOPSnowyConiferousForest();
            }
            if (BOPBiomes.snowy_forest.isPresent()) {
                bopSnowyForest = new RealisticBiomeBOPSnowyForest();
            }
            if (BOPBiomes.steppe.isPresent()) {
                bopSteppe = new RealisticBiomeBOPSteppe();
            }
            if (BOPBiomes.temperate_rainforest.isPresent()) {
                bopTemperateRainforest = new RealisticBiomeBOPTemperateRainforest();
            }
            if (BOPBiomes.tropical_island.isPresent()) {
                bopTropicalIsland = new RealisticBiomeBOPTropicalIsland();
            }
            if (BOPBiomes.tropical_rainforest.isPresent()) {
                bopTropicalRainforest = new RealisticBiomeBOPTropicalRainforest();
            }
            if (BOPBiomes.tundra.isPresent()) {
                bopTundra = new RealisticBiomeBOPTundra();
            }
            if (BOPBiomes.volcanic_island.isPresent()) {
                bopVolcanicIsland = new RealisticBiomeBOPVolcanicIsland();
            }
            if (BOPBiomes.wasteland.isPresent()) {
                bopWasteland = new RealisticBiomeBOPWasteland();
            }
            if (BOPBiomes.wetland.isPresent()) {
                bopWetland = new RealisticBiomeBOPWetland();
            }
            if (BOPBiomes.woodland.isPresent()) {
                bopWoodland = new RealisticBiomeBOPWoodland();
            }
            if (BOPBiomes.xeric_shrubland.isPresent()) {
                bopXericShrubland = new RealisticBiomeBOPXericShrubland();
            }
        }
    }

    @Override
    public void addBOPGenerators() {

        HashMap<GeneratorStage, ImmutableCollection<IGenerator>> bopGeneratorStages = this.bopStageGenerators();
        IGenerationManager bopGenerationManager = this.bopGenerationManager();

        Set set = bopGeneratorStages.entrySet();
        Iterator i = set.iterator();
        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();

            GeneratorStage stage = (GeneratorStage) me.getKey();
            ImmutableCollection<IGenerator> generators = (ImmutableCollection<IGenerator>) me.getValue();

            for (IGenerator generator : generators) {
                bopGenerationManager.addGenerator(generator.getName(), stage, generator);
            }
        }
    }

    @Override
    public void removeBOPGenerators() {

        HashMap<GeneratorStage, ImmutableCollection<IGenerator>> bopGeneratorStages = this.bopStageGenerators();
        IGenerationManager bopGenerationManager = this.bopGenerationManager();

        Set set = bopGeneratorStages.entrySet();
        Iterator i = set.iterator();
        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();

            GeneratorStage stage = (GeneratorStage) me.getKey();
            ImmutableCollection<IGenerator> generators = (ImmutableCollection<IGenerator>) me.getValue();

            for (IGenerator generator : generators) {
                bopGenerationManager.removeGenerator(generator.getName());
            }
        }
    }

    private void removeStagesFromBaseBiomeDecoration() {

        // Loop through all the BOP biomes.
        for (RealisticBiomeBOPBase biome : bopBiomes) {

            ArrayList<DecoBase> decos = biome.getDecos(); // We need to get the decos before we cast the biome.
            IRealisticBOPBiome bopBiome = (IRealisticBOPBiome) biome;

            // Loop through all the decos until we find an instance of DecoBOPBaseBiomeDecorations and remove stages as necessary.
            for (DecoBase deco : decos) {

                if (deco instanceof DecoBOPBaseBiomeDecorations) {

                    DecoBOPBaseBiomeDecorations decoBOPBaseBiomeDecorations = (DecoBOPBaseBiomeDecorations) deco;

                    if (decoBOPBaseBiomeDecorations.generatorStagesToRemove.size() > 0) {

                        HashMap<GeneratorStage, ImmutableCollection<IGenerator>> stageGenerators = bopBiome.bopStageGenerators();

                        for (GeneratorStage stage : decoBOPBaseBiomeDecorations.generatorStagesToRemove) {

                            if (stageGenerators.containsKey(stage)) {
                                stageGenerators.remove(stage);
                            }
                        }
                    }
                }
            }
        }
    }
}
