package rtg.api.world.gen;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.stream.JsonReader;

import net.minecraft.util.JsonUtils;

import rtg.api.util.Logger;


@SuppressWarnings("WeakerAccess")
public final class ChunkProviderSettingsRTG
{
// TODO: [Generator settings] Requires an extension of net.minecraft.world.gen.layer.GenLayerBiome for this to work.
// TODO: [Generator settings] Disable fixedBiome and biomeSize for now as they require modification to the GenLayer classes to work.
//  public final int     fixedBiome;            // Vanilla
//  public final int     biomeSize;             // Vanilla
// TODO: [Generator settings] seaLevel requires extensive edits to hard-coded values for it to work correctly.
    public final int     seaLevel;              // Vanilla
    public final boolean oceanWaves;            // RTG

    public final boolean useBoulders;           // RTG
    public final float   boulderMult;           // RTG
    public final int     sandDuneHeight;        // RTG
//  public final int     snowDuneHeight;        // RTG - No current use
    public final boolean useSnowLayers;         // RTG

    public final int     bedrockLayers;         // RTG
    public final boolean useCaves;              // Vanilla
// TODO: [Generator settings] Possibly use a float value as a multiplier
    public final int     caveChance;            // RTG
// TODO: [Generator settings] Remove caveDensity and only use caveChance
    public final int     caveDensity;           // RTG
    public final boolean useRavines;            // Vanilla
    public final int     ravineChance;          // RTG

    public final boolean useVolcanos;           // RTG
    public final boolean volcanosErupt;         // RTG
    public final int     volcanosChance;        // RTG
    public final float   volcanosCalderaMult;   // RTG
    public final boolean volcanoConduits;       // RTG
    public final int     volcanoConduitDepth;   // RTG


    public final float   riverSize;             // Vanilla (repurposed)
    public final float   riverFrequency;        // RTG
    public final float   riverBendMult;         // RTG
    public final float   riverCutOffAmpl;       // RTG
    public final float   riverCutOffScale;      // RTG

    public final float   RTGlakeSizeMult;       // RTG
    public final float   RTGlakeFreqMult;       // RTG
    public final float   RTGlakeShoreBend;      // RTG

    public final boolean useWaterLakes;         // Vanilla
    public final int     waterLakeChance;       // Vanilla
    public final boolean useLavaLakes;          // Vanilla
    public final int     lavaLakeChance;        // Vanilla

    public final boolean useWaterUndLakes;      // RTG
    public final int     waterUndLakeChance;    // RTG
    public final boolean useLavaUndLakes;       // RTG
    public final int     lavaUndLakeChance;     // RTG

    public final int     waterSpoutChance;      // RTG
    public final int     lavaSpoutChance;       // RTG


    public final boolean useVillages;           // Vanilla
    public final int     villageSize;           // RTG
    public final int     villageMinDistance;    // RTG : TODO: [Generator settings] Deprecated. To be removed.
    public final int     villageMaxDistance;    // RTG : TODO: [Generator settings] Rename to villageDistance when villageMinDistance is removed.

    public final boolean useMineShafts;         // Vanilla
    public final float   mineShaftChance;       // RTG

    public final boolean useDungeons;           // Vanilla
    public final int     dungeonChance;         // Vanilla

    public final boolean useTemples;            // Vanilla (Scattered Features)
    public final int     templeMinDistance;     // RTG : TODO: [Generator settings] Deprecated. To be removed.
    public final int     templeMaxDistance;     // RTG : TODO: [Generator settings] Rename to templeDistance when templeMinDistance is removed.

    public final boolean useMonuments;          // Vanilla
    public final int     monumentSpacing;       // RTG
    public final int     monumentSeparation;    // RTG

// TODO: [Generator settings] Disabled until >= MC 1.11... requires a custom structure class as vanilla settings are hardcoded
//  public final boolean useMansions;           // Vanilla
//  public final int     mansionSpacing;        // RTG

    public final boolean useStrongholds;        // Vanilla
    public final int     strongholdCount;       // RTG
    public final int     strongholdDistance;    // RTG
    public final int     strongholdSpread;      // RTG


    public final int     dirtSize;              // Vanilla
    public final int     dirtCount;             // Vanilla
    public final int     dirtMinHeight;         // Vanilla
    public final int     dirtMaxHeight;         // Vanilla

    public final int     gravelSize;            // Vanilla
    public final int     gravelCount;           // Vanilla
    public final int     gravelMinHeight;       // Vanilla
    public final int     gravelMaxHeight;       // Vanilla

    public final int     graniteSize;           // Vanilla
    public final int     graniteCount;          // Vanilla
    public final int     graniteMinHeight;      // Vanilla
    public final int     graniteMaxHeight;      // Vanilla

    public final int     dioriteSize;           // Vanilla
    public final int     dioriteCount;          // Vanilla
    public final int     dioriteMinHeight;      // Vanilla
    public final int     dioriteMaxHeight;      // Vanilla

    public final int     andesiteSize;          // Vanilla
    public final int     andesiteCount;         // Vanilla
    public final int     andesiteMinHeight;     // Vanilla
    public final int     andesiteMaxHeight;     // Vanilla

    public final int     coalSize;              // Vanilla
    public final int     coalCount;             // Vanilla
    public final int     coalMinHeight;         // Vanilla
    public final int     coalMaxHeight;         // Vanilla

    public final int     ironSize;              // Vanilla
    public final int     ironCount;             // Vanilla
    public final int     ironMinHeight;         // Vanilla
    public final int     ironMaxHeight;         // Vanilla

