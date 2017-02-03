package net.kevinmendoza.igneouspack.geology.intrusiveobjects;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.geochemistry.ChemicalConditions;
import net.kevinmendoza.geoworldlibrary.geology.geologicobject.GeologicObjectInterface;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;
import net.kevinmendoza.igneouspack.geology.intrusivefactories.BuilderClassContainer.PrototypeBuilder;

public class IntrusiveRegionPrototype implements GeologicObjectInterface {

	private final Region region;

	public IntrusiveRegionPrototype(PrototypeBuilder prototypeBuilder) {
		region = prototypeBuilder.getRegion();
	}

	@Override
	public boolean isInObject(Vector2i center) {
		return region.isInside(center);
	}

	@Override
	public ChemicalConditions getConditions(Vector3i query) {
		return null;
	}

	@Override
	public Region getRegion() {
		return region;
	}

}
