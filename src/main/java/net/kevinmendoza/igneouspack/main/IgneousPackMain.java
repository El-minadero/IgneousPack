package net.kevinmendoza.igneouspack.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

import com.google.inject.Inject;

import net.kevinmendoza.geoworld.geology.GeologicContainer;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

@Plugin(
id=IgneousPackMain.ID,
name=IgneousPackMain.NAME,
version=IgneousPackMain.VERSION,
url="http://www.kevinmendoza.net/geoworld-a-minecraft-geology-addon/",
authors = {"El_Minadero"},
description = "A Geologic Minecraft Mod",
dependencies = @Dependency(id = "geoworld", optional = false))
public class IgneousPackMain extends Module {
	public static final String ID = "igneouspack";
	public static final String NAME = "IgneousPack";
	public static final String VERSION = "1.0.1a";
	private GeologicContainer batholiths;
	private GeologicContainer volcanics;
	
	@Inject
	public IgneousPackMain(Logger logger, @DefaultConfig(sharedRoot = true) File config, 
			@DefaultConfig(sharedRoot = true)ConfigurationLoader<CommentedConfigurationNode> configLoader,
			PluginContainer container) {
		super(logger, config,configLoader, container);
		defaults.onLoad(VERSION);
		module=this;
	}
	
	
	@Listener
	public void onGamePreInitialization(GamePreInitializationEvent event) {
		module 	 = this;
		this.batholiths = IgneousFactory.newInstance().createGeologicContainer();
		//this.volcanics  = IgneousMapFactory.MakeExtrusiveGeology(defaults,debug);
	}

	@Override
	public List<GeologicContainer> getGeologicContainers() {
		List<GeologicContainer> containers = new ArrayList<>();
		containers.add(batholiths); containers.add(volcanics);
		return containers;
	}
}
