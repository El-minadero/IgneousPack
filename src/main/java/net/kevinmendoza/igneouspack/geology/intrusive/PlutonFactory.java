package net.kevinmendoza.igneouspack.geology.intrusive;

import java.util.HashMap;
import java.util.List;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;
import com.google.inject.Inject;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.AbstractPrototypeFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeologyNode;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.NodeBuilder;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.Order;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractRock;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.AbstractPrototype;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifierFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifier;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMap;
import net.kevinmendoza.igneouspack.configuration.IGeologyBehavior;
import net.kevinmendoza.igneouspack.configuration.IGeometryBehavior;
import net.kevinmendoza.igneouspack.configuration.IStructureDefaults;
import net.kevinmendoza.igneouspack.geology.IgneousPackPrototypeBuilder;
import net.kevinmendoza.igneouspack.geology.classificationmodel.RockFactoryAccess;
import net.kevinmendoza.igneouspack.geology.classificationmodel.TextureTypes;


class PlutonFactory extends AbstractIntrusiveStructureFactory {

	private Builder builder;
	
	@Override
	protected HashMap<Integer, AbstractPrototypeFactory> produceSubObjectFactoryMap(long vectorSeed) {
		HashMap<Integer,AbstractPrototypeFactory> factoryMap = new HashMap<>();
		List<StructureFactoryTypes> types = getDefaults().getGeologyBehavior().getSubObjectTypes();
		int number; int iteration=0;
		for(StructureFactoryTypes type : types) {
			number = getDefaults().getGeologyBehavior().getStructureTypeAmount(type,vectorSeed+iteration);
			factoryMap.put(number, IntrusiveFactoryHub.getFactory(type,vectorSeed));
			iteration++;
		}
		return factoryMap;
	}
	
	private Builder getBuilder() {
		if(builder==null) {
			builder = new Builder();
			builder.setDefaults(getDefaults())
			.setSeed(getSeed())
			.setFactory(this);
		}
		return builder;
	}
	
	@Override
	protected IStructureDefaults getStructureDefaults() {
		return getGlobalDefaults().getPlutonDefaults();
	}

	@Override
	public IGeologyNode makePrototype(Vector2i vec) {
		Region region = makeRegion(vec);
		long vectorSeed = createVectorSeed(vec);
		TextureTypes type = getDefaults().getGeologyBehavior().getTextureType();
		double silicaContent = getDefaults().getGeologyBehavior().getSilicaContent(vectorSeed);
		double valueCutoff = getDefaults().getGeologyBehavior().getRockCutoff();
		return getBuilder()
				.setRock(RockFactoryAccess.getRockFactory().getRock(type,silicaContent,valueCutoff))
				.setGeology(getDefaults().getGeologyBehavior(),vectorSeed)
				.setRegionBehavior(getDefaults().getGeometryBehavior(),vectorSeed)
				.setRegion(region)
				.build();
	}


	static class Builder implements IgneousPackPrototypeBuilder {

		private Order order;
		private Region region;
		private double Internal_Decay;
		private double External_Decay;
		private NoiseMap controlMap;
		private AbstractIntrusiveStructureFactory nodeInitializationFactory;
		private long seed;
		private AbstractRock rock;
		
		private Builder setSeed(long seed) { this.seed = seed; return this;}
		public Builder setRock(AbstractRock rock2) { rock=rock2; return this;}
		public Builder setRegionBehavior(
				IGeometryBehavior regionBehavior, long vectorSeed) {
			controlMap = regionBehavior.getNoiseMap(vectorSeed);
			return this;
		}
		public Builder setGeology(IGeologyBehavior geologyBehavior,
				long vectorSeed) {
			
			return this;
		}
		private Builder setDefaults(IStructureDefaults defaults) {
			order = defaults.getGeologyBehavior().getOrder();
			Internal_Decay = defaults.getGeometryBehavior().getInternalDecay();
			External_Decay = defaults.getGeometryBehavior().getExternalDecay();
			return this;
		}
		private Builder setFactory(AbstractIntrusiveStructureFactory factory) {
			this.nodeInitializationFactory = factory; return this;
		}
		private Builder setRegion(Region region) { this.region = region; return this; }

		private IGeologyNode build() { return new Pluton(this); }
		public Order getOrder() { return order;  }
		public AbstractPrototypeFactory getFactory() { return nodeInitializationFactory; }
		public Region getRegion() {	return region; }
		public double getExternalDecayConstant() { return External_Decay; }
		public double getInternalDecayConstant() { return Internal_Decay; }
		public NoiseMap getControlMap() { return controlMap; }
		public long getSeed() { return seed; }
		public AbstractRock getRock() { return rock; }

	}

}
