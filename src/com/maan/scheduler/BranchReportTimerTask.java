package com.maan.scheduler;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;



public class BranchReportTimerTask extends TimerTask {
	private static final Logger logger = LogUtil.getLogger(BranchReportTimerTask.class);
	private Timer timer;
	private final static long fONCE_PER_DAY = 1000*60*60*24;
	private final static int fONE_DAY = 1000*60*60*24;
	
	private final static int HOUR = 16;
	private final static int MINUTES = 30;
	
	public static void initTimerTask() {
		TimerTask timerTask = new BranchReportTimerTask();
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, getScheduledTime(), fONCE_PER_DAY);
	}

	/**

	 * Implements TimerTask's abstract run method.

	 */
	public void run(){
		logger.info("Time task run Started");
		BranchReportService service = new BranchReportService();
		service.BranchReportMail();
		logger.info("Time task run Ended");
	}

	private static Date getScheduledTime(){
		Calendar today = new GregorianCalendar();
		Calendar result = new GregorianCalendar(
				today.get(Calendar.YEAR),
				today.get(Calendar.MONTH),
				today.get(Calendar.DATE),
				HOUR,
				MINUTES
		);
		if(today.getTime().compareTo(result.getTime())>0) {
			result.add(Calendar.DATE, fONE_DAY);
		}
		logger.info("Scheduled TIme==>" + result.getTime());
		return result.getTime();
	}

	public Timer getTimer() {
		return this.timer;
	}
}
