// Date: 2016.02.20. 20:53:02
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package com.tom.core.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSaw extends ModelBase {
	// fields
	ModelRenderer sawHandle;
	ModelRenderer handle1;
	ModelRenderer sawBlade;
	ModelRenderer handle2;
	ModelRenderer blade2;
	ModelRenderer handle3;

	public ModelSaw() {
		textureWidth = 64;
		textureHeight = 64;

		sawHandle = new ModelRenderer(this, 8, 18);
		sawHandle.addBox(0F, 0F, 0F, 1, 1, 4);
		sawHandle.setRotationPoint(0F, 15F, 0F);
		sawHandle.setTextureSize(64, 64);
		sawHandle.mirror = true;
		setRotation(sawHandle, 0F, 0F, 0F);
		handle1 = new ModelRenderer(this, 9, 18);
		handle1.addBox(0F, 0F, 0F, 1, 1, 1);
		handle1.setRotationPoint(0F, 14F, 0F);
		handle1.setTextureSize(64, 64);
		handle1.mirror = true;
		setRotation(handle1, 0F, 0F, 0F);
		sawBlade = new ModelRenderer(this, 32, 0);
		sawBlade.addBox(0F, 0F, 0F, 1, 8, 3);
		sawBlade.setRotationPoint(0F, 16F, 0F);
		sawBlade.setTextureSize(64, 64);
		sawBlade.mirror = true;
		setRotation(sawBlade, 0F, 0F, 0F);
		handle2 = new ModelRenderer(this, 11, 18);
		handle2.addBox(0F, 0F, 0F, 1, 1, 1);
		handle2.setRotationPoint(0F, 14F, 4F);
		handle2.setTextureSize(64, 64);
		handle2.mirror = true;
		setRotation(handle2, 0F, 0F, 0F);
		blade2 = new ModelRenderer(this, 33, 2);
		blade2.addBox(0F, 0F, 0F, 1, 3, 1);
		blade2.setRotationPoint(0F, 16F, 3F);
		blade2.setTextureSize(64, 64);
		blade2.mirror = true;
		setRotation(blade2, 0F, 0F, 0F);
		handle3 = new ModelRenderer(this, 8, 18);
		handle3.addBox(0F, 0F, 0F, 1, 1, 5);
		handle3.setRotationPoint(0F, 13F, 0F);
		handle3.setTextureSize(64, 64);
		handle3.mirror = true;
		setRotation(handle3, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		sawHandle.render(f5);
		handle1.render(f5);
		sawBlade.render(f5);
		handle2.render(f5);
		blade2.render(f5);
		handle3.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity ent) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
	}

	public void renderStatic(float f5) {
		sawHandle.render(f5);
		handle1.render(f5);
		sawBlade.render(f5);
		handle2.render(f5);
		blade2.render(f5);
		handle3.render(f5);
	}
}
