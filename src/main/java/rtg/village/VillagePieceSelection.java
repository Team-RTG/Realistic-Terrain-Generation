package rtg.village;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.registry.VillagerRegistry;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureVillagePieces;
//import enhancedbiomes.handlers.VillagerRegistryEB;
import rtg.event.*;
import rtg.village.StructureVillagePiecesRTG.Start;
import rtg.village.oriental.*;
import rtg.village.standard.*;
/*import enhancedbiomes.world.biomestats.BiomeCategorisation;
import static enhancedbiomes.world.biome.EnhancedBiomesArchipelago.*;
import static enhancedbiomes.world.biome.EnhancedBiomesBiome.*;
import static enhancedbiomes.world.biome.EnhancedBiomesGrass.*;
import static enhancedbiomes.world.biome.EnhancedBiomesPlains.*;
import static enhancedbiomes.world.biome.EnhancedBiomesRock.*;
import static enhancedbiomes.world.biome.EnhancedBiomesSand.*;
import static enhancedbiomes.world.biome.EnhancedBiomesSandstone.*;
import static enhancedbiomes.world.biome.EnhancedBiomesSnow.*;
import static enhancedbiomes.world.biome.EnhancedBiomesTropical.*;
import static enhancedbiomes.world.biome.EnhancedBiomesWetland.*;
import static enhancedbiomes.world.biome.EnhancedBiomesWoodland.*;*/
import static net.minecraft.world.biome.BiomeGenBase.*;

public class VillagePieceSelection
{
	public static void registerVillagePieces() {
		//D - Default, O - Oriental 
		//H - House, F - Field, L - Light, C - Council, S - Small, T - Tower, P - Path, W - Well
		MapGenStructureIO.func_143031_a(House1.class, "EB_D_H1");

		MapGenStructureIO.func_143031_a(House2.class, "EB_D_H2");

		MapGenStructureIO.func_143031_a(House3.class, "EB_D_H3");

		MapGenStructureIO.func_143031_a(House4Garden.class, "EB_D_H4");

		MapGenStructureIO.func_143031_a(Church.class, "EB_D_T");

		MapGenStructureIO.func_143031_a(Hall.class, "EB_D_C");

		MapGenStructureIO.func_143031_a(Field1.class, "EB_D_F1");

		MapGenStructureIO.func_143031_a(Field2.class, "EB_D_F2");

		MapGenStructureIO.func_143031_a(Torch.class, "EB_D_L");
		MapGenStructureIO.func_143031_a(Lantern.class, "EB_O_L");

		MapGenStructureIO.func_143031_a(WoodHut.class, "EB_D_S");
		MapGenStructureIO.func_143031_a(Torii.class, "EB_O_S");

		MapGenStructureIO.func_143031_a(Start.class, "EB_Start");
		MapGenStructureIO.func_143031_a(Path.class, "EB_P");
		MapGenStructureIO.func_143031_a(Well.class, "EB_W");
	}

	public static List getStructureVillageWeightedPieceList(Random p_75084_0_, int villageSize, BiomeGenBase biome) {
		ArrayList arraylist = new ArrayList();
		//Oriental
		/*if(isOriental(biome)) {
			arraylist.add(new StructureVillagePieces.PieceWeight(Torii.class, 		3, 	MathHelper.getRandomIntegerInRange(p_75084_0_, 2 + villageSize, 5 + villageSize * 3)));
			//TODO Exchange torii generation values
			arraylist.add(new StructureVillagePiecesRTG.PieceWeight(Torii.class, 		20,	MathHelper.getRandomIntegerInRange(p_75084_0_, 0 + villageSize, 2 + villageSize)));
		}*/

		//Standard
		//else {
		arraylist.add(new StructureVillagePieces.PieceWeight(House4Garden.class, 4, MathHelper.getRandomIntegerInRange(p_75084_0_, 2 + villageSize, 4 + villageSize * 2)));
		arraylist.add(new StructureVillagePieces.PieceWeight(Church.class, 20, MathHelper.getRandomIntegerInRange(p_75084_0_, 0 + villageSize, 1 + villageSize)));
		arraylist.add(new StructureVillagePieces.PieceWeight(House1.class, 20, MathHelper.getRandomIntegerInRange(p_75084_0_, 0 + villageSize, 2 + villageSize)));
		arraylist.add(new StructureVillagePieces.PieceWeight(WoodHut.class, 3, MathHelper.getRandomIntegerInRange(p_75084_0_, 2 + villageSize, 5 + villageSize * 3)));
		arraylist.add(new StructureVillagePieces.PieceWeight(Hall.class, 15, MathHelper.getRandomIntegerInRange(p_75084_0_, 0 + villageSize, 2 + villageSize)));
		arraylist.add(new StructureVillagePieces.PieceWeight(Field1.class, 3, MathHelper.getRandomIntegerInRange(p_75084_0_, 1 + villageSize, 4 + villageSize)));
		arraylist.add(new StructureVillagePieces.PieceWeight(Field2.class, 3, MathHelper.getRandomIntegerInRange(p_75084_0_, 2 + villageSize, 4 + villageSize * 2)));
		arraylist.add(new StructureVillagePieces.PieceWeight(House2.class, 15, MathHelper.getRandomIntegerInRange(p_75084_0_, 0, 1 + villageSize)));
		arraylist.add(new StructureVillagePieces.PieceWeight(House3.class, 8, MathHelper.getRandomIntegerInRange(p_75084_0_, 0 + villageSize, 3 + villageSize * 2)));
		//}

		//Mod
		VillagerRegistry.addExtraVillageComponents(arraylist, p_75084_0_, villageSize);

		Iterator iterator = arraylist.iterator();

		while(iterator.hasNext()) {
			if(((StructureVillagePieces.PieceWeight) iterator.next()).villagePiecesLimit == 0) {
				iterator.remove();
			}
		}

		return arraylist;
	}

