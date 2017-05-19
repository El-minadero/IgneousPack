package net.kevinmendoza.igneouspack.configuration;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;
import com.google.common.reflect.TypeToken;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifier;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.RegionFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.RegionTypes;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMap;
import net.kevinmendoza.igneouspack.configuration.PrimitiveWrappers.IntMinMax;
import net.kevinmendoza.igneouspack.configuration.PrimitiveWrappers.MultiplierMapConfig;
import net.kevinmendoza.igneouspack.configuration.PrimitiveWrappers.NoiseMapConfig;
import net.kevinmendoza.igneouspack.configuration.GeologyConfiguration.AlterationParameters;
import net.kevinmendoza.igneouspack.configuration.PrimitiveWrappers.ControlMapConfig;
import net.kevinmendoza.igneouspack.configuration.PrimitiveWrappers.RegionConfig3D;
import net.kevinmendoza.igneouspack.configuration.PrimitiveWrappers.RegionConfig2D;
import net.kevinmendoza.igneouspack.configuration.geoconf.AlterationDefaults;
import net.kevinmendoza.igneouspack.configuration.geoconf.IAlterationDefaults;
import net.kevinmendoza.igneouspack.configuration.geoconf.IReplacementDefaults;
import net.kevinmendoza.igneouspack.configuration.geoconf.ISurfaceDefaults;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class PlutonDefaults implements IPlutonDefaults {
	static final TypeToken<PlutonDefaults> type = TypeToken.of(PlutonDefaults.class);
	@Setting
	private double internalDecay;
	@Setting
	private double externalDecay;
	@Setting
	private RegionConfig3D regionConfig;
	@Setting
	private AlterationDefaults alteration;
	@Setting 
	private MultiplierMapConfig plutonMap;
	@Setting
	private ControlMapConfig controlMapConfig;
	@Setting 
	private IntMinMax plutonEmplacementDepth;
	PlutonDefaults(){
		NoiseMapConfig plutonSimplexMap =  new NoiseMapConfig(100,3,1);
		plutonMap = new MultiplierMapConfig(plutonSimplexMap,5);
		NoiseMapConfig domain = new NoiseMapConfig(500,3,1);
		controlMapConfig = new ControlMapConfig(domain,-0.2);
		int[] width  = { 100,150 };
		int[] length = { 100,150 };
		int[] depth  = { 100,150 };
		double[] heat = {2300,2700};
		double[] pressure = {10,25};
		int[] depthRange = {-20,30};
		plutonEmplacementDepth = new IntMinMax(depthRange[0],depthRange[1]);
		//100
		alteration = new AlterationDefaults(20,heat,pressure);
		internalDecay=1;
		externalDecay=0.01;
		regionConfig = new RegionConfig3D(width,length,depth,RegionTypes.ELLIPSE);
	}
	
	public int getSubObjectNumber(Vector2i randomInternalPoint) { return 0; }
	
	public Region getRegion(Vector3i vec) { 
		return regionConfig.getRegion(vec);
	}
	
	public NoiseMap getControlMap(long vectorSeed) {
		return controlMapConfig.getControlMap(vectorSeed);
	}
	
	public NoiseMap getAlterationMap(long vectorSeed) {
		return plutonMap.getMultiplierMap(vectorSeed);
	}

	public double getExternalDecay() { return externalDecay; }

	public double getInternalDecay() { return internalDecay; }
	
	public double getEmplacementDepth(long seed) { return plutonEmplacementDepth.getValue(seed); }

	public IAlterationDefaults getAlteration(long seed) { return alteration.getAlterationConfig(seed); }

	public ISurfaceDefaults getSurface(long seed) { return null; }

	public IReplacementDefaults getReplacement(long seed) { return null; }
}
