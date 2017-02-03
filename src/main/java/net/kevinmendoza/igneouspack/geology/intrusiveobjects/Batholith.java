package net.kevinmendoza.igneouspack.geology.intrusiveobjects;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.geochemistry.ChemicalConditions;
import net.kevinmendoza.geoworldlibrary.geology.geologicobject.GeologicObjectInterface;
import net.kevinmendoza.geoworldlibrary.geology.region.GeologicRegion;
import net.kevinmendoza.igneouspack.geology.intrusivefactories.IntrusiveRegionFactory;
import net.kevinmendoza.igneouspack.geology.intrusivefactories.SubRegionFactory;
import net.kevinmendoza.igneouspack.geology.intrusivefactories.BuilderClassContainer.BatholithBuilder;;

public class Batholith extends GeologicRegion implements GeologicObjectInterface {

	public Batholith(BatholithBuilder builder) {
		super(builder);
	}

	@Override
	public ChemicalConditions getConditions(Vector3i query) {
		return null;
	}


	@Override
	protected GeologicObjectInterface makeSubRegion(String region,
			Vector2i center) {
		if(region.equalsIgnoreCase("Pluton-Swarm"))
			return SubRegionFactory.MakePlutonSwarm(center);
		return null;
	}


	
}
