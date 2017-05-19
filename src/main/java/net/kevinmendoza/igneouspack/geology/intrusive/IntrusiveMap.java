package net.kevinmendoza.igneouspack.geology.intrusive;

import java.util.Random;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GenerationData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.IGeologyData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;
import net.kevinmendoza.igneouspack.geology.IgneousPackPrototype;
import net.kevinmendoza.igneouspack.geology.data.DataFactory;
import net.kevinmendoza.igneouspack.geology.intrusive.IntrusiveMapFactory.MapPrototypeBuilder;

final class IntrusiveMap extends IgneousPackPrototype {
	
	private int h;
	public IntrusiveMap(MapPrototypeBuilder mapPrototypeBuilder) {
		super(mapPrototypeBuilder,Order.FIRST);
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
			return DataFactory.CreateAlterationInstance(getHeat(depth,h),getPressure(depth,h), Order.FIRST);
		}
		return DataFactory.CreateEmptyAlterationInstance(Order.FIRST);
	}
	
	public String getName() { return "IgneousMap"; }

}
