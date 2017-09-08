package net.kevinmendoza.igneouspack.geology.classificationmodel;

import com.google.inject.Inject;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractRock;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.GeoWorldRock;
import net.kevinmendoza.igneouspack.configuration.IClassificationDefaults;

public class RockFactory implements IRockFactory {

	@Inject
	private IClassificationDefaults defaults;

	public AbstractRock getRock(TextureTypes type, double silicaContent,
			double valueCutoff) {
		String name = defaults.getRockName(type,silicaContent);
	
		return new GeoWorldRock.Builder()
				.setCutoff(valueCutoff)
				.setName(name)
				.build();
	}
}
