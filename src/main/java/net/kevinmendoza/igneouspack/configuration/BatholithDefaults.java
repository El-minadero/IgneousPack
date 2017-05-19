package net.kevinmendoza.igneouspack.configuration;

import com.flowpowered.math.vector.Vector2i;
import com.google.common.reflect.TypeToken;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.RegionTypes;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMap;
import net.kevinmendoza.igneouspack.configuration.PrimitiveWrappers.ControlMapConfig;
import net.kevinmendoza.igneouspack.configuration.PrimitiveWrappers.IntMinMax;
import net.kevinmendoza.igneouspack.configuration.PrimitiveWrappers.NoiseMapConfig;
import net.kevinmendoza.igneouspack.configuration.PrimitiveWrappers.RegionConfig2D;
import net.kevinmendoza.igneouspack.configuration.geoconf.AlterationDefaults;
import net.kevinmendoza.igneouspack.configuration.geoconf.IAlterationDefaults;
import net.kevinmendoza.igneouspack.configuration.geoconf.IReplacementDefaults;
import net.kevinmendoza.igneouspack.configuration.geoconf.ISurfaceDefaults;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
class BatholithDefaults implements IBatholithDefaults {
	static final TypeToken<BatholithDefaults> type = TypeToken.of(BatholithDefaults.class);
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
	
	BatholithDefaults(){
		NoiseMapConfig domain = new NoiseMapConfig(1000,3,1);
		controlMapConfig = new ControlMapConfig(domain,0);
		double[] heat = {10,15};
		double[] pressure = {20,25};
		alteration = new AlterationDefaults(20,heat,pressure);
		internalDecay=1;
		externalDecay=0.5;
		int[] width  = { 900,1000 };
		int[] length = { 1000,1600};
		regionConfig = new RegionConfig2D(width,length,RegionTypes.ELLIPSE);
		subObjectNumber = new IntMinMax(2,5);
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
