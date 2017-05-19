package net.kevinmendoza.igneouspack.geology.intrusive;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GenerationData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.IGeologyData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifierFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMap;
import net.kevinmendoza.igneouspack.geology.IgneousPackPrototype;
import net.kevinmendoza.igneouspack.geology.data.DataFactory;
import net.kevinmendoza.igneouspack.geology.intrusive.PlutonFactory.PlutonBuilder;

class Pluton extends IgneousPackPrototype {

	private int h;
	private double ext_decay;
	private double int_decay;
	private int emplacementDepth;
	private NoiseMap map;

	Pluton(PlutonBuilder plutonBuilder) {
		super(plutonBuilder);
		ext_decay = plutonBuilder.getExternalDecayConstant();
		int_decay = plutonBuilder.getInternalDecayConstant();
		emplacementDepth = plutonBuilder.getEmplacementDepth();
		map = plutonBuilder.getAlterationMap();
	}

	@Override
	protected double getPrototypeInternalDecay(Vector2i vec) {
		Vector3i v = new Vector3i(vec.getX(),emplacementDepth,vec.getY());
		return getExternalDecay(v);
	}
	@Override
	protected double getPrototypeExternalDecay(Vector2i vec) {
		Vector3i v = new Vector3i(vec.getX(),emplacementDepth,vec.getY());
		return getExternalDecay(v);
	}
	@Override
	protected double getPrototypeExternalDecay(Vector3i vec) {
		double val = 1-getDistanceToEdge(vec)*ext_decay;
		if(val > 1)
			return 1*map.getNoise(vec);
		else if(val < ZERO_THRESHOLD)
			return 0;
		else
			return val*map.getNoise(vec);
	}
	@Override
	protected double getPrototypeInternalDecay(Vector3i vec) {
		double val = getDistanceToEdge(vec)*int_decay;
		if(val > 1)
			return 1*map.getNoise(vec);
		else if(val < ZERO_THRESHOLD)
			return 0;
		else
			return val*map.getNoise(vec);
	}
	
	@Override
	public void primeGeneration(GenerationData metaData) {
		h = metaData.getBaseHeight();
	}

	@Override
	protected IGeologyData getGeologyData(IGeologyData t,
			Vector2i query) {
		if(t.getID()==DataFactory.GetSurfaceID()) {
			return DataFactory.CreateEmptySurfaceInstance(Order.FIRST);
		}
		return null;
	}

	@Override
	protected IGeologyData getGeologyData(IGeologyData t,
			Vector3i query) {
		int depth = query.getY();
		if(t.getID()==DataFactory.GetAlterationID()) {
			return DataFactory.CreateAlterationInstance(getAlteration().getHeatGradient(),
					getPressure(depth,h), Order.FIRST);
		}
		return DataFactory.CreateEmptyAlterationInstance(Order.FIRST);
	}

	public String getName() { return "Map Object"; }
}
