package com.service.events;

import java.util.EventObject;

import com.service.lib.WatchDogTimer;

public class WatchDogTimerEventObject  extends EventObject {

	WatchDogTimer  watchdogtimer = new WatchDogTimer();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public WatchDogTimerEventObject(WatchDogTimer source) {
		super(source);
		// TODO Auto-generated constructor stub
		this.watchdogtimer = source;
	}
	
	
	public WatchDogTimer getWatchDogTimer() {
		return watchdogtimer;
	}

}
