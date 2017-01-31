package net.kevinmendoza.igneouspack.configuration;

import com.google.common.reflect.TypeToken;

import net.kevinmendoza.igneouspack.configuration.IgneousConfigs.Batholith;
import net.kevinmendoza.igneouspack.configuration.IgneousConfigs.Pluton;
import net.kevinmendoza.igneouspack.configuration.IgneousConfigs.PlutonSwarm;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class IgneousPackDefaults {
	public static final TypeToken<IgneousPackDefaults> type = TypeToken.of(IgneousPackDefaults.class);

	@Setting("Batholith")
	private Batholith batholith;
	
	@Setting("Pluton-Swarm")
	private PlutonSwarm plutonSwarm;
	
	@Setting("Pluton")
	private Pluton pluton;

}
