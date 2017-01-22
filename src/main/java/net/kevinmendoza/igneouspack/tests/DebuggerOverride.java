package net.kevinmendoza.igneouspack.tests;

import org.slf4j.Logger;

import net.kevinmendoza.geoworld.config.Debug;

public class DebuggerOverride extends Debug {

	public DebuggerOverride() {
		super(null);
	}
	
	@Override
	public void log(String string) {
		System.out.println(string);
	}

}
