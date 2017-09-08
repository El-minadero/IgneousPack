package net.kevinmendoza.igneouspack.geology.intrusive;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.EmptyDataFactory;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.GenerationData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractRock;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.ISingularGeologyData;
import net.kevinmendoza.igneouspack.geology.IgneousPackPrototype;

class Pluton extends IgneousPackPrototype {

	private final AbstractRock rock;

	Pluton(PlutonFactory.Builder builder) {
		super(builder);
		rock			= builder.getRock();
	}

	
	@Override
	public void loadNearbyNodes(GenerationData metaData) {
	}

	@Override
	public void primeLoadedObjects(GenerationData metaData) {
		
	}

	@Override
	protected ISingularGeologyData getGeologyData(ISingularGeologyData t,
			Vector2i query) {
		return EmptyDataFactory.getEmptyDataObject(t.getID());
	}

	@Override
	protected ISingularGeologyData getGeologyData(ISingularGeologyData t,
			Vector3i query) {
		ISingularGeologyData data = EmptyDataFactory.getEmptyDataObject(t.getID());
		if(t.getID()==2) {
			
		}
		else if(t.getID()==3) {
			data.merge(rock);
			return  data;
		}
		return data;
	}
	public String getName() { return "Map Object"; }
}
