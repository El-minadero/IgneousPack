package net.kevinmendoza.igneouspack.configuration;

import java.util.Random;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;
import com.google.common.reflect.TypeToken;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifier;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifierFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.RegionFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.RegionTypes;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMap;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMapFactory;
import net.kevinmendoza.geoworldlibrary.utilities.HashCodeOperations;
import net.kevinmendoza.igneouspack.configuration.GeologyConfiguration.AlterationParameters;
import net.kevinmendoza.igneouspack.configuration.geoconf.IAlterationDefaults;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

public class PrimitiveWrappers {
	
	@ConfigSerializable
	static class ControlMapConfig {
		static final TypeToken<ControlMapConfig> type = TypeToken.of(ControlMapConfig.class);
		@Setting
		NoiseMapConfig cutoffMap;
		@Setting
		double cutoff;
		
		ControlMapConfig(NoiseMapConfig cutoffMap ,double cutoff){
			this.cutoffMap = cutoffMap;
			this.cutoff = cutoff;
		}
		
		NoiseMap getControlMap(long seed){
			NoiseMap map1 = cutoffMap.getNoiseMap(seed);
			return NoiseMapFactory.MakeSimpleDomainMap(map1, cutoff);
		}
	}
	@ConfigSerializable
	static class MultiplierMapConfig {
		static final TypeToken<MultiplierMapConfig> type = TypeToken.of(MultiplierMapConfig.class);
		@Setting
		NoiseMapConfig noiseMap;
		@Setting
		double multiplier;
		
		MultiplierMapConfig(NoiseMapConfig noiseMap ,double multiplier){
			this.noiseMap = noiseMap;
			this.multiplier = multiplier;
		}
		
		NoiseMap getMultiplierMap(long seed){
			NoiseMap map1 = noiseMap.getNoiseMap(seed);
			return NoiseMapFactory.MakeMultiplierNoiseMap(map1, multiplier);
		}
	}
	@ConfigSerializable
	static class NoiseMapConfig {
		static final TypeToken<NoiseMapConfig> type = TypeToken.of(NoiseMapConfig.class);
		@Setting
		double baseFrequency;
		@Setting
		int octaves;
		@Setting
		int octavePower;
		
		NoiseMapConfig(double baseFrequency, int octaves,int octavePower){
			this.baseFrequency = baseFrequency;
			this.octaves = octaves;
			this.octavePower = octavePower;
		}
		
		NoiseMap getNoiseMap(long seed) {
			return NoiseMapFactory.MakePowerOctaveMap(baseFrequency, octaves, octavePower, seed);
		}
	}
	@ConfigSerializable
	static class RegionConfig2D {
		static final TypeToken<RegionConfig2D> type = TypeToken.of(RegionConfig2D.class);
		@Setting
		IntMinMax major;
		@Setting
		IntMinMax minor;
		@Setting
		RegionTypes types;
		
		RegionConfig2D(int[] majorAxisDim, int[] minorAxisDim,RegionTypes type){
			major = new IntMinMax(majorAxisDim[0],majorAxisDim[1]);
			minor = new IntMinMax(minorAxisDim[0],minorAxisDim[1]);
			this.types = type;
		}
		
		Region getRegion(Vector2i vec) {
			long seed = HashCodeOperations.createVectorSeed(vec);
			Random rand = new Random(seed-3);
			double rotation = rand.nextDouble()*Math.PI*2;
			return RegionFactory.MakeRegionType(types, vec, major.getValue(seed), minor.getValue(seed+4), rotation);
		}
	}
	@ConfigSerializable
	static class RegionConfig3D {
		static final TypeToken<RegionConfig3D> type = TypeToken.of(RegionConfig3D.class);
		@Setting
		IntMinMax x_extent;
		@Setting
		IntMinMax y_extent;
		@Setting
		IntMinMax z_extent;
		@Setting
		RegionTypes types;
		
		RegionConfig3D(int[] xAxisDim, int[] yAxisDim,int[] zAxisDim,RegionTypes type){
			x_extent = new IntMinMax(xAxisDim[0],xAxisDim[1]);
			y_extent = new IntMinMax(yAxisDim[0],yAxisDim[1]);
			z_extent = new IntMinMax(zAxisDim[0],zAxisDim[1]);
			this.types = type;
		}
		
		Region getRegion(Vector3i vec) {
			long seed = HashCodeOperations.createVectorSeed(vec);
			return RegionFactory.MakeEllipsoid(vec,x_extent.getValue(seed)
					,y_extent.getValue(seed+1),z_extent.getValue(seed+2));
		}
	}
	@ConfigSerializable
	public static class IntMinMax {
		static final TypeToken<IntMinMax> type = TypeToken.of(IntMinMax.class);
		@Setting
		private int min;
		@Setting
		private int max;
		
		public IntMinMax(int min, int max){
			this.min = min; this.max=max;
		}
		
		public int getValue(long seed) {
			Random rand = new Random(seed);
			rand.nextDouble();
			return min + rand.nextInt(max-min);
		}
	}
	
	@ConfigSerializable
	public static class DoubleMinMax {
		static final TypeToken<DoubleMinMax> type = TypeToken.of(DoubleMinMax.class);
		@Setting
		private double min;
		@Setting
		private double max;
		
		public DoubleMinMax(double min, double max){
			this.min = min; this.max=max;
		}
		
		public double getValue(long seed) {
			Random rand = new Random(seed);
			rand.nextDouble();
			return min + rand.nextDouble()*(max-min);
		}
	}
}
