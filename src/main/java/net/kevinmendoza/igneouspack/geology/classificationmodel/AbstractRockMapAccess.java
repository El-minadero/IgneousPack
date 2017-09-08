package net.kevinmendoza.igneouspack.geology.classificationmodel;

import java.util.HashMap;

import com.google.inject.Guice;
import com.google.inject.Injector;

import net.kevinmendoza.igneouspack.configuration.ConfigBind;
import net.kevinmendoza.igneouspack.main.IgneousPackMain;

class AbstractRockMapAccess {
	
	private static IRockFactory rockFactory;
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

	protected static IRockFactory getRockFactoryInstance() {
		 if(rockFactory==null)
		 {
			 rockFactory = GetInjector().getInstance(RockFactory.class);
		 }
		 return rockFactory;
	}
}
