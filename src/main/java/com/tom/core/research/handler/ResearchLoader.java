package com.tom.core.research.handler;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.tom.api.research.IResearch;
import com.tom.api.research.Research;
import com.tom.api.research.ResearchComplexity;
import com.tom.core.CoreInit;
import com.tom.core.TMResource;
import com.tom.core.TMResource.CraftingMaterial;
import com.tom.core.TMResource.Type;
import com.tom.core.research.ResearchAcids;
import com.tom.core.research.ResearchPower;
import com.tom.core.research.ResearchPower2;
import com.tom.energy.EnergyInit;
import com.tom.factory.FactoryInit;
import com.tom.storage.StorageInit;

public class ResearchLoader {
	public static final IResearch researchAcids = new ResearchAcids();
	public static final IResearch researchPower = new ResearchPower();
	public static final IResearch researchPower2 = new ResearchPower2();
	public static Research hammer, mortar, bronzeAge, basicBoiler, basicBronzeMachines, bronzeAlloySmelter, bronzePlateBlender, refinedBricks, cokeOven, blastFurnace, advancedTank, steelBoiler, steelFurnace, rubberProcessing, soldering, basicTurbine, multimeter, lvCable, batteryBox, basicLvMachines, wireProcessing, speedUpgrade, lvPlateBlender, lvAlloySmelter, waterCollector, pump, fluidTransposer, eSolderingStation, industrialBlastFurnace, mvTransformer, mvCable, mvMachines;
	public static void init(){
		CoreInit.log.info("Loading Researches...");
		hammer = new Research("hammer", TMResource.COAL.getHammerStack(1)).addRequiredScan(Blocks.IRON_BLOCK, 0).addRequiredItem(new ItemStack(Items.FLINT, 4)).setResearchTime(150);
		mortar = new Research("mortar", new ItemStack(CoreInit.mortarAndPestle)).addParent(hammer).addRequiredItem(new ItemStack(Items.FLINT, 2)).addRequiredItem(CraftingMaterial.STONE_BOWL.getStackNormal(2)).setResearchTime(200);
		bronzeAge = new Research("bronzeAge", TMResource.BRONZE.getStackNormal(Type.PLATE)).addParent(mortar).addParent(researchAcids).addRequiredItem(TMResource.BRONZE.getStackNormal(Type.PLATE, 2)).addRequiredItem(new ItemStack(Blocks.BRICK_BLOCK)).addRequiredScan(Blocks.CLAY, 0).addRequiredScan(Blocks.HARDENED_CLAY, -1).setResearchTime(200);
		basicBoiler = new Research("basicBoiler", new ItemStack(FactoryInit.basicBoiler)).setResearchTime(300).addParent(bronzeAge).addRequiredItem(new ItemStack(Items.CLAY_BALL, 2)).addRequiredItem(TMResource.BRONZE.getStackNormal(Type.PLATE, 4)).addRequiredItem(new ItemStack(Blocks.BRICK_BLOCK, 2)).addRequiredScan(Blocks.FURNACE, -1).addRequiredScan(Blocks.LIT_FURNACE, -1);
		basicBronzeMachines = new Research("basicBronzeMachines", new ItemStack(FactoryInit.steamFurnace)).setResearchTime(300).addParent(basicBoiler).addRequiredItem(new ItemStack(Blocks.FURNACE)).addRequiredItem(new ItemStack(Items.CLAY_BALL, 2)).addRequiredItem(new ItemStack(Blocks.BRICK_BLOCK, 2));
		bronzeAlloySmelter = new Research("bronzeAlloySmelter", new ItemStack(FactoryInit.steamAlloySmelter)).setResearchTime(350).addParent(basicBronzeMachines).addRequiredItem(TMResource.BRONZE.getStackNormal(Type.DUST)).addRequiredItem(TMResource.COPPER.getStackNormal(Type.INGOT)).addRequiredItem(new ItemStack(Blocks.FURNACE));
		bronzePlateBlender = new Research("bronzePlateBlender", new ItemStack(FactoryInit.steamPlateBlender)).setResearchTime(350).addParent(basicBronzeMachines).addRequiredItem(TMResource.BRONZE.getStackNormal(Type.PLATE, 2)).addRequiredItem(TMResource.BRONZE.getStackNormal(Type.INGOT, 3));
		refinedBricks = new Research("refinedBricks", CraftingMaterial.REFINED_BRICK.getStackNormal()).setResearchTime(400).addParent(researchPower).addParent(bronzeAlloySmelter).addParent(bronzePlateBlender).addRequiredItem(new ItemStack(Items.CLAY_BALL, 10)).addRequiredItem(new ItemStack(Items.BRICK, 2)).addRequiredItem(new ItemStack(Items.NETHERBRICK)).addRequiredItem(TMResource.BRONZE.getStackNormal(Type.PLATE));
		cokeOven = new Research("cokeOven", new ItemStack(FactoryInit.cokeOven)).addParent(refinedBricks).addRequiredItem(new ItemStack(Blocks.COAL_BLOCK)).addRequiredItem(new ItemStack(Blocks.SAND, 16)).addRequiredItem(new ItemStack(Items.BRICK, 2)).setResearchTime(500).addRequiredScan(Blocks.SAND, 0);
		blastFurnace = new Research("blastFurnace", new ItemStack(FactoryInit.blastFurnace)).addParent(cokeOven).addRequiredItem(new ItemStack(Items.MAGMA_CREAM)).setResearchTime(550).addRequiredItem(new ItemStack(Blocks.SOUL_SAND, 4)).addRequiredScan(Blocks.SOUL_SAND, 0).addRequiredItem(new ItemStack(Items.NETHERBRICK)).addRequiredScan(Blocks.NETHER_BRICK, -1);
		steelBoiler = new Research("advBoiler", new ItemStack(FactoryInit.advBoiler)).addRequiredItem(TMResource.STEEL.getStackNormal(Type.INGOT, 4)).addRequiredItem(new ItemStack(Blocks.FURNACE)).setResearchTime(600).addParent(blastFurnace);
		steelFurnace = new Research("advSteamFurnace", new ItemStack(FactoryInit.advSteamFurnace)).addParent(steelBoiler).addRequiredItem(new ItemStack(FactoryInit.steamFurnace)).addRequiredItem(TMResource.STEEL.getStackNormal(Type.PLATE, 4)).setResearchTime(700);
		rubberProcessing = new Research("rubberProcessing", CraftingMaterial.BOTTLE_OF_RUBBER.getStackNormal()).addParent(blastFurnace).addRequiredItem(new ItemStack(FactoryInit.steamFurnace)).addRequiredItem(TMResource.STEEL.getStackNormal(Type.PLATE, 4)).addRequiredItem(CraftingMaterial.BOTTLE_OF_RUBBER.getStackNormal(2)).setResearchTime(600).addRequiredScan(CoreInit.rubberWood, -1).addRequiredScan(CoreInit.rubberLeaves, -1).addRequiredScan(CoreInit.rubberSapling, -1);
		soldering = new Research("soldering", new ItemStack(FactoryInit.steamSolderingStation)).setResearchTime(650).addParent(rubberProcessing).addRequiredItem(CraftingMaterial.SOLDERING_ALLOY.getStackNormal(3)).addRequiredItem(TMResource.STEEL.getStackNormal(Type.PLATE, 2));
		basicTurbine = new Research("basicTurbine", null).addParent(soldering).addParent(steelBoiler).setResearchTime(700).addRequiredItem(TMResource.COPPER.getStackNormal(Type.CABLE, 16)).addRequiredItem(CraftingMaterial.BASIC_CIRCUIT.getStackNormal(2)).addRequiredItem(TMResource.STEEL.getStackNormal(Type.PLATE, 5)).addParent(researchPower2);
		multimeter = new Research("multimeter", new ItemStack(EnergyInit.multimeter)).addParent(basicTurbine).setResearchTime(700).addRequiredItem(new ItemStack(Items.COMPASS)).addRequiredItem(new ItemStack(Items.REDSTONE, 10)).addRequiredItem(TMResource.COPPER.getStackNormal(Type.CABLE));
		lvCable = new Research("lvCable", new ItemStack(EnergyInit.lvCable)).addParent(basicTurbine).setResearchTime(800).addRequiredItem(TMResource.COPPER.getStackNormal(Type.CABLE, 10)).addRequiredItem(CraftingMaterial.RUBBER.getStackNormal(5)).addRequiredItem(new ItemStack(Items.REDSTONE));
		batteryBox = new Research("batteryBox", new ItemStack(EnergyInit.batteryBox)).addParent(lvCable).setResearchTime(800).addRequiredItem(new ItemStack(Items.REDSTONE, 5)).addRequiredItem(TMResource.LEAD.getStackNormal(Type.INGOT)).addRequiredItem(TMResource.COPPER.getStackNormal(Type.INGOT, 3));
		basicLvMachines = new Research("basicLvMachines", new ItemStack(FactoryInit.crusher)).addParent(lvCable).setResearchTime(900).addRequiredItem(new ItemStack(CoreInit.MachineFrameBasic)).addRequiredItem(new ItemStack(Items.FLINT, 3)).addRequiredItem(CraftingMaterial.BASIC_CIRCUIT.getStackNormal(3)).addRequiredItem(new ItemStack(Items.REDSTONE, 6));
		wireProcessing = new Research("wireProcessingLv", new ItemStack(FactoryInit.wireMill)).addParent(basicLvMachines).setResearchTime(1000).addRequiredItem(TMResource.COPPER.getStackNormal(Type.CABLE, 20)).addRequiredItem(new ItemStack(CoreInit.MachineFrameBasic)).addRequiredItem(CraftingMaterial.BASIC_CIRCUIT.getStackNormal());
		speedUpgrade = new Research("speedUpgrade", new ItemStack(FactoryInit.speedUpgrade)).addParent(basicLvMachines).setResearchTime(2000).addRequiredItem(new ItemStack(Blocks.REDSTONE_BLOCK, 16)).addRequiredItem(new ItemStack(Blocks.REDSTONE_ORE)).addRequiredItem(TMResource.BLUE_METAL.getStackNormal(Type.INGOT, 3)).addRequiredItem(CraftingMaterial.UPGRADE_FRAME.getStackNormal(2)).setComplexity(ResearchComplexity.ADVANCED);
		lvPlateBlender = new Research("lvPlateBlender", new ItemStack(FactoryInit.plateBlendingMachine)).setResearchTime(1000).addRequiredItem(CraftingMaterial.BASIC_CIRCUIT.getStackNormal()).addRequiredItem(new ItemStack(CoreInit.MachineFrameBasic)).addParent(basicLvMachines).addRequiredItem(new ItemStack(Items.DIAMOND));
		lvAlloySmelter = new Research("lvAlloySmelter", new ItemStack(FactoryInit.alloySmelter)).setResearchTime(1000).addParent(basicLvMachines).addRequiredItem(CraftingMaterial.BASIC_CIRCUIT.getStackNormal()).addRequiredItem(new ItemStack(CoreInit.MachineFrameBasic)).addRequiredItem(TMResource.ELECTRUM.getStackNormal(Type.INGOT, 3));
		waterCollector = new Research("waterCollector", new ItemStack(FactoryInit.waterCollector)).setResearchTime(700).addParent(basicBronzeMachines).addRequiredItem(new ItemStack(Items.WATER_BUCKET)).addRequiredItem(TMResource.BRONZE.getStackNormal(Type.INGOT));
		eSolderingStation = new Research("eSolderingStation", new ItemStack(FactoryInit.solderingStation)).setResearchTime(1100).addParent(lvAlloySmelter).addRequiredItem(CraftingMaterial.SOLDERING_ALLOY.getStackNormal(4)).addRequiredItem(CraftingMaterial.CUPRONICKEL_HEATING_COIL.getStackNormal()).addRequiredItem(CraftingMaterial.BASIC_CIRCUIT.getStackNormal()).addRequiredItem(new ItemStack(CoreInit.MachineFrameBasic));
		industrialBlastFurnace = new Research("industrialBlastFurnace", null).setResearchTime(1200).addParent(eSolderingStation).addRequiredItem(new ItemStack(CoreInit.MachineFrameBasic)).addRequiredItem(CraftingMaterial.CUPRONICKEL_HEATING_COIL.getStackNormal(4)).addRequiredItem(CraftingMaterial.BASIC_CIRCUIT.getStackNormal(2)).addRequiredItem(new ItemStack(CoreInit.MachineFrameSteel));
		pump = new Research("pump", new ItemStack(FactoryInit.pump)).setResearchTime(1000).addParent(basicLvMachines).addRequiredItem(new ItemStack(CoreInit.itemPump, 2)).addRequiredItem(CraftingMaterial.BASIC_CIRCUIT.getStackNormal()).addRequiredItem(new ItemStack(CoreInit.MachineFrameBasic));
		fluidTransposer = new Research("fluidTransposer", new ItemStack(FactoryInit.fluidTransposer)).addParent(basicLvMachines).addRequiredItem(new ItemStack(CoreInit.itemPump, 2)).addRequiredItem(CraftingMaterial.BASIC_CIRCUIT.getStackNormal()).addRequiredItem(new ItemStack(CoreInit.MachineFrameBasic)).addRequiredItem(new ItemStack(StorageInit.tankBasic)).setResearchTime(1100);
		advancedTank = new Research("advTank", new ItemStack(StorageInit.tankAdv)).setResearchTime(500).addParent(blastFurnace).addRequiredItem(TMResource.STEEL.getStackNormal(Type.PLATE, 4)).addRequiredItem(new ItemStack(Blocks.GLASS, 32));
		ResearchHandler.registerResearch(researchAcids);
		ResearchHandler.registerResearch(researchPower);
		ResearchHandler.registerResearch(researchPower2);
		ResearchHandler.registerResearch(hammer);
		ResearchHandler.registerResearch(mortar);
		ResearchHandler.registerResearch(bronzeAge);
		ResearchHandler.registerResearch(basicBoiler);
		ResearchHandler.registerResearch(basicBronzeMachines);
		ResearchHandler.registerResearch(bronzeAlloySmelter);
		ResearchHandler.registerResearch(bronzePlateBlender);
		ResearchHandler.registerResearch(refinedBricks);
		ResearchHandler.registerResearch(cokeOven);
		ResearchHandler.registerResearch(blastFurnace);
		ResearchHandler.registerResearch(steelBoiler);
		ResearchHandler.registerResearch(steelFurnace);
		ResearchHandler.registerResearch(rubberProcessing);
		ResearchHandler.registerResearch(basicTurbine);
		ResearchHandler.registerResearch(soldering);
		ResearchHandler.registerResearch(multimeter);
		ResearchHandler.registerResearch(lvCable);
		ResearchHandler.registerResearch(batteryBox);
		ResearchHandler.registerResearch(basicLvMachines);
		ResearchHandler.registerResearch(wireProcessing);
		ResearchHandler.registerResearch(speedUpgrade);
		ResearchHandler.registerResearch(lvPlateBlender);
		ResearchHandler.registerResearch(lvAlloySmelter);
		ResearchHandler.registerResearch(waterCollector);
		ResearchHandler.registerResearch(eSolderingStation);
		ResearchHandler.registerResearch(industrialBlastFurnace);
		ResearchHandler.registerResearch(pump);
		ResearchHandler.registerResearch(fluidTransposer);
		ResearchHandler.registerResearch(advancedTank);
	}
}