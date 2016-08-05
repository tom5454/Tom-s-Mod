package com.tom.lib;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
/**{@link com.tom.config.Config}*/
public final class Configs {
	public static final String Modid = "TomsMod";
	public static final String version = "2.0.12";
	public static final String CLIENT_PROXY_CLASS = "com.tom.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "com.tom.proxy.ServerProxy";
	public static final String Chanel1 = Modid + ":Chanel1";
	public static final int InjectorMaxEnergy = 100000000;
	public static final int InjectorMaxEnergyInput = 100000;
	public static final String FMP = "ForgeMultipart";
	public static final String COMPUTERCRAFT = "ComputerCraft";
	public static final int InjectorUsage = 100;
	public static final EnumFacing InjectorPort = EnumFacing.DOWN;
	public static final int ChargerMaxEnergy = 1000000;
	public static final int textureUpdateRate = 40;
	public static final float DUCT_MIN_POS = 0.375F;
	public static final float DUCT_MAX_POS = 0.625F;
	public static final int BASIC_TANK_SIZE = 10000;
	public static final int FusionStartFluidAmmount = 500;
	public static final int chargerStart = 1000;
	public static final int ChargerUsage = 10;
	public static final boolean machinesExplode = true;
	public static final int multiblockPressurePortVolume = 100000;
	public static final int updateRate = 100;
	public static final double EnergyCellCoreMax = 500000000;
	public static final int monitorSize = 64;
	public static final String BLOOD_MAGIC = "AWWayofTime";
	public static final String BOTANIA = "Botania";
	public static final String MODEL_LOCATION = "minecraft:textures/model/tm/";
	public static final ResourceLocation antennaA = new ResourceLocation(MODEL_LOCATION+"TabletAccessPoint.png");
	public static final ResourceLocation antennaLight = new ResourceLocation(MODEL_LOCATION+"TabletAccessPointOn.png");
	public static final ResourceLocation antennaLightOn = new ResourceLocation(MODEL_LOCATION+"Antenna.png");
	public static final String JEI = "JEI";
	public static final double wirelessChargerLoss = 4D;
	public static final int maxProcessorTier = 10;
	public static final ResourceLocation contBoxOff = new ResourceLocation(MODEL_LOCATION+"ControllerBox.png");
	public static final String AE2 = "appliedenergistics2";
	public static final String PNEUMATICCRAFT = "PneumaticCraft";
	public static final ResourceLocation cam = new ResourceLocation(MODEL_LOCATION+"Camera.png");
	public static final String keyCatergory = "keys.tomsmod.category";
	public static final String keyPrefix = "keys.tomsmod.key.";
	public static final String CHISEL = "chisel";
	public static final ResourceLocation monitor = new ResourceLocation(MODEL_LOCATION+"monitor.png");
	public static final ResourceLocation pixel = new ResourceLocation("tm:pixel.png");
	public static final String modid_Short = "tm";
	public static final String coreDependencies = "required-after:TomsMod|Core";
	public static final ResourceLocation driveModel = new ResourceLocation(MODEL_LOCATION+"driveCellsModel.png");
	public static final String fakePlayerName = "[TomsMod]";
	public static final int fluidDuctMaxInsert = 1000;
	public static final int fluidDuctMaxExtract = 500;
	public static final String mainDependencies = "";
	public static final String OPEN_COMPUTERS = "OpenComputers|Core";
}