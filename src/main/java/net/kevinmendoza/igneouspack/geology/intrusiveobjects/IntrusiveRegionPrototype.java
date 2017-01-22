package net.kevinmendoza.igneouspack.geology.intrusiveobjects;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworld.config.Debug;
import net.kevinmendoza.geoworld.geology.geochemistry.ChemicalConditions;
import net.kevinmendoza.geoworld.geology.geologicobject.GeologicObjectInterface;
import net.kevinmendoza.geoworld.proceduralgeneration.shapes.Region;
import net.kevinmendoza.geoworld.proceduralgeneration.shapes.RegionFactory;
import net.kevinmendoza.igneouspack.geology.intrusivefactories.IntrusiveRegionFactory.PrototypeBuilder;

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

}
