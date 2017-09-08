package net.kevinmendoza.igneouspack.geology;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.EmptyDataFactory;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.GenerationData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.IGeologyData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.Order;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractAlteration;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractRock;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.ISingularGeologyData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.Surface;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.AbstractPrototype;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IPrototypeBuilder;

public abstract class IgneousPackPrototype extends AbstractPrototype {


	public IgneousPackPrototype(IgneousPackPrototypeBuilder builder) {
		super(builder);
	}
	
	@Override
	public final ISingularGeologyData getStartingData(ISingularGeologyData dataType) {
		return EmptyDataFactory.getEmptyDataObject(dataType.getID());
	}

}
