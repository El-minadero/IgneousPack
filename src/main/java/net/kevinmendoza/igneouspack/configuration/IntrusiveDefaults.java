package net.kevinmendoza.igneouspack.configuration;

import com.google.common.reflect.TypeToken;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class IntrusiveDefaults implements IIntrusiveDefaults {
	public static final TypeToken<IntrusiveDefaults> type = TypeToken.of(IntrusiveDefaults.class);
	
	@Setting
	IntrusiveMapDefaults IntrusiveMapDefaults;
	@Setting
	IntrusiveStructureDefaults batholithDefaults;
	@Setting
	IntrusiveStructureDefaults plutonDefaults;
	
	public IntrusiveDefaults() {
		IntrusiveMapDefaults  = new IntrusiveMapDefaults();
		batholithDefaults	= new IntrusiveStructureDefaults(true);
		plutonDefaults		= new IntrusiveStructureDefaults(false);
	}
	
	public IStructureDefaults getBatholithDefaults() {
		return batholithDefaults;
	}
	
	public IStructureDefaults getPlutonDefaults() {
		return plutonDefaults;
	}

}