    public final int     goldSize;              // Vanilla
    public final int     goldCount;             // Vanilla
    public final int     goldMinHeight;         // Vanilla
    public final int     goldMaxHeight;         // Vanilla

    public final int     redstoneSize;          // Vanilla
    public final int     redstoneCount;         // Vanilla
    public final int     redstoneMinHeight;     // Vanilla
    public final int     redstoneMaxHeight;     // Vanilla

    public final int     diamondSize;           // Vanilla
    public final int     diamondCount;          // Vanilla
    public final int     diamondMinHeight;      // Vanilla
    public final int     diamondMaxHeight;      // Vanilla

    public final int     lapisSize;             // Vanilla
    public final int     lapisCount;            // Vanilla
    public final int     lapisCenterHeight;     // Vanilla
    public final int     lapisSpread;           // Vanilla


    private ChunkProviderSettingsRTG(ChunkProviderSettingsRTG.Factory settingsFactory) {

// TODO: [Generator settings] Disable fixedBiome and biomeSize for now as they require modification to the GenLayer classes to work.
//      this.fixedBiome             = settingsFactory.fixedBiome;
//      this.biomeSize              = settingsFactory.biomeSize;
        this.seaLevel               = settingsFactory.seaLevel;
        this.oceanWaves             = settingsFactory.oceanWaves;

        this.useBoulders            = settingsFactory.useBoulders;
        this.boulderMult            = settingsFactory.boulderMult;
        this.sandDuneHeight         = settingsFactory.sandDuneHeight;
//      this.snowDuneHeight         = settingsFactory.snowDuneHeight;
        this.useSnowLayers          = settingsFactory.useSnowLayers;

        this.bedrockLayers          = settingsFactory.bedrockLayers;
        this.useCaves               = settingsFactory.useCaves;
        this.caveChance             = settingsFactory.caveChance;
        this.caveDensity            = settingsFactory.caveDensity;
        this.useRavines             = settingsFactory.useRavines;
        this.ravineChance           = settingsFactory.ravineChance;

        this.useVolcanos            = settingsFactory.useVolcanos;
        this.volcanosErupt          = settingsFactory.volcanosErupt;
        this.volcanosChance         = settingsFactory.volcanosChance;
        this.volcanosCalderaMult    = settingsFactory.volcanosCalderaMult;
        this.volcanoConduits        = settingsFactory.volcanoConduits;
        this.volcanoConduitDepth    = settingsFactory.volcanoConduitDepth;


        this.riverSize              = settingsFactory.riverSize;
        this.riverFrequency         = settingsFactory.riverFrequency;
        this.riverBendMult          = settingsFactory.riverBendMult;
        this.riverCutOffAmpl        = settingsFactory.riverCutOffAmpl;
        this.riverCutOffScale       = settingsFactory.riverCutOffScale;

        this.RTGlakeSizeMult        = settingsFactory.RTGlakeSizeMult;
        this.RTGlakeFreqMult        = settingsFactory.RTGlakeFreqMult;
        this.RTGlakeShoreBend       = settingsFactory.RTGlakeShoreBend;

        this.useWaterLakes          = settingsFactory.useWaterLakes;
        this.waterLakeChance        = settingsFactory.waterLakeChance;
        this.useLavaLakes           = settingsFactory.useLavaLakes;
        this.lavaLakeChance         = settingsFactory.lavaLakeChance;

        this.useWaterUndLakes       = settingsFactory.useWaterUndLakes;
        this.waterUndLakeChance     = settingsFactory.waterUndLakeChance;
        this.useLavaUndLakes        = settingsFactory.useLavaUndLakes;
        this.lavaUndLakeChance      = settingsFactory.lavaUndLakeChance;

        this.waterSpoutChance       = settingsFactory.waterSpoutChance;
        this.lavaSpoutChance        = settingsFactory.lavaSpoutChance;


        this.useVillages            = settingsFactory.useVillages;
        this.villageSize            = settingsFactory.villageSize;
        this.villageMinDistance     = settingsFactory.villageMinDistance;
        this.villageMaxDistance     = settingsFactory.villageMaxDistance;

        this.useMineShafts          = settingsFactory.useMineShafts;
        this.mineShaftChance        = settingsFactory.mineShaftChance;

        this.useDungeons            = settingsFactory.useDungeons;
        this.dungeonChance          = settingsFactory.dungeonChance;

        this.useTemples             = settingsFactory.useTemples;
        this.templeMinDistance      = settingsFactory.templeMinDistance;
        this.templeMaxDistance      = settingsFactory.templeMaxDistance;

        this.useMonuments           = settingsFactory.useMonuments;
        this.monumentSpacing        = settingsFactory.monumentSpacing;
        this.monumentSeparation     = settingsFactory.monumentSeparation;

// TODO: Disabled until >= MC 1.11
//      this.useMansions            = settingsFactory.useMansions;
//      this.mansionSpacing         = settingsFactory.mansionSpacing;

        this.useStrongholds         = settingsFactory.useStrongholds;
        this.strongholdCount        = settingsFactory.strongholdCount;
        this.strongholdDistance     = settingsFactory.strongholdDistance;
        this.strongholdSpread       = settingsFactory.strongholdSpread;


        this.dirtSize               = settingsFactory.dirtSize;
        this.dirtCount              = settingsFactory.dirtCount;
        this.dirtMinHeight          = settingsFactory.dirtMinHeight;
        this.dirtMaxHeight          = settingsFactory.dirtMaxHeight;

        this.gravelSize             = settingsFactory.gravelSize;
        this.gravelCount            = settingsFactory.gravelCount;
        this.gravelMinHeight        = settingsFactory.gravelMinHeight;
        this.gravelMaxHeight        = settingsFactory.gravelMaxHeight;

        this.graniteSize            = settingsFactory.graniteSize;
        this.graniteCount           = settingsFactory.graniteCount;
        this.graniteMinHeight       = settingsFactory.graniteMinHeight;
        this.graniteMaxHeight       = settingsFactory.graniteMaxHeight;

        this.dioriteSize            = settingsFactory.dioriteSize;
        this.dioriteCount           = settingsFactory.dioriteCount;
        this.dioriteMinHeight       = settingsFactory.dioriteMinHeight;
        this.dioriteMaxHeight       = settingsFactory.dioriteMaxHeight;

        this.andesiteSize           = settingsFactory.andesiteSize;
        this.andesiteCount          = settingsFactory.andesiteCount;
        this.andesiteMinHeight      = settingsFactory.andesiteMinHeight;
        this.andesiteMaxHeight      = settingsFactory.andesiteMaxHeight;

        this.coalSize               = settingsFactory.coalSize;
        this.coalCount              = settingsFactory.coalCount;
        this.coalMinHeight          = settingsFactory.coalMinHeight;
        this.coalMaxHeight          = settingsFactory.coalMaxHeight;

        this.ironSize               = settingsFactory.ironSize;
        this.ironCount              = settingsFactory.ironCount;
        this.ironMinHeight          = settingsFactory.ironMinHeight;
        this.ironMaxHeight          = settingsFactory.ironMaxHeight;

        this.goldSize               = settingsFactory.goldSize;
        this.goldCount              = settingsFactory.goldCount;
        this.goldMinHeight          = settingsFactory.goldMinHeight;
        this.goldMaxHeight          = settingsFactory.goldMaxHeight;

        this.redstoneSize           = settingsFactory.redstoneSize;
        this.redstoneCount          = settingsFactory.redstoneCount;
        this.redstoneMinHeight      = settingsFactory.redstoneMinHeight;
        this.redstoneMaxHeight      = settingsFactory.redstoneMaxHeight;

        this.diamondSize            = settingsFactory.diamondSize;
        this.diamondCount           = settingsFactory.diamondCount;
        this.diamondMinHeight       = settingsFactory.diamondMinHeight;
        this.diamondMaxHeight       = settingsFactory.diamondMaxHeight;

        this.lapisSize              = settingsFactory.lapisSize;
        this.lapisCount             = settingsFactory.lapisCount;
        this.lapisCenterHeight      = settingsFactory.lapisCenterHeight;
        this.lapisSpread            = settingsFactory.lapisSpread;
    }

