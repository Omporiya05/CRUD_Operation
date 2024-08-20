package com.doa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.entity.Student;

public class StudentDao 
{
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String query = null;
	
	public StudentDao(Connection con)
	{
		this.con = con;
	}
	
	public boolean addStudent(Student student)
	{
		boolean result = false;
		
		try 
		{
			query = "insert into student(name,dob,address,qualification,email) values(?,?,?,?,?)";
			ps = con.prepareStatement(query);
			ps.setString(1,student.getName());
			ps.setString(2,student.getDob());
			ps.setString(3,student.getAddress());
			ps.setString(4,student.getQualification());
			ps.setString(5,student.getEmail());
			
			int sval = ps.executeUpdate();
			if(sval == 1)
			{
				result = true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return result; 
	}
	
}
