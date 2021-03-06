// Date: 2016.12.08. 16:36:38
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX
package com.tom.storage.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import com.tom.client.EventHandlerClient;
import com.tom.lib.Configs;
import com.tom.model.IBaseModel;
import com.tom.storage.tileentity.TileEntityStorageNetworkController;
import com.tom.storage.tileentity.TileEntityStorageNetworkController.ControllerState;

public class ModelControllerScreen extends ModelBase implements IBaseModel {
	// fields
	ModelRenderer screenStand;
	ModelRenderer screenStand2;
	ModelRenderer screen;
	ModelRenderer screenOff;

	public ModelControllerScreen() {
		textureWidth = 64;
		textureHeight = 64;

		screenStand = new ModelRenderer(this, 0, 0);
		screenStand.addBox(0F, 0F, 0F, 1, 4, 1);
		screenStand.setRotationPoint(-7F, 5F, 0F);
		screenStand.setTextureSize(64, 64);
		screenStand.mirror = true;
		setRotation(screenStand, -0.4363323F, 0F, 0F);
		screenStand2 = new ModelRenderer(this, 0, 0);
		screenStand2.addBox(0F, 0F, 0F, 1, 4, 1);
		screenStand2.setRotationPoint(6F, 5F, 0F);
		screenStand2.setTextureSize(64, 64);
		screenStand2.mirror = true;
		setRotation(screenStand2, -0.4363323F, 0F, 0F);
		screen = new ModelRenderer(this, 5, 1);
		screen.addBox(0F, 0F, 0F, 12, 3, 0);
		screen.setRotationPoint(-6F, 5.3F, 0.5F);
		screen.setTextureSize(64, 64);
		screen.mirror = true;
		setRotation(screen, -0.4363323F, 0F, 0F);
		screenOff = new ModelRenderer(this, 5, 5);
		screenOff.addBox(0F, 0F, 0F, 12, 3, 0);
		screenOff.setRotationPoint(-6F, 5.3F, 0.5F);
		screenOff.setTextureSize(64, 64);
		screenOff.mirror = true;
		setRotation(screenOff, -0.4363323F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		screenStand.render(f5);
		screenStand2.render(f5);
		screen.render(f5);
		screenOff.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

	@Override
	public void renderStatic(float size, TileEntity te) {
		TileEntityStorageNetworkController c = (TileEntityStorageNetworkController) te;
		ControllerState state = c.getState();
		if (state != ControllerState.OFF)
			screen.render(size);
		else
			screenOff.render(size);
		screenStand.render(size);
		screenStand2.render(size);
		if (state != ControllerState.OFF) {
			GL11.glTranslated(-0.358, 0.35, 0.01);
			GL11.glRotated(-25, 1, 0, 0);
			GlStateManager.disableLighting();
			float f = 0.007F;
			GL11.glScalef(f, f, f);
			// Minecraft.getMinecraft().fontRendererObj.drawString("Online", 0,
			// 0, 0x0000FF);
			try {
				EventHandlerClient.lcdFont.drawString(state.getText1(te.getWorld().getTotalWorldTime(), 13, c.getCmd()).replace(' ', '\u0000'), 0, 0, 0x00D8DE);// 0x00D8DE
				EventHandlerClient.lcdFont.drawString(state.getText2(te.getWorld().getTotalWorldTime(), 13, c.getCmd()).replace(' ', '\u0000'), 0, 10, 0x00D8DE);// 0x00BFFF
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void renderDynamic(float size, TileEntity te, float partialTicks) {
	}

	@Override
	public ResourceLocation getModelTexture(TileEntity tile) {
		return Configs.controllerScreenModel;
	}

	@Override
	public boolean rotateModelBasedOnBlockMeta() {
		return true;
	}

}