    public static class Factory {

        static final Gson JSON_ADAPTER = (new GsonBuilder()).registerTypeAdapter(ChunkProviderSettingsRTG.Factory.class, new ChunkProviderSettingsRTG.Serializer()).create();

//      public int      fixedBiome           = -1;
//      public int      biomeSize            = 4;
        public int      seaLevel             = 63;  
        public boolean  oceanWaves           = true;

        public boolean  useBoulders          = true;
        public float    boulderMult          = 1.0f;
        public int      sandDuneHeight       = 4;
//      public int      snowDuneHeight       = 4;
        public boolean  useSnowLayers        = true;

        public int      bedrockLayers        = 5;
        public boolean  useCaves             = true;
        public int      caveChance           = 7;
        public int      caveDensity          = 15;
        public boolean  useRavines           = true;
        public int      ravineChance         = 50;

        public boolean  useVolcanos          = true;
        public boolean  volcanosErupt        = true;
        public int      volcanosChance       = 48;
        public float    volcanosCalderaMult  = 1.0f;
        public boolean  volcanoConduits      = true;
        public int      volcanoConduitDepth  = 0;


        public float    riverSize            = 4.0f;
        public float    riverFrequency       = 1.0f;
        public float    riverBendMult        = 1.0f;
        public float    riverCutOffAmpl      = 0.5f;
        public float    riverCutOffScale     = 350.0f;

        public float    RTGlakeSizeMult      = 1.0f;
        public float    RTGlakeFreqMult      = 1.0f;
        public float    RTGlakeShoreBend     = 1.0f;

        public boolean  useWaterLakes        = true;
        public int      waterLakeChance      = 10;
        public boolean  useLavaLakes         = true;
        public int      lavaLakeChance       = 80;

        public boolean  useWaterUndLakes     = true;
        public int      waterUndLakeChance   = 10;
        public boolean  useLavaUndLakes      = true;
        public int      lavaUndLakeChance    = 80;

        public int      waterSpoutChance     = 200;
        public int      lavaSpoutChance      = 200;


        public boolean  useVillages          = true;
        public int      villageSize          = 0;
        public int      villageMinDistance   = 12;
        public int      villageMaxDistance   = 48;

        public boolean  useMineShafts        = true;
        public float    mineShaftChance      = 0.004f;

        public boolean  useDungeons          = true;
        public int      dungeonChance        = 8;   

        public boolean  useTemples           = true;
        public int      templeMinDistance    = 12;
        public int      templeMaxDistance    = 48;

        public boolean  useMonuments         = true;
        public int      monumentSpacing      = 32;
        public int      monumentSeparation   = 5;

// TODO: Disabled until >= MC 1.11
//      public boolean  useMansions          = true;
//      public int      mansionSpacing       = 80;

        public boolean  useStrongholds       = true;
        public int      strongholdCount      = 128;
        public int      strongholdDistance   = 32;
        public int      strongholdSpread     = 3;


        public int      dirtSize             = 33;  
        public int      dirtCount            = 10;  
        public int      dirtMinHeight        = 0;   
        public int      dirtMaxHeight        = 256; 

