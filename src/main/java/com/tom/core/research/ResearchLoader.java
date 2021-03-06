package com.tom.core.research;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;

import com.tom.api.research.Research;
import com.tom.api.research.ResearchComplexity;
import com.tom.core.CoreInit;
import com.tom.core.TMResource;
import com.tom.core.TMResource.CraftingMaterial;
import com.tom.core.TMResource.Type;
import com.tom.energy.EnergyInit;
import com.tom.factory.FactoryInit;
import com.tom.recipes.AdvancedCraftingRecipes;
import com.tom.storage.StorageInit;
import com.tom.transport.TransportInit;

public class ResearchLoader {
	public static Research power, hammer, mortar, bronzeAge, conveyorBelts, fluidDucts, basicBoiler,
	basicBronzeMachines, bronzeAlloySmelter, bronzePlateBendingMachine, refinedBricks, cokeOven, blastFurnace,
	improvedBlastFurnace, advancedTank, steelBoiler, steelFurnace, steelCrusher, rubberProcessing, soldering,
	basicTurbine, multimeter, lvCable, batteryBox, basicLvMachines, wireProcessing, speedUpgrade, lvPlateBendingMachine,
	lvAlloySmelter, waterCollector, pump, fluidTransposer, eSolderingStation, solarPanel,
	industrialBlastFurnace, mvTransformer, mvCable, mvCircuit, mvMachines, refinery, multiblockComponents,
	plasticProcessing, electrolyzer, centrifuge, advancedMultiblockParts, hvTransformer, advancedCircuit,
	hvCable, hvMachines, eliteTank, ultimateTank, limitableChest, mvTurbine, charger,
	configurator, geoThermalBoiler, geoThermalGenerator, liquidFueledBoiler, liquidFueledSteelBoiler,
	liquidFueledGenerator, uvLightbox, steamMixer, mixer, laserEngraver, basicChipset, advChipset,
	machineUpgrades, powerConverterModules, electricalRubberProcessing, enderLinkPower, advWaterCollector;

