import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.realtime.PriorityScheduler;
import javax.realtime.RealtimeThread;
import javax.realtime.RelativeTime;

public class TrafficLight extends RealtimeThread {
	
	public int CurrentActiveDirection;
	public boolean[] directions = new boolean[4];
	public int id;
	public Intersection position;
	
	
	public TrafficLight(int i, Intersection position) {
		super (
				new PriorityParameters(PriorityScheduler.instance().getMaxPriority()), 
				new PeriodicParameters(new RelativeTime((1000 * i),0))
			);
		
		this.id = i;
		this.position = position;
		this.directions[0] = false;
		this.directions[1] = false;
		this.directions[2] = false;
		this.directions[3] = false;
		this.start();
	}
	
	@Override
	public void run() {
		while(true) {
			this.boot();
		}
	}
	
	public void boot() {
		
		for(int i = 0; i<3; i++) {
			this.CurrentActiveDirection = i;
			this.directions[i] = true;
			
			System.out.println("\n Traffic light at: " 
					+ this.position.name + "  \n" + 
					this.getDirectionName(i) + " is Green.");

			waitForNextPeriod();
			
		}
	}
	
	public void switchLight(int i) {
		this.turnAllLightsOff();
		this.getCurrentActiveLight(i);
	}
	
	public String getDirectionName(int i) {
		switch(i) {
			case 0:
				return "NorthSouthDirection";
			case 1:
				return "SouthNorthDirection";
			case 2:
				return "EastWestDirection";
			case 3:
				return "WestEastDirection";
		}
		return "NorthSouthDirection";
		
	}

	public void getCurrentActiveLight(int i) {

	}
	
	private void turnAllLightsOff() {

		
	}

}