        public int      gravelSize           = 33;  
        public int      gravelCount          = 8;   
        public int      gravelMinHeight      = 0;   
        public int      gravelMaxHeight      = 256; 

        public int      graniteSize          = 33;  
        public int      graniteCount         = 10;  
        public int      graniteMinHeight     = 0;   
        public int      graniteMaxHeight     = 80;  

        public int      dioriteSize          = 33;  
        public int      dioriteCount         = 10;  
        public int      dioriteMinHeight     = 0;   
        public int      dioriteMaxHeight     = 80;  

        public int      andesiteSize         = 33;  
        public int      andesiteCount        = 10;  
        public int      andesiteMinHeight    = 0;   
        public int      andesiteMaxHeight    = 80;  

        public int      coalSize             = 17;  
        public int      coalCount            = 20;  
        public int      coalMinHeight        = 0;   
        public int      coalMaxHeight        = 128; 

        public int      ironSize             = 9;   
        public int      ironCount            = 20;  
        public int      ironMinHeight        = 0;   
        public int      ironMaxHeight        = 64;  

        public int      goldSize             = 9;   
        public int      goldCount            = 2;   
        public int      goldMinHeight        = 0;   
        public int      goldMaxHeight        = 32;  

        public int      redstoneSize         = 8;   
        public int      redstoneCount        = 8;   
        public int      redstoneMinHeight    = 0;   
        public int      redstoneMaxHeight    = 16;  

        public int      diamondSize          = 8;   
        public int      diamondCount         = 1;   
        public int      diamondMinHeight     = 0;   
        public int      diamondMaxHeight     = 16;  

        public int      lapisSize            = 7;   
        public int      lapisCount           = 1;   
        public int      lapisCenterHeight    = 16;  
        public int      lapisSpread          = 16;  


        public static ChunkProviderSettingsRTG.Factory jsonToFactory(String generatorSettings) {

            if (generatorSettings.isEmpty()) {
                return new ChunkProviderSettingsRTG.Factory();
            }
            try {
                JsonReader reader = new JsonReader(new StringReader(generatorSettings));
                reader.setLenient(true);
                return JSON_ADAPTER.getAdapter(Factory.class).read(reader);
            }
            catch (IOException ex)
            {
                Logger.error("Error parsing chunk generator settings: {}", ex.getMessage());
                Logger.error("Settings: {}", generatorSettings);
                return new ChunkProviderSettingsRTG.Factory();
            }
        }

        public String toString() {return JSON_ADAPTER.toJson(this);}

        public Factory() {this.setDefaults();}

        public final void setDefaults() {

//          this.fixedBiome             = -1;
//          this.biomeSize              = 4;
            this.seaLevel               = 63;  
            this.oceanWaves             = true;

            this.useBoulders            = true;
            this.boulderMult            = 1.0f;
            this.sandDuneHeight         = 4;
//          this.snowDuneHeight         = 4;
            this.useSnowLayers          = true;

            this.bedrockLayers          = 5;
            this.useCaves               = true;
            this.caveChance             = 7;
            this.caveDensity            = 15;
            this.useRavines             = true;
            this.ravineChance           = 50;

            this.useVolcanos            = true;
            this.volcanosErupt          = true;
            this.volcanosChance         = 48;
            this.volcanosCalderaMult    = 1.0f;
            this.volcanoConduits        = true;
            this.volcanoConduitDepth    = 0;


            this.riverSize              = 4.0f;
            this.riverFrequency         = 1.0f;
            this.riverBendMult          = 1.0f;
            this.riverCutOffAmpl        = 0.5f;
            this.riverCutOffScale       = 350.0f;

            this.RTGlakeSizeMult        = 1.0f;
            this.RTGlakeFreqMult        = 1.0f;
            this.RTGlakeShoreBend       = 1.0f;

            this.useWaterLakes          = true;
            this.waterLakeChance        = 10;
            this.useLavaLakes           = true;
            this.lavaLakeChance         = 80;

            this.useWaterUndLakes       = true;
            this.waterUndLakeChance     = 10;
            this.useLavaUndLakes        = true;
            this.lavaUndLakeChance      = 80;

            this.waterSpoutChance       = 200;
            this.lavaSpoutChance        = 200;


            this.useVillages            = true;
            this.villageSize            = 0;
            this.villageMinDistance     = 12;
            this.villageMaxDistance     = 48;

            this.useMineShafts          = true;
            this.mineShaftChance        = 0.004f;

            this.useDungeons            = true;
            this.dungeonChance          = 8;   

            this.useTemples             = true;
            this.templeMinDistance      = 12;
            this.templeMaxDistance      = 48;

            this.useMonuments           = true;
            this.monumentSpacing        = 32;
            this.monumentSeparation     = 5;

// TODO: Disabled until >= MC 1.11
//          this.useMansions            = true;
//          this.mansionSpacing         = 80;

            this.useStrongholds         = true;
            this.strongholdCount        = 128;
            this.strongholdDistance     = 32;
            this.strongholdSpread       = 3;


            this.dirtSize               = 33;  
            this.dirtCount              = 10;  
            this.dirtMinHeight          = 0;   
            this.dirtMaxHeight          = 256; 

            this.gravelSize             = 33;  
            this.gravelCount            = 8;   
            this.gravelMinHeight        = 0;   
            this.gravelMaxHeight        = 256; 

            this.graniteSize            = 33;  
            this.graniteCount           = 10;  
            this.graniteMinHeight       = 0;   
            this.graniteMaxHeight       = 80;  

            this.dioriteSize            = 33;  
            this.dioriteCount           = 10;  
            this.dioriteMinHeight       = 0;   
            this.dioriteMaxHeight       = 80;  

            this.andesiteSize           = 33;  
            this.andesiteCount          = 10;  
            this.andesiteMinHeight      = 0;   
            this.andesiteMaxHeight      = 80;  

            this.coalSize               = 17;  
            this.coalCount              = 20;  
            this.coalMinHeight          = 0;   
            this.coalMaxHeight          = 128; 

            this.ironSize               = 9;   
            this.ironCount              = 20;  
            this.ironMinHeight          = 0;   
            this.ironMaxHeight          = 64;  

            this.goldSize               = 9;   
            this.goldCount              = 2;   
            this.goldMinHeight          = 0;   
            this.goldMaxHeight          = 32;  

            this.redstoneSize           = 8;   
            this.redstoneCount          = 8;   
            this.redstoneMinHeight      = 0;   
            this.redstoneMaxHeight      = 16;  

            this.diamondSize            = 8;   
            this.diamondCount           = 1;   
            this.diamondMinHeight       = 0;   
            this.diamondMaxHeight       = 16;  

            this.lapisSize              = 7;   
            this.lapisCount             = 1;   
            this.lapisCenterHeight      = 16;  
            this.lapisSpread            = 16;  
        }

