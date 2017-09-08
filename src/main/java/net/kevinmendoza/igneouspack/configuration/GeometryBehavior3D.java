package net.kevinmendoza.igneouspack.configuration;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.configuration.PrimitiveWrapperConfig.IntMinMax;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
@ConfigSerializable
class GeometryBehavior3D extends GeometryBehavior {

	@Setting
	private IntMinMax emplacementDepth;
	
	public GeometryBehavior3D(Builder builder) {
		super(builder);
		emplacementDepth=builder.getEmplacementDepth();
	}

	@Override
	public Region getRegion(Vector2i vec) { 
		Vector3i vec3 = new Vector3i(vec.getX(), emplacementDepth.getValue(vec),vec.getY());
		return getRegion(vec3); 
	}
}
