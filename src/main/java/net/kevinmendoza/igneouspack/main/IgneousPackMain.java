package net.kevinmendoza.igneouspack.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.GameReloadEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

import com.google.inject.Inject;

import net.kevinmendoza.geoworldlibrary.utilities.Debug;
import net.kevinmendoza.geoworldlibrary.utilities.GeoWorldPluginInterface;
import net.kevinmendoza.geoworldlibrary.geology.GeologicContainer;
import net.kevinmendoza.igneouspack.configuration.IgneousPackDefaults;
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
public class IgneousPackMain  implements GeoWorldPluginInterface {
	public static final String ID = "igneouspack";
	public static final String NAME = "IgneousPack";
	public static final String VERSION = "1.0.1a";
	private IgneousPackDefaults defaults;
	private GeologicContainer batholiths;
	private GeologicContainer volcanics;
	@Inject
	private static Logger logger; 
	@Inject 
	private static PluginContainer container;
	
	@DefaultConfig(sharedRoot = true) File file; 
	@DefaultConfig(sharedRoot = true)ConfigurationLoader<CommentedConfigurationNode> loader;
	
	
	@Listener
	public void onGamePreInitialization(GamePreInitializationEvent event) throws IOException, ObjectMappingException {
		//this.batholiths = IgneousFactory.newInstance().createGeologicContainer();
		createConfigs();
	}
	
	@Listener
	public void onGameReload(GameReloadEvent event) throws IOException, ObjectMappingException {
		createConfigs();
	}

	@Override
	public List<GeologicContainer> getGeologicContainers() {
		List<GeologicContainer> containers = new ArrayList<>();
		containers.add(batholiths); containers.add(volcanics);
		return containers;
	}

	public void createConfigs() throws IOException, ObjectMappingException {
		logger.info("on game preinit");
		ConfigurationNode node = loader.createEmptyNode();
		node.setValue(IgneousPackDefaults.type, defaults == null ? (defaults= new IgneousPackDefaults()) : defaults);
		loader.save(node);

	}

	@Override
	public GeoWorldPluginInterface GetInstance() {
		return this;
	}

	public static Debug GetDebugger() {
		return new Debug(logger);
	}
}
