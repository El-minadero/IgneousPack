package net.kevinmendoza.igneouspack.geology.intrusive;


import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.DefaultDataFactory;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.EmptyDataFactory;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.GenerationData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.ISingularGeologyData;
import net.kevinmendoza.igneouspack.geology.IgneousPackPrototype;

final class Batholith extends IgneousPackPrototype {

	
	Batholith(BatholithFactory.Builder  batholithBuilder) {
		super(batholithBuilder);
	}
	
	public final void loadNearbyNodes(GenerationData metaData) { }
	public final void primeLoadedObjects(GenerationData metaData) { }

	protected final ISingularGeologyData getGeologyData(ISingularGeologyData t,
			Vector2i query) {
		return EmptyDataFactory.getEmptyDataObject(t.getID());
	}
	protected final ISingularGeologyData getGeologyData(ISingularGeologyData t,
			Vector3i query) {
		return DefaultDataFactory.getEmptyDataObject(t.getID());
	}
	
	public String getName() { return "Batholith"; }
	

}
