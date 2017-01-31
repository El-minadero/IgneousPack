package net.kevinmendoza.igneouspack.main;


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.slf4j.Logger;
import org.spongepowered.api.plugin.PluginContainer;
import net.kevinmendoza.geoworld.config.Debug;
import net.kevinmendoza.geoworld.config.GeoWorldPluginInterface;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

abstract class Module implements GeoWorldPluginInterface {

	protected static GeoWorldPluginInterface module;
	private Debug debug;
	public PluginContainer container;
	
	public Module(Logger logger, File config,ConfigurationLoader<CommentedConfigurationNode> configLoader,
			PluginContainer container) {
		this.container = container;
		debug    = new Debug(logger);
	}
	
	public static GeoWorldPluginInterface GetStaticInstance() 	 { return module; }
	public GeoWorldPluginInterface GetInstance() 	 { return module;       	  }
	public Debug getDebugger() 				 		 { return debug;              }
	
}
