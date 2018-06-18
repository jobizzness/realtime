import javax.realtime.AsyncEventHandler;

public class LightChangedHandler extends AsyncEventHandler{
	
	private Train train;

	public LightChangedHandler(Train train) {
		this.train = train;

	}

	public void handleAsyncEvent() {
		this.train.beforeMoveForward(); //tell train light is green
		
	}
}
