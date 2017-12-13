package net.kevinmendoza.igneouspack.configuration;

import com.google.common.reflect.TypeToken;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.Order;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifier;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifierFactory;
import net.kevinmendoza.igneouspack.configuration.GeologyConfiguration.AlterationParameters;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
@ConfigSerializable
class IntrusiveMapDefaults implements IMapDefaults {
	static final TypeToken<IntrusiveMapDefaults> type = TypeToken.of(IntrusiveMapDefaults.class);
	@Setting
	int spacing;
	@Setting
	double subRegionFrequency;
	@Setting
	private Order order;
	
	IntrusiveMapDefaults(){
		spacing = 1200;
		subRegionFrequency = .2;
	}
	
	public int getSpacing() { return spacing; }
	public double getFrequency() { return subRegionFrequency; }
	public PointModifier getPointOffsetModifier(long seed) { return PointModifierFactory.CreateNullPointOffset(); }
	public Order getOrder() { return order; }
}
