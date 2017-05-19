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
import net.kevinmendoza.igneouspack.configuration.PrimitiveWrappers.NoiseMapConfig;
import net.kevinmendoza.igneouspack.configuration.PrimitiveWrappers.ControlMapConfig;
import net.kevinmendoza.igneouspack.configuration.PrimitiveWrappers.DoubleMinMax;
import net.kevinmendoza.igneouspack.configuration.PrimitiveWrappers.RegionConfig2D;
import net.kevinmendoza.igneouspack.configuration.geoconf.AlterationDefaults;
import net.kevinmendoza.igneouspack.configuration.geoconf.IAlterationDefaults;
import net.kevinmendoza.igneouspack.configuration.geoconf.IReplacementDefaults;
import net.kevinmendoza.igneouspack.configuration.geoconf.ISurfaceDefaults;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
class SwarmDefaults implements ISwarmDefaults {
	static final TypeToken<SwarmDefaults> type = TypeToken.of(SwarmDefaults.class);
	@Setting
	private double internalDecay;
	@Setting
	private double externalDecay;
	@Setting
	private IntMinMax subObjectNumber;
	@Setting
	private RegionConfig2D regionConfig;
	@Setting
	private AlterationDefaults alteration;
	@Setting
	private ControlMapConfig controlMapConfig;
	
	SwarmDefaults(){
		NoiseMapConfig domain = new NoiseMapConfig(1000,3,1);
		controlMapConfig = new ControlMapConfig(domain,0.3);
		double[] heat = {20,30};
		double[] pressure = {10,25};
		alteration = new AlterationDefaults(20,heat,pressure);
		internalDecay=1;
		externalDecay=0.01;
		int[] width  = { 200,900};
		int[] length = { 500,600};
		regionConfig = new RegionConfig2D(width,length,RegionTypes.ELLIPSE);
		subObjectNumber = new IntMinMax(3,5);
	}
	
	public int getSubObjectNumber(long seed) { 
		return subObjectNumber.getValue(seed); 
	}
	
	public Region getRegion(Vector2i vec) { 
		return regionConfig.getRegion(vec);
	}
	
	public NoiseMap getControlMap(long vectorSeed) {
		return controlMapConfig.getControlMap(vectorSeed);
	}

	public double getExternalDecay() { return externalDecay; }

	public double getInternalDecay() { return internalDecay; }

	@Override
	public IAlterationDefaults getAlteration(long seed) { return alteration.getAlterationConfig(seed);}

	@Override
	public ISurfaceDefaults getSurface(long seed) { return null; }

	@Override
	public IReplacementDefaults getReplacement(long seed) { return null; }

}
