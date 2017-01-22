package net.kevinmendoza.igneouspack.geology.intrusiveobjects;

import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworld.geology.geochemistry.ChemicalConditions;
import net.kevinmendoza.geoworld.geology.geologicobject.GeologicObjectInterface;
import net.kevinmendoza.geoworld.geology.region.GeologicRegion;
import net.kevinmendoza.geoworld.geology.region.GeologicRegionPrecursor;
import net.kevinmendoza.igneouspack.geology.intrusivefactories.IntrusiveRegionFactory.BatholithBuilder;

public class Batholith extends GeologicRegion implements GeologicObjectInterface {

	public Batholith(BatholithBuilder builder) {
		super(builder);
	}

	
	@Override
	public ChemicalConditions getConditions(Vector3i query) {
		return null;
	}

	@Override
	protected GeologicObjectInterface buildSubGeologicRegion(
			GeologicRegionPrecursor region) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
