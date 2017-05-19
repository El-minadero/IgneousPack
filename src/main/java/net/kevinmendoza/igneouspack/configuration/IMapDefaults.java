package net.kevinmendoza.igneouspack.configuration;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;
import net.kevinmendoza.igneouspack.configuration.geoconf.IAlterationDefaults;
import net.kevinmendoza.igneouspack.configuration.geoconf.IGeologyDefaults;
import net.kevinmendoza.igneouspack.configuration.geoconf.IReplacementDefaults;
import net.kevinmendoza.igneouspack.configuration.geoconf.ISurfaceDefaults;

public interface IMapDefaults extends IGeologyDefaults {
	public Order getOrder();
	public int getSpacing();
	public double getFrequency();

}
