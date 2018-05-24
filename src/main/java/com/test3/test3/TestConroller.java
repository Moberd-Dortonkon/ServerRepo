package com.test3.test3;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class TestConroller {
	
	Gson gson;
    @Autowired
    private DataSource dataSource;
	@RequestMapping("/start")
	public String init() throws SQLException
	{
	    Statement stmt=dataSource.getConnection().createStatement();
	    
	    ResultSet rs =stmt.executeQuery("select name from test8 where id=1;");
	    rs.next();
	    String s = rs.getString("name");
		return s;
	}
	@RequestMapping("/")
	public String init2()
	{
	 
		return "hello";
	}
}
