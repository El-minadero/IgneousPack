package net.kevinmendoza.igneouspack.geology.intrusive;

import java.util.HashMap;

import com.google.inject.Guice;
import com.google.inject.Injector;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.AbstractMapFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.AbstractPrototypeFactory;
import net.kevinmendoza.igneouspack.configuration.ConfigBind;

public class IntrusiveFactoryHub {


	private static HashMap<Long,AbstractMapFactory> intrusiveMapFactories;
	private static HashMap<Long,AbstractPrototypeFactory> batholithFactories;
	private static HashMap<Long,AbstractPrototypeFactory> swarmFactories;
	private static HashMap<Long,AbstractPrototypeFactory> plutonFactories;
	private static Injector injector;

	private static Injector GetInjector() {
		if(injector==null) {
			injector = Guice.createInjector(new ConfigBind());
		}
		return injector;
	}
	
	public static void setSeed(long seed) {
		GetMapFactory(seed);
		GetBatholithFactory(seed);
		GetSwarmFactory(seed);
		GetPlutonFactory(seed);
	}

	static AbstractPrototypeFactory GetBatholithFactory(long seed) {
		if(batholithFactories==null) {
			batholithFactories = new HashMap<>();
		}
		if(!batholithFactories.containsKey(seed)) {
			BatholithFactory batholithFactory = GetInjector().getInstance(BatholithFactory.class);
			batholithFactory.setSeed(seed);
			batholithFactories.put(seed, batholithFactory);
		}
		return batholithFactories.get(seed);
	}

	static AbstractPrototypeFactory GetSwarmFactory(long seed) {
		if(swarmFactories==null) {
			swarmFactories = new HashMap<>();
		}
		if(!swarmFactories.containsKey(seed)) {
			SwarmFactory swarmFactory = GetInjector().getInstance(SwarmFactory.class);
			swarmFactory.setSeed(seed);
			swarmFactories.put(seed, swarmFactory);
		}
		return swarmFactories.get(seed);
	}

	static AbstractPrototypeFactory GetPlutonFactory(long seed) {
		if(plutonFactories==null) {
			plutonFactories = new HashMap<>();
		}
		if(!plutonFactories.containsKey(seed)) {
			PlutonFactory plutonFactory = GetInjector().getInstance(PlutonFactory.class);
			plutonFactory.setSeed(seed);
			plutonFactories.put(seed, plutonFactory);
		}
		return plutonFactories.get(seed);
	}

	public static AbstractMapFactory GetMapFactory(long seed) {
		if(intrusiveMapFactories==null) {
			intrusiveMapFactories = new HashMap<>();
		}
		if(!intrusiveMapFactories.containsKey(seed)) {
			IntrusiveMapFactory intrusiveMapFactory = GetInjector().getInstance(IntrusiveMapFactory.class);
			intrusiveMapFactory.setSeed(seed);
			intrusiveMapFactories.put(seed, intrusiveMapFactory);
		}
		return intrusiveMapFactories.get(seed);
	}
}
