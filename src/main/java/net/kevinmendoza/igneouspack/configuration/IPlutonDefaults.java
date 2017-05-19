package net.kevinmendoza.igneouspack.configuration;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifier;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMap;
import net.kevinmendoza.igneouspack.configuration.geoconf.IGeologyDefaults;

public interface IPlutonDefaults extends IGeologyDefaults {

	int getSubObjectNumber(Vector2i vec);

	Region getRegion(Vector3i vec2);

	double getExternalDecay();

	double getInternalDecay();
	
	double getEmplacementDepth(long seed);

	NoiseMap getControlMap(long vectorSeed);

	NoiseMap getAlterationMap(long vectorSeed);

}
