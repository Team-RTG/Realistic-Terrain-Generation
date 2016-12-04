package rtg.api.config.property;

import net.minecraftforge.common.config.Configuration;

public class ConfigProperty {

    public Type type;
    public String name;
    public String category;
    public String description;

    public ConfigProperty(Type type, String name, String category, String description) {

        this.type = type;
        this.name = name;
        this.category = category;
        this.description = description;
    }

    public void formatDescription() {

        if (!this.description.isEmpty()) {
            this.description += Configuration.NEW_LINE;
        }
    }

    public enum Type {
        INTEGER,
        FLOAT,
        BOOLEAN,
        STRING
    }
}
