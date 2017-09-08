package net.kevinmendoza.igneouspack.configuration;

import com.google.common.reflect.TypeToken;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.configuration.PrimitiveWrapperConfig.DoubleMinMax;
import net.kevinmendoza.igneouspack.geology.classificationmodel.TextureTypes;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
class RockClassificationDefaults implements IClassificationDefaults {
	static final TypeToken<RockClassificationDefaults> type = TypeToken.of(RockClassificationDefaults.class);
	
	@Setting
	private double felsicCutoff;
	@Setting
	private String[][] felsicRocks;
	@Setting
	private double intermediateCutoff;
	@Setting
	private String[][] intermediateRocks;
	@Setting
	private String[][] maficRocks;
	
	RockClassificationDefaults(){
		
		felsicCutoff = 0.55;
		intermediateCutoff= 0.45;
		
		felsicRocks 		= new String[4][1];
		felsicRocks[TextureTypes.COARSE.ordinal()][0]		= "GRANITE";
		felsicRocks[TextureTypes.MEDIUM.ordinal()][0]		= "GRANITE";
		felsicRocks[TextureTypes.FINE.ordinal()][0]			= "RHYOLITE";
		felsicRocks[TextureTypes.AMORPHOUS.ordinal()][0]	= "OBSIDIAN";
		intermediateRocks 	= new String[4][1];
		intermediateRocks[TextureTypes.COARSE.ordinal()][0]		= "DIORITE";
		intermediateRocks[TextureTypes.MEDIUM.ordinal()][0]		= "DIORITE";
		intermediateRocks[TextureTypes.FINE.ordinal()][0]		= "ANDESITE";
		intermediateRocks[TextureTypes.AMORPHOUS.ordinal()][0]	= "OBSIDIAN";
		maficRocks 			= new String[4][1];
		maficRocks[TextureTypes.COARSE.ordinal()][0]	= "GABRRO";
		maficRocks[TextureTypes.MEDIUM.ordinal()][0]	= "GABRRO";
		maficRocks[TextureTypes.FINE.ordinal()][0]		= "BASALT";
		maficRocks[TextureTypes.AMORPHOUS.ordinal()][0] = "OBSIDIAN";
	}
	
	@Override
	public String getRockName(TextureTypes type, double silicaContent) {
		String name;
		if( silicaContent> felsicCutoff) {
			name = felsicRocks[type.ordinal()][0];
		}
		else if(silicaContent > intermediateCutoff) {
			name = intermediateRocks[type.ordinal()][0];
		}
		else {
			name = maficRocks[type.ordinal()][0];
		}
		return name;
	}

	
}