        public ChunkProviderSettingsRTG build() {
            return new ChunkProviderSettingsRTG(this);
        }
    }

    public static class Serializer implements JsonDeserializer<Factory>, JsonSerializer<Factory> {

        /**
         * @param element .
         * @param type .
         * @param context .
         * @return .
         * @throws JsonParseException .
         */
        @Override
        public Factory deserialize(JsonElement element, Type type, JsonDeserializationContext context) {

            JsonObject json = element.getAsJsonObject();
            Factory    settings   = new Factory();

            try {
// TODO: [Generator settings] Disable fixedBiome and biomeSize for now as they require modification to the GenLayer classes to work.
//              settings.fixedBiome             = JsonUtils.getInt(json,    "fixedBiome",           settings.fixedBiome);
//              settings.biomeSize              = JsonUtils.getInt(json,    "biomeSize",            settings.biomeSize);
                settings.seaLevel               = JsonUtils.getInt(json,    "seaLevel",             settings.seaLevel);
                settings.oceanWaves             = JsonUtils.getBoolean(json,"oceanWaves",           settings.oceanWaves);

                settings.useBoulders            = JsonUtils.getBoolean(json,"useBoulders",          settings.useBoulders);
                settings.boulderMult            = JsonUtils.getFloat(json,  "boulderMult",          settings.boulderMult);
                settings.sandDuneHeight         = JsonUtils.getInt(json,    "sandDuneHeight",       settings.sandDuneHeight);
//              settings.snowDuneHeight         = JsonUtils.getInt(json,    "snowDuneHeight",       settings.snowDuneHeight);
                settings.useSnowLayers          = JsonUtils.getBoolean(json,"useSnowLayers",        settings.useSnowLayers);

                settings.bedrockLayers          = JsonUtils.getInt(json,    "bedrockLayers",        settings.bedrockLayers);
                settings.useCaves               = JsonUtils.getBoolean(json,"useCaves",             settings.useCaves);
                settings.caveChance             = JsonUtils.getInt(json,    "caveChance",           settings.caveChance);
                settings.caveDensity            = JsonUtils.getInt(json,    "caveDensity",          settings.caveDensity);
                settings.useRavines             = JsonUtils.getBoolean(json,"useRavines",           settings.useRavines);
                settings.ravineChance           = JsonUtils.getInt(json,    "ravineChance",         settings.ravineChance);

                settings.useVolcanos            = JsonUtils.getBoolean(json,"useVolcanos",          settings.useVolcanos);
                settings.volcanosErupt          = JsonUtils.getBoolean(json,"volcanosErupt",        settings.volcanosErupt);
                settings.volcanosChance         = JsonUtils.getInt(json,    "volcanosChance",       settings.volcanosChance);
                settings.volcanosCalderaMult    = JsonUtils.getFloat(json,  "volcanosCalderaMult",  settings.volcanosCalderaMult);
                settings.volcanoConduits        = JsonUtils.getBoolean(json,"volcanoConduits",      settings.volcanoConduits);
                settings.volcanoConduitDepth    = JsonUtils.getInt(json,    "volcanoConduitDepth",  settings.volcanoConduitDepth);


                settings.riverSize              = JsonUtils.getFloat(json,  "riverSize",            settings.riverSize);
                settings.riverFrequency         = JsonUtils.getFloat(json,  "riverFrequency",       settings.riverFrequency);
                settings.riverBendMult          = JsonUtils.getFloat(json,  "riverBendMult",        settings.riverBendMult);
                settings.riverCutOffAmpl        = JsonUtils.getFloat(json,  "riverCutOffAmpl",      settings.riverCutOffAmpl);
                settings.riverCutOffScale       = JsonUtils.getFloat(json,  "riverCutOffScale",     settings.riverCutOffScale);

                settings.RTGlakeSizeMult        = JsonUtils.getFloat(json,  "RTGlakeSizeMult",      settings.RTGlakeSizeMult);
                settings.RTGlakeFreqMult        = JsonUtils.getFloat(json,  "RTGlakeFreqMult",      settings.RTGlakeFreqMult);
                settings.RTGlakeShoreBend       = JsonUtils.getFloat(json,  "RTGlakeShoreBend",     settings.RTGlakeShoreBend);

                settings.useWaterLakes          = JsonUtils.getBoolean(json,"useWaterLakes",        settings.useWaterLakes);
                settings.waterLakeChance        = JsonUtils.getInt(json,    "waterLakeChance",      settings.waterLakeChance);
                settings.useLavaLakes           = JsonUtils.getBoolean(json,"useLavaLakes",         settings.useLavaLakes);
                settings.lavaLakeChance         = JsonUtils.getInt(json,    "lavaLakeChance",       settings.lavaLakeChance);

                settings.useWaterUndLakes       = JsonUtils.getBoolean(json,"useWaterUndLakes",     settings.useWaterUndLakes);
                settings.waterUndLakeChance     = JsonUtils.getInt(json,    "waterUndLakeChance",   settings.waterUndLakeChance);
                settings.useLavaUndLakes        = JsonUtils.getBoolean(json,"useLavaUndLakes",      settings.useLavaUndLakes);
                settings.lavaUndLakeChance      = JsonUtils.getInt(json,    "lavaUndLakeChance",    settings.lavaUndLakeChance);

                settings.waterSpoutChance       = JsonUtils.getInt(json,    "waterSpoutChance",     settings.waterSpoutChance);
                settings.lavaSpoutChance        = JsonUtils.getInt(json,    "lavaSpoutChance",      settings.lavaSpoutChance);


                settings.useVillages            = JsonUtils.getBoolean(json,"useVillages",          settings.useVillages);
                settings.villageSize            = JsonUtils.getInt(json,    "villageSize",          settings.villageSize);
                settings.villageMinDistance     = JsonUtils.getInt(json,    "villageMinDistance",   settings.villageMinDistance);
                settings.villageMaxDistance     = JsonUtils.getInt(json,    "villageMaxDistance",   settings.villageMaxDistance);

                settings.useMineShafts          = JsonUtils.getBoolean(json,"useMineShafts",        settings.useMineShafts);
                settings.mineShaftChance        = JsonUtils.getFloat(  json,"mineShaftChance",      settings.mineShaftChance);

                settings.useDungeons            = JsonUtils.getBoolean(json,"useDungeons",          settings.useDungeons);
                settings.dungeonChance          = JsonUtils.getInt(json,    "dungeonChance",        settings.dungeonChance);

                settings.useTemples             = JsonUtils.getBoolean(json,"useTemples",           settings.useTemples);
                settings.templeMinDistance      = JsonUtils.getInt(json,    "templeMinDistance",    settings.templeMinDistance);
                settings.templeMaxDistance      = JsonUtils.getInt(json,    "templeMaxDistance",    settings.templeMaxDistance);

                settings.useMonuments           = JsonUtils.getBoolean(json,"useMonuments",         settings.useMonuments);
                settings.monumentSpacing        = JsonUtils.getInt(json,    "monumentSpacing",      settings.monumentSpacing);
                settings.monumentSeparation     = JsonUtils.getInt(json,    "monumentSeparation",   settings.monumentSeparation);

// TODO: Disabled until >= MC 1.11
//              settings.useMansions            = JsonUtils.getBoolean(json,"useMansions",          settings.useMansions);
//              settings.mansionSpacing         = JsonUtils.getInt(json,    "mansionSpacing",       settings.mansionSpacing);

                settings.useStrongholds         = JsonUtils.getBoolean(json,"useStrongholds",       settings.useStrongholds);
                settings.strongholdCount        = JsonUtils.getInt(json,    "strongholdCount",      settings.strongholdCount);
                settings.strongholdDistance     = JsonUtils.getInt(json,    "strongholdDistance",   settings.strongholdDistance);
                settings.strongholdSpread       = JsonUtils.getInt(json,    "strongholdSpread",     settings.strongholdSpread);


                settings.dirtSize               = JsonUtils.getInt(json,    "dirtSize",             settings.dirtSize);
                settings.dirtCount              = JsonUtils.getInt(json,    "dirtCount",            settings.dirtCount);
                settings.dirtMinHeight          = JsonUtils.getInt(json,    "dirtMinHeight",        settings.dirtMinHeight);
                settings.dirtMaxHeight          = JsonUtils.getInt(json,    "dirtMaxHeight",        settings.dirtMaxHeight);

                settings.gravelSize             = JsonUtils.getInt(json,    "gravelSize",           settings.gravelSize);
                settings.gravelCount            = JsonUtils.getInt(json,    "gravelCount",          settings.gravelCount);
                settings.gravelMinHeight        = JsonUtils.getInt(json,    "gravelMinHeight",      settings.gravelMinHeight);
                settings.gravelMaxHeight        = JsonUtils.getInt(json,    "gravelMaxHeight",      settings.gravelMaxHeight);

                settings.graniteSize            = JsonUtils.getInt(json,    "graniteSize",          settings.graniteSize);
                settings.graniteCount           = JsonUtils.getInt(json,    "graniteCount",         settings.graniteCount);
                settings.graniteMinHeight       = JsonUtils.getInt(json,    "graniteMinHeight",     settings.graniteMinHeight);
                settings.graniteMaxHeight       = JsonUtils.getInt(json,    "graniteMaxHeight",     settings.graniteMaxHeight);

                settings.dioriteSize            = JsonUtils.getInt(json,    "dioriteSize",          settings.dioriteSize);
                settings.dioriteCount           = JsonUtils.getInt(json,    "dioriteCount",         settings.dioriteCount);
                settings.dioriteMinHeight       = JsonUtils.getInt(json,    "dioriteMinHeight",     settings.dioriteMinHeight);
                settings.dioriteMaxHeight       = JsonUtils.getInt(json,    "dioriteMaxHeight",     settings.dioriteMaxHeight);

                settings.andesiteSize           = JsonUtils.getInt(json,    "andesiteSize",         settings.andesiteSize);
                settings.andesiteCount          = JsonUtils.getInt(json,    "andesiteCount",        settings.andesiteCount);
                settings.andesiteMinHeight      = JsonUtils.getInt(json,    "andesiteMinHeight",    settings.andesiteMinHeight);
                settings.andesiteMaxHeight      = JsonUtils.getInt(json,    "andesiteMaxHeight",    settings.andesiteMaxHeight);

                settings.coalSize               = JsonUtils.getInt(json,    "coalSize",             settings.coalSize);
                settings.coalCount              = JsonUtils.getInt(json,    "coalCount",            settings.coalCount);
                settings.coalMinHeight          = JsonUtils.getInt(json,    "coalMinHeight",        settings.coalMinHeight);
                settings.coalMaxHeight          = JsonUtils.getInt(json,    "coalMaxHeight",        settings.coalMaxHeight);

                settings.ironSize               = JsonUtils.getInt(json,    "ironSize",             settings.ironSize);
                settings.ironCount              = JsonUtils.getInt(json,    "ironCount",            settings.ironCount);
                settings.ironMinHeight          = JsonUtils.getInt(json,    "ironMinHeight",        settings.ironMinHeight);
                settings.ironMaxHeight          = JsonUtils.getInt(json,    "ironMaxHeight",        settings.ironMaxHeight);

                settings.goldSize               = JsonUtils.getInt(json,    "goldSize",             settings.goldSize);
                settings.goldCount              = JsonUtils.getInt(json,    "goldCount",            settings.goldCount);
                settings.goldMinHeight          = JsonUtils.getInt(json,    "goldMinHeight",        settings.goldMinHeight);
                settings.goldMaxHeight          = JsonUtils.getInt(json,    "goldMaxHeight",        settings.goldMaxHeight);

                settings.redstoneSize           = JsonUtils.getInt(json,    "redstoneSize",         settings.redstoneSize);
                settings.redstoneCount          = JsonUtils.getInt(json,    "redstoneCount",        settings.redstoneCount);
                settings.redstoneMinHeight      = JsonUtils.getInt(json,    "redstoneMinHeight",    settings.redstoneMinHeight);
                settings.redstoneMaxHeight      = JsonUtils.getInt(json,    "redstoneMaxHeight",    settings.redstoneMaxHeight);

                settings.diamondSize            = JsonUtils.getInt(json,    "diamondSize",          settings.diamondSize);
                settings.diamondCount           = JsonUtils.getInt(json,    "diamondCount",         settings.diamondCount);
                settings.diamondMinHeight       = JsonUtils.getInt(json,    "diamondMinHeight",     settings.diamondMinHeight);
                settings.diamondMaxHeight       = JsonUtils.getInt(json,    "diamondMaxHeight",     settings.diamondMaxHeight);

                settings.lapisSize              = JsonUtils.getInt(json,    "lapisSize",            settings.lapisSize);
                settings.lapisCount             = JsonUtils.getInt(json,    "lapisCount",           settings.lapisCount);
                settings.lapisCenterHeight      = JsonUtils.getInt(json,    "lapisCenterHeight",    settings.lapisCenterHeight);
                settings.lapisSpread            = JsonUtils.getInt(json,    "lapisSpread",          settings.lapisSpread);
            } catch (Exception ignore) {}

            return settings;
        }

