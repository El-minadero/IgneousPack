package net.kevinmendoza.igneouspack.geology;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.AbstractPrototype;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IPrototypeBuilder;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GenerationData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.IGeologyData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;
import net.kevinmendoza.igneouspack.configuration.geoconf.IAlterationDefaults;
import net.kevinmendoza.igneouspack.configuration.geoconf.IReplacementDefaults;
import net.kevinmendoza.igneouspack.configuration.geoconf.ISurfaceDefaults;

public abstract class IgneousPackPrototype extends AbstractPrototype {

	private final IAlterationDefaults alteration;
	private final IReplacementDefaults replacement;
	private final ISurfaceDefaults surface;
	
	public IgneousPackPrototype(IgneousPackPrototypeBuilder builder) {
		super(builder);
		alteration  = builder.getAlteration();
		replacement = builder.getReplacement();
		surface		= builder.getSurface();
	}
	
	public IgneousPackPrototype(IgneousPackPrototypeBuilder builder,Order order) {
		super(builder);
		alteration  = builder.getAlteration();
		replacement = builder.getReplacement();
		surface		= builder.getSurface();
	}
	
	protected IAlterationDefaults getAlteration() { return alteration; }
	protected IReplacementDefaults getReplacement() { return replacement; }
	protected ISurfaceDefaults getSurface() { return surface; }
	
	protected double getHeat(double yVal,int surface) {
		double tempH =  (surface- yVal)*getAlteration().getHeatGradient() + getAlteration().getSurfaceTemperature();
		if(tempH<0)
			tempH= 0;
		return tempH;
	}
	
	protected double getPressure(int queryDepth,int surface) {
		double tempP =  (surface - queryDepth)*getAlteration().getPressureGradient();
		if(tempP<0)
			tempP= 0;
		return tempP;
	}
	

}
