package com.tom.factory.block;

import net.minecraft.world.World;

import com.tom.api.block.BlockMultiblockController2;
import com.tom.factory.tileentity.TileEntityFusionPreHeater;
import com.tom.factory.tileentity.TileEntityMultiblockController;

public class FusionPreHeater extends BlockMultiblockController2 {
	@Override
	public TileEntityMultiblockController createNewTileEntity(World arg0, int arg1) {
		return new TileEntityFusionPreHeater();
	}
}
