package net.kevinmendoza.igneouspack.main;

import net.kevinmendoza.geoworld.geology.GeologicContainer;
import net.kevinmendoza.igneouspack.main.IgneousFactory.IgneousMapFactory;

public class IntrusiveFactory implements IgneousMapFactory {

	@Override
	public GeologicContainer createGeologicContainer() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	public GeologicContainer createGeologicContainer() {
		return new IgneousMapBuilder().setFactory(new IntrusiveRegionFactory()).setFrequency(FREQUENCY)
										.setSpacing(SPACING).setOrder(order).Build();
	}
	*/

}
