import javax.realtime.RealtimeThread;

public class Train extends RealtimeThread{
	
	public Track track;
	public String direction;
	public int speed;
	public double coveredDistance;
	public int id;
	
	public Train(int id, Track track, String direction) {
		this.id = id;
		this.direction = direction;
		this.track = track;
	}

	public void run() {
		System.out.println("----------------------- \n Train with id: " + 
				this.id +" has started and is placed on track: "+ this.track.name + 
				" \n with direction " + this.direction + 
				"This track length is: " + this.track.distance + " Miles \n -------------------------------" 
		);
		this.startMoving();
	}
	
	public void startMoving() {
		while (true) {
			if(nearIntersection()) {
				acquireIntersection(); // This will pause the train if intersection is busy
			}
			
			moveForward();
			
			// Move forward
		}
	}

	private void moveForward() {
		// TODO Auto-generated method stub
		
	}

	private boolean nearIntersection() {
		// TODO Auto-generated method stub
		return false;
	}

	private void acquireIntersection() {
		// TODO Auto-generated method stub
		
	}
}
