package rtg.api.config;

import net.minecraft.block.state.IBlockState;
import rtg.api.util.debug.RTGException;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * An object that holds config properties
 * Does not provide a way to store those properties in a file
 * @see ModConfig for that functionality.
 * @author topisani
 */
public class Config {

    public ArrayList<ConfigProperty> properties;

    public Config() {
        this.properties = getProperties(this);
    }

    protected ArrayList<ConfigProperty> getProperties(Object holder) {
        Field[] declaredFields = holder.getClass().getDeclaredFields();
        ArrayList<ConfigProperty> properties = new ArrayList<>();
        for (Field field : declaredFields) {
            if (Modifier.isPublic(field.getModifiers()) &&
                    Modifier.isStatic(field.getModifiers()) &&
                    Modifier.isFinal(field.getModifiers()) &&
                    field.getType() == ConfigProperty.class) {
                RTGException.ignore(() -> properties.add((ConfigProperty) field.get(holder)));
            }
        }
        return properties;
    }
    public ConfigProperty getProp(String id) {
        for (int i = 0; i < this.properties.size(); i++) {

            if (this.properties.get(i).getID().contentEquals(id)) {
                return this.properties.get(i);
            }
        }
        return null;
    }

    public boolean _boolean(String id) {
        try {
            return getProp(id).valueBoolean;
        } catch (Exception e) {
            throw new RuntimeException("Config property " + id + " could not be found. Reason: " + e.getMessage());
        }
    }

    public int _int(String id) {
        try {

            return getProp(id).valueInt;
        } catch (Exception e) {

            throw new RuntimeException("Config property " + id + " could not be found. Reason: " + e.getMessage());
        }
    }

    public String _string(String id) {
        try {

            return getProp(id).valueString;
        } catch (Exception e) {

            throw new RuntimeException("Config property " + id + " could not be found. Reason: " + e.getMessage());
        }
    }

    public IBlockState _block(String id) {
        try {
            return getProp(id).valueBlock;
        } catch (Exception e) {

            throw new RuntimeException("Config property " + id + " could not be found. Reason: " + e.getMessage());
        }
    }

}
