package net.kevinmendoza.igneouspack.tests;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.EmptyDataFactory;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.PrimeData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractRock;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.ISingularGeologyData;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;
import net.kevinmendoza.igneouspack.configuration.IgneousDefaults;
import net.kevinmendoza.igneouspack.geology.intrusive.IntrusiveFactoryHub;

public class CommandTest {

	public static void main(String[] args) {
		System.out.println("Starting test.");
		long l;
		l = 558618376743604964L;
		IgneousDefaults defaults = new IgneousDefaults();
		IGeology geology = IntrusiveFactoryHub.GetMapFactory(l).createGeologyMap();
		for(int z=125;z>-123;z--) {
			for(int x=249;x>-249;x--) {
				Vector2i query1 = new Vector2i(x*10,z*10);
				PrimeData data = new PrimeData(query1); 
				data.setHeight(60);
				geology.loadNearbyNodes(data);
				Vector3i q1 = new Vector3i(query1.getX(),50,query1.getY());
				String m = geology.getLocationData(q1);
				System.out.println(m);
			}
		}
	}

}
