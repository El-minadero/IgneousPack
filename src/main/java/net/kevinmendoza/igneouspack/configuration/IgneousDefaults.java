package net.kevinmendoza.igneouspack.configuration;

import com.google.common.reflect.TypeToken;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class IgneousDefaults {
	public static final TypeToken<IgneousDefaults> type = TypeToken.of(IgneousDefaults.class);

	@Setting
	private IntrusiveDefaults intrusiveDefaults;
	@Setting
	private RockClassificationDefaults classificationDefaults;

	public IgneousDefaults() {
		intrusiveDefaults  = new IntrusiveDefaults();
		classificationDefaults = new RockClassificationDefaults();
	}

}
