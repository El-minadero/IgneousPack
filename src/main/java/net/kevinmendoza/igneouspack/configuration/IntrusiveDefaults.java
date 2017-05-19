package net.kevinmendoza.igneouspack.configuration;

import com.google.common.reflect.TypeToken;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class IntrusiveDefaults {
	public static final TypeToken<IntrusiveDefaults> type = TypeToken.of(IntrusiveDefaults.class);
	
	@Setting
	IGlobalDefaults globalDefaults;
	@Setting
	IMapDefaults IgneousMapDefaults;
	@Setting
	ISwarmDefaults swarmDefaults;
	@Setting
	IBatholithDefaults batholithDefaults;
	@Setting
	PlutonDefaults plutonDefaults;
	
	public IntrusiveDefaults() {
		globalDefaults 		= new GlobalDefaults();
		IgneousMapDefaults  = new IntrusiveMapDefaults();
		batholithDefaults	= new BatholithDefaults();
		swarmDefaults		= new SwarmDefaults();
		plutonDefaults		= new PlutonDefaults();
	}

}
