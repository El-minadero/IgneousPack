package net.kevinmendoza.igneouspack.geology.intrusiveobjects;

import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworld.geology.geochemistry.ChemicalConditions;
import net.kevinmendoza.geoworld.geology.geologicobject.GeologicObject;
import net.kevinmendoza.igneouspack.geology.intrusivefactories.BuilderClassContainer.PlutonBuilder;

public class Pluton extends GeologicObject {

	public Pluton(PlutonBuilder plutonBuilder) {
		super(plutonBuilder);
	}

	@Override
	public ChemicalConditions getConditions(Vector3i query) {
		// TODO Auto-generated method stub
		return null;
	}

}
