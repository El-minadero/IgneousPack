package net.kevinmendoza.igneouspack.geology.intrusive;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;
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
import net.kevinmendoza.igneouspack.configuration.IGlobalDefaults;
import net.kevinmendoza.igneouspack.configuration.IPlutonDefaults;
import net.kevinmendoza.igneouspack.configuration.ISwarmDefaults;
import net.kevinmendoza.igneouspack.configuration.geoconf.IAlterationDefaults;
import net.kevinmendoza.igneouspack.configuration.geoconf.IReplacementDefaults;
import net.kevinmendoza.igneouspack.configuration.geoconf.ISurfaceDefaults;
import net.kevinmendoza.igneouspack.geology.IgneousPackPrototypeBuilder;
import net.kevinmendoza.igneouspack.geology.intrusive.SwarmFactory.SwarmBuilder;

class PlutonFactory extends AbstractPrototypeFactory {

	@Inject
	IGlobalDefaults globalDefaults;
	@Inject
	IPlutonDefaults plutonDefaults;
	PlutonBuilder builder;
	
	private PlutonBuilder getBuilder() {
		if(builder==null) {
			builder = new PlutonBuilder();
			builder.setExternalDecay(plutonDefaults.getExternalDecay())
			.setInternalDecay(plutonDefaults.getInternalDecay())
			.setFactory(null);
		}
		return builder;
	}

	@Override
	public IGeologyNode makePrototype(Vector2i vec) {
		Region region = makeRegion(vec);
		long vectorSeed = createVectorSeed(vec);
		return getBuilder().setAlteration(plutonDefaults.getAlteration( vectorSeed))
				.setSurface(plutonDefaults.getSurface(vectorSeed))
				.setAlterationMap(plutonDefaults.getAlterationMap(vectorSeed))
				.setReplacement(plutonDefaults.getReplacement(vectorSeed))
				.setOrder(Order.FIRST)
				.setControlMap(plutonDefaults.getControlMap(vectorSeed))
				.setEmplacementDepth((int)plutonDefaults.getEmplacementDepth(vectorSeed))
				.setRegion(region)
				.build();
	}

	@Override
	public IGeologyNode makeObject(AbstractPrototype prototype) {
		return new NodeBuilder()
				.setFactory(null)
				.setPrototype(prototype)
				.setSubObjectNumber(0).build();
	}

	@Override
	public Region makeRegion(Vector2i vec) {
		long vectorSeed = createVectorSeed(vec);
		Vector3i vec2 = new Vector3i(vec.getX(),plutonDefaults.getEmplacementDepth(vectorSeed),vec.getY());
		return plutonDefaults.getRegion(vec2);
	}

	static class PlutonBuilder implements IgneousPackPrototypeBuilder {

		private Order order;
		private Region region;
		private double Internal_Decay;
		private double External_Decay;
		private AbstractPrototypeFactory factory;
		private IAlterationDefaults alteration;
		private IReplacementDefaults replacement;
		private ISurfaceDefaults surface;
		private int emplacementDepth;
		private NoiseMap controlMap;
		private NoiseMap alterationMap;
		
		private PlutonBuilder setAlterationMap(NoiseMap m) {this.alterationMap =m; return this;}
		private PlutonBuilder setControlMap(NoiseMap m) {this.controlMap =m; return this;}
		private PlutonBuilder setEmplacementDepth(int d) { emplacementDepth = d; return this; }
		private PlutonBuilder setAlteration(IAlterationDefaults conf) { alteration = conf; return this; }
		private PlutonBuilder setReplacement(IReplacementDefaults conf) { replacement = conf; return this; }
		private PlutonBuilder setSurface(ISurfaceDefaults conf) { surface = conf; return this; }
		private PlutonBuilder setExternalDecay(double d) { this.External_Decay=d; return this; }
		private PlutonBuilder setInternalDecay(double d) { this.Internal_Decay=d; return this; }
		private PlutonBuilder setRegion(Region region) { this.region = region; return this; }
		private PlutonBuilder setOrder(Order order)    {this.order = order; return this; }
		private PlutonBuilder setFactory(AbstractPrototypeFactory factory) { this.factory = factory; return this; }

		private IGeologyNode build() { return new Pluton(this); }
		public IAlterationDefaults getAlteration() { return alteration; } 
		public IReplacementDefaults getReplacement() { return replacement; } 
		public ISurfaceDefaults getSurface() { return surface; } 
		public Order getOrder() { return order;  }
		public AbstractPrototypeFactory getFactory() { return factory;}
		public Region getRegion() {	return region; }
		public int getEmplacementDepth() {return emplacementDepth; }
		public double getExternalDecayConstant() { return External_Decay; }
		public double getInternalDecayConstant() { return Internal_Decay; }
		public NoiseMap getControlMap() { return controlMap; }
		public NoiseMap getAlterationMap() { return alterationMap; } 
	}

}
