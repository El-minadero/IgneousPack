package net.kevinmendoza.igneouspack.configuration;

import com.google.common.reflect.TypeToken;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifier;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifierFactory;
import net.kevinmendoza.igneouspack.configuration.GeologyConfiguration.AlterationParameters;
import net.kevinmendoza.igneouspack.configuration.geoconf.AlterationDefaults;
import net.kevinmendoza.igneouspack.configuration.geoconf.IAlterationDefaults;
import net.kevinmendoza.igneouspack.configuration.geoconf.IReplacementDefaults;
import net.kevinmendoza.igneouspack.configuration.geoconf.ISurfaceDefaults;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
@ConfigSerializable
class IntrusiveMapDefaults implements IMapDefaults {
	static final TypeToken<IntrusiveMapDefaults> type = TypeToken.of(IntrusiveMapDefaults.class);
	@Setting
	private AlterationDefaults alteration;
	@Setting
	Order order;
	@Setting
	int spacing;
	@Setting
	double subRegionFrequency;
	
	IntrusiveMapDefaults(){
		order = Order.FIRST;
		spacing = 3000;
		subRegionFrequency = .7;
		double[] heat = {4,6};
		double[] pressure = {10,25};
		double temp = 20;
		alteration = new AlterationDefaults(temp,heat,pressure);
	}
	
	public Order getOrder() { return order; }
	public int getSpacing() { return spacing; }
	public IAlterationDefaults getAlteration(long seed) {return alteration.getAlterationConfig(seed); }
	public ISurfaceDefaults getSurface(long seed) { return null; }
	public IReplacementDefaults getReplacement(long seed) { return null; }
	public double getFrequency() { return subRegionFrequency; }
	public PointModifier getPointOffsetModifier(long seed) { return PointModifierFactory.CreateNullPointOffset(); }
}
