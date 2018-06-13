import java.util.ArrayList;

import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.realtime.PriorityScheduler;
import javax.realtime.RealtimeThread;
import javax.realtime.RelativeTime;

public class Train extends RealtimeThread{
	
	public Track track;
	public String direction;
	public double speed;
	public double coveredDistance;
	public int id;
	public ArrayList<Intersection> lockedIntersections = new ArrayList<Intersection>();
	
	/**
	 * Constructor of the train class
	 * @param id
	 * @param track
	 * @param direction
	 */
	public Train(int id, Track track, String direction, double speed) {
		super (
			new PriorityParameters(PriorityScheduler.instance().getMaxPriority()), 
			new PeriodicParameters(new RelativeTime((long) (1000),0))
		);
		this.id = id;
		this.direction = direction;
		this.track = track;
		this.speed = speed;
	}

	/**
	 * 
	 */
	public void run() {
		System.out.println("----------------------- \n Train with id: " + 
				this.id +" has started and is placed on track: "+ this.track.name + 
				" \n with direction " + this.direction + 
				"This track length is: " + this.track.distance + " Miles \n -------------------------------" 
		);
		this.startMoving();
	}
	
	/**
	 * 
	 */
	public void startMoving() {
		while(true) {
			if(!beforeMoveForward()) {
				return;
			};
		}
			
	}
	
	/**
	 * 
	 */
	private boolean beforeMoveForward() {
		
		if(shouldStop()) {
			return false;
		}
		
		if(trafficLightIsRed()) {
			//We need to pause and wait
			// we may need to be notified when it changes
			//We will log
			//we will now be delayed so we need to handle this accordingly
		}
		
		int indexOfIntersection = nearIntersection();
		if(indexOfIntersection != -1) {
			System.out.println("woops we  are near an int");
			acquireIntersection(indexOfIntersection);
		}
		
		checkReleaseIntersection();
		
		moveForward();
		
		return true;
		
	}
	
	private void checkReleaseIntersection() {
		if(this.track.checkPassedIntersection(this.coveredDistance) != -1) {
			for (int i = 0; i < this.lockedIntersections.size(); i++) {
			    Intersection intersection = this.lockedIntersections.get(i);
			    intersection.release();
			    this.lockedIntersections.remove(i);
			    System.out.println("LOL released");
			}
		}
		
	}

	private boolean trafficLightIsRed() {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean shouldStop() {
		// TODO Auto-generated method stub
		return this.coveredDistance >= this.track.distance;
	}

	/**
	 * 
	 */
	private void moveForward() {
		// TODO Auto-generated method stub
		
		this.coveredDistance += this.speed; 
		waitForNextPeriod();
		
		this.afterMoveForward();
	}
	
	/**
	 * 
	 */
	private void afterMoveForward() {
		System.out.println("----------------------- \n Train with id:" + 
					this.id + " has travelled for "+ this.coveredDistance +" miles"
				+ " \n The remaining distance is: " + this.remainingDistance());
	}
	
	private double remainingDistance() {
		double distance = this.track.distance - this.coveredDistance;
		
		return distance;
	}

	/**
	 * 
	 * @return
	 */
	private int nearIntersection() {
		return this.track.checkNearIntersection(this.coveredDistance);
	}
	
	/**
	 * @param indexOfIntersection 
	 * 
	 */
	private void acquireIntersection(int indexOfIntersection) {
		Intersection j1 = this.track.intersections.get(indexOfIntersection);
		try {
			j1.acquire();
			this.lockedIntersections.add(j1);
			System.out.println("we have locked this junction");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
