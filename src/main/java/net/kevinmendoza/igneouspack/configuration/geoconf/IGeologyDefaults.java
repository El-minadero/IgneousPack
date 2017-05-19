package net.kevinmendoza.igneouspack.configuration.geoconf;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifier;

public interface IGeologyDefaults {

	public IAlterationDefaults getAlteration(long seed);
	public ISurfaceDefaults getSurface(long seed);
	public IReplacementDefaults getReplacement(long seed);
	
}
