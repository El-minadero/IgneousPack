package net.kevinmendoza.igneouspack.configuration;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifier;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMap;
import net.kevinmendoza.igneouspack.configuration.geoconf.IGeologyDefaults;

public interface ISwarmDefaults extends IGeologyDefaults {

	double getExternalDecay();

	double getInternalDecay();

	int getSubObjectNumber(long seed);

	Region getRegion(Vector2i vec);

	NoiseMap getControlMap(long vectorSeed);

}