	public static void init() {
		CoreInit.log.info("Loading Researches...");
		hammer = new Research("hammer", TMResource.COAL.getHammerStack(1)).addRequiredItem(new ItemStack(Items.FLINT, 4)).setResearchTime(150);
		mortar = new Research("mortar", new ItemStack(CoreInit.mortarAndPestle)).addParent(hammer).addRequiredItem(new ItemStack(Items.FLINT, 2)).addRequiredItem(CraftingMaterial.STONE_BOWL.getStackNormal(2)).setResearchTime(200);
		bronzeAge = new Research("bronzeAge", TMResource.BRONZE.getStackNormal(Type.PLATE)).addParent(mortar)/*.addParent(researchAcids)*/.addRequiredItem(TMResource.BRONZE.getStackNormal(Type.PLATE, 2)).addRequiredItem(new ItemStack(Blocks.BRICK_BLOCK)).addRequiredScan(Blocks.CLAY, 0, "tile.clay.name").addRequiredScan(Blocks.BRICK_BLOCK, -1, "tile.brick.name").setResearchTime(200);
		fluidDucts = new Research("fluidDuct", new ItemStack(TransportInit.fluidDuct)).addParent(bronzeAge).addRequiredItem(TMResource.IRON.getStackNormal(Type.PLATE, 2)).addRequiredItem(new ItemStack(Blocks.GLASS)).setResearchTime(300).addRequiredItem(TMResource.LEAD.getStackNormal(Type.PLATE));
		basicBoiler = new Research("basicBoiler", new ItemStack(FactoryInit.basicBoiler)).setResearchTime(300).addParent(bronzeAge).addRequiredItem(new ItemStack(Items.CLAY_BALL, 2)).addRequiredItem(TMResource.BRONZE.getStackNormal(Type.PLATE, 4)).addRequiredItem(new ItemStack(Blocks.BRICK_BLOCK, 2)).addRequiredScan(Blocks.FURNACE, -1, "tile.furnace.name").addRequiredScan(Blocks.LIT_FURNACE, -1, "tile.lit_furnace.name");
		basicBronzeMachines = new Research("basicBronzeMachines", new ItemStack(FactoryInit.steamFurnace)).setResearchTime(300).addParent(basicBoiler).addRequiredItem(new ItemStack(Blocks.FURNACE)).addRequiredItem(new ItemStack(Items.CLAY_BALL, 2)).addRequiredItem(new ItemStack(Blocks.BRICK_BLOCK, 2));
		bronzeAlloySmelter = new Research("bronzeAlloySmelter", new ItemStack(FactoryInit.steamAlloySmelter)).setResearchTime(350).addParent(basicBronzeMachines).addRequiredItem(TMResource.BRONZE.getStackNormal(Type.DUST)).addRequiredItem(TMResource.COPPER.getStackNormal(Type.INGOT)).addRequiredItem(new ItemStack(Blocks.FURNACE));
		bronzePlateBendingMachine = new Research("bronzePlateBender", new ItemStack(FactoryInit.steamPlateBendeingMachine)).setResearchTime(350).addParent(basicBronzeMachines).addRequiredItem(TMResource.BRONZE.getStackNormal(Type.PLATE, 2)).addRequiredItem(TMResource.BRONZE.getStackNormal(Type.INGOT, 3));
		refinedBricks = new Research("refinedBricks", CraftingMaterial.REFINED_BRICK.getStackNormal()).setComplexity(ResearchComplexity.BRONZE).setResearchTime(400).addParent(bronzeAlloySmelter).addParent(bronzePlateBendingMachine).addRequiredItem(new ItemStack(Items.CLAY_BALL, 10)).addRequiredItem(new ItemStack(Items.BRICK, 2)).addRequiredItem(new ItemStack(Items.NETHERBRICK)).addRequiredItem(TMResource.BRONZE.getStackNormal(Type.PLATE));
		cokeOven = new Research("cokeOven", new ItemStack(FactoryInit.cokeOven)).setComplexity(ResearchComplexity.BRONZE).addParent(refinedBricks).addRequiredItem(new ItemStack(Blocks.COAL_BLOCK)).addRequiredItem(new ItemStack(Blocks.SAND, 16)).addRequiredItem(new ItemStack(Items.BRICK, 2)).setResearchTime(500).addRequiredScan(Blocks.SAND, 0, "tile.sand.name").addRequiredItem(CraftingMaterial.REFINED_BRICK.getStackNormal());
		blastFurnace = new Research("blastFurnace", new ItemStack(FactoryInit.blastFurnace)).setComplexity(ResearchComplexity.BRONZE).addParent(cokeOven).addRequiredItem(new ItemStack(Items.MAGMA_CREAM)).setResearchTime(550).addRequiredItem(new ItemStack(Blocks.SOUL_SAND, 4)).addRequiredScan(Blocks.SOUL_SAND, 0, "tile.soul_sand.name").addRequiredItem(new ItemStack(Items.NETHERBRICK)).addRequiredScan(Blocks.NETHER_BRICK, -1, "tile.nether_brick.name").addRequiredItem(CraftingMaterial.REFINED_BRICK.getStackNormal());
		steelBoiler = new Research("advBoiler", new ItemStack(FactoryInit.advBoiler)).setComplexity(ResearchComplexity.BRONZE).addRequiredItem(TMResource.STEEL.getStackNormal(Type.INGOT, 4)).addRequiredItem(new ItemStack(Blocks.FURNACE)).setResearchTime(600).addParent(blastFurnace);
		steelFurnace = new Research("advSteamFurnace", new ItemStack(FactoryInit.advSteamFurnace)).setComplexity(ResearchComplexity.BRONZE).addParent(steelBoiler).addRequiredItem(new ItemStack(FactoryInit.steamFurnace)).addRequiredItem(TMResource.STEEL.getStackNormal(Type.PLATE, 4)).setResearchTime(700);
		steelCrusher = new Research("advSteamCrusher", new ItemStack(FactoryInit.advSteamCrusher)).setComplexity(ResearchComplexity.BRONZE).addParent(steelBoiler).addRequiredItem(new ItemStack(FactoryInit.steamCrusher)).addRequiredItem(TMResource.STEEL.getStackNormal(Type.PLATE, 4)).setResearchTime(700);
		rubberProcessing = new Research("rubberProcessing", CraftingMaterial.BOTTLE_OF_RESIN.getStackNormal()).setComplexity(ResearchComplexity.BRONZE).addParent(steelBoiler).addRequiredItem(new ItemStack(FactoryInit.steamFurnace)).addRequiredItem(TMResource.STEEL.getStackNormal(Type.PLATE, 4)).addRequiredItem(CraftingMaterial.BOTTLE_OF_RESIN.getStackNormal(2)).setResearchTime(600).addRequiredScan("logRubber").addRequiredScan("leavesRubber").addRequiredScan("saplingRubber");
		steamMixer = new Research("steamMixer", new ItemStack(FactoryInit.steamMixer)).setResearchTime(800).addParent(basicBronzeMachines).setComplexity(ResearchComplexity.BRONZE).addRequiredItem(new ItemStack(CoreInit.MachineFrameBronze)).addRequiredItem(TMResource.STEEL.getStackNormal(Type.PLATE, 4)).addRequiredItem(TMResource.SULFUR.getStackNormal(Type.DUST)).addRequiredItem(new ItemStack(Blocks.BRICK_BLOCK));
		enderLinkPower = new Research("enderLinkPower", CraftingMaterial.REDSTONE_CRYSTAL.getStackNormal()).setResearchTime(400).addParent(bronzeAlloySmelter).setComplexity(ResearchComplexity.BRONZE).addRequiredItem(CraftingMaterial.REDSTONE_CRYSTAL.getStackNormal()).addRequiredItem(TMResource.COPPER.getStackNormal(Type.CABLE)).addRequiredItem(new ItemStack(Blocks.FURNACE)).addRequiredScan(Blocks.REDSTONE_ORE, -1, "tile.redstone_ore.name");
		power = new Research("power", CraftingMaterial.ACID_PAPER.getStackNormal()).addParent(blastFurnace).addParent(enderLinkPower).setResearchTime(100).addRequiredItem(TMResource.COPPER.getStackNormal(Type.PLATE, 2)).addRequiredItem(TMResource.ZINC.getStackNormal(Type.PLATE, 2)).addRequiredItem(CraftingMaterial.ACID_PAPER.getStackNormal(4));
		soldering = new Research("soldering", new ItemStack(FactoryInit.steamSolderingStation)).setComplexity(ResearchComplexity.BRONZE).setResearchTime(650).addParent(rubberProcessing).addParent(steamMixer).addRequiredItem(CraftingMaterial.SOLDERING_ALLOY.getStackNormal(3)).addRequiredItem(TMResource.STEEL.getStackNormal(Type.PLATE, 2)).addParent(power);
		basicTurbine = new Research("basicTurbine", new ItemStack(EnergyInit.steamTurbine)).setComplexity(ResearchComplexity.BRONZE).addParent(soldering).addParent(steelBoiler).setResearchTime(700).addRequiredItem(TMResource.COPPER.getStackNormal(Type.CABLE, 16)).addRequiredItem(AdvancedCraftingRecipes.BASIC_CIRCUIT.getStackNormal(2)).addRequiredItem(TMResource.STEEL.getStackNormal(Type.PLATE, 5));
		multimeter = new Research("multimeter", new ItemStack(EnergyInit.multimeter)).setComplexity(ResearchComplexity.BRONZE).addParent(basicTurbine).setResearchTime(700).addRequiredItem(new ItemStack(Items.COMPASS)).addRequiredItem(new ItemStack(Items.REDSTONE, 10)).addRequiredItem(TMResource.COPPER.getStackNormal(Type.CABLE));
		lvCable = new Research("lvCable", new ItemStack(EnergyInit.lvCable)).setComplexity(ResearchComplexity.BRONZE).addParent(basicTurbine).setResearchTime(800).addRequiredItem(TMResource.COPPER.getStackNormal(Type.CABLE, 10)).addRequiredItem(CraftingMaterial.RUBBER.getStackNormal(5)).addRequiredItem(new ItemStack(Items.REDSTONE));
		batteryBox = new Research("batteryBox", new ItemStack(EnergyInit.batteryBox)).setComplexity(ResearchComplexity.BRONZE).addParent(lvCable).setResearchTime(800).addRequiredItem(new ItemStack(Items.REDSTONE, 5)).addRequiredItem(TMResource.LEAD.getStackNormal(Type.INGOT)).addRequiredItem(TMResource.COPPER.getStackNormal(Type.INGOT, 3));
		basicLvMachines = new Research("basicLvMachines", new ItemStack(FactoryInit.crusher)).setComplexity(ResearchComplexity.BRONZE).addParent(lvCable).setResearchTime(900).addRequiredItem(new ItemStack(CoreInit.MachineFrameBasic)).addRequiredItem(new ItemStack(Items.FLINT, 3)).addRequiredItem(AdvancedCraftingRecipes.BASIC_CIRCUIT.getStackNormal(3)).addRequiredItem(new ItemStack(Items.REDSTONE, 6));
		wireProcessing = new Research("wireProcessingLv", new ItemStack(FactoryInit.wireMill)).setComplexity(ResearchComplexity.BRONZE).addParent(basicLvMachines).setResearchTime(1000).addRequiredItem(TMResource.COPPER.getStackNormal(Type.CABLE, 20)).addRequiredItem(new ItemStack(CoreInit.MachineFrameBasic)).addRequiredItem(AdvancedCraftingRecipes.BASIC_CIRCUIT.get());
		lvPlateBendingMachine = new Research("lvPlateBender", new ItemStack(FactoryInit.plateBendingMachine)).setComplexity(ResearchComplexity.BRONZE).setResearchTime(1000).addRequiredItem(AdvancedCraftingRecipes.BASIC_CIRCUIT.get()).addRequiredItem(new ItemStack(CoreInit.MachineFrameBasic)).addParent(basicLvMachines).addRequiredItem(new ItemStack(Items.DIAMOND));
		lvAlloySmelter = new Research("lvAlloySmelter", new ItemStack(FactoryInit.alloySmelter)).setComplexity(ResearchComplexity.BRONZE).setResearchTime(1000).addParent(basicLvMachines).addRequiredItem(AdvancedCraftingRecipes.BASIC_CIRCUIT.get()).addRequiredItem(new ItemStack(CoreInit.MachineFrameBasic)).addRequiredItem(TMResource.ELECTRUM.getStackNormal(Type.INGOT, 3));
		waterCollector = new Research("waterCollector", new ItemStack(FactoryInit.waterCollector)).setResearchTime(700).addParent(enderLinkPower).addRequiredItem(new ItemStack(Items.WATER_BUCKET)).addRequiredItem(TMResource.BRONZE.getStackNormal(Type.INGOT));
		eSolderingStation = new Research("eSolderingStation", new ItemStack(FactoryInit.solderingStation)).setComplexity(ResearchComplexity.BRONZE).setResearchTime(1100).addParent(lvAlloySmelter).addRequiredItem(CraftingMaterial.SOLDERING_ALLOY.getStackNormal(4)).addRequiredItem(CraftingMaterial.CUPRONICKEL_HEATING_COIL.getStackNormal()).addRequiredItem(AdvancedCraftingRecipes.BASIC_CIRCUIT.get()).addRequiredItem(new ItemStack(CoreInit.MachineFrameBasic));
		industrialBlastFurnace = new Research("industrialBlastFurnace", new ItemStack(FactoryInit.industrialBlastFurnace)).setComplexity(ResearchComplexity.BRONZE).setResearchTime(1200).addParent(eSolderingStation).addRequiredItem(new ItemStack(CoreInit.MachineFrameBasic)).addRequiredItem(CraftingMaterial.CUPRONICKEL_HEATING_COIL.getStackNormal(4)).addRequiredItem(AdvancedCraftingRecipes.BASIC_CIRCUIT.getStackNormal(2)).addRequiredItem(new ItemStack(CoreInit.MachineFrameSteel));
		pump = new Research("pump", new ItemStack(FactoryInit.pump)).setResearchTime(1000).addParent(basicLvMachines).setComplexity(ResearchComplexity.BRONZE).addRequiredItem(CraftingMaterial.PUMP.getStackNormal(2)).addRequiredItem(AdvancedCraftingRecipes.BASIC_CIRCUIT.get()).addRequiredItem(new ItemStack(CoreInit.MachineFrameBasic));
		fluidTransposer = new Research("fluidTransposer", new ItemStack(FactoryInit.fluidTransposer)).setComplexity(ResearchComplexity.BRONZE).addParent(basicLvMachines).addRequiredItem(CraftingMaterial.PUMP.getStackNormal(2)).addRequiredItem(AdvancedCraftingRecipes.BASIC_CIRCUIT.get()).addRequiredItem(new ItemStack(CoreInit.MachineFrameBasic)).addRequiredItem(new ItemStack(StorageInit.tankBasic)).setResearchTime(1100);
		advancedTank = new Research("advTank", new ItemStack(StorageInit.tankAdv)).setComplexity(ResearchComplexity.BRONZE).setResearchTime(500).addParent(blastFurnace).addRequiredItem(TMResource.STEEL.getStackNormal(Type.PLATE, 4)).addRequiredItem(new ItemStack(Blocks.GLASS, 32)).addRequiredItem(new ItemStack(StorageInit.tankBasic));
		solarPanel = new Research("solarPanel", new ItemStack(EnergyInit.solarPanel)).setComplexity(ResearchComplexity.BRONZE).setResearchTime(1300).addParent(eSolderingStation).addRequiredItem(new ItemStack(Items.DYE, 4, EnumDyeColor.BLUE.getDyeDamage())).addRequiredItem(CraftingMaterial.SILICON_PLATE.getStackNormal(4)).addRequiredItem(new ItemStack(Items.REDSTONE, 4));
		mvTransformer = new Research("mvTransformer", new ItemStack(EnergyInit.transformerLMV)).setComplexity(ResearchComplexity.ELECTRICAL).setResearchTime(1500).addParent(eSolderingStation).addRequiredItem(TMResource.COPPER.getStackNormal(Type.COIL, 4)).addRequiredItem(new ItemStack(Items.IRON_INGOT, 2)).addRequiredItem(TMResource.ELECTRUM.getStackNormal(Type.COIL, 2)).addRequiredItem(new ItemStack(Items.REDSTONE));
		mvCable = new Research("mvCable", new ItemStack(EnergyInit.mvCable)).setResearchTime(1500).setComplexity(ResearchComplexity.ELECTRICAL).addParent(mvTransformer).addRequiredItem(CraftingMaterial.RUBBER.getStackNormal(4)).addRequiredItem(TMResource.ELECTRUM.getStackNormal(Type.CABLE));
		mvCircuit = new Research("mvCircuit", AdvancedCraftingRecipes.NORMAL_CIRCUIT.get()).setComplexity(ResearchComplexity.ELECTRICAL).setResearchTime(1800).addParent(mvTransformer).addRequiredItem(new ItemStack(Items.REDSTONE, 20)).addRequiredItem(AdvancedCraftingRecipes.BASIC_CIRCUIT.getStackNormal(5)).addRequiredItem(CraftingMaterial.SILICON_PLATE.getStackNormal(3)).addRequiredItem(TMResource.COPPER.getStackNormal(Type.COIL));
		mvMachines = new Research("mvMachines", new ItemStack(FactoryInit.crusher)).setComplexity(ResearchComplexity.ELECTRICAL).setResearchTime(2000).addParent(mvCable).addParent(mvCircuit).addRequiredItem(AdvancedCraftingRecipes.NORMAL_CIRCUIT.getStackNormal(4)).addRequiredItem(new ItemStack(EnergyInit.transformerLMV)).addRequiredItem(new ItemStack(CoreInit.MachineFrameSteel)).addRequiredItem(TMResource.ELECTRUM.getStackNormal(Type.CABLE, 5));
		refinery = new Research("refinery", new ItemStack(FactoryInit.refinery)).setComplexity(ResearchComplexity.ELECTRICAL).setResearchTime(2500).addParent(basicLvMachines).addRequiredItem(TMResource.STEEL.getStackNormal(Type.PLATE, 10)).addRequiredItem(TMResource.ALUMINUM.getStackNormal(Type.PLATE, 4)).addRequiredItem(new ItemStack(CoreInit.MachineFrameSteel)).addRequiredItem(new ItemStack(Blocks.GLASS, 20));
		multiblockComponents = new Research("multiblockComponents", new ItemStack(FactoryInit.MultiblockCase)).setComplexity(ResearchComplexity.ELECTRICAL).addParent(refinery).addParent(mvMachines).addRequiredItem(TMResource.ALUMINUM.getStackNormal(Type.PLATE, 16)).addRequiredItem(new ItemStack(CoreInit.MachineFrameAluminum)).addRequiredItem(AdvancedCraftingRecipes.NORMAL_CIRCUIT.get());
		electrolyzer = new Research("electrolyzer", new ItemStack(FactoryInit.Electrolyzer)).setComplexity(ResearchComplexity.ELECTRICAL).setResearchTime(2000).addParent(multiblockComponents).addRequiredItem(new ItemStack(FactoryInit.MultiblockCase)).addRequiredItem(AdvancedCraftingRecipes.NORMAL_CIRCUIT.getStackNormal(4)).addRequiredItem(TMResource.COAL.getStackNormal(Type.GEM, 32)).addRequiredItem(TMResource.ALUMINUM.getStackNormal(Type.PLATE, 4));
		centrifuge = new Research("centrifuge", new ItemStack(FactoryInit.Centrifuge)).setComplexity(ResearchComplexity.ELECTRICAL).setResearchTime(2000).addParent(multiblockComponents).addRequiredItem(new ItemStack(FactoryInit.MultiblockCase)).addRequiredItem(AdvancedCraftingRecipes.NORMAL_CIRCUIT.getStackNormal(4)).addRequiredItem(TMResource.ALUMINUM.getBlockStackNormal(2)).addRequiredItem(CraftingMaterial.ELECTRIC_MOTOR.getStackNormal());
		plasticProcessing = new Research("plasticProcessing", CraftingMaterial.PLASTIC_SHEET.getStackNormal()).setComplexity(ResearchComplexity.ELECTRICAL).setResearchTime(2200).addParent(mvMachines).addParent(refinery).addRequiredItem(CraftingMaterial.BOTTLE_OF_RESIN.getStackNormal(3)).addRequiredItem(new ItemStack(Items.SUGAR)).addRequiredItem(TMResource.COAL.getStackNormal(Type.GEM, 20));
		hvTransformer = new Research("hvTransformer", new ItemStack(EnergyInit.transformerMHV)).setComplexity(ResearchComplexity.ELECTRICAL).setResearchTime(2100).addParent(refinery).addParent(mvMachines).addRequiredItem(TMResource.ELECTRUM.getStackNormal(Type.COIL, 8)).addRequiredItem(TMResource.IRON.getStackNormal(Type.COIL, 3)).addRequiredItem(new ItemStack(Items.IRON_INGOT, 5)).addRequiredItem(new ItemStack(Items.REDSTONE, 16));
		hvCable = new Research("hvCable", new ItemStack(EnergyInit.hvCable)).setComplexity(ResearchComplexity.ELECTRICAL).setResearchTime(2100).addParent(hvTransformer).addRequiredItem(TMResource.IRON.getStackNormal(Type.CABLE, 10)).addRequiredItem(CraftingMaterial.RUBBER.getStackNormal(20)).addRequiredItem(new ItemStack(EnergyInit.mvCable, 4)).addRequiredItem(new ItemStack(Items.REDSTONE, 4));
		advancedCircuit = new Research("advCircuit", AdvancedCraftingRecipes.ADVANCED_CIRCUIT.get()).setComplexity(ResearchComplexity.ELECTRICAL).setResearchTime(2500).addParent(hvTransformer).addParent(plasticProcessing).addRequiredItem(AdvancedCraftingRecipes.NORMAL_CIRCUIT.getStackNormal(10)).addRequiredItem(TMResource.ELECTRUM.getStackNormal(Type.COIL, 10)).addRequiredItem(TMResource.ENDERIUM.getStackNormal(Type.COIL));
		hvMachines = new Research("hvMachines", new ItemStack(FactoryInit.crusher)).setComplexity(ResearchComplexity.ELECTRICAL).setResearchTime(2500).addParent(advancedCircuit).addRequiredItem(AdvancedCraftingRecipes.ADVANCED_CIRCUIT.getStackNormal(3)).addRequiredItem(new ItemStack(CoreInit.MachineFrameTitanium)).addRequiredItem(TMResource.ENDERIUM.getStackNormal(Type.COIL)).addRequiredItem(new ItemStack(Items.REDSTONE, 32));
		advancedMultiblockParts = new Research("advMultiblockParts", new ItemStack(FactoryInit.AdvancedMultiblockCasing)).addParent(multiblockComponents).addParent(hvMachines).setComplexity(ResearchComplexity.ELECTRICAL).setResearchTime(2200).addRequiredItem(new ItemStack(FactoryInit.MultiblockCase, 4)).addRequiredItem(TMResource.TUNGSTENSTEEL.getStackNormal(Type.PLATE, 5)).addRequiredItem(new ItemStack(CoreInit.MachineFrameTitanium, 2));
		eliteTank = new Research("eliteTank", new ItemStack(StorageInit.tankElite)).setComplexity(ResearchComplexity.ELECTRICAL).setResearchTime(1500).addParent(advancedTank).addParent(mvMachines).addRequiredItem(TMResource.TITANIUM.getStackNormal(Type.PLATE, 5)).addRequiredItem(new ItemStack(CoreInit.hardenedGlass, 5)).addRequiredItem(new ItemStack(StorageInit.tankAdv)).addRequiredItem(new ItemStack(Blocks.GLASS, 5));
		ultimateTank = new Research("ultimateTank", new ItemStack(StorageInit.tankUltimate)).setComplexity(ResearchComplexity.ELECTRICAL).setResearchTime(3000).addParent(eliteTank).addParent(hvMachines).addRequiredItem(TMResource.ENDERIUM.getStackNormal(Type.PLATE, 20)).addRequiredItem(new ItemStack(CoreInit.hardenedGlass, 10)).addRequiredItem(new ItemStack(Blocks.GLASS, 5)).addRequiredItem(new ItemStack(StorageInit.tankElite));
		limitableChest = new Research("configurableChest", new ItemStack(StorageInit.limitableChest)).setResearchTime(500).addParent(bronzeAge).addRequiredItem(new ItemStack(Blocks.CHEST, 2)).addRequiredItem(new ItemStack(Blocks.TRAPDOOR)).addRequiredItem(new ItemStack(Blocks.LEVER)).addRequiredItem(TMResource.BRONZE.getStackNormal(Type.PLATE));
		mvTurbine = new Research("mvTurbine", new ItemStack(EnergyInit.steamTurbineMK2)).setComplexity(ResearchComplexity.ELECTRICAL).setResearchTime(1600).addParent(mvCircuit).addRequiredItem(TMResource.STEEL.getStackNormal(Type.PLATE, 4)).addRequiredItem(AdvancedCraftingRecipes.NORMAL_CIRCUIT.get()).addRequiredItem(new ItemStack(CoreInit.MachineFrameSteel)).addRequiredItem(new ItemStack(Items.REDSTONE, 16));
		charger = new Research("charger", new ItemStack(EnergyInit.charger)).setComplexity(ResearchComplexity.BRONZE).setResearchTime(1500).addParent(basicLvMachines).addRequiredItem(new ItemStack(CoreInit.MachineFrameBasic)).addRequiredItem(AdvancedCraftingRecipes.BASIC_CIRCUIT.get()).addRequiredItem(new ItemStack(EnergyInit.battery, 2)).addRequiredItem(TMResource.COPPER.getStackNormal(Type.CABLE, 5));
		configurator = new Research("configurator", new ItemStack(CoreInit.configurator)).setComplexity(ResearchComplexity.BRONZE).setResearchTime(1600).addParent(charger).addParent(lvAlloySmelter).addParent(eSolderingStation).addRequiredItem(AdvancedCraftingRecipes.BASIC_CIRCUIT.getStackNormal(2)).addRequiredItem(CraftingMaterial.DISPLAY.getStackNormal()).addRequiredItem(TMResource.IRON.getStackNormal(Type.PLATE, 2)).addRequiredItem(new ItemStack(Items.REDSTONE, 6));
		geoThermalBoiler = new Research("geoBoiler", new ItemStack(FactoryInit.geothermalBoiler)).setComplexity(ResearchComplexity.BRONZE).addParent(steelBoiler).setResearchTime(800).addRequiredItem(new ItemStack(FactoryInit.advBoiler)).addRequiredItem(new ItemStack(CoreInit.MachineFrameSteel)).addRequiredItem(TMResource.STEEL.getStackNormal(Type.PLATE)).addRequiredItem(CraftingMaterial.REFINED_BRICK.getStackNormal(4));
		geoThermalGenerator = new Research("geoGenerator", new ItemStack(EnergyInit.geothermalGenerator)).setComplexity(ResearchComplexity.BRONZE).addParent(geoThermalBoiler).setResearchTime(2000).addRequiredItem(new ItemStack(FactoryInit.geothermalBoiler)).addRequiredItem(CraftingMaterial.GENERATOR_COMPONENT.getStackNormal(2)).addRequiredItem(AdvancedCraftingRecipes.BASIC_CIRCUIT.get()).addRequiredItem(CraftingMaterial.REFINED_BRICK.getStackNormal(4));
		liquidFueledBoiler = new Research("liquidFueledBoiler", new ItemStack(FactoryInit.fluidBolier)).setResearchTime(600).addParent(basicBoiler).addRequiredItem(new ItemStack(CoreInit.MachineFrameBronze)).addRequiredItem(new ItemStack(StorageInit.tankBasic)).addRequiredItem(new ItemStack(Blocks.BRICK_BLOCK)).addParent(refinedBricks).addRequiredItem(CraftingMaterial.REFINED_BRICK.getStackNormal(3));
		liquidFueledSteelBoiler = new Research("liquidFueledAdvBoiler", new ItemStack(FactoryInit.advFluidBoiler)).setComplexity(ResearchComplexity.BRONZE).setResearchTime(1200).addParent(liquidFueledBoiler).addParent(steelBoiler).addRequiredItem(new ItemStack(CoreInit.MachineFrameSteel)).addRequiredItem(TMResource.STEEL.getStackNormal(Type.PLATE, 10)).addRequiredItem(CraftingMaterial.REFINED_BRICK.getStackNormal(5));
		liquidFueledGenerator = new Research("liquidFueledGenerator", new ItemStack(EnergyInit.fluidGenerator)).setComplexity(ResearchComplexity.BRONZE).setResearchTime(1600).addParent(basicLvMachines).addParent(liquidFueledSteelBoiler).addRequiredItem(new ItemStack(CoreInit.MachineFrameBasic)).addRequiredItem(AdvancedCraftingRecipes.BASIC_CIRCUIT.getStackNormal(2)).addRequiredItem(new ItemStack(StorageInit.tankAdv)).addRequiredItem(CraftingMaterial.GENERATOR_COMPONENT.getStackNormal());
		uvLightbox = new Research("uvLightbox", new ItemStack(FactoryInit.uvLightbox)).setResearchTime(1800).addParent(basicLvMachines).setComplexity(ResearchComplexity.ELECTRICAL).addRequiredScan(Blocks.GLOWSTONE, -1, "tile.glowstone.name").addRequiredItem(new ItemStack(Blocks.GLOWSTONE, 2)).addRequiredItem(new ItemStack(CoreInit.MachineFrameBasic)).addRequiredItem(AdvancedCraftingRecipes.BASIC_CIRCUIT.get()).addRequiredItem(new ItemStack(Items.REDSTONE, 6));
		mixer = new Research("mixer", new ItemStack(FactoryInit.mixer)).setResearchTime(1200).addParent(basicLvMachines).setComplexity(ResearchComplexity.ELECTRICAL).addRequiredItem(new ItemStack(CoreInit.MachineFrameBasic)).addRequiredItem(AdvancedCraftingRecipes.BASIC_CIRCUIT.get()).addRequiredItem(new ItemStack(Items.REDSTONE, 6)).addRequiredItem(new ItemStack(FactoryInit.steamMixer));
		laserEngraver = new Research("laserEngraver", new ItemStack(FactoryInit.laserEngraver)).addParent(uvLightbox).setResearchTime(2000).setComplexity(ResearchComplexity.ELECTRICAL).addRequiredItem(new ItemStack(CoreInit.MachineFrameBasic)).addRequiredItem(AdvancedCraftingRecipes.BASIC_CIRCUIT.getStackNormal(2)).addRequiredItem(new ItemStack(Blocks.REDSTONE_BLOCK, 3)).addRequiredItem(TMResource.RED_DIAMOND.getStackNormal(Type.GEM, 2));
		basicChipset = new Research("basicChipset", AdvancedCraftingRecipes.BASIC_CHIPSET.getStackNormal(1)).addParent(laserEngraver).setResearchTime(2200).setComplexity(ResearchComplexity.ELECTRICAL).addRequiredItem(new ItemStack(Blocks.REDSTONE_BLOCK, 3)).addRequiredItem(CraftingMaterial.SILICON_PLATE.getStackNormal(10)).addRequiredItem(new ItemStack(Items.GOLD_NUGGET, 2));
		advChipset = new Research("advChipset", AdvancedCraftingRecipes.ADVANCED_CHIPSET.getStackNormal(1)).addParent(basicChipset).addParent(mvCircuit).setComplexity(ResearchComplexity.ELECTRICAL).setResearchTime(3000).addRequiredItem(new ItemStack(Blocks.REDSTONE_BLOCK, 2)).addRequiredItem(TMResource.GOLD.getStackNormal(Type.DUST, 3)).addRequiredItem(CraftingMaterial.SILICON_PLATE.getStackNormal(12)).addRequiredItem(new ItemStack(Items.GOLD_NUGGET, 2));
		machineUpgrades = new Research("machineUpgrades", CraftingMaterial.UPGRADE_FRAME.getStackNormal()).setComplexity(ResearchComplexity.ELECTRICAL).setResearchTime(1200).addParent(basicLvMachines).addRequiredItem(new ItemStack(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE, 3)).addRequiredItem(TMResource.BLUE_METAL.getStackNormal(Type.INGOT, 3)).addRequiredItem(TMResource.IRON.getStackNormal(Type.PLATE, 8)).addRequiredItem(TMResource.ELECTRUM.getStackNormal(Type.CABLE, 2));
		powerConverterModules = new Research("powerConverterModules", new ItemStack(FactoryInit.rfModule)).setComplexity(ResearchComplexity.ELECTRICAL).setResearchTime(1600).addParent(machineUpgrades).addRequiredItem(new ItemStack(Blocks.REDSTONE_BLOCK, 4)).addRequiredItem(CraftingMaterial.UPGRADE_FRAME.getStackNormal(3)).addRequiredItem(TMResource.BLUE_METAL.getStackNormal(Type.INGOT, 3)).addRequiredItem(TMResource.ELECTRUM.getStackNormal(Type.CABLE, 2));
		speedUpgrade = new Research("speedUpgrade", new ItemStack(FactoryInit.speedUpgrade)).setComplexity(ResearchComplexity.ELECTRICAL).addParent(basicLvMachines).addParent(machineUpgrades).setResearchTime(2000).addRequiredItem(new ItemStack(Blocks.REDSTONE_BLOCK, 16)).addRequiredItem(new ItemStack(Blocks.REDSTONE_ORE)).addRequiredItem(TMResource.BLUE_METAL.getStackNormal(Type.INGOT, 3)).addRequiredItem(CraftingMaterial.UPGRADE_FRAME.getStackNormal(2)).setComplexity(ResearchComplexity.ADVANCED);
		electricalRubberProcessing = new Research("eRubberProcessing", new ItemStack(FactoryInit.electricalRubberProcessor)).addParent(basicLvMachines).setComplexity(ResearchComplexity.ELECTRICAL).setResearchTime(1600).addRequiredItem(CraftingMaterial.RUBBER.getStackNormal(10)).addRequiredItem(AdvancedCraftingRecipes.BASIC_CIRCUIT.get()).addRequiredItem(new ItemStack(CoreInit.MachineFrameBasic));
		improvedBlastFurnace = new Research("advBlastFurnace", new ItemStack(FactoryInit.advBlastFurnace)).addParent(blastFurnace).setResearchTime(700).setComplexity(ResearchComplexity.BRONZE).addRequiredItem(new ItemStack(Items.NETHERBRICK, 4)).addRequiredItem(TMResource.STEEL.getStackNormal(Type.PLATE)).addRequiredItem(CraftingMaterial.REFINED_BRICK.getStackNormal(2));
		conveyorBelts = new Research("conveyorBelts", new ItemStack(TransportInit.conveyorBeltFast)).addParent(enderLinkPower).setResearchTime(800).addRequiredItem(TMResource.COPPER.getStackNormal(Type.PLATE, 5)).addRequiredItem(TMResource.TIN.getStackNormal(Type.PLATE, 5));
		advWaterCollector = new Research("advWaterCollector", new ItemStack(FactoryInit.advWaterCollector)).addParent(waterCollector).addParent(blastFurnace).setResearchTime(800).setComplexity(ResearchComplexity.BRONZE).addRequiredItem(new ItemStack(FactoryInit.waterCollector)).addRequiredItem(new ItemStack(Items.WATER_BUCKET)).addRequiredItem(TMResource.STEEL.getStackNormal(Type.PLATE, 4)).addRequiredItem(CraftingMaterial.REDSTONE_CRYSTAL.getStackNormal());
		registerResearch(power);
		registerResearch(hammer);
		registerResearch(mortar);
		registerResearch(bronzeAge);
		registerResearch(fluidDucts);
		registerResearch(basicBoiler);
		registerResearch(basicBronzeMachines);
		registerResearch(bronzeAlloySmelter);
		registerResearch(enderLinkPower);
		registerResearch(bronzePlateBendingMachine);
		registerResearch(refinedBricks);
		registerResearch(cokeOven);
		registerResearch(blastFurnace);
		registerResearch(steelBoiler);
		registerResearch(steelFurnace);
		registerResearch(steelCrusher);
		registerResearch(rubberProcessing);
		registerResearch(basicTurbine);
		registerResearch(soldering);
		registerResearch(multimeter);
		registerResearch(lvCable);
		registerResearch(batteryBox);
		registerResearch(basicLvMachines);
		registerResearch(wireProcessing);
		registerResearch(speedUpgrade);
		registerResearch(lvPlateBendingMachine);
		registerResearch(lvAlloySmelter);
		registerResearch(waterCollector);
		registerResearch(eSolderingStation);
		registerResearch(industrialBlastFurnace);
		registerResearch(pump);
		registerResearch(fluidTransposer);
		registerResearch(advancedTank);
		registerResearch(solarPanel);
		registerResearch(mvTransformer);
		registerResearch(mvCable);
		registerResearch(mvCircuit);
		registerResearch(mvMachines);
		registerResearch(refinery);
		registerResearch(multiblockComponents);
		registerResearch(electrolyzer);
		registerResearch(centrifuge);
		registerResearch(plasticProcessing);
		registerResearch(hvTransformer);
		registerResearch(hvCable);
		registerResearch(advancedMultiblockParts);
		registerResearch(hvMachines);
		registerResearch(eliteTank);
		registerResearch(ultimateTank);
		registerResearch(limitableChest);
		registerResearch(mvTurbine);
		registerResearch(charger);
		registerResearch(configurator);
		registerResearch(geoThermalBoiler);
		registerResearch(geoThermalGenerator);
		registerResearch(liquidFueledBoiler);
		registerResearch(liquidFueledSteelBoiler);
		registerResearch(liquidFueledGenerator);
		registerResearch(uvLightbox);
		registerResearch(steamMixer);
		registerResearch(mixer);
		registerResearch(laserEngraver);
		registerResearch(basicChipset);
		registerResearch(advChipset);
		registerResearch(machineUpgrades);
		registerResearch(powerConverterModules);
		registerResearch(electricalRubberProcessing);
		registerResearch(improvedBlastFurnace);
		registerResearch(conveyorBelts);
		registerResearch(advancedCircuit);
		registerResearch(advWaterCollector);
	}

	private static void registerResearch(Research research) {
		research.setPrefix("tomsmod.");
		research.setRegistryName(research.getName());
		ResearchHandler.REGISTRY.register(research);
	}
}
