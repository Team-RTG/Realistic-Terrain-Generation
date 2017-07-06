package rtg.api.config.property;


import net.minecraft.block.state.IBlockState;

public class ConfigPropertyBlockstate extends ConfigProperty {

    public IBlockState valueBlockstate;

    public ConfigPropertyBlockstate(String name, String category, String description, IBlockState defaultValue) {

        super(name, category, description);

        this.valueBlockstate = defaultValue;

        this.formatDescription();
    }

    public IBlockState get() {
        return this.valueBlockstate;
    }

    public void set(IBlockState value) {
        this.valueBlockstate = value;
    }
}