        @Override
        public JsonElement serialize(ChunkProviderSettingsRTG.Factory factory, Type type, JsonSerializationContext context) {

            JsonObject json = new JsonObject();

// TODO: [Generator settings] Disable fixedBiome and biomeSize for now as they require modification to the GenLayer classes to work.
//          json.addProperty("fixedBiome",           factory.fixedBiome);
//          json.addProperty("biomeSize",            factory.biomeSize);
            json.addProperty("seaLevel",             factory.seaLevel);
            json.addProperty("oceanWaves",           factory.oceanWaves);

            json.addProperty("useBoulders",          factory.useBoulders);
            json.addProperty("boulderMult",          factory.boulderMult);
            json.addProperty("sandDuneHeight",       factory.sandDuneHeight);
//          json.addProperty("snowDuneHeight",       factory.snowDuneHeight);
            json.addProperty("useSnowLayers",        factory.useSnowLayers);

            json.addProperty("bedrockLayers",        factory.bedrockLayers);
            json.addProperty("useCaves",             factory.useCaves);
            json.addProperty("caveChance",           factory.caveChance);
            json.addProperty("caveDensity",          factory.caveDensity);
            json.addProperty("useRavines",           factory.useRavines);
            json.addProperty("ravineChance",         factory.ravineChance);

            json.addProperty("useVolcanos",          factory.useVolcanos);
            json.addProperty("volcanosErupt",        factory.volcanosErupt);
            json.addProperty("volcanosChance",       factory.volcanosChance);
            json.addProperty("volcanosCalderaMult",  factory.volcanosCalderaMult);
            json.addProperty("volcanoConduits",      factory.volcanoConduits);
            json.addProperty("volcanoConduitDepth",  factory.volcanoConduitDepth);


            json.addProperty("riverSize",            factory.riverSize);
            json.addProperty("riverFrequency",       factory.riverFrequency);
            json.addProperty("riverBendMult",        factory.riverBendMult);
            json.addProperty("riverCutOffAmpl",      factory.riverCutOffAmpl);
            json.addProperty("riverCutOffScale",     factory.riverCutOffScale);

            json.addProperty("RTGlakeSizeMult",      factory.RTGlakeSizeMult);
            json.addProperty("RTGlakeFreqMult",      factory.RTGlakeFreqMult);
            json.addProperty("RTGlakeShoreBend",     factory.RTGlakeShoreBend);

            json.addProperty("useWaterLakes",        factory.useWaterLakes);
            json.addProperty("waterLakeChance",      factory.waterLakeChance);
            json.addProperty("useLavaLakes",         factory.useLavaLakes);
            json.addProperty("lavaLakeChance",       factory.lavaLakeChance);

            json.addProperty("useWaterUndLakes",     factory.useWaterUndLakes);
            json.addProperty("waterUndLakeChance",   factory.waterUndLakeChance);
            json.addProperty("useLavaUndLakes",      factory.useLavaUndLakes);
            json.addProperty("lavaUndLakeChance",    factory.lavaUndLakeChance);

            json.addProperty("waterSpoutChance",     factory.waterSpoutChance);
            json.addProperty("lavaSpoutChance",      factory.lavaSpoutChance);


            json.addProperty("useVillages",          factory.useVillages);
            json.addProperty("villageSize",          factory.villageSize);
            json.addProperty("villageMinDistance",   factory.villageMinDistance);
            json.addProperty("villageMaxDistance",   factory.villageMaxDistance);

            json.addProperty("useMineShafts",        factory.useMineShafts);
            json.addProperty("mineShaftChance",      factory.mineShaftChance);

            json.addProperty("useDungeons",          factory.useDungeons);
            json.addProperty("dungeonChance",        factory.dungeonChance);

            json.addProperty("useTemples",           factory.useTemples);
            json.addProperty("templeMinDistance",    factory.templeMinDistance);
            json.addProperty("templeMaxDistance",    factory.templeMaxDistance);

            json.addProperty("useMonuments",         factory.useMonuments);
            json.addProperty("monumentSpacing",      factory.monumentSpacing);
            json.addProperty("monumentSeparation",   factory.monumentSeparation);

// TODO: Disabled until >= MC 1.11
//          json.addProperty("useMansions",          factory.useMansions);
//          json.addProperty("mansionSpacing",       factory.mansionSpacing);

            json.addProperty("useStrongholds",       factory.useStrongholds);
            json.addProperty("strongholdCount",      factory.strongholdCount);
            json.addProperty("strongholdDistance",   factory.strongholdDistance);
            json.addProperty("strongholdSpread",     factory.strongholdSpread);


            json.addProperty("dirtSize",             factory.dirtSize);
            json.addProperty("dirtCount",            factory.dirtCount);
            json.addProperty("dirtMinHeight",        factory.dirtMinHeight);
            json.addProperty("dirtMaxHeight",        factory.dirtMaxHeight);

            json.addProperty("gravelSize",           factory.gravelSize);
            json.addProperty("gravelCount",          factory.gravelCount);
            json.addProperty("gravelMinHeight",      factory.gravelMinHeight);
            json.addProperty("gravelMaxHeight",      factory.gravelMaxHeight);

            json.addProperty("graniteSize",          factory.graniteSize);
            json.addProperty("graniteCount",         factory.graniteCount);
            json.addProperty("graniteMinHeight",     factory.graniteMinHeight);
            json.addProperty("graniteMaxHeight",     factory.graniteMaxHeight);

            json.addProperty("dioriteSize",          factory.dioriteSize);
            json.addProperty("dioriteCount",         factory.dioriteCount);
            json.addProperty("dioriteMinHeight",     factory.dioriteMinHeight);
            json.addProperty("dioriteMaxHeight",     factory.dioriteMaxHeight);

            json.addProperty("andesiteSize",         factory.andesiteSize);
            json.addProperty("andesiteCount",        factory.andesiteCount);
            json.addProperty("andesiteMinHeight",    factory.andesiteMinHeight);
            json.addProperty("andesiteMaxHeight",    factory.andesiteMaxHeight);

            json.addProperty("coalSize",             factory.coalSize);
            json.addProperty("coalCount",            factory.coalCount);
            json.addProperty("coalMinHeight",        factory.coalMinHeight);
            json.addProperty("coalMaxHeight",        factory.coalMaxHeight);

            json.addProperty("ironSize",             factory.ironSize);
            json.addProperty("ironCount",            factory.ironCount);
            json.addProperty("ironMinHeight",        factory.ironMinHeight);
            json.addProperty("ironMaxHeight",        factory.ironMaxHeight);

            json.addProperty("goldSize",             factory.goldSize);
            json.addProperty("goldCount",            factory.goldCount);
            json.addProperty("goldMinHeight",        factory.goldMinHeight);
            json.addProperty("goldMaxHeight",        factory.goldMaxHeight);

            json.addProperty("redstoneSize",         factory.redstoneSize);
            json.addProperty("redstoneCount",        factory.redstoneCount);
            json.addProperty("redstoneMinHeight",    factory.redstoneMinHeight);
            json.addProperty("redstoneMaxHeight",    factory.redstoneMaxHeight);

            json.addProperty("diamondSize",          factory.diamondSize);
            json.addProperty("diamondCount",         factory.diamondCount);
            json.addProperty("diamondMinHeight",     factory.diamondMinHeight);
            json.addProperty("diamondMaxHeight",     factory.diamondMaxHeight);

            json.addProperty("lapisSize",            factory.lapisSize);
            json.addProperty("lapisCount",           factory.lapisCount);
            json.addProperty("lapisCenterHeight",    factory.lapisCenterHeight);
            json.addProperty("lapisSpread",          factory.lapisSpread);
            return json;
        }
    }
}
