import java.util.ArrayList;
import java.util.Date;

import javax.realtime.AsyncEvent;
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
	public LightChangedHandler lightChangedHandler;
	public ArrayList<Intersection> lockedIntersections = new ArrayList<Intersection>();
	public AsyncEvent DeadlineMissed = new AsyncEvent();
	private long startTime;
	
	/**
	 * Constructor of the train class
	 * @param id
	 * @param track
	 * @param direction
	 * @param speed in miles per second
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
		this.lightChangedHandler = new LightChangedHandler(this);
		this.DeadlineMissed.addHandler(new DeadlineMissedHandler(this));
		this.startTimer();
		this.start();
	}

	/**
	 * 
	 */
	public void run() {
		System.out.println("----------------------- \n Train with id: " + 
				this.id +" has started and is placed on track: "+ this.track.name + 
				" \n with direction " + this.direction + 
				" This track length is: " + this.track.distance + " Miles \n -------------------------------" 
		);
		
		this.startMoving();
	}
	
	private void startTimer() {
		this.startTime = System.currentTimeMillis();
		
	}

	/**
	 * 
	 */
	public void startMoving() {
		while(true) {
			if(!beforeMoveForward()) {
				return;
			};
			
			waitForNextPeriod();
		}
			
	}
	
	/**
	 * 
	 */
	public boolean beforeMoveForward() {
		
		if(shouldStop()) {
			System.out.println("----------------------- \n Train with id: " + 
					this.id +" has reached its station... ");
			
			this.deschedulePeriodic();
			return false;
			
		}
		
		if(trafficLightIsGreen()) {
			this.checkMissedDealine();
			lightIsGreen();
		}
		
		return true;
		
	}
	
	/**
	 * Check if train is running late,
	 * if so we fire out an event
	 */
	private void checkMissedDealine() {
		long elapsedTime = (new Date()).getTime() - startTime;
		
		if(2 * (elapsedTime/ 1000) > this.coveredDistance) {
			System.out.println("Train with id:"+ this.id +" is under schedule => Deadline Missed Event fired...");
			DeadlineMissed.fire();
		}else {
			this.speed = 2;
		}
		
		
	}
	
	/**
	 * When the light is green, we move forward
	 */
	public void lightIsGreen() {
		int indexOfIntersection = nearIntersection();
		if(indexOfIntersection != -1) {
			System.out.println("----------------------- \n Train with id: " + 
					this.id +" has reached an intersection... ");
			acquireIntersection(indexOfIntersection);
		}
		
		checkReleaseIntersection();
		
		moveForward();
		
	}

	private void checkReleaseIntersection() {
		if(this.track.checkPassedIntersection(this.coveredDistance) != -1) {
			for (int i = 0; i < this.lockedIntersections.size(); i++) {
			    Intersection intersection = this.lockedIntersections.get(i);
			    intersection.release();
			    System.out.println("------------\n Intersection ("+
			    		intersection.name +") is now Free...");
			    this.lockedIntersections.remove(i);
			    
			}
		}
		
	}

	private boolean trafficLightIsGreen() {

		int indexOfIntersection = nearIntersection();
		
		if(indexOfIntersection == -1) {
			return true;
		}
		
		
		Intersection intersection = this.track.intersections.get(indexOfIntersection);
		return intersection.light().isGreen(this);
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
