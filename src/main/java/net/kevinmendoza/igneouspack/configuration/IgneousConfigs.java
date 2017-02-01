package net.kevinmendoza.igneouspack.configuration;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworld.geology.LithogenicOrder;
import net.kevinmendoza.geoworld.proceduralgeneration.shapes.Region;
import net.kevinmendoza.geoworld.proceduralgeneration.simplex.NoiseMap;
import net.kevinmendoza.igneouspack.configuration.Config.ExtentConfig;
import net.kevinmendoza.igneouspack.configuration.Config.RegionConfig;
import net.kevinmendoza.igneouspack.configuration.Config.NoiseGenerator;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

class IgneousConfigs {

	@ConfigSerializable
	static class Batholith {
		@Setting(value="spacing",comment="average spacing between batholiths")
		private int spacing = 500;
		@Setting(value="frequency",comment="frequency of batholith placement. Must be between 0-1")
		private double frequency = 0.5;
		@Setting(value="order",comment="Geologic order of batholith placement. Must be Capitalized letters")
		private String order = "SECOND";
		@Setting(value="region-parameters",comment="define batholith boundaries")
		private RegionConfig regionConfig = new RegionConfig("RECTANGLE",500,300);
		@Setting(value="pluton-swarm-number",comment="number of possible pluton swarms")
		private ExtentConfig swarmNumber = new ExtentConfig(10);
		
		public int getSpacing() { return spacing; }
		public double getFreq() { return frequency; }
		public LithogenicOrder getOrder() { return LithogenicOrder.valueOf(order); }
		public Region getRegion(Vector2i vec) { return regionConfig.getRegion(vec); }
		public int getSwarmNumber(long seed) { return (int) swarmNumber.getValue(seed); }

	}
	
	@ConfigSerializable
	static class PlutonSwarm {
		@Setting(value="order",comment="Geologic order of swarm placement. Must be Capitalized letters")
		private String order = "SECOND";
		@Setting(value="region-parameters",comment="swarm boundaries")
		private RegionConfig regionConfig = new RegionConfig("RECTANGLE",200,150);
		@Setting(value="pluton-number",comment="number of possible plutons")
		private ExtentConfig number = new ExtentConfig(10);

		public LithogenicOrder getOrder() { return LithogenicOrder.valueOf(order); }
		public Region getRegion(Vector2i vec) { return regionConfig.getRegion(vec); }
		public int getPlutonNumber(long seed) { return (int) number.getValue(seed); }

	}
	
	@ConfigSerializable
	static class Pluton {
		@Setting(value="order",comment="Geologic order of pluton placement. Must be Capitalized letters")
		private String order = "SECOND";
		@Setting(value="region-parameters",comment="swarm boundaries")
		private RegionConfig regionConfig = new RegionConfig("RECTANGLE",200,150);
		@Setting(value="noise-generator")
		private NoiseGenerator generator = new NoiseGenerator(5,500);

		public LithogenicOrder getOrder() { return LithogenicOrder.valueOf(order); }
		public Region getRegion(Vector2i vec) { return regionConfig.getRegion(vec); }
		public NoiseMap getNoiseGenerator(Vector2i vec) { return generator.getNoiseMap(vec); }
	}
}
