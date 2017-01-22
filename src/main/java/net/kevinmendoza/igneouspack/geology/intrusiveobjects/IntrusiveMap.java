package net.kevinmendoza.igneouspack.geology.intrusiveobjects;

import java.util.List;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworld.geology.GeologicContainer;
import net.kevinmendoza.geoworld.geology.StratigraphicColumn;
import net.kevinmendoza.geoworld.geology.geologicobject.GeologicObjectInterface;
import net.kevinmendoza.geoworld.geology.regionmap.GeologicRegionMap;
import net.kevinmendoza.igneouspack.geology.intrusivefactories.IntrusiveRegionFactory;
import net.kevinmendoza.igneouspack.main.IntrusiveFactory.IgneousMapBuilder;

public class IntrusiveMap extends GeologicRegionMap implements GeologicContainer {
	
	private final IntrusiveRegionFactory factory;
	
	public IntrusiveMap(IgneousMapBuilder builder) {
		super(builder);
		this.factory = builder.getFactory();
	}
	public void setFactorySeed(long seed) { factory.setSeed(seed); }
	protected GeologicObjectInterface buildRegion(Vector2i fullCenter) 
	{ return factory.makeRegionPrototype(fullCenter); }
	
	@Override
	protected StratigraphicColumn buildStratigraphicColumn(
			List<GeologicObjectInterface> relevantRegions) {
		// TODO Auto-generated method stub
		return null;
	}



}
