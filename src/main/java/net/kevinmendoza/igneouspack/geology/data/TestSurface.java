package net.kevinmendoza.igneouspack.geology.data;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.IGeologyData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.ISurfaceBuilder;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Surface;
import net.kevinmendoza.igneouspack.geology.data.TestAlteration.TestAlterationBuilder;

class TestSurface extends Surface {

	private final int id;

	TestSurface(TestSurfaceBuilder builder) {
		super(builder);
		id = builder.getID();
	}
	
	public final int getHeight() { return getProtectedHeight(); }
	public void applyMultiplier(double multiplier) { setProtectedHeight((int) (getHeight()*multiplier));}
	public void merge(IGeologyData data, double mergeWeight) {
		if(data.getID()==id) {
			mergeLeaf((Surface)data,mergeWeight);
		}
	}
	public void merge(IGeologyData data) {
		if(data.getID()==id) {
			mergeLeaf((Surface)data);
		}
	}
	
	private void mergeLeaf(Surface data, double mergeWeight) {
		int h = (int) (getProtectedHeight()*(1-mergeWeight) + data.getHeight()*mergeWeight);
		setProtectedHeight(h);
	}
	private void mergeLeaf(Surface data) {
		if(data.getHeight()>getHeight()) {
			setProtectedHeight(data.getHeight());
		}
	}

	@Override
	public IGeologyData copy() {
		return new TestSurfaceBuilder()
				.setOrder(getOrder())
				.setHeight(getHeight())
				.build();
	}

	static class TestSurfaceBuilder implements ISurfaceBuilder {
		private Order order;
		private int height;
		private final int ID;
		
		TestSurfaceBuilder(){ ID = 1; }
		public Order getOrder() { return order; }
		public int getSurface() { return height; }
		public int getID() { return ID; }
		
		TestSurfaceBuilder setOrder(Order order) { this.order = order; return this; }
		TestSurfaceBuilder setHeight(int height) { this.height = height; return this; }
		
		Surface build() {
			return new TestSurface(this);
		}
	}
}
