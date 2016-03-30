package rtg.api.biome;


import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import org.apache.commons.lang3.StringUtils;
import rtg.api.biome.BiomeConfigProperty.IBiomePropertyEnum.Type;

import static rtg.api.biome.BiomeConfigProperty.IBiomePropertyEnum.Type.BLOCK;
import static rtg.api.biome.BiomeConfigProperty.IBiomePropertyEnum.Type.BOOLEAN;

public class BiomeConfigProperty {

    public String id;
    public Type type;
    public String name;
    public String description;

    public int minValue;
    public int maxValue;
    public int valueInt;
    public boolean valueBoolean;
    public String valueString;
    public IBlockState valueBlock;

    public BiomeConfigProperty(String id, Type type, String name, String description) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.minValue = type.minValue;
        this.maxValue = type.maxValue;
        this.valueInt = type.valueInt;
        this.valueBoolean = type.valueBoolean;
        this.valueString = type.valueString;
    }

    public enum BiomeProperty implements IBiomePropertyEnum{
        ALLOW_VILLAGES(BOOLEAN.setDefault(true)),
        USE_RTG_DECORATIONS(BOOLEAN.setDefault(true)),
        USE_RTG_SURFACES(BOOLEAN.setDefault(true)),
        SURFACE_TOP_BLOCK(BLOCK.setDefault(Blocks.grass.getDefaultState())),
        SURFACE_FILLER_BLOCK(BLOCK.setDefault(Blocks.grass.getDefaultState()));

        public final String localized;
        public final String comment;
        public final Type type;
        public final BiomeConfigProperty prop;

        BiomeProperty(Type type) {
            this(type, "");
        }
        BiomeProperty(Type type, String comment) {
            this.type = type;
            this.localized = StringUtils.capitalize(this.name().replaceAll("_", " "));
            this.comment = comment;
            this.prop = new BiomeConfigProperty(name(), type, localized, comment);
        }
    }

    public interface IBiomePropertyEnum {
        enum Type {
            INTEGER,
            BOOLEAN,
            STRING,
            BLOCK;

            public int minValue;
            public int maxValue;
            public int valueInt;
            public boolean valueBoolean;
            public String valueString;

            public IBlockState valueBlock;

            public Type setMin(int minValue) {
                this.minValue = minValue;
                return this;
            }
            public Type setMax(int maxValue) {
                this.maxValue = maxValue;
                return this;
            }

            public Type setDefault(int valueInt) {
                this.valueInt = valueInt;
                return this;
            }

            public Type setDefault(boolean valueBoolean) {
                this.valueBoolean = valueBoolean;
                return this;
            }

            public Type setDefault(String valueString) {
                this.valueString = valueString;
                return this;
            }

            public Type setDefault(IBlockState valueBlock) {
                this.valueBlock = valueBlock;
                return this;
            }
        }

        String name();
    }
}
