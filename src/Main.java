import java.util.ArrayList;

public class Main {

	public ArrayList<Track> tracks = new ArrayList<Track>();
	public ArrayList<TrafficLight> trafficLights = new ArrayList<TrafficLight>();
	public ArrayList<Intersection> intersections = new ArrayList<Intersection>();
	
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
		this.createTracks();
		this.createIntersections();
		this.createTrafficLights();
		this.createTrains(this.tracks, this.trafficLights);
		this.init();
	}
	
	private void createIntersections() {
		this.intersections.add(new Intersection("Intersection-A"));
		this.intersections.add(new Intersection("Intersection-B"));
		this.intersections.add(new Intersection("Intersection-C"));
		this.intersections.add(new Intersection("Intersection-D"));
		this.intersections.add(new Intersection("Intersection-E"));
		this.intersections.add(new Intersection("Intersection-F"));
	}

	private void init() {
		// I have the trains and the tracks and the traffic lights
		// Now i want to position the trains and ask them to start
		// moving
		
	}

	/**
	 * create all the tracks
	 * and add them to a list
	 */
	private void createTracks() {
		this.tracks.add(new Track("EastWestA", 100.00));
		this.tracks.add(new Track("EastWestB", 100.00));
		this.tracks.add(new Track("NorthSouthA", 80.00));
		this.tracks.add(new Track("NorthSouthB", 80.00));
		this.tracks.add(new Track("NorthSouthC", 80.00));
	}
	
	/**
	 * Create 4 traffic lights and 
	 * store them in an array
	 */
	private void createTrafficLights() {
		this.trafficLights.add(new TrafficLight(1, this.intersections.get(0)));
		this.trafficLights.add(new TrafficLight(2, this.intersections.get(1)));
		this.trafficLights.add(new TrafficLight(3, this.intersections.get(2)));
		this.trafficLights.add(new TrafficLight(4, this.intersections.get(3)));
		this.trafficLights.add(new TrafficLight(5, this.intersections.get(4)));
		this.trafficLights.add(new TrafficLight(6, this.intersections.get(5)));
	}
	
	/**
	 * 
	 * @param tracks
	 * @param trafficLights
	 */
	private void createTrains(ArrayList<Track> tracks, ArrayList<TrafficLight> trafficLights) {
		// We can stimulate many trains 
		Train train = new Train(1, this.tracks.get(0), "EastToWest");
		train.start();
	}

	
	
	

}
