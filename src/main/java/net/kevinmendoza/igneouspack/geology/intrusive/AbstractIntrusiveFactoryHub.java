package net.kevinmendoza.igneouspack.geology.intrusive;

import java.util.HashMap;

import com.google.inject.Guice;
import com.google.inject.Injector;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.AbstractMapFactory;
import net.kevinmendoza.igneouspack.configuration.ConfigBind;
import net.kevinmendoza.igneouspack.main.IgneousPackMain;

abstract class AbstractIntrusiveFactoryHub {

	private static HashMap<StructureFactoryTypes,HashMap<Long,AbstractIntrusiveStructureFactory>> structureFactoryMap;
	private static HashMap<Long,AbstractMapFactory> intrusiveMapFactories;

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
	
	protected static final AbstractMapFactory getMapFactory(long seed) {
		if(intrusiveMapFactories==null) {
			intrusiveMapFactories = new HashMap<>();
		}
		if(!intrusiveMapFactories.containsKey(seed)) {
			IntrusiveMapFactory factory = GetInjector().getInstance(IntrusiveMapFactory.class);
			factory.setSeed(seed);
			intrusiveMapFactories.put(seed, factory);
		}
		return intrusiveMapFactories.get(seed);
	}
	
	protected static final AbstractIntrusiveStructureFactory getStructureFactory(StructureFactoryTypes type, long seed) {
		if(structureFactoryMap==null) {
			structureFactoryMap = new HashMap<>();
		}
		if(!structureFactoryMap.containsKey(type)) {
			HashMap<Long,AbstractIntrusiveStructureFactory> factoryMap = new HashMap<>();
			structureFactoryMap.put(type, factoryMap);
		}
		if(!structureFactoryMap.get(type).containsKey(seed)) {
			structureFactoryMap.get(type).put(seed, type.getFactoryInstance(seed));
		}
		return structureFactoryMap.get(type).get(seed);
	}
	
}
	
	
