package net.kevinmendoza.igneouspack.geology.intrusivefactories;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworld.geology.geologicobject.GeologicObjectInterface;
import net.kevinmendoza.geoworld.proceduralgeneration.shapes.RegionFactory;
import net.kevinmendoza.igneouspack.configuration.IgneousConfigs;
import net.kevinmendoza.igneouspack.geology.intrusivefactories.BuilderClassContainer.BatholithBuilder;
import net.kevinmendoza.igneouspack.geology.intrusivefactories.BuilderClassContainer.PrototypeBuilder;

public class IntrusiveRegionFactory  {
	
	private static long SEED;
	private static IgneousConfigs bath;

	public static void setSeed(long seed) { SEED=seed; }

	public static GeologicObjectInterface MakeBatholith(Vector2i fullCenter) {
		return new BatholithBuilder().setRegion(bath.getRegion(SEED, fullCenter))
									.setOrder(bath.getOrder())
									.setSubRegions(bath.getSubRegions(SEED,fullCenter)).build();
	}
	
	public static GeologicObjectInterface MakeRegionPrototype(Vector2i fullCenter) {
		return new PrototypeBuilder().setRegion(RegionFactory.MakeRandomRegion(SEED, fullCenter, 200)).build();
	}


}
