package net.kevinmendoza.igneouspack.main;

import net.kevinmendoza.geoworld.config.AbstractDefaults;
import net.kevinmendoza.geoworld.geology.GeologicContainer;
import net.kevinmendoza.geoworld.geology.LithogenicOrder;
import net.kevinmendoza.geoworld.geology.regionmap.GeologicRegionMapBuilderInterface;
import net.kevinmendoza.igneouspack.geology.intrusivefactories.IntrusiveRegionFactory;
import net.kevinmendoza.igneouspack.geology.intrusiveobjects.IntrusiveMap;
import net.kevinmendoza.igneouspack.main.IgneousFactory.IgneousMapFactory;

public class IntrusiveFactory extends AbstractDefaults implements IgneousMapFactory {

	private final int SPACING;
	private final double FREQUENCY;
	private final LithogenicOrder order;
	
	public IntrusiveFactory() {
		super("Igneous Provinces",IgneousPackMain.GetStaticInstance());
		SPACING = getNode("Spacing").getInt(500);
		FREQUENCY 		  = getNode("Frequency").getDouble(0.1);
		order			  = LithogenicOrder.valueOf(getNode("Geologic Order").getString("Second"));
	}
	
	public GeologicContainer createGeologicContainer() {
		return new IgneousMapBuilder().setFactory(new IntrusiveRegionFactory()).setFrequency(FREQUENCY)
										.setSpacing(SPACING).setOrder(order).Build();
	}
	
	
	public class IgneousMapBuilder implements GeologicRegionMapBuilderInterface {

		private int SPACING;
		private double FREQUENCY;
		private LithogenicOrder order;
		private IntrusiveRegionFactory factory;
		
		public IgneousMapBuilder() {SPACING=500;FREQUENCY=0.1;order=LithogenicOrder.SECOND;}
		public double getSpacing() 			 	  { return SPACING; }
		public double getFrequency() 			  { return FREQUENCY;}
		public LithogenicOrder getOrder() 		  { return order; }
		public IntrusiveRegionFactory getFactory(){ return factory; }
		
		public IgneousMapBuilder setSpacing(int spacing) 			{SPACING=spacing;return this;}
		public IgneousMapBuilder setFrequency(double frequency) 	{FREQUENCY=frequency;return this;}
		public IgneousMapBuilder setOrder(LithogenicOrder order) 	{this.order=order;return this;}
		public IgneousMapBuilder setFactory(IntrusiveRegionFactory factory){this.factory=factory;return this;}

		public GeologicContainer Build() {
			return new IntrusiveMap(this);
		}
	}

}
