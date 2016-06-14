package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoCactus;
import rtg.world.biome.deco.DecoDoubleGrass;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.biome.deco.collection.DecoCollectionDesertRiver;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGAcaciaBucheri;
import rtg.world.gen.surface.vanilla.SurfaceVanillaSavannaPlateauM;
import rtg.world.gen.terrain.vanilla.TerrainVanillaSavannaPlateauM;

public class RealisticBiomeVanillaSavannaPlateauM extends RealisticBiomeVanillaBase
{
    public static BiomeGenBase standardBiome = BiomeGenBase.savannaPlateau;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(standardBiome.biomeID + MUTATION_ADDEND);
    
    public static Block topBlock = mutationBiome.topBlock;
    public static Block fillerBlock = mutationBiome.fillerBlock;
    
    public RealisticBiomeVanillaSavannaPlateauM(BiomeConfig config)
    {
    
        super(config, 
            mutationBiome,
            BiomeGenBase.river,
            new TerrainVanillaSavannaPlateauM(true, 35f, 160f, 60f, 40f, 69f),
            new SurfaceVanillaSavannaPlateauM(config, topBlock, (byte)0, fillerBlock, (byte)0, 0)
        );
        this.noLakes=true;
        
		/**
		 * ##################################################
		 * # DECORATIONS (ORDER MATTERS)
		 * ##################################################
		 */
    	
        this.addDecoCollection(new DecoCollectionDesertRiver());
        
		DecoBoulder decoBoulder1 = new DecoBoulder();
		decoBoulder1.boulderBlock = Blocks.cobblestone;
		decoBoulder1.maxY = 80;
		decoBoulder1.chance = 24;
		this.addDeco(decoBoulder1);
		
		DecoBoulder decoBoulder2 = new DecoBoulder();
		decoBoulder2.boulderBlock = Blocks.cobblestone;
		decoBoulder1.minY = 110;
		decoBoulder2.chance = 24;
		this.addDeco(decoBoulder2);

        DecoShrub acaciaShrub = new DecoShrub();
        acaciaShrub.logBlock = Blocks.log2;
        acaciaShrub.logMeta = (byte)0;
        acaciaShrub.leavesBlock = Blocks.leaves2;
        acaciaShrub.leavesMeta = (byte)0;
        acaciaShrub.maxY = 160;
        acaciaShrub.strengthFactor = 3f;
        acaciaShrub.chance = 9;
		this.addDeco(acaciaShrub);
		
		TreeRTG acaciaTree = new TreeRTGAcaciaBucheri();
		acaciaTree.logBlock = Blocks.log2;
		acaciaTree.logMeta = (byte)0;
		acaciaTree.leavesBlock = Blocks.leaves2;
		acaciaTree.leavesMeta = (byte)0;
		acaciaTree.minTrunkSize = 12;
		acaciaTree.maxTrunkSize = 16;
		
		this.addTree(acaciaTree);
		
		DecoTree acaciaTrees = new DecoTree(acaciaTree);
		acaciaTrees.strengthFactorForLoops = 2f;
		acaciaTrees.treeType = TreeType.RTG_TREE;
		acaciaTrees.treeCondition = TreeCondition.RANDOM_CHANCE;
		acaciaTrees.treeConditionChance = 12;
		acaciaTrees.maxY = 160;
		this.addDeco(acaciaTrees);

		DecoCactus decoCactus = new DecoCactus();
		decoCactus.maxY = 160;
		decoCactus.loops = 60;
		decoCactus.chance = 8;
        this.addDeco(decoCactus);
        
		DecoDoubleGrass decoDoubleGrass = new DecoDoubleGrass();
		decoDoubleGrass.maxY = 128;
		decoDoubleGrass.strengthFactor = 3f;
        this.addDeco(decoDoubleGrass);
        
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.strengthFactor = 10f;
        this.addDeco(decoGrass);
    }
}
