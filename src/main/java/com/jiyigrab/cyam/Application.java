package com.jiyigrab.cyam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.jiyigrab.cyam.controller.CoalPriceController;


public class Application {
	public static void runApplication(){
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd E HH");
		String[] string = simpleDateFormat.format(date).split(" ");
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				if(string[1].equals("星期三") && Integer.valueOf(string[2])>=15 && Integer.valueOf(string[2])<=16) {
					System.out.println("开始执行");
					new CoalPriceController().getHtml(string[0]);
				}else {
					System.out.println("今天不是星期三");
				}
				new CoalPriceController().getHtml(string[0]);
			}
		};
		timer.schedule(task, date, 1000*30);
    }
}
