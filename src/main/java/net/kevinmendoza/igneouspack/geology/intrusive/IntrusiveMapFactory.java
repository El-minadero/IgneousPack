package net.kevinmendoza.igneouspack.geology.intrusive;

import com.google.inject.Inject;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.Order;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.AbstractMapFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.AbstractPrototypeFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeologyMapBuilder;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.AbstractPrototype;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IPrototypeBuilder;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifier;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifierFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMap;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMapFactory;
import net.kevinmendoza.igneouspack.configuration.IIntrusiveDefaults;
import net.kevinmendoza.igneouspack.configuration.IMapDefaults;
import net.kevinmendoza.igneouspack.geology.IgneousPackPrototypeBuilder;


final class IntrusiveMapFactory extends AbstractMapFactory {
	@Inject
	private IMapDefaults mapDefaults;
	
	private MapBuilder b;
	
	private MapBuilder getMapBuilder() {
		if(b==null) {
			 b = new MapBuilder()
						.setSpacing(mapDefaults.getSpacing())
						.setFrequency(mapDefaults.getFrequency())
						.setSubObjectFactory(IntrusiveFactoryHub
								.getFactory(
										StructureFactoryTypes.BATHOLITH, getSeed()))
						.setSeed(getSeed())
						.setOrder(mapDefaults.getOrder());
		}
		return b;
	}
	
	@Override
	public IGeology createGeologyMap() {
		return makeGeologyMap(getMapBuilder());
	}


	
	private static class MapBuilder implements IGeologyMapBuilder {
		
		private int spacing;
		private double freq;
		private Order order;
		private long seed;
		private AbstractPrototypeFactory factory;
		
		private MapBuilder setSeed(long seed) { this.seed = seed; return this;}
		private MapBuilder setSpacing(int spacing) {this.spacing = spacing; return this;}
		private MapBuilder setOrder(Order order)    {this.order = order; return this; }
		private MapBuilder setFrequency(double freq) {this.freq = freq; return this; }
		private MapBuilder setSubObjectFactory(AbstractPrototypeFactory factory) { this.factory = factory; return this; }
		
		public int getSpacing() { return spacing; }
		public double getFrequency() { return freq; }
		public Order getOrder() { return order;  }
		public long getSeed() { return seed; }
		public AbstractPrototypeFactory getSubObjectFactory() { return factory;}
		
	}
}
