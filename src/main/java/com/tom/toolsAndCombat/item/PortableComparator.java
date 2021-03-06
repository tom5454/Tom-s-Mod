package com.tom.toolsAndCombat.item;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.tom.util.TomsModUtils;

public class PortableComparator extends Item {

	@SuppressWarnings("deprecation")
	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState blockState = worldIn.getBlockState(pos);
		if (blockState.getBlock().hasComparatorInputOverride(blockState)) {
			if (!worldIn.isRemote) {
				int value = blockState.getBlock().getComparatorInputOverride(blockState, worldIn, pos);
				TomsModUtils.sendNoSpamTranslate(playerIn, "tomsMod.chat.compValue", value);
			}
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.FAIL;
	}
}
