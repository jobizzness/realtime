
import java.util.concurrent.Semaphore;

public class Intersection extends Semaphore {
	
	public String name;
	public TrafficLight light;
	
	public Intersection(String name, TrafficLight light) {
		super(1);
		this.name = name;
		this.light = light;
	}
	
	public TrafficLight light() {
		return light;
	}
}
