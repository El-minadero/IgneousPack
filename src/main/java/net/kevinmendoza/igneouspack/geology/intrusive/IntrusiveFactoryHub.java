package net.kevinmendoza.igneouspack.geology.intrusive;

import java.util.HashMap;

import com.google.inject.Guice;
import com.google.inject.Injector;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.factory.AbstractMapFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.factory.AbstractPrototypeFactory;
import net.kevinmendoza.igneouspack.configuration.ConfigBind;
import net.kevinmendoza.igneouspack.main.IgneousPackMain;

public class IntrusiveFactoryHub extends AbstractIntrusiveFactoryHub {
	
	public static void setSeed(long seed) {
		GetMapFactory(seed);
		for(StructureFactoryTypes type : StructureFactoryTypes.values()) {
			getStructureFactory(type,seed);
		}
	}

	public static AbstractMapFactory GetMapFactory(long seed) {
		return getMapFactory(seed);
	}

	static AbstractPrototypeFactory getFactory(
			StructureFactoryTypes type, long seed) {
		return getStructureFactory(type, seed);
	}
}
