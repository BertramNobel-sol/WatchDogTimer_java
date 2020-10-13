package main;

import java.text.SimpleDateFormat;

import com.service.events.WatchDogTimerEventListener;
import com.service.events.WatchDogTimerEventObject;
import com.service.lib.WatchDogTimer;

public class WatchDogEventManager implements WatchDogTimerEventListener  {

	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");	
	
	@Override
	public void onChangeCyclesAlert(WatchDogTimerEventObject args) {
		// TODO Auto-generated method stub
		
		WatchDogTimer watchdogtimer = args.getWatchDogTimer();
		System.out.println(sdf.format(watchdogtimer.getRegisterDate()) + " - " + watchdogtimer.getClassName() + " - Event_WatchDog_TimeAlert - "  + " Clock time (sec) " + watchdogtimer.getTotal_time_counter());
	}

	@Override
	public void onChangeCyclesExceeded(WatchDogTimerEventObject args) {
		// TODO Auto-generated method stub
		
		WatchDogTimer watchdogtimer = args.getWatchDogTimer();
		System.out.println(sdf.format(watchdogtimer.getRegisterDate()) + " - " +watchdogtimer.getClassName() + " - WatchDog_TimeExceeded - "  + " Clock time (sec) " + watchdogtimer.getTotal_time_counter());
	
		watchdogtimer.reset();
	}

	@Override
	public void onChangeStopped(WatchDogTimerEventObject args) {
		// TODO Auto-generated method stub
		WatchDogTimer watchdogtimer = args.getWatchDogTimer();
		System.out.println(sdf.format(watchdogtimer.getRegisterDate()) + " - " + watchdogtimer.getClassName() + " - WatchDog_stopped - "  + " Clock time (sec) " + watchdogtimer.getTotal_time_counter());
	
		
		
	}

	
	
	
}
