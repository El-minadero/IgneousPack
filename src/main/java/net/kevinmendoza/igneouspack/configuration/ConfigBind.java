package net.kevinmendoza.igneouspack.configuration;

import com.google.inject.AbstractModule;

public class ConfigBind extends AbstractModule {

	@Override
	protected void configure() {
		bind(IGlobalDefaults.class).to(GlobalDefaults.class);
		bind(IMapDefaults.class).to(IntrusiveMapDefaults.class);
		bind(IBatholithDefaults.class).to(BatholithDefaults.class);
		bind(ISwarmDefaults.class).to(SwarmDefaults.class);
		bind(IPlutonDefaults.class).to(PlutonDefaults.class);
	}

}
