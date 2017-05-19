package net.kevinmendoza.igneouspack.geology;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IPrototypeBuilder;
import net.kevinmendoza.igneouspack.configuration.geoconf.IAlterationDefaults;
import net.kevinmendoza.igneouspack.configuration.geoconf.IReplacementDefaults;
import net.kevinmendoza.igneouspack.configuration.geoconf.ISurfaceDefaults;

public interface IgneousPackPrototypeBuilder extends IPrototypeBuilder {

	IAlterationDefaults getAlteration();
	IReplacementDefaults getReplacement();
	ISurfaceDefaults getSurface();
	
}
