package net.kevinmendoza.igneouspack.geology.intrusiveobjects;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworld.geology.geochemistry.ChemicalConditions;
import net.kevinmendoza.geoworld.geology.geologicobject.GeologicObjectInterface;
import net.kevinmendoza.geoworld.geology.region.GeologicRegion;
import net.kevinmendoza.geoworld.geology.region.GeologicRegionBuilderGetInterface;
import net.kevinmendoza.igneouspack.geology.intrusivefactories.SubRegionFactory;

public class PlutonSwarm extends GeologicRegion implements GeologicObjectInterface {

	public PlutonSwarm(GeologicRegionBuilderGetInterface builder) {
		super(builder);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ChemicalConditions getConditions(Vector3i query) {
		return null;
	}


	@Override
	protected GeologicObjectInterface makeSubRegion(String region,
			Vector2i center) {
		if(region.equalsIgnoreCase("Pluton"))
			return SubRegionFactory.MakePlutonSwarm(center);
		return null;
	}


	
}