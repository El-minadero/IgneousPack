package net.kevinmendoza.igneouspack.configuration;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworld.proceduralgeneration.probability.DistributionFactory;
import net.kevinmendoza.geoworld.proceduralgeneration.shapes.Region;
import net.kevinmendoza.geoworld.proceduralgeneration.shapes.RegionFactory;
import net.kevinmendoza.geoworld.proceduralgeneration.shapes.RegionTypes;
import net.kevinmendoza.geoworld.proceduralgeneration.simplex.NoiseMap;
import net.kevinmendoza.geoworld.proceduralgeneration.simplex.NoiseMapFactory;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

public class Config {
	@ConfigSerializable
	public static class RegionConfig{
		@Setting(value="region-width")
		private ExtentConfig width;
		@Setting(value="region-length")
		private ExtentConfig length;
		@Setting(value="region-type", comment="can either be rectangle or ellipse")
		private String type;
		
		public RegionConfig(String string, int w, int l) {
			type = string;
			width = new ExtentConfig(w);
			length = new ExtentConfig(l);
		}
		
		public Region getRegion(Vector2i vec) {
			long hash = vec.hashCode();
			int a = (int) width.getValue(hash);
			int b = (int) length.getValue(hash^1);
			double angle = DistributionFactory.BuildUniformDistribution(hash^2,0, Math.PI*2).getRVar();
			return RegionFactory.MakeRegionType(RegionTypes.valueOf(type), vec, a, b,angle);
		}
	}
	
	@ConfigSerializable
	public static class ExtentConfig{
		
		@Setting(value="max")
		private int max;
		@Setting(value="min")
		private int min;
		
		public ExtentConfig(int i) {
			min = i/2;
			max = i + min;
		}
		
		public double getValue(long seed) {
			return DistributionFactory.BuildUniformDistribution(seed, min, max).getRVar();
		}
	}
	
	@ConfigSerializable
	public static class NoiseGenerator{
		
		@Setting(value="octaves")
		private int octaves;
		@Setting(value="base-frequency")
		private int frequency;
		
		public NoiseGenerator(int oct,int b) {
			octaves 	 = oct;
			frequency    = b;
		}
		
		public NoiseMap getNoiseMap(Vector2i vec) {
			long hash = vec.hashCode();
			double[] freq = new double[octaves]; double[] weight = new double[octaves];
			for(int i = 0;i<octaves;i++) {
				freq[i] = frequency/(i+1);
				weight[i] = 1/(i+1);
			}
			return NoiseMapFactory.MakeSimplexNoiseMap(freq, weight, hash);
		}
	}

}
