package net.kevinmendoza.igneouspack.configuration;

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
	}
	
	@ConfigSerializable
	public static class ExtentConfig{
		
		@Setting(value="max")
		public int max;
		@Setting(value="min")
		public int min;
		
		public ExtentConfig(int i) {
			min = i/2;
			max = i + min;
		}
	}
	
	@ConfigSerializable
	public static class NoiseGenerator{
		
		@Setting(value="octaves")
		public int octaves;
		@Setting(value="base-frequency")
		public int frequency;
		
		public NoiseGenerator(int oct,int b) {
			octaves 	 = oct;
			frequency    = b;
		}
	}

}
