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
	
	public int checkNearIntersection(double coveredDistance) {
		//Loop through all the intersections
		for (int i = 0; i < this.intersections.size(); i++) {
		    Intersection intersection = this.intersections.get(i);
		   double intersectiondistance = this.intersectionDistances.get(i);
		   
		   // Are we there yet?
		   if( coveredDistance + 4  == intersectiondistance) {
			   return i;
		   }
		}
		return -1; //another way of saying false
	}
	
	public int checkPassedIntersection(double coveredDistance) {
		//Loop through all the intersections
		for (int i = 0; i < this.intersections.size(); i++) {
		    Intersection intersection = this.intersections.get(i);
		   double intersectiondistance = this.intersectionDistances.get(i);
		   
		   // Are we there yet?
		   if( coveredDistance == intersectiondistance + 4) {
			  
			   return i;
		   }
		}
		return -1; //another way of saying false
	}


}
