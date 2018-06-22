import java.util.ArrayList;

import javax.realtime.AsyncEvent;
import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.realtime.PriorityScheduler;
import javax.realtime.RealtimeThread;
import javax.realtime.RelativeTime;

public class TrafficLight extends RealtimeThread {
	
	public int CurrentActiveDirection;
	public boolean[] directions = new boolean[4];
	public int id;
	public ArrayList<Train> waiters = new ArrayList<Train>();
	public AsyncEvent LightChangedEvent = new AsyncEvent();

	
	public TrafficLight(int i) {
		super (
			new PriorityParameters(PriorityScheduler.instance().getMinPriority()), 
			new PeriodicParameters(new RelativeTime((1000 * i),0))
		);
		
		this.id = i;
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
			this.turnAllLightsOff();
			this.directions[i] = true;
			
			// i am going to tell all my listeners that my light has changed
			LightChangedEvent.fire();
			
			
			System.out.println(
					"----------------------------------------\n"+
					"Traffic Light with id:" + 
					this.id + " at the (" + 
					this.getDirectionName(i) + ") Direction is (green)"
			);
					
			waitForNextPeriod();
			
		}
	}
	
	public String getDirectionName(int i) {
		switch(i) {
			case 0:
				return "NorthToSouth";
			case 1:
				return "SouthToNorth";
			case 2:
				return "EastToWest";
			case 3:
				return "WestToEast";
		}
		return "NorthToSouth";
		
	}
	
	public int getDirectionIndex(String name) {
		switch(name) {
			case "NorthToSouth":
				return 0;
			case "SouthToNorth":
				return 1;
			case "EastToWest":
				return 2;
			case "WestToEast":
				return 3;
		}
		return 0;
		
	}


	private void turnAllLightsOff() {
		this.directions[0] = false;
		this.directions[1] = false;
		this.directions[2] = false;
		this.directions[3] = false;
		
	}
	
	/**
	 * 
	 * @param train
	 * @return
	 */
	public boolean isGreen(Train train) {
		
		int index = this.getDirectionIndex(train.direction);
		if(this.directions[index] == true) {
			System.out.println("Well light is green here");
			return true;
		};
		
		LightChangedEvent.addHandler(train.lightChangedHandler);
		System.out.println(
				"----------------------------------------\n"+
				"Traffic Light with id: " + this.id + " is red => Train with id: " + train.id
				+ " is waiting..."
		);
		return false;
	}

}
