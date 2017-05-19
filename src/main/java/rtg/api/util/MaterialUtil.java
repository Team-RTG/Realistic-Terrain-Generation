package rtg.api.util;

import net.minecraft.block.material.Material;

/**
 * Created by WhichOnesPink on 19/05/2017.
 */
public class MaterialUtil {

    private Material material = null;

    public MaterialUtil(String name) {

        if (name.equalsIgnoreCase("AIR")) {
            material = Material.AIR;
        }
        else if (name.equalsIgnoreCase("ANVIL")) {
            material = Material.ANVIL;
        }
        else if (name.equalsIgnoreCase("BARRIER")) {
            material = Material.BARRIER;
        }
        else if (name.equalsIgnoreCase("CACTUS")) {
            material = Material.CACTUS;
        }
        else if (name.equalsIgnoreCase("CAKE")) {
            material = Material.CAKE;
        }
        else if (name.equalsIgnoreCase("CARPET")) {
            material = Material.CARPET;
        }
        else if (name.equalsIgnoreCase("CIRCUITS")) {
            material = Material.CIRCUITS;
        }
        else if (name.equalsIgnoreCase("CLAY")) {
            material = Material.CLAY;
        }
        else if (name.equalsIgnoreCase("CLOTH")) {
            material = Material.CLOTH;
        }
        else if (name.equalsIgnoreCase("CORAL")) {
            material = Material.CORAL;
        }
        else if (name.equalsIgnoreCase("CRAFTED_SNOW")) {
            material = Material.CRAFTED_SNOW;
        }
        else if (name.equalsIgnoreCase("DRAGON_EGG")) {
            material = Material.DRAGON_EGG;
        }
        else if (name.equalsIgnoreCase("FIRE")) {
            material = Material.FIRE;
        }
        else if (name.equalsIgnoreCase("GLASS")) {
            material = Material.GLASS;
        }
        else if (name.equalsIgnoreCase("GOURD")) {
            material = Material.GOURD;
        }
        else if (name.equalsIgnoreCase("GRASS")) {
            material = Material.GRASS;
        }
        else if (name.equalsIgnoreCase("GROUND")) {
            material = Material.GROUND;
        }
        else if (name.equalsIgnoreCase("ICE")) {
            material = Material.ICE;
        }
        else if (name.equalsIgnoreCase("IRON")) {
            material = Material.IRON;
        }
        else if (name.equalsIgnoreCase("LAVA")) {
            material = Material.LAVA;
        }
        else if (name.equalsIgnoreCase("LEAVES")) {
            material = Material.LEAVES;
        }
        else if (name.equalsIgnoreCase("PACKED_ICE")) {
            material = Material.PACKED_ICE;
        }
        else if (name.equalsIgnoreCase("PISTON")) {
            material = Material.PISTON;
        }
        else if (name.equalsIgnoreCase("PLANTS")) {
            material = Material.PLANTS;
        }
        else if (name.equalsIgnoreCase("PORTAL")) {
            material = Material.PORTAL;
        }
        else if (name.equalsIgnoreCase("REDSTONE_LIGHT")) {
            material = Material.REDSTONE_LIGHT;
        }
        else if (name.equalsIgnoreCase("ROCK")) {
            material = Material.ROCK;
        }
        else if (name.equalsIgnoreCase("SAND")) {
            material = Material.SAND;
        }
        else if (name.equalsIgnoreCase("SNOW")) {
            material = Material.SNOW;
        }
        else if (name.equalsIgnoreCase("SPONGE")) {
            material = Material.SPONGE;
        }
        else if (name.equalsIgnoreCase("STRUCTURE_VOID")) {
            material = Material.STRUCTURE_VOID;
        }
        else if (name.equalsIgnoreCase("TNT")) {
            material = Material.TNT;
        }
        else if (name.equalsIgnoreCase("VINE")) {
            material = Material.VINE;
        }
        else if (name.equalsIgnoreCase("WATER")) {
            material = Material.WATER;
        }
        else if (name.equalsIgnoreCase("WEB")) {
            material = Material.WEB;
        }
        else if (name.equalsIgnoreCase("WOOD")) {
            material = Material.WOOD;
        }
        else {
            throw new RuntimeException("Invalid material name.");
        }
    }

    public Material getMaterial() {
        return this.material;
    }
}
