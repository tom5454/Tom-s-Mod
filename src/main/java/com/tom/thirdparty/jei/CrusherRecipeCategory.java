package com.tom.thirdparty.jei;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import com.tom.apis.RecipeData;
import com.tom.lib.Configs;
import com.tom.recipes.handler.MachineCraftingHandler;
import com.tom.thirdparty.jei.CrusherRecipeCategory.CrusherRecipeJEI;

import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.IRecipeCategory;

public class CrusherRecipeCategory implements IRecipeCategory<CrusherRecipeJEI> {
	public static List<CrusherRecipeJEI> get() {
		List<CrusherRecipeJEI> recipes = new ArrayList<>();
		List<RecipeData> recipeList = MachineCraftingHandler.getCrusherRecipes();
		for (int i = 0;i < recipeList.size();i++) {
			RecipeData data = recipeList.get(i);
			// ItemStack[] array = {data.itemstack1, data.itemstack2,
			// data.itemstack3, data.itemstack4, data.itemstack5,
			// data.itemstack6, data.itemstack7, data.itemstack8,
			// data.itemstack9};
			CrusherRecipeJEI cr = new CrusherRecipeJEI(data.itemstack0, data.itemstack1);
			recipes.add(cr);
		}
		return recipes;
	}

	@Nonnull
	private final IDrawable background = JEIHandler.jeiHelper.getGuiHelper().createDrawable(new ResourceLocation("tomsmod:textures/gui/jei/jeiCrusher.png"), 1, 5, 85, 50);

	@Override
	public String getUid() {
		return JEIConstants.CRUSHER_ID;
	}

	@Override
	public String getTitle() {
		return I18n.format("tomsmod.jei.crusher");
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public void drawExtras(Minecraft minecraft) {

	}

	public static class CrusherRecipeJEI extends BlankRecipeWrapper {
		@Nullable
		private final ItemStack input;
		@Nonnull
		private final ItemStack output;

		public CrusherRecipeJEI(ItemStack input, ItemStack output) {
			this.input = input;
			this.output = output;
		}

		@Override
		public void getIngredients(IIngredients ingredients) {
			ingredients.setInput(ItemStack.class, input);
			ingredients.setOutput(ItemStack.class, output);
		}
	}

	@Override
	public IDrawable getIcon() {
		return null;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, CrusherRecipeJEI recipe, IIngredients ingredients) {
		int x = 6;
		int y = 21;
		recipeLayout.getItemStacks().init(0, true, x, y);
		recipeLayout.getItemStacks().init(1, false, x + 55, y);
		recipeLayout.getItemStacks().set(0, recipe.input);
		recipeLayout.getItemStacks().set(1, recipe.output);
	}

	@Override
	public List<String> getTooltipStrings(int mouseX, int mouseY) {
		return Collections.emptyList();
	}

	@Override
	public String getModName() {
		return Configs.ModName;
	}
}
