package net.kevinmendoza.igneouspack.geology.data;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Alteration;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.IAlterationBuilder;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.IGeologyData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Surface;

class TestAlteration extends Alteration {

	private TestAlterationBuilder builder;

	TestAlteration(TestAlterationBuilder builder) {
		super(builder);
		this.builder = builder;
	}

	public void applyMultiplier(double multiplier) { 
		setProtectedHeat(getProtectedHeat()*multiplier);
		setProtectedPressure(getProtectedPressure()*multiplier);			
	}
	public final double getHeat() { return this.getProtectedHeat(); }
	public final double getPressure() { return this.getProtectedPressure(); }
	
	public void merge(IGeologyData data, double mergeWeight) {
		if(data.getID()==getID()) {
			mergeLeaf((Alteration)data,mergeWeight);
		}
	}
	public void merge(IGeologyData data) {
		if(data.getID()==getID()) {
			mergeLeaf((Alteration)data);
		}
	}

	private void mergeLeaf(Alteration data, double mergeWeight) {
		double invMerge = 1-mergeWeight;
		double startHeat = getProtectedHeat();
		double startPressure = getProtectedPressure();
		setProtectedHeat(startHeat*invMerge + data.getHeat()*mergeWeight);
		setProtectedPressure(startPressure*invMerge + data.getPressure()*mergeWeight);
	}
	private void mergeLeaf(Alteration data) {
		if(getProtectedHeat()<data.getHeat())
			setProtectedHeat(data.getHeat());
		if(getProtectedPressure()<data.getPressure())
			setProtectedPressure(data.getPressure());
	}
	
	@Override
	public IGeologyData copy() {
		return new TestAlterationBuilder()
				.setOrder(getOrder())
				.setHeat(getHeat())
				.setPressure(getPressure())
				.build();
	}

	static class TestAlterationBuilder implements IAlterationBuilder {
		private Order order;
		private double heat;
		private double pressure;
		private final int ID;
		
		TestAlterationBuilder(){ ID = 2; }
		public Order getOrder() { return order; }
		public double getHeat() { return heat; }
		public double getPressure() { return pressure; }
		public int getID() { return ID; }
		
		TestAlterationBuilder setOrder(Order order) { this.order = order; return this; }
		TestAlterationBuilder setHeat(double heat) { this.heat = heat; return this; }
		TestAlterationBuilder setPressure(double pressure) { this.pressure = pressure; return this; }
		
		Alteration build() {
			return new TestAlteration(this);
		}
	}

}
