package net.kevinmendoza.igneouspack.geology.classificationmodel;

public class RockFactoryAccess extends AbstractRockMapAccess {

	public static IRockFactory getRockFactory() {
		return getRockFactoryInstance();
	}
	
}
