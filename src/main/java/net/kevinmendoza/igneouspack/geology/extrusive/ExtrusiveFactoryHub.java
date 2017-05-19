package net.kevinmendoza.igneouspack.geology.extrusive;

import com.google.inject.Guice;
import com.google.inject.Injector;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.AbstractMapFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.AbstractPrototypeFactory;
import net.kevinmendoza.igneouspack.configuration.ConfigBind;

public class ExtrusiveFactoryHub {

	
	private static AbstractMapFactory testMapFactory;
	private static AbstractPrototypeFactory object1Factory;
	private static AbstractPrototypeFactory object2Factory;
	private static Injector injector;
	
	private static Injector GetInjector() {
		if(injector==null) {
			injector = Guice.createInjector(new ConfigBind());
		}
		return injector;
	}
	public static AbstractPrototypeFactory GetObject1Factory() {
			if(object1Factory==null) {
				
			}
			return object1Factory;
	}
	
	public static AbstractPrototypeFactory GetObject2Factory() {
		if(object2Factory==null) {
			
		}
		return object2Factory;
	}
	

}
