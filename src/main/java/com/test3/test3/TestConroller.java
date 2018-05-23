package com.test3.test3;

import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestConroller {
    @Autowired
    private DataSource dataSource;
	@RequestMapping("/")
	public String init() throws SQLException
	{
	    Statement stmt=dataSource.getConnection().createStatement();
	    stmt.executeUpdate("CREATE TABLE ticks (tick timestamp)");
		return "hello";
	}
}
