package net.kevinmendoza.igneouspack.configuration;


import java.util.ArrayList;
import java.util.List;

import com.google.common.reflect.TypeToken;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.RegionConfiguration;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.RegionTypes;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.MultiplierMapConfiguration;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMapConfiguration;
import net.kevinmendoza.igneouspack.geology.intrusive.StructureFactoryTypes;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.configuration.PrimitiveWrapperConfig.IntMinMax;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.configuration.PrimitiveWrapperConfig.TruncatedSkewedGaussianDistribution;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class IntrusiveStructureDefaults implements IStructureDefaults {
	static final TypeToken<IntrusiveStructureDefaults> type = TypeToken.of(IntrusiveStructureDefaults.class);
	
	@Setting
	private GeometryBehavior geometryBehavior;
	
	@Setting
	private GeologyBehavior geologyBehavior;
	
	public IntrusiveStructureDefaults(boolean isBatholith){
		double[] decayConst;
		double baseFrequency;
		int octaves, power, min_emplacement_depth,max_emplacement_depth;
		int x_extent_min,x_extent_max,y_extent_min,y_extent_max,z_extent_min,z_extent_max;	
		List<StructureFactoryTypes> stype = new ArrayList<>();
		List<TruncatedSkewedGaussianDistribution> dists = new ArrayList<>();
		IntMinMax emplacementDepth;
		ArrayList<IntMinMax> dimensions = new ArrayList<>();
		RegionTypes rtype; GeometryBehavior.Builder geoBuilder = new GeometryBehavior.Builder();
		if(isBatholith) {
			decayConst = new double[2];
			
			decayConst[0]	=1;		decayConst[1]	=1;
			baseFrequency=1000;octaves=3; power=1;
			x_extent_min = 300; x_extent_max=600;
			y_extent_min = 300; y_extent_max=600;
			rtype = RegionTypes.ELLIPSE;
			stype.add(StructureFactoryTypes.PLUTON);
			dists.add(new TruncatedSkewedGaussianDistribution(3,12,0.5,0.5));
			emplacementDepth = new IntMinMax();
			dimensions.add(new IntMinMax(x_extent_min,x_extent_max));
			dimensions.add(new IntMinMax(y_extent_min,y_extent_max));

		}
		else {
			decayConst = new double[2];
			baseFrequency=500;octaves=3; power=1;
			decayConst[0]	=1;		decayConst[1]	=1;
			x_extent_min = 50; x_extent_max=300;
			y_extent_min = 50; y_extent_max=300;
			z_extent_min = 50; z_extent_max=300;
			min_emplacement_depth=-200;max_emplacement_depth=40;
			rtype = RegionTypes.ELLIPSOID;
			
			dimensions.add(new IntMinMax(x_extent_min,x_extent_max));
			dimensions.add(new IntMinMax(y_extent_min,y_extent_max));
			dimensions.add(new IntMinMax(z_extent_min,z_extent_max));
			emplacementDepth = new IntMinMax(min_emplacement_depth,max_emplacement_depth);
		}
		geometryBehavior = geoBuilder.setDecayConstant(decayConst[0], decayConst[1])
				.setMultiplierMap(baseFrequency, octaves, power)
				.setRegionConfiguration(rtype, dimensions)
				.setEmplacementDepth(emplacementDepth)
				.build();
				
		geologyBehavior = new GeologyBehavior(stype,dists);
	}

	@Override
	public IGeologyBehavior getGeologyBehavior() { return geologyBehavior; }

	@Override
	public IGeometryBehavior getGeometryBehavior() { return geometryBehavior; }
	
}
