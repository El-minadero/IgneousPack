package net.kevinmendoza.igneouspack.geology.intrusivefactories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.kevinmendoza.geoworldlibrary.geology.LithogenicOrder;
import net.kevinmendoza.geoworldlibrary.geology.geologicobject.GeologicObjectBuilderGetInterface;
import net.kevinmendoza.geoworldlibrary.geology.geologicobject.GeologicObjectInterface;
import net.kevinmendoza.geoworldlibrary.geology.region.GeologicRegionBuilderGetInterface;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;
import net.kevinmendoza.igneouspack.geology.intrusiveobjects.Batholith;
import net.kevinmendoza.igneouspack.geology.intrusiveobjects.IntrusiveRegionPrototype;
import net.kevinmendoza.igneouspack.geology.intrusiveobjects.Pluton;

public class BuilderClassContainer {

	public static class PlutonBuilder implements GeologicObjectBuilderGetInterface {

		private Region region;
		private LithogenicOrder order;
		
		public PlutonBuilder setRegion(Region region) { this.region = region; return this; }
		public PlutonBuilder setOrder(LithogenicOrder order) {this.order=order;return this;}
		
		public Region getRegion() { return region; }
		public LithogenicOrder getOrder() { return order;}
		
		public Pluton build() {
			return new Pluton(this);
		}
	}
	
	public static class PrototypeBuilder implements GeologicObjectBuilderGetInterface {

		private Region region;
		private LithogenicOrder order;
		
		public PrototypeBuilder setRegion(Region region) { this.region = region; return this; }
		public PrototypeBuilder setOrder(LithogenicOrder order) {this.order=order;return this;}
		
		public Region getRegion() { return region; }
		public LithogenicOrder getOrder() { return order;}
		
		public GeologicObjectInterface build() {
			return new IntrusiveRegionPrototype(this);
		}
	}
	
	public static class BatholithBuilder implements GeologicRegionBuilderGetInterface {

		private Region region;
		private LithogenicOrder order;
		private HashMap<String, Integer> subRegions;
		
		public BatholithBuilder setRegion(Region region) { this.region = region; return this; }
		public BatholithBuilder setSubRegions(
			   HashMap<String, Integer> subRegions) { this.subRegions=subRegions; return this;}
		public BatholithBuilder setOrder(LithogenicOrder order) {this.order=order;return this;}
		
		public Region getRegion() { return region; }
		public LithogenicOrder getOrder() { return order;}
		public HashMap<String, Integer> getSubRegions() { return subRegions; }
		
		public Batholith build() {
			return new Batholith(this);
		}
	}
}
