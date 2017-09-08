package net.kevinmendoza.igneouspack.configuration;

import com.google.inject.AbstractModule;

public class ConfigBind extends AbstractModule {

	@Override
	protected void configure() {
		bind(IMapDefaults.class).to(IntrusiveMapDefaults.class);
		bind(IIntrusiveDefaults.class).to(IntrusiveDefaults.class);
		bind(IClassificationDefaults.class).to(RockClassificationDefaults.class);
	}

}
