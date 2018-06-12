import java.util.ArrayList;

public class Track {

	public String name;
	public double distance;
	public ArrayList<Intersection> intersections;
	public ArrayList<Double> intersectionDistances;
	
	/**
	 * Our constructor
	 * @param name
	 */
	public Track(String name, double distance, ArrayList<Intersection> intersections) {
		this.name = name;
		this.distance = distance;
		this.intersections = intersections;
	}

	public void setIntersectionDistance(ArrayList<Double> intersectionDistances) {
		this.intersectionDistances = intersectionDistances;
	}
	
	public boolean checkNearIntersection(double coveredDistance) {
		// TODO Auto-generated method stub
		return false;
	}


}
