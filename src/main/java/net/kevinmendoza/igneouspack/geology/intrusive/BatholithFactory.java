package net.kevinmendoza.igneouspack.geology.intrusive;

import com.flowpowered.math.vector.Vector2i;
import com.google.inject.Inject;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.AbstractPrototypeFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeologyMapBuilder;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeologyNode;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IPrototypeBuilder;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.NodeBuilder;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.AbstractPrototype;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifierFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifier;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.RegionPointGenerator;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMap;
import net.kevinmendoza.igneouspack.configuration.IBatholithDefaults;
import net.kevinmendoza.igneouspack.configuration.IGlobalDefaults;
import net.kevinmendoza.igneouspack.configuration.geoconf.IAlterationDefaults;
import net.kevinmendoza.igneouspack.configuration.geoconf.IReplacementDefaults;
import net.kevinmendoza.igneouspack.configuration.geoconf.ISurfaceDefaults;
import net.kevinmendoza.igneouspack.geology.IgneousPackPrototypeBuilder;
import net.kevinmendoza.igneouspack.geology.intrusive.IntrusiveMapFactory.MapPrototypeBuilder;

class BatholithFactory extends AbstractPrototypeFactory {

	@Inject
	IGlobalDefaults globalDefaults;
	@Inject
	IBatholithDefaults batholithDefaults;
	BatholithBuilder builder;
	
	private BatholithBuilder getBuilder() {
		if(builder==null) {
			builder = new BatholithBuilder();
			builder.setExternalDecay(batholithDefaults.getExternalDecay())
			.setInternalDecay(batholithDefaults.getInternalDecay())
			.setFactory(IntrusiveFactoryHub.GetPlutonFactory(getSeed()))
			.setOrder(Order.FIRST);
		}
		return builder;
	}

	@Override
	public IGeologyNode makePrototype(Vector2i vec) {
		Region region = makeRegion(vec);
		long vectorSeed = createVectorSeed(vec);
		return getBuilder().setAlteration(batholithDefaults.getAlteration( vectorSeed))
				.setSurface(batholithDefaults.getSurface(vectorSeed))
				.setReplacement(batholithDefaults.getReplacement(vectorSeed))
				.setControlMap(batholithDefaults.getControlMap(vectorSeed))
				.setRegion(region)
				.build();
	}

	@Override
	public IGeologyNode makeObject(AbstractPrototype prototype) {
		long vectorSeed = createVectorSeed(prototype.hashCode());
		return new NodeBuilder()
				.setFactory(IntrusiveFactoryHub.GetPlutonFactory(getSeed()))
				.setPrototype(prototype)
				.setSubObjectNumber(batholithDefaults.getSubObjectNumber(vectorSeed)).build();
	}

	@Override
	public Region makeRegion(Vector2i vec) {
		return batholithDefaults.getRegion(vec);
	}

	static class BatholithBuilder implements IgneousPackPrototypeBuilder {

		private Order order;
		private Region region;
		private double Internal_Decay;
		private double External_Decay;
		private NoiseMap controlMap;
		private AbstractPrototypeFactory factory;
		private IAlterationDefaults alteration;
		private IReplacementDefaults replacement;
		private ISurfaceDefaults surface;
		
		private BatholithBuilder setAlteration(IAlterationDefaults conf) { alteration = conf; return this; }
		private BatholithBuilder setReplacement(IReplacementDefaults conf) { replacement = conf; return this; }
		private BatholithBuilder setSurface(ISurfaceDefaults conf) { surface = conf; return this; }
		private BatholithBuilder setControlMap(NoiseMap m) {this.controlMap =m; return this;}
		private BatholithBuilder setExternalDecay(double d) { this.External_Decay=d; return this; }
		private BatholithBuilder setInternalDecay(double d) { this.Internal_Decay=d; return this; }
		private BatholithBuilder setRegion(Region region) { this.region = region; return this; }
		private BatholithBuilder setOrder(Order order)    {this.order = order; return this; }
		private BatholithBuilder setFactory(AbstractPrototypeFactory factory) { this.factory = factory; return this; }

		private IGeologyNode build() { return new Batholith(this); }
		public IAlterationDefaults getAlteration() { return alteration; } 
		public IReplacementDefaults getReplacement() { return replacement; } 
		public ISurfaceDefaults getSurface() { return surface; } 
		public Order getOrder() { return order;  }
		public AbstractPrototypeFactory getFactory() { return factory;}
		public Region getRegion() {	return region; }
		public double getExternalDecayConstant() { return External_Decay; }
		public double getInternalDecayConstant() { return Internal_Decay; }
		public NoiseMap getControlMap() { return controlMap; }

	}

}
