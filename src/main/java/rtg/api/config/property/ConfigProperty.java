package rtg.api.config.property;

import net.minecraftforge.common.config.Configuration;

public class ConfigProperty {

    public String name;
    public String category;
    public String description;

    public ConfigProperty(String name, String category, String description) {

        this.name = name;
        this.category = category;
        this.description = description;
    }

    public void formatDescription() {

        if (!this.description.isEmpty()) {
            this.description += Configuration.NEW_LINE;
        }
    }
}
