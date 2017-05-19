package net.kevinmendoza.igneouspack.geology.intrusive;

import com.google.inject.Inject;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.AbstractMapFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.AbstractPrototypeFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeologyMapBuilder;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.AbstractPrototype;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IPrototypeBuilder;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifier;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifierFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMap;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMapFactory;
import net.kevinmendoza.igneouspack.configuration.IGlobalDefaults;
import net.kevinmendoza.igneouspack.configuration.IMapDefaults;
import net.kevinmendoza.igneouspack.configuration.geoconf.IAlterationDefaults;
import net.kevinmendoza.igneouspack.configuration.geoconf.IReplacementDefaults;
import net.kevinmendoza.igneouspack.configuration.geoconf.ISurfaceDefaults;
import net.kevinmendoza.igneouspack.geology.IgneousPackPrototypeBuilder;

final class IntrusiveMapFactory extends AbstractMapFactory {

	@Inject
	IGlobalDefaults globalDefaults;
	@Inject
	IMapDefaults mapDefaults;
	private MapBuilder b;
	private MapPrototypeBuilder p;
	
	private MapBuilder getMapBuilder() {
		if(b==null) {
			 b = new MapBuilder()
						.setSpacing(mapDefaults.getSpacing())
						.setFrequency(mapDefaults.getFrequency())
						.setSeed(getSeed())
						.setFactory(IntrusiveFactoryHub.GetBatholithFactory(getSeed()))
						.setOrder(mapDefaults.getOrder());
		}
		return b;
	}
	private MapPrototypeBuilder getMapPrototypeBuilder() {
		if(p==null) {
			 p = new MapPrototypeBuilder()
						.setAlterationDefaults(mapDefaults.getAlteration(getSeed()))
						.setReplacementDefaults(mapDefaults.getReplacement(getSeed()))
						.setSurfaceDefaults(mapDefaults.getSurface(getSeed()));
		}
		return p;
	}
	
	@Override
	public IGeology createGeologyMap() {
		MapBuilder b1 = getMapBuilder();
				b1.setPrototype(getPrototype());
		return makeGeologyMap(b);
	}
	public IGeology createDebuggedGeologyMap() {
		MapBuilder b1 = getMapBuilder();
		b1.debug();
		b1.setPrototype(getPrototype());
		return makeGeologyMap(b);
	}
	
	private AbstractPrototype getPrototype() {
		return getMapPrototypeBuilder().build();
	}

	static class MapPrototypeBuilder implements IgneousPackPrototypeBuilder {
		private IAlterationDefaults alteration;
		private IReplacementDefaults replacement;
		private ISurfaceDefaults surface;	
		
		public ISurfaceDefaults	getSurface() { return surface; }
		public IReplacementDefaults getReplacement() { return replacement; }
		public IAlterationDefaults getAlteration() { return alteration; }
		
		private MapPrototypeBuilder setAlterationDefaults(IAlterationDefaults conf) { alteration = conf; return this; }
		private MapPrototypeBuilder setReplacementDefaults(IReplacementDefaults conf) { replacement = conf; return this; }
		private MapPrototypeBuilder setSurfaceDefaults(ISurfaceDefaults conf) { surface = conf; return this; }
		
		private IntrusiveMap build() {
			return new IntrusiveMap(this);
		}
		public Region getRegion() {return null;}
		public Order getOrder() {return null;}
		public double getExternalDecayConstant() {return 1;}
		public double getInternalDecayConstant() {return 1;}
		public NoiseMap getControlMap() {return NoiseMapFactory.MakeSingleValueMap(1.0);}
	}
	
	private static class MapBuilder implements IGeologyMapBuilder {
		
		private int spacing;
		private double freq;
		private Order order;
		private long seed;
		private AbstractPrototypeFactory factory;
		private AbstractPrototype prototype;
		private boolean debug;
		
		MapBuilder(){
			debug = false;
		}
		
		private MapBuilder debug() { debug=true; return this; }
		private MapBuilder setPrototype(AbstractPrototype prototype) {this.prototype = prototype; return this;}
		private MapBuilder setSpacing(int spacing) {this.spacing = spacing; return this;}
		private MapBuilder setOrder(Order order)    {this.order = order; return this; }
		private MapBuilder setFrequency(double freq) {this.freq = freq; return this; }
		private MapBuilder setSeed(long seed) 		{this.seed = seed; return this; }
		private MapBuilder setFactory(AbstractPrototypeFactory factory) { this.factory = factory; return this; }
	
		public boolean debugMode() { return debug; }
		public int getSpacing() { return spacing; }
		public double getFrequency() { return freq; }
		public Order getOrder() { return order;  }
		public long getSeed() { return seed; }
		public AbstractPrototypeFactory getFactory() { return factory;}
		public AbstractPrototype getPrototype() { return prototype;  }
		
	}
}
