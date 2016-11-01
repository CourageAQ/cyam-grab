package com.jiyigrab.cyam.service;

import com.jiyigrab.cyam.dao.CoalPriceDao;
import com.jiyigrab.cyam.model.CoalPrice;

public class CoalPriceService {
	CoalPriceDao coalPriceDao = new CoalPriceDao();
	public void saveCoal(CoalPrice coalPrice){
		coalPriceDao.saveCoal(coalPrice);
	}
	public String selectCoal(String date){
		return coalPriceDao.selectCoal(date);
	}
}
