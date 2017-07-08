package rtg.api.config.property;

import net.minecraftforge.common.config.Configuration;

public class ConfigProperty {

    public String name;
    public String category;
    public String description;
    public boolean restricted;

    public ConfigProperty(String name, String category, String description, boolean restricted) {

        this.name = name;
        this.category = category;
        this.description = description;
        this.restricted = restricted;
    }

    public ConfigProperty(String name, String category, String description) {

        this.name = name;
        this.category = category;
        this.description = description;
        this.restricted = false;
    }

    public void formatDescription() {

        if (!this.description.isEmpty()) {
            this.description += Configuration.NEW_LINE;
        }
    }

    public ConfigProperty restrict() {
        this.restricted = true;
        return this;
    }
}
