package net.kevinmendoza.igneouspack.geology.intrusive;

import com.google.inject.Guice;
import com.google.inject.Injector;

import net.kevinmendoza.igneouspack.configuration.ConfigBind;
import net.kevinmendoza.igneouspack.main.IgneousPackMain;

public enum StructureFactoryTypes {
PLUTON{
	@Override 
	AbstractIntrusiveStructureFactory getFactoryInstance(long seed) {
		AbstractIntrusiveStructureFactory factory = GetInjector().getInstance(PlutonFactory.class);
		factory.setSeed(seed);
		return factory;
	}
},BATHOLITH{
	@Override 
	AbstractIntrusiveStructureFactory getFactoryInstance(long seed) {
		AbstractIntrusiveStructureFactory factory = GetInjector().getInstance(BatholithFactory.class);
		factory.setSeed(seed);
		return factory;
	}
};

	AbstractIntrusiveStructureFactory getFactoryInstance(long seed) {

	return null;
	}

	private static Injector injector;

	protected static final Injector GetInjector() {
		if(injector==null) {
			if(IgneousPackMain.PluginMain==null) {
				injector = Guice.createInjector(new ConfigBind());
			}
			else {
				injector = IgneousPackMain.PluginMain.getInjector();
			}
		}
		return injector;
	}
}
