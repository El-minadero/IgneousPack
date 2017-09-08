package net.kevinmendoza.igneouspack.geology.classificationmodel;

import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractRock;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.ISingularGeologyData;

public interface IRockFactory {

	public AbstractRock getRock(TextureTypes type, double silicaContent,
			double valueCutoff);

}
