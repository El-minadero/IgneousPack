package net.kevinmendoza.igneouspack.configuration;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifier;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMap;

public interface IStructureDefaults  {

	IGeologyBehavior getGeologyBehavior();
	
	IGeometryBehavior getGeometryBehavior();

}
