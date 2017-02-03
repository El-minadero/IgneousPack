package net.kevinmendoza.igneouspack.main;

import net.kevinmendoza.geoworldlibrary.geology.GeologicContainer;

public final class IgneousFactory {

	public static final IgneousMapFactory newInstance() {
		String implName = "Intrusive";
		return newInstance(implName);
	}
	
	public static final IgneousMapFactory newInstance(String factory) {
		IgneousMapFactory igFactory;
		switch (factory) {
        case "Intrusive":  igFactory = new IntrusiveFactory();
                 break;
        case "Extrusive":  igFactory = new IntrusiveFactory();
                 break;
        default: igFactory = new IntrusiveFactory();
    }
		return igFactory;
	}

	public interface IgneousMapFactory {
		public GeologicContainer createGeologicContainer();
	}
}

