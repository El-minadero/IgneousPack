package net.kevinmendoza.igneouspack.configuration;

import java.util.List;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.Order;
import net.kevinmendoza.igneouspack.geology.classificationmodel.TextureTypes;
import net.kevinmendoza.igneouspack.geology.intrusive.StructureFactoryTypes;

public interface IGeologyBehavior {

	Order getOrder();

	TextureTypes getTextureType();

	double getSilicaContent(long vectorSeed);

	double getRockCutoff();

	List<StructureFactoryTypes> getSubObjectTypes();

	int getStructureTypeAmount(StructureFactoryTypes type, long vectorSeed);

}
