package net.kevinmendoza.igneouspack.geology.intrusivefactories;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworld.config.AbstractDefaults;
import net.kevinmendoza.geoworld.geology.LithogenicOrder;
import net.kevinmendoza.geoworld.geology.geologicobject.GeologicObjectInterface;
import net.kevinmendoza.geoworld.geology.region.GeologicRegionBuilderGetInterface;
import net.kevinmendoza.geoworld.geology.region.GeologicRegionPrecursor;
import net.kevinmendoza.geoworld.proceduralgeneration.probability.DistributionFactory;
import net.kevinmendoza.geoworld.proceduralgeneration.shapes.Region;
import net.kevinmendoza.geoworld.proceduralgeneration.shapes.RegionFactory;
import net.kevinmendoza.igneouspack.geology.intrusiveobjects.Batholith;
import net.kevinmendoza.igneouspack.geology.intrusiveobjects.IntrusiveRegionPrototype;
import net.kevinmendoza.igneouspack.main.IgneousPackMain;
import ninja.leaping.configurate.ConfigurationNode;

public class IntrusiveRegionFactory extends AbstractDefaults {
	
	private long SEED;

	public IntrusiveRegionFactory() {
		super("Igneous Region",IgneousPackMain.GetStaticInstance());
		SEED = 1;
	}

	public void setSeed(long seed) { this.SEED=seed; }

	public GeologicObjectInterface makeBatholith(Vector2i fullCenter) {
		Region region = RegionFactory.MakeRegionWithConfig(getNode(""), fullCenter);
		LithogenicOrder order = LithogenicOrder.valueOf(getNode("Order").getString("SECOND"));
		List<GeologicRegionPrecursor> precursors = getBatholithPrecursors(region,fullCenter);
		
		return new BatholithBuilder().setRegion(region).setOrder(order).setPrecursors(precursors).build();
	}
	
	public GeologicObjectInterface makeRegionPrototype(Vector2i fullCenter) {
		return new PrototypeBuilder().setRegion(RegionFactory.MakeRandomRegion(SEED, fullCenter, 200)).build();
	}
	
	private List<GeologicRegionPrecursor> getBatholithPrecursors(Region region, Vector2i vec){
		long tempSeed = SEED^vec.hashCode();
		Random rand = new Random(tempSeed);
		double dist = DistributionFactory.BuildDistribution(getNode("SubProvinces"), tempSeed).getRVar();
		List<String> subs = new ArrayList<>();
		List<Double> subFreqs = new ArrayList<>();
		List<GeologicRegionPrecursor> geoRegionPrecursor = new ArrayList<>();
		ConfigurationNode parent = getNode("").getParent();
		for (ConfigurationNode node : getNode("Sub Provinces").getChildrenList()) {
			subs.add(node.toString());
			subFreqs.add(node.getDouble(0.1));
		}
		while(geoRegionPrecursor.size() < dist) {
			for(int i =0; i< subs.size();i++) {
				if(rand.nextInt((int)(10/(10*subFreqs.get(i).doubleValue())))==0){
					geoRegionPrecursor.add(new GeologicRegionPrecursor(subs.get(i),
						RegionFactory.MakeRegionWithConfig(parent.getNode(subs.get(i)), region.getRandPoint())));
				}
			}
		}
		return geoRegionPrecursor;
	}

	public class BatholithBuilder implements GeologicRegionBuilderGetInterface {

		private Region region;
		private LithogenicOrder order;
		private List<GeologicRegionPrecursor> precursors;
		
		public BatholithBuilder setPrecursors(List<GeologicRegionPrecursor> prec) 
		{ this.precursors = prec; return this; }
		public BatholithBuilder setRegion(Region region) { this.region = region; return this; }
		public BatholithBuilder setOrder(LithogenicOrder order) {this.order=order;return this;}
		public BatholithBuilder setSubFactory() { return this; }
		
		public Region getRegion() { return region; }
		public LithogenicOrder getOrder() { return order;}
		public List<GeologicRegionPrecursor> getSubRegionList() { return precursors; }
		
		public Batholith build() {
			return new Batholith(this);
		}
	}
	
	public class PrototypeBuilder implements GeologicRegionBuilderGetInterface {

		private Region region;
		private LithogenicOrder order;
		
		public PrototypeBuilder setRegion(Region region) { this.region = region; return this; }
		public PrototypeBuilder setOrder(LithogenicOrder order) {this.order=order;return this;}
		
		public GeologicObjectInterface build() {
			return new IntrusiveRegionPrototype(this);
		}

		public Region getRegion() { return region; }
		public LithogenicOrder getOrder() { return order;}
		public List<GeologicRegionPrecursor> getSubRegionList() {
			return new ArrayList<>();
		}
		
	}
}
