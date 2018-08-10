package rtg.api.config.property;

import net.minecraftforge.common.config.Configuration;


public abstract class ConfigProperty {

    private Type type;
    private String name;
    private String category;
    private String description;

    ConfigProperty(Type type, String name, String category, String description) {
        this.type = type;
        this.name = name;
        this.category = category;
        this.description = description;
    }

    void formatDescription() {
        if (!this.description.isEmpty()) {
            this.description += Configuration.NEW_LINE;
        }
    }

    public Type getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public String getCategory() {
        return this.category;
    }

    public String getDescription() {
        return this.description;
    }

    public enum Type {
        INTEGER,
        INTEGER_ARRAY,
        FLOAT,
        DOUBLE_ARRAY,
        BOOLEAN,
        STRING,
        STRING_ARRAY
    }
}
