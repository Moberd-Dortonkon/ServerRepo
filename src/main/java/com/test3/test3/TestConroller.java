package com.test3.test3;

import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import com.test3.test3.Volonteer;
import com.test3.test3.DisplayVolonteers;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class TestConroller {
	
	Gson gson;
    @Autowired
    private DataSource dataSource;
    
    
    
    
    
    
    @RequestMapping(value="/createGroup",method=RequestMethod.GET)
	public String start(@RequestParam(value="lName",defaultValue="")String lName)
	{
		
     	if(lName!="")
		{
     	
     	while(true) //подумать об отмене
     	{
     		String pass =createPassword();
     		try {
				Statement stmt=dataSource.getConnection().createStatement();
			    stmt.executeUpdate("insert into volonteers values(\'"+pass+"\',\'"+lName+"\',true);");
			    stmt.close();
			    return pass;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
     		
     		
     	}
		}
    
     	return "notcomplete";
	}
    @RequestMapping(value="/createVolonteer",method=RequestMethod.GET)	
    public String createVolonteer(@RequestParam(value="name",defaultValue="")String name,@RequestParam(value="lName",defaultValue="")String key) throws SQLException
	{
    	Statement stmt=dataSource.getConnection().createStatement();
    	stmt.executeUpdate("insert into volonteers values(\'"+key+"\',\'"+name+"\',false);");
    	stmt.close();
        return "complete";
   
	}
    @RequestMapping(value="/display",method=RequestMethod.GET)
	public DisplayVolonteers display(@RequestParam(value="lName",defaultValue="Test")String name) throws SQLException
	{
		HashMap<String,Volonteer>map = new HashMap<String, Volonteer>();
		Statement stmt=dataSource.getConnection().createStatement();
		ResultSet rs=stmt.executeQuery("select name from volonteers where grouppassword=\'"+name+"\' and type=false;");
		while(rs.next())
		{
			map.put(rs.getString("name"),new Volonteer(rs.getString("name"),false,false));
			
		}
		stmt.close();
		return new DisplayVolonteers(map);
	}
    
    
    
	@RequestMapping("/start")
	public String init() throws SQLException
	{
	    Statement stmt=dataSource.getConnection().createStatement();
	    
	    ResultSet rs =stmt.executeQuery("select name from test8 where id=1;");
	    rs.next();
	    String s = rs.getString("name");
	    stmt.close();
		return s;
	}
	@RequestMapping("/")
	public String init2()
	{
	 
		return "hello";
	}
	
	
	
	
	
	
	
	private String createPassword() 
	{
		   SecureRandom secureRandom = new SecureRandom();
	        String s="Aaa";
	        String summ="";
	        for(int i =0;i<s.length();i++)
	        {
	        char c=(char) (s.charAt(i)+secureRandom.nextInt(25));
	         summ=summ+c;

	        }
	        summ+=secureRandom.nextInt(999);
	       return summ;
	}
}
