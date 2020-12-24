package com.spring.testaaa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@RequestMapping(value = "bbb.bo")
	public String home(Model model, @RequestParam(value="no", required=false, defaultValue="1") int no ) {
		Connection con =null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		AaaVO  avo=null;
		int num = no;
		System.out.println(num);
		try {
			String driver ="oracle.jdbc.driver.OracleDriver";
			String url ="jdbc:oracle:thin:@127.0.0.1:1521:orcl";
			
			Class.forName(driver);
			con =DriverManager.getConnection(driver);
			String sql = "select * from aaa where no=?";
			System.out.println(sql);

			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs= pstmt.executeQuery();
			
			rs.next();
			avo = new AaaVO();
			avo.setNo(rs.getInt("no"));
			avo.setName(rs.getString("name"));
			avo.setAge(rs.getInt("age"));
			avo.setAddr(rs.getString("addr"));
			avo.setHobby(rs.getString("hobby"));
			
			model.addAttribute("avo", avo);
			
		}catch(Exception e) {
			e.getMessage();
		}
		return "bbb";
	}
	
}
