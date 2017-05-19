package net.kevinmendoza.igneouspack.configuration;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifier;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMap;
import net.kevinmendoza.igneouspack.configuration.geoconf.IAlterationDefaults;
import net.kevinmendoza.igneouspack.configuration.geoconf.IGeologyDefaults;
import net.kevinmendoza.igneouspack.configuration.geoconf.IReplacementDefaults;
import net.kevinmendoza.igneouspack.configuration.geoconf.ISurfaceDefaults;

public interface IBatholithDefaults extends IGeologyDefaults {

	int getSubObjectNumber(long seed);
	
	Region getRegion(Vector2i vec);

	double getExternalDecay();
	
	double getInternalDecay();

	NoiseMap getControlMap(long vectorSeed);
	

}
