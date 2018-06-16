import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public ArrayList<Track> tracks = new ArrayList<Track>();
	public ArrayList<TrafficLight> trafficLights = new ArrayList<TrafficLight>();
	public ArrayList<Intersection> intersections = new ArrayList<Intersection>();
	public ArrayList<Train> trains = new ArrayList<Train>();
	
	/**
	 * Instantiate the class and call boot.
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("The App is starting.....");
		(new Main()).boot();
	}
	
	/**
	 * This method starts the application,
	 * creates the tracks, trains and traffic lights
	 */
	public void boot() {
		this.createTrafficLights();
		this.createIntersections();
		this.createTracks();
		this.createTrains(this.tracks, this.trafficLights);
		this.init();
	}
	
	private void createIntersections() {
		this.intersections.add(new Intersection("Intersection-A", this.trafficLights.get(0)));
		this.intersections.add(new Intersection("Intersection-B", this.trafficLights.get(1)));
		this.intersections.add(new Intersection("Intersection-C", this.trafficLights.get(2)));
		this.intersections.add(new Intersection("Intersection-D", this.trafficLights.get(3)));
		this.intersections.add(new Intersection("Intersection-E", this.trafficLights.get(4)));
		this.intersections.add(new Intersection("Intersection-F", this.trafficLights.get(5)));
	}

	/**
	 * We are creating a track and placing the intersections
	 * intesections are in a list, so this means a track has many intersections
	 */
	private void createTracks() {
		
		//Track East West A
		// And its intersections
		ArrayList<Intersection> intersectionsEastWestA = new ArrayList<Intersection>();
		intersectionsEastWestA.add(this.intersections.get(0));
		intersectionsEastWestA.add(this.intersections.get(1));
		intersectionsEastWestA.add(this.intersections.get(2));
		Track trackEastWestA = new Track("EastWestA", 100.00, intersectionsEastWestA);
		trackEastWestA.setIntersectionDistance(
				new ArrayList<Double>(Arrays.asList(20.0, 40.0, 60.0))
		);
		this.tracks.add(trackEastWestA);
		
		
		//Track East West B
		// And its intersections
		ArrayList<Intersection> intersectionsEastWestB = new ArrayList<Intersection>();
		intersectionsEastWestB.add(this.intersections.get(3));
		intersectionsEastWestB.add(this.intersections.get(4));
		intersectionsEastWestB.add(this.intersections.get(5));
		Track trackEastWestB = new Track("EastWestB", 100.00, intersectionsEastWestB);
		trackEastWestB.setIntersectionDistance(
				new ArrayList<Double>(Arrays.asList(20.0, 40.0, 60.0))
		);
		this.tracks.add(trackEastWestB);
		
		//Track north South A
		// And its intersections
		ArrayList<Intersection> intersectionsNorthSouthA = new ArrayList<Intersection>();
		intersectionsNorthSouthA.add(this.intersections.get(2));
		intersectionsNorthSouthA.add(this.intersections.get(5));
		Track trackNorthSouthA = new Track("NorthSouthA", 100.00, intersectionsNorthSouthA);
		trackNorthSouthA.setIntersectionDistance(
				new ArrayList<Double>(Arrays.asList(20.0, 60.0))
		);
		this.tracks.add(trackNorthSouthA);
		
		//Track north South B
		// And its intersections
		ArrayList<Intersection> intersectionsNorthSouthB = new ArrayList<Intersection>();
		intersectionsNorthSouthB.add(this.intersections.get(1));
		intersectionsNorthSouthB.add(this.intersections.get(4));
		Track trackNorthSouthB = new Track("NorthSouthB", 100.00, intersectionsNorthSouthB);
		trackNorthSouthB.setIntersectionDistance(
				new ArrayList<Double>(Arrays.asList(20.0, 60.0))
		);
		this.tracks.add(trackNorthSouthB);
		
		//Track north South C
		// And its intersections
		ArrayList<Intersection> intersectionsNorthSouthC = new ArrayList<Intersection>();
		intersectionsNorthSouthC.add(this.intersections.get(0));
		intersectionsNorthSouthC.add(this.intersections.get(3));
		Track trackNorthSouthC = new Track("NorthSouthC", 100.00, intersectionsNorthSouthC);
		trackNorthSouthC.setIntersectionDistance(
				new ArrayList<Double>(Arrays.asList(20.0, 60.0))
		);
		this.tracks.add(trackNorthSouthC);
	}
	
	/**
	 * Create 4 traffic lights and 
	 * store them in an array
	 */
	private void createTrafficLights() {
		this.trafficLights.add(new TrafficLight(1));
		this.trafficLights.add(new TrafficLight(2));
		this.trafficLights.add(new TrafficLight(3));
		this.trafficLights.add(new TrafficLight(4));
		this.trafficLights.add(new TrafficLight(5));
		this.trafficLights.add(new TrafficLight(6));
	}
	
	/**
	 * 
	 * @param tracks
	 * @param trafficLights
	 */
	private void createTrains(ArrayList<Track> tracks, ArrayList<TrafficLight> trafficLights) {
		// We can stimulate many trains 
		// 2 miles per second
		this.trains.add(new Train(1, this.tracks.get(0), "EastToWest", 2));
	}
	
	/**
	 * Make the trains start moving
	 * we can release one every 20 seconds
	 */
	private void init() {
		this.trains.get(0).start();
	}

	
	
	

}
