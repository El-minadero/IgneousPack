package net.kevinmendoza.igneouspack.geology.intrusive;

import java.util.HashMap;

import com.flowpowered.math.vector.Vector2i;
import com.google.inject.Inject;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.AbstractPrototype;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.AbstractPrototypeFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeologyNode;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.NodeBuilder;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;
import net.kevinmendoza.igneouspack.configuration.IIntrusiveDefaults;
import net.kevinmendoza.igneouspack.configuration.IStructureDefaults;

abstract class AbstractIntrusiveStructureFactory  extends AbstractPrototypeFactory {

	@Inject
	private IIntrusiveDefaults globalDefaults;
	private IStructureDefaults defaults;
	
	protected final IStructureDefaults getDefaults() {
		if(defaults==null) {
			defaults = getStructureDefaults();
		}
		return defaults;
	}

	protected final IIntrusiveDefaults getGlobalDefaults() {
		return globalDefaults;
	}

	protected abstract IStructureDefaults getStructureDefaults();
	
	protected abstract HashMap<Integer, AbstractPrototypeFactory> produceSubObjectFactoryMap(long vectorSeed);
	
	@Override
	public final IGeologyNode makeObject(AbstractPrototype prototype) {
		long vectorSeed = createVectorSeed(prototype.hashCode());
		HashMap<Integer,AbstractPrototypeFactory> subObjectFactoryMap = produceSubObjectFactoryMap(vectorSeed);
		return new NodeBuilder().setFactories(subObjectFactoryMap).setPrototype(prototype).build();
	}
	
	@Override
	public final Region makeRegion(Vector2i vec) {
		return getDefaults().getGeometryBehavior().getRegion(vec);
	}
}
