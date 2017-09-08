package net.kevinmendoza.igneouspack.configuration;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMap;

public interface IGeometryBehavior {
	
	Region getRegion (Vector3i vec);
	
	Region getRegion(Vector2i vec);
	
	double getInternalDecay();
	
	double getExternalDecay();
	
	NoiseMap getNoiseMap(Vector3i vec);
	
	NoiseMap getNoiseMap(Vector2i vec);

	NoiseMap getNoiseMap(long seed);
	
}
