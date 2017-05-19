package net.kevinmendoza.igneouspack.geology.data;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Alteration;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Comparison;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.IAlterationBuilder;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.IGeologyData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.ISurfaceBuilder;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Surface;
import net.kevinmendoza.igneouspack.geology.data.TestAlteration.TestAlterationBuilder;
import net.kevinmendoza.igneouspack.geology.data.TestSurface.TestSurfaceBuilder;
public class DataFactory {
	
	private static TestSurfaceBuilder builder1;
	private static TestAlterationBuilder builder2;
	
	public static int GetSurfaceID(){ return GetTestSurfaceBuilder().getID(); }
	public static int GetAlterationID(){ return GetTestAlterationBuilder().getID(); }
	
	public static Surface CreateSurfaceInstance(int height,Order order) {
		TestSurfaceBuilder b = GetTestSurfaceBuilder();
		return b.setOrder(order).setHeight(height).build();
	}
	
	public static Alteration CreateAlterationInstance(double heat, double pressure, Order order) {
		TestAlterationBuilder b = GetTestAlterationBuilder();
		return b.setOrder(order).setHeat(heat).setPressure(pressure).build();
	}
	
	public static IGeologyData CreateEmptySurfaceInstance(Order order) {
		return new EmptySurfaceData(GetTestSurfaceBuilder());
	}
	
	public static IGeologyData CreateEmptyAlterationInstance(Order order) {
		return new EmptyAlterationData(GetTestAlterationBuilder());
	}

	private static TestSurfaceBuilder GetTestSurfaceBuilder() {
		if(builder1==null)
			builder1 = new TestSurfaceBuilder();
		return builder1;
	}
	
	private static TestAlterationBuilder GetTestAlterationBuilder() {
		if(builder2==null)
			builder2 = new TestAlterationBuilder();
		return builder2;
	}
	
	private static class EmptySurfaceData extends Surface {

		public EmptySurfaceData(ISurfaceBuilder builder) {
			super(builder);
		}

		public void merge(IGeologyData data,double d) { }
		public void merge(IGeologyData data) { }

		@Override
		public IGeologyData getOrderData(Order order) { return null; }

		@Override
		public int getHeight() { return 0; }
		@Override
		public IGeologyData copy() { return CreateEmptySurfaceInstance(getOrder()); }

		@Override
		public void applyMultiplier(double multiplier) { }
		
	}
	
	private static class EmptyAlterationData extends Alteration {

		protected EmptyAlterationData(IAlterationBuilder builder) {
			super(builder);
		}

		public void merge(IGeologyData data,double d) { }
		public void merge(IGeologyData data) { }

		@Override
		public IGeologyData getOrderData(Order order) { return null; }

		@Override
		public double getPressure() { return 0;}
		@Override
		public IGeologyData copy() { return CreateEmptyAlterationInstance(getOrder()); }

		@Override
		public double getHeat() { return 0; }

		@Override
		public void applyMultiplier(double multiplier) { }
		
	}
}
