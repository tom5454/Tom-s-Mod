package com.tom.storage.tileentity.gui;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;

import com.tom.api.gui.GuiTomsMod;
import com.tom.storage.tileentity.TileEntityDrive;
import com.tom.storage.tileentity.inventory.ContainerDrive;

public class GuiDrive extends GuiTomsMod {

	public GuiDrive(InventoryPlayer playerInv, TileEntityDrive te) {
		super(new ContainerDrive(playerInv, te), "guiDrive");
	}

	@Override
	public void initGui() {
		ySize = 199;
		super.initGui();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		fontRenderer.drawString(I18n.format("container.inventory"), 8, ySize - 96 + 2, 4210752);
		String s = I18n.format("tile.tms.drive.name");
		fontRenderer.drawString(s, xSize / 2 - fontRenderer.getStringWidth(s) / 2, 5, 4210752);
	}
}
