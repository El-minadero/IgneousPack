package net.kevinmendoza.igneouspack.geology.intrusive;

import com.flowpowered.math.vector.Vector2i;
import com.google.inject.Inject;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.AbstractPrototypeFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeologyNode;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.NodeBuilder;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.AbstractPrototype;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifierFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifier;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMap;
import net.kevinmendoza.igneouspack.configuration.ISwarmDefaults;
import net.kevinmendoza.igneouspack.configuration.IGlobalDefaults;
import net.kevinmendoza.igneouspack.configuration.geoconf.IAlterationDefaults;
import net.kevinmendoza.igneouspack.configuration.geoconf.IReplacementDefaults;
import net.kevinmendoza.igneouspack.configuration.geoconf.ISurfaceDefaults;
import net.kevinmendoza.igneouspack.geology.IgneousPackPrototypeBuilder;
import net.kevinmendoza.igneouspack.geology.intrusive.BatholithFactory.BatholithBuilder;
import net.kevinmendoza.igneouspack.geology.intrusive.SwarmFactory.SwarmBuilder;

class SwarmFactory extends AbstractPrototypeFactory {

	@Inject
	IGlobalDefaults globalDefaults;
	@Inject
	ISwarmDefaults swarmDefaults;
	SwarmBuilder builder;
	
	private SwarmBuilder getBuilder() {
		if(builder==null) {
			builder = new SwarmBuilder();
			builder.setExternalDecay(swarmDefaults.getExternalDecay())
			.setInternalDecay(swarmDefaults.getInternalDecay())
			.setFactory(IntrusiveFactoryHub.GetPlutonFactory(getSeed()));
		}
		return builder;
	}
	
	@Override
	public IGeologyNode makePrototype(Vector2i vec) {
		Region region = makeRegion(vec);
		long vectorSeed = createVectorSeed(vec);
		return getBuilder().setAlteration(swarmDefaults.getAlteration( vectorSeed))
				.setSurface(swarmDefaults.getSurface(vectorSeed))
				.setReplacement(swarmDefaults.getReplacement(vectorSeed))
				.setControlMap(swarmDefaults.getControlMap(vectorSeed))
				.setOrder(Order.FIRST)
				.setRegion(region)
				.build();
	}

	@Override
	public IGeologyNode makeObject(AbstractPrototype prototype) {
		long vectorSeed = createVectorSeed(prototype.hashCode());
		return new NodeBuilder()
				.setFactory(IntrusiveFactoryHub.GetPlutonFactory(getSeed()))
				.setPrototype(prototype)
				.setSubObjectNumber(swarmDefaults.getSubObjectNumber(vectorSeed)).build();
	}

	@Override
	public Region makeRegion(Vector2i vec) {
		return swarmDefaults.getRegion(vec);
	}

	static class SwarmBuilder implements IgneousPackPrototypeBuilder {

		private Order order;
		private Region region;
		private double Internal_Decay;
		private double External_Decay;
		private NoiseMap controlMap;
		private AbstractPrototypeFactory factory;
		private IAlterationDefaults alteration;
		private IReplacementDefaults replacement;
		private ISurfaceDefaults surface;
		private double surfaceHeat;

		private SwarmBuilder setAlteration(IAlterationDefaults conf) { alteration = conf; return this; }
		private SwarmBuilder setReplacement(IReplacementDefaults conf) { replacement = conf; return this; }
		private SwarmBuilder setSurface(ISurfaceDefaults conf) { surface = conf; return this; }
		private SwarmBuilder setControlMap(NoiseMap m) {this.controlMap =m; return this;}
		private SwarmBuilder setExternalDecay(double d) { this.External_Decay=d; return this; }
		private SwarmBuilder setInternalDecay(double d) { this.Internal_Decay=d; return this; }
		private SwarmBuilder setRegion(Region region) { this.region = region; return this; }
		private SwarmBuilder setOrder(Order order)    {this.order = order; return this; }
		private SwarmBuilder setFactory(AbstractPrototypeFactory factory) { this.factory = factory; return this; }

		private IGeologyNode build() { return new Swarm(this); }
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
