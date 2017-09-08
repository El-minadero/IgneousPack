package net.kevinmendoza.igneouspack.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.Order;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.configuration.PrimitiveWrapperConfig.DoubleMinMax;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.configuration.PrimitiveWrapperConfig.TruncatedSkewedGaussianDistribution;
import net.kevinmendoza.igneouspack.geology.classificationmodel.TextureTypes;
import net.kevinmendoza.igneouspack.geology.intrusive.StructureFactoryTypes;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class GeologyBehavior implements IGeologyBehavior {

	@Setting
	private Order order;
	@Setting
	private TextureTypes type;
	@Setting
	private double valueCutoff;
	@Setting
	private TruncatedSkewedGaussianDistribution silicaContentRange;
	@Setting
	private HashMap<StructureFactoryTypes,TruncatedSkewedGaussianDistribution> subStructures;
	
	GeologyBehavior(List<StructureFactoryTypes> types, List<TruncatedSkewedGaussianDistribution> dist){
		valueCutoff = 0.2;
		order = Order.FIRST;
		type = TextureTypes.COARSE;
		silicaContentRange = new TruncatedSkewedGaussianDistribution(0.2,0.8,1,1);
		subStructures = new HashMap<>();
		for(int i =0; i< types.size(); i++) {
			subStructures.put(types.get(i), dist.get(i));
		}
	}

	public Order getOrder() { return order; }
	public TextureTypes getTextureType() { return type; }
	public double getSilicaContent(long seed) { return silicaContentRange.getValue(seed); }
	public double getRockCutoff() { return valueCutoff; }

	public List<StructureFactoryTypes> getSubObjectTypes() { return new ArrayList<>(subStructures.keySet());}
	public int getStructureTypeAmount(StructureFactoryTypes type,
			long vectorSeed) { 
		if(!subStructures.containsKey(type)) {
			return 0;
		}
		return (int)subStructures.get(type).getValue(vectorSeed);
	}

}
