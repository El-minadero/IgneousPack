package net.kevinmendoza.igneouspack.configuration;

import java.util.ArrayList;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.configuration.PrimitiveWrapperConfig.IntMinMax;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifier;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifierFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.RegionConfiguration;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.AbstractRegionFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.RegionTypes;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.MultiFrequencyNoiseMapConfiguration;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMap;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class GeometryBehavior implements IGeometryBehavior {
	
	@Setting
	private IntMinMax emplacementDepth;

	@Setting
	private MultiFrequencyNoiseMapConfiguration noiseMapConfiguration;
	
	@Setting
	private RegionConfiguration regionConfiguration;
	
	@Setting
	private double internalDecayConstant;
	
	@Setting 
	private double externalDecayConstant;
	
	GeometryBehavior(Builder builder){
		emplacementDepth		= builder.emplacementDepth;
		noiseMapConfiguration 	= builder.noiseMapConfiguration;
		regionConfiguration 	= builder.regionConfiguration;
		internalDecayConstant 	= builder.internalDecayConstant;
		externalDecayConstant 	= builder.externalDecayConstant;
	}
	public Region getRegion(Vector2i vec) { 
		Vector3i vec3 = produceCenter(vec);
		return regionConfiguration.getRegion(vec3); 
	}
	private Vector3i produceCenter(Vector2i vec) {
		int y = emplacementDepth.getValue(vec);
		return new Vector3i(vec.getX(), y, vec.getY());
	}
	public Region getRegion(Vector3i vec)  { 
		return regionConfiguration.getRegion(vec); 
	}

	public double getInternalDecay() { return this.internalDecayConstant; }
	public double getExternalDecay() { return this.externalDecayConstant; }
	
	public NoiseMap getNoiseMap(Vector3i vec) { return this.noiseMapConfiguration.getMultiplierMap(vec); }
	public NoiseMap getNoiseMap(Vector2i vec) { return this.noiseMapConfiguration.getMultiplierMap(vec); }
	public NoiseMap getNoiseMap(long seed) { return this.noiseMapConfiguration.getMultiplierMap(seed);   }

	
	public static class Builder {
		
		private IntMinMax emplacementDepth;
		private MultiFrequencyNoiseMapConfiguration noiseMapConfiguration;
		private RegionConfiguration regionConfiguration;
		private double internalDecayConstant;
		private double externalDecayConstant;
		
		public Builder () {
			emplacementDepth = new IntMinMax();
			internalDecayConstant = 1;
			externalDecayConstant = 1;
		}
		
		public Builder setMultiplierMap(double maxFrequency, int octaves, int power) {
			noiseMapConfiguration = new MultiFrequencyNoiseMapConfiguration(maxFrequency,octaves,power);
			return this;
		}
		
		public IntMinMax getEmplacementDepth() { return emplacementDepth; }
		public Builder setEmplacementDepth(IntMinMax imm) { this.emplacementDepth = imm; return this; }
		public Builder setRegionConfiguration(RegionTypes type,ArrayList<IntMinMax> dimensions) {
			regionConfiguration = new RegionConfiguration(dimensions,type);
			return this;
		}
		
		public Builder setDecayConstant(double external, double internal) {
			this.internalDecayConstant=internal;
			this.externalDecayConstant=external;
			return this;
		}
		
		public GeometryBehavior build() {
			return new GeometryBehavior(this);
		}
	}

}
