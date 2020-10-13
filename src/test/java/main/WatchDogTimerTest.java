package main;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.service.lib.WatchDogTimer;

public class WatchDogTimerTest {
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long counter =0;
		WatchDogTimer wdt = new WatchDogTimer("WatchDogTimerTest",5,60);
		
		wdt.addWatchDogTimerEventListener(new WatchDogEventManager());
		
		wdt.start();
		
		while(counter < 13) {
			try {
				Thread.sleep(1000);
				System.out.println(sdf.format(new Date()) + " - " + "Task Principal - (sec) " + counter);
				counter = counter+1;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		wdt.terminate();
		
	}

}
