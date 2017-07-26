package com.tom.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

import com.tom.api.client.MultiblockRenderer;
import com.tom.apis.TomsModUtils;
import com.tom.model.IBaseModel;

public class TileEntityMultiblockCustomRenderer<T extends TileEntity> extends MultiblockRenderer<T> {
	public TileEntityMultiblockCustomRenderer(IBaseModel model) {
		this.model = model;
	}

	private IBaseModel model;

	@Override
	public void render(double x, double y, double z, float partialTicks, T te) {
		GlStateManager.pushMatrix();
		GlStateManager.enableLighting();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		ResourceLocation resourcelocation = model.getModelTexture(te);
		// float f = 0.6666667F;
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glScalef(1.0F, -1F, -1F);
		if (te instanceof ICustomModelledTileEntity && model.rotateModelBasedOnBlockMeta()) {
			EnumFacing facing = ((ICustomModelledTileEntity) te).getFacing();
			TomsModUtils.rotateMatrixByMetadata(facing.ordinal() % 6);
			// if(facing == EnumFacing.NORTH) GL11.glTranslatef(0.9999F,
			// -2.156F, 0.5F);
		}
		if (resourcelocation != null) {
			this.bindTexture(resourcelocation);
			GlStateManager.pushMatrix();
			// GlStateManager.enableRescaleNormal();
			// GlStateManager.scale(f, -f, -f);
			// GlStateManager.translate(0.0F, -0.3125F, -0.4375F);
			this.model.renderStatic(0.0625F, te);
			this.model.renderDynamic(0.0625F, te, partialTicks);
			GlStateManager.popMatrix();
		}
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
	}
}