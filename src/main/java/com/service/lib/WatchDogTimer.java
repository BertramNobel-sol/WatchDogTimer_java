package com.service.lib;

import java.util.ArrayList;
import java.util.Date;
import java.util.ListIterator;

import com.service.events.WatchDogTimerEventListener;
import com.service.events.WatchDogTimerEventObject;


public class WatchDogTimer extends Thread {

	private long MILLISECOND = 1000;
	
	private long ALERT_TIME = 0;
	
	private long TIME_SIZE = 0;
	
	private long time_counter=0;
	
	private long milisec_counter=0;
	
	private long total_time_counter=0;

	private String className=""; 
	
	private boolean enabled = true;

	private Date registerDate = null;
	
	@SuppressWarnings("unused")
	private Date lastDate = null;
	

	@SuppressWarnings("unused")
	private boolean trigger = false;
	
	
	private ArrayList<WatchDogTimerEventListener> listeners = new ArrayList<WatchDogTimerEventListener>();

	
	public WatchDogTimer() {
		
	}
	
	public WatchDogTimer(String className,long check_time,long top_time) {
		this.ALERT_TIME = check_time;
		this.TIME_SIZE = top_time;
		this.className = className;
		this.time_counter = this.TIME_SIZE;
	}
	
	
	public void addWatchDogTimerEventListener (WatchDogTimerEventListener listener){
	    // agregamos el manejador a nuestra lista
	    listeners.add(listener);
	}

	@Override
	public void run() {
		
		 try {
				do{
					this.registerDate = new Date();
					Thread.sleep(1);
					
					//Triggers trg = new Triggers(this);
					//trg.run();
					if (this.trigger) { WatchDogProcess(); }
					this.lastDate = new Date();
					MilisecondsPlus();
				} while (this.enabled);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public void MilisecondsPlus() {
		
		milisec_counter = milisec_counter + (this.lastDate.getTime() - this.registerDate.getTime());
		 if (milisec_counter>=MILLISECOND) {
			 trigger = true;
			 time_counter = time_counter - 1;
			 total_time_counter = total_time_counter + 1;
			 milisec_counter =milisec_counter - MILLISECOND;
		}
			 
	}
	
	
//	public void MilisecondsPlus() {
//		 milisec_counter=milisec_counter+1;
//		//milisec_counter = milisec_counter + (this.lastDate.getTime() - this.registerDate.getTime());
//		 if (milisec_counter>=MILLISECOND) {
//			 time_counter = time_counter - 1;
//			 total_time_counter = total_time_counter + 1;
//			 milisec_counter =0;
//			 trigger = true;
//		}
//			 
//	}

	
	public void WatchDogProcess() {
			
		//this.registerDate = new Date();
		
		// alert each alert time
		if ((time_counter != TIME_SIZE)  && (time_counter % ALERT_TIME) == 0) {
			
			triggerChangeCyclesAlert();
		}
		
		// alert each time size
		if ((time_counter == 0)) {
			
			triggerChangeCyclesExceeded();
			time_counter = TIME_SIZE;
		}
		
		this.trigger = false;
	}
	
	
	private void triggerChangeCyclesAlert() {
		
		ListIterator<WatchDogTimerEventListener> li = listeners.listIterator();
			
		   while (li.hasNext()) {
		   	   ((WatchDogTimerEventListener)li.next()).onChangeCyclesAlert(new WatchDogTimerEventObject(this));  
		   }
	}
	
	private void triggerChangeCyclesExceeded() {
		ListIterator<WatchDogTimerEventListener> li = listeners.listIterator();
			
		   while (li.hasNext()) {
		   	   ((WatchDogTimerEventListener)li.next()).onChangeCyclesExceeded(new WatchDogTimerEventObject(this));  
		   }
	}
	
	private void triggerChangeStopped() {
		ListIterator<WatchDogTimerEventListener> li = listeners.listIterator();
			
		   while (li.hasNext()) {
		   	   ((WatchDogTimerEventListener)li.next()).onChangeStopped(new WatchDogTimerEventObject(this));  
		   }
	}
	
	
	public void reset() {
		//this.time_counter = 0;
	
		this.time_counter = TIME_SIZE;
		this.total_time_counter=0;
	}
	
	public void terminate() {
		this.enabled = false;
		
		triggerChangeStopped();
	}
	
	public long get_time_size() {
		return TIME_SIZE;
	}

	public void set_time_size(long time_size) {
		TIME_SIZE = time_size;
	}

	public long get_time_alert() {
		return ALERT_TIME;
	}

	public void set_time_alert(long alert_time) {
		ALERT_TIME = alert_time;
	}
	
	public long getTime_counter() {
		return time_counter;
	}

	public void setTime_counter(long time_counter) {
		this.time_counter = time_counter;
	}

	public long getTotal_time_counter() {
		return total_time_counter;
	}

	public void setTotal_time_counter(long total_time_counter) {
		this.total_time_counter = total_time_counter;
	}
		
	public long getMilisec_counter() {
		return milisec_counter;
	}

	public void setMilisec_counter(long milisec_counter) {
		this.milisec_counter = milisec_counter;
	}
	
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	


	
	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	
}


