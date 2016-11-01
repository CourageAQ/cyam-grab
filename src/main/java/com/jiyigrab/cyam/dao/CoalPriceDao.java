package com.jiyigrab.cyam.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.jiyigrab.cyam.jdbc.JDBCTools;
import com.jiyigrab.cyam.model.CoalPrice;


public class CoalPriceDao {
	PreparedStatement pstmt = null;
	public void saveCoal(CoalPrice coalPrice){
		String sql = "insert into coalprice (heat,nowpeace,lastpeace,Degree,huanbi,lasttime,tongbi,date) values (?,?,?,?,?,?,?,?)";
		try {
			pstmt = (PreparedStatement) JDBCTools.getConn().prepareStatement(sql);
			pstmt.setString(1, coalPrice.getHeat());
			pstmt.setString(2, coalPrice.getNowpeace());
			pstmt.setString(3, coalPrice.getLastpeace());
			pstmt.setString(4, coalPrice.getDegree());
			pstmt.setString(5, coalPrice.getHuanbi());
			pstmt.setString(6, coalPrice.getLasttime());
			pstmt.setString(7, coalPrice.getTongbi());
			pstmt.setString(8, coalPrice.getDate());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}