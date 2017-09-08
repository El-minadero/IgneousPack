package net.kevinmendoza.igneouspack.configuration;

import net.kevinmendoza.igneouspack.geology.classificationmodel.TextureTypes;

public interface IClassificationDefaults {

	String getRockName(TextureTypes type, double silicaContent);


}
