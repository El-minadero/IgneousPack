package net.kevinmendoza.igneouspack.geology.intrusivefactories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworld.geology.LithogenicOrder;
import net.kevinmendoza.geoworld.geology.geologicobject.GeologicObjectBuilderGetInterface;
import net.kevinmendoza.geoworld.geology.geologicobject.GeologicObjectInterface;
import net.kevinmendoza.geoworld.geology.region.GeologicRegionBuilderGetInterface;
import net.kevinmendoza.geoworld.proceduralgeneration.shapes.Region;
import net.kevinmendoza.geoworld.proceduralgeneration.shapes.RegionFactory;
import net.kevinmendoza.igneouspack.geology.intrusiveobjects.Batholith;
import net.kevinmendoza.igneouspack.geology.intrusiveobjects.IntrusiveRegionPrototype;
import net.kevinmendoza.igneouspack.geology.intrusiveobjects.Pluton;

public class GeologicObjectFactory {
	
	private long SEED;
/*
	public GeologicObjectFactory() {
		SEED = 1;
	}

	public void setSeed(long seed) { this.SEED=seed; }

	public GeologicObjectInterface makeBatholith(Vector2i fullCenter) {
		return new BatholithBuilder().setRegion(bath.getRegion(SEED, fullCenter))
									.setOrder(bath.getOrder())
									.setSubRegions(bath.getSubRegions(SEED,fullCenter)).build();
	}
	
	public GeologicObjectInterface makeRegionPrototype(Vector2i fullCenter) {
		return new PrototypeBuilder().setRegion(RegionFactory.MakeRandomRegion(SEED, fullCenter, 200)).build();
	}

	public class PlutonBuilder implements GeologicObjectBuilderGetInterface {

		private Region region;
		private LithogenicOrder order;
		
		public PlutonBuilder setRegion(Region region) { this.region = region; return this; }
		public PlutonBuilder setOrder(LithogenicOrder order) {this.order=order;return this;}
		
		public Region getRegion() { return region; }
		public LithogenicOrder getOrder() { return order;}
		
		public Pluton build() {
			return new Pluton(this);
		}
	}*/
	
}