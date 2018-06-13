
import java.util.concurrent.Semaphore;

public class Intersection extends Semaphore {
	
	public String name;
	
	public Intersection(String name) {
		super(1);
		this.name = name;
	}
}
