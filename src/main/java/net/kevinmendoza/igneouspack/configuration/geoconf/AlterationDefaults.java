package net.kevinmendoza.igneouspack.configuration.geoconf;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import com.google.common.reflect.TypeToken;

import net.kevinmendoza.igneouspack.configuration.PrimitiveWrappers.DoubleMinMax;;

@ConfigSerializable
public class AlterationDefaults {
	static final TypeToken<AlterationDefaults> type = TypeToken.of(AlterationDefaults.class);
	@Setting
	private DoubleMinMax heatGradient;
	@Setting
	private DoubleMinMax pressureGradient;
	@Setting
	private double surfaceTemperature;
	
	public AlterationDefaults(double surfaceTemp,double[] heat, double[] pressure) {
		heatGradient = new DoubleMinMax(heat[0],heat[1]);
		pressureGradient = new DoubleMinMax(pressure[0],pressure[1]);
		surfaceTemperature = surfaceTemp;
	}
	
	public IAlterationDefaults getAlterationConfig(long seed) {
		return new AlterationConfig(heatGradient.getValue(seed),surfaceTemperature,pressureGradient.getValue(seed));
	}
	
	static class AlterationConfig implements IAlterationDefaults {
		static final TypeToken<AlterationConfig> type = TypeToken.of(AlterationConfig.class);
		double heatGradient;
		double surfaceTemperature;
		double pressureGradient;
		
		AlterationConfig(double heatGradient,double surfaceTemperature,double pressureGradient){
			this.heatGradient = heatGradient;
			this.surfaceTemperature = surfaceTemperature;
			this.pressureGradient = pressureGradient;
		}
		public double getHeatGradient() { return heatGradient; }
		public double getSurfaceTemperature() { return surfaceTemperature; }
		public double getPressureGradient() { return pressureGradient; }
	}
	
}
