package net.kevinmendoza.igneouspack.tests;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.EmptyDataFactory;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.IGeologyData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.Order;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.PrimeData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractAlteration;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractRock;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.ISingularGeologyData;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;
import net.kevinmendoza.igneouspack.configuration.IgneousDefaults;
import net.kevinmendoza.igneouspack.geology.intrusive.IntrusiveFactoryHub;

public class TestMain {

	public static void main(String[] args) {
		System.out.println("Starting test.");
		long l;
		l = 558618376743604964L;
		IgneousDefaults defaults = new IgneousDefaults();
		BufferedImage img = new BufferedImage(500,250,BufferedImage.TYPE_INT_RGB);
		IGeology geology = IntrusiveFactoryHub.GetMapFactory(l).createGeologyMap();
		long start = System.nanoTime();
		long totalIterations = 0;
		for(int z=125;z>-123;z--) {
			for(int x=249;x>-249;x--) {
				Vector2i query1 = new Vector2i(x*10,z*10);
				PrimeData data = new PrimeData(query1); 
				data.setHeight(60);
				geology.loadNearbyNodes(data);
				for(int y=1;y>0;y--) {
					totalIterations++;
					Vector3i query2 = new Vector3i(query1.getX(),y,query1.getY());
					ISingularGeologyData geoData = 
							geology.get3DGeologyData(EmptyDataFactory.getEmptyDataObject(3),query2);
					AbstractRock surf = (AbstractRock)geoData;
					int rgb = surf.toRGBCode();
					img.setRGB(x+249,z+124,rgb);
					int n = geology.getRGBDebugAtCoordinates(query2);
					if(n!=0)
						img.setRGB(x+249,z+124,n);
				}
			}
		}
		long end = System.nanoTime();
		double totalTime =(end-start)/1000000;
		double timePerIteration = totalTime/totalIterations;
		int chunks = (int) (totalIterations/(16*16));
		System.out.println("test Complete. Generation took " + totalTime + "ms and "
		+ timePerIteration + "ms per iteration.");
		System.out.println("Estimated Chunk Generation time is: " + (totalTime/chunks) + "ms");
		File outputfile = new File("CoordinateVariations1.png");
		try {
			ImageIO.write(img, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