	public static StructureVillagePieces.Village getPiece(Start start, StructureVillagePieces.PieceWeight pieceWeight, List list, Random rand, int p4, int p5, int p6, int p7, int p8) {
		Class oclass = pieceWeight.villagePieceClass;
		Object object = null;

		//Standard
		if(oclass == House4Garden.class) object = House4Garden.getPiece(start, list, rand, p4, p5, p6, p7, p8);
		else if(oclass == Church.class) object = Church.getPiece(start, list, rand, p4, p5, p6, p7, p8);
		else if(oclass == House1.class) object = House1.getPiece(start, list, rand, p4, p5, p6, p7, p8);
		else if(oclass == WoodHut.class) object = WoodHut.getPiece(start, list, rand, p4, p5, p6, p7, p8);
		else if(oclass == Hall.class) object = Hall.getPiece(start, list, rand, p4, p5, p6, p7, p8);
		else if(oclass == Field1.class) object = Field1.getPiece(start, list, rand, p4, p5, p6, p7, p8);
		else if(oclass == Field2.class) object = Field2.getPiece(start, list, rand, p4, p5, p6, p7, p8);
		else if(oclass == House2.class) object = House2.getPiece(start, list, rand, p4, p5, p6, p7, p8);
		else if(oclass == House3.class) object = House3.getPiece(start, list, rand, p4, p5, p6, p7, p8);

		//Oriental
	//	else if(oclass == Torii.class) object = Torii.getPiece(start, list, rand, p4, p5, p6, p7, p8);

		//Mod
		else object = VillagerRegistry.getVillageComponent(pieceWeight, start, list, rand, p4, p5, p6, p7, p8);

		return (StructureVillagePieces.Village) object;
	}

	public static StructureVillagePiecesRTG.Village getTorch(BiomeGenBase biome, Start p_75081_0_, int p_75081_7_, Random p_75081_2_, StructureBoundingBox structureboundingbox, int p_75081_6_) {
		//if(BiomeCategorisation.isOriental(biome)) return new Lantern(p_75081_0_, p_75081_7_, p_75081_2_, structureboundingbox, p_75081_6_);
		return new Torch(p_75081_0_, p_75081_7_, p_75081_2_, structureboundingbox, p_75081_6_);
	}

	public static Path getPath(Start p_75080_0_, int p_75080_7_, Random p_75080_2_, StructureBoundingBox structureboundingbox, int p_75080_6_) {
		return new Path(p_75080_0_, p_75080_7_, p_75080_2_, structureboundingbox, p_75080_6_);
	}

	public static StructureBoundingBox getTorchBoundingBox(BiomeGenBase biome, StructureVillagePiecesRTG.Start p_74904_0_, List p_74904_1_, Random p_74904_2_, int p_74904_3_, int p_74904_4_, int p_74904_5_, int p_74904_6_) {
	//	if(BiomeCategorisation.isOriental(biome)) return Lantern.func_74904_a(p_74904_0_, p_74904_1_, p_74904_2_, p_74904_3_, p_74904_4_, p_74904_5_, p_74904_6_);
		return Torch.func_74904_a(p_74904_0_, p_74904_1_, p_74904_2_, p_74904_3_, p_74904_4_, p_74904_5_, p_74904_6_);
	}

	public static StructureBoundingBox getPathBoundingBox(BiomeGenBase biome, StructureVillagePiecesRTG.Start p_74904_0_, List p_74904_1_, Random p_74904_2_, int p_74904_3_, int p_74904_4_, int p_74904_5_, int p_74904_6_) {
		return Path.func_74933_a(p_74904_0_, p_74904_1_, p_74904_2_, p_74904_3_, p_74904_4_, p_74904_5_, p_74904_6_);
	}
}
