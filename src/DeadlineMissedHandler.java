import javax.realtime.AsyncEventHandler;

public class DeadlineMissedHandler extends AsyncEventHandler{
	
	private Train train;

	public DeadlineMissedHandler(Train train) {
		this.train = train;

	}
	
	public void handleAsyncEvent() {
		// handle this event
		if(this.train.speed <6)
		this.train.speed +=2;
		
	}
}
