package com.service.events;

import java.util.EventListener;

public interface WatchDogTimerEventListener extends EventListener {
	
	public abstract void onChangeCyclesAlert(WatchDogTimerEventObject args);

	public abstract void onChangeCyclesExceeded(WatchDogTimerEventObject args);
	
	public abstract void onChangeStopped(WatchDogTimerEventObject args);
	
}
