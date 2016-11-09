package com.jiyigrab.cyam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.jiyigrab.cyam.controller.CoalPriceController;


public class Application {
	public void runApplication(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				String[] string = simpleDateFormat.format(new Date()).split(" ");
					System.out.println(string[1] + "开始执行,每隔三十秒执行一次");
					new CoalPriceController().getHtml(string[0]);
			}
		};
		timer.schedule(task, new Date(), 1000*30);
    }
}
