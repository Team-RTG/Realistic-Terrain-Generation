package enhancedbiomes.api.internal;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class BlockModOreRenderer implements ISimpleBlockRenderingHandler {

	public static final int renderID = RenderingRegistry.getNextAvailableRenderId();

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		RenderModOre rendererEB = new RenderModOre(renderer.blockAccess, renderer);
		rendererEB.renderBlockAsItem(block, metadata, 1);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess blockAccess, int x, int y, int z, Block block, int modelID, RenderBlocks renderer) {
		RenderModOre rendererEB = new RenderModOre(renderer.blockAccess, renderer);

		if (!renderer.hasOverrideBlockTexture())
			rendererEB.renderOreBlock(block, x, y, z);
		else renderer.renderStandardBlock(block, x, y, z);

		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return renderID;
	}
}
