package net.kevinmendoza.igneouspack.configuration;

import com.flowpowered.math.vector.Vector2i;
import com.google.common.reflect.TypeToken;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.Order;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.probability.DistributionFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.AbstractRegionFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.RegionTypes;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMap;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMapFactory;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

public class GeologyConfiguration {
	
	@ConfigSerializable
	public static class AlterationParameters {
		static final TypeToken<AlterationParameters> type = TypeToken.of(AlterationParameters.class);
		@Setting
		public double geothermalGradient;
		@Setting 
		public double surfaceTemperature;
		
		public AlterationParameters(double gradient, double temp) {
			geothermalGradient = gradient;
			surfaceTemperature = temp;
		}
		public double getSurfaceTemperature() { return surfaceTemperature; }
		public double getHeatGradient() { return geothermalGradient; }
	}
	


}
