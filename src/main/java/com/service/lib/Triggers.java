package com.service.lib;


public class Triggers extends Thread {
	
	private WatchDogTimer watchDog = null;
	
	public Triggers (WatchDogTimer wdt) {
		this.watchDog = wdt;
	}
	
	@Override
	public void run() {

		watchDog.MilisecondsPlus();
		watchDog.WatchDogProcess();
	}
	
	
}
