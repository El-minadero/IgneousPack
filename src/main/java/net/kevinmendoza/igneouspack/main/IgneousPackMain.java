package net.kevinmendoza.igneouspack.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.GameReloadEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.text.Text;

import com.google.common.reflect.TypeToken;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;
import net.kevinmendoza.geoworldlibrary.utilities.Debug;
import net.kevinmendoza.geoworldlibrary.utilities.GeoWorldPlugin;
import net.kevinmendoza.igneouspack.configuration.ConfigBind;
import net.kevinmendoza.igneouspack.configuration.IntrusiveDefaults;
import net.kevinmendoza.igneouspack.geology.intrusive.IntrusiveFactoryHub;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

@Plugin(
id=IgneousPackMain.ID,
name=IgneousPackMain.NAME,
version=IgneousPackMain.VERSION,
url="http://www.kevinmendoza.net/geoworld-a-minecraft-geology-addon/",
authors = {"El_Minadero"},
description = "A Geologic Minecraft Mod",
dependencies = @Dependency(id = "geoworld", optional = false))
public class IgneousPackMain implements GeoWorldPlugin  {
	public static final String ID = "igneouspack";
	public static final String NAME = "IgneousPack";
	public static final String VERSION = "1.0.1a";
	public static IgneousPackMain PluginMain;
	private static final boolean DEBUG = true;
	private HashMap<Long,IGeology> geologyMaps;
	private IntrusiveDefaults defaults;
	@Inject
	private Logger logger; 
	@Inject 
	private PluginContainer container;
	@Inject @DefaultConfig(sharedRoot = true)
	private File file; 
	@Inject @DefaultConfig(sharedRoot = true) 
	private ConfigurationLoader<CommentedConfigurationNode> loader;
	private Injector injector;
	
	public IgneousPackMain() {
		PluginMain = this;
		geologyMaps = new HashMap<>();
	}
	@Listener
	public void onGamePreInitialization(GamePreInitializationEvent event) throws IOException, ObjectMappingException {
	 createConfigs();
	 CommandSpec myCommandSpec = CommandSpec.builder()
			    .description(Text.of("Show Local Data Command"))
			    .permission("igneouspack.command.revealsurroundingstructures")
			    .executor(new IgneousPackCommand())
			    .build();

			Sponge.getCommandManager().register(PluginMain, myCommandSpec, "localstructuredata");
	}
	
	@Listener
	public void onGameReload(GameReloadEvent event) throws IOException, ObjectMappingException {
		createConfigs();
	}
	public IGeology getGeology(long seed) {
		if(!geologyMaps.containsKey(seed)) {
			IntrusiveFactoryHub.setSeed(seed);
			IGeology geology = IntrusiveFactoryHub.GetMapFactory(seed).createGeologyMap();
			geologyMaps.put(seed, geology);
		}
		return geologyMaps.get(seed);
	}

	public void createConfigs() throws IOException, ObjectMappingException {
		ConfigurationNode node = loader.createEmptyNode();
		node.setValue(IntrusiveDefaults.type, defaults == null ? (defaults= new IntrusiveDefaults()) : defaults);
		loader.save(node);
	}


	public Logger GetLog() {
		return logger;
	}

	@Override
	public String GetPluginID() { return ID; }
	
	public Injector getInjector() {
		if(injector==null) {
			injector = Guice.createInjector(new ConfigBind());
		}
		return injector;
	}
}
