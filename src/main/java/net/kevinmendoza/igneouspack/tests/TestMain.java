package net.kevinmendoza.igneouspack.tests;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Alteration;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.IGeologyData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.PrimeData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Surface;
import net.kevinmendoza.igneouspack.geology.data.DataFactory;
import net.kevinmendoza.igneouspack.configuration.IntrusiveDefaults;
import net.kevinmendoza.igneouspack.geology.intrusive.IntrusiveFactoryHub;

public class TestMain {

	public static void main(String[] args) {
		IntrusiveDefaults defaults = new IntrusiveDefaults();
		BufferedImage img = new BufferedImage(250,250,BufferedImage.TYPE_INT_RGB);
		IntrusiveFactoryHub.setSeed(11);
		IGeology geology = IntrusiveFactoryHub.GetMapFactory(11).createGeologyMap();
		System.out.println("Starting test.");
		long start = System.nanoTime();
		long totalIterations = 0;
		for(int z=25;z>24;z--) {
			for(int x=249;x>0;x--) {
				Vector2i query1 = new Vector2i(x*15 + 7000,z*25);
				PrimeData data = new PrimeData(query1); 
				data.setHeight(60);
				geology.primeGeneration(data);
				for(int y=50;y>0;y--) {
					totalIterations++;
					Vector3i query2 = new Vector3i(query1.getX(),y,query1.getY());
					IGeologyData geoData = 
							geology.get3DGeologyData(DataFactory.CreateEmptyAlterationInstance(Order.FIRST), query2);
					Alteration surf = (Alteration)geoData.getOrderData(Order.FIRST);
					//System.out.println("Heat:" + surf.getHeat() + "Pressure:" + surf.getPressure() + " depth" + (64-y));
					Color color = new Color((int)surf.getHeat()/20,(int)surf.getPressure()/20,0);
					img.setRGB(x,y,color.getRGB());
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
