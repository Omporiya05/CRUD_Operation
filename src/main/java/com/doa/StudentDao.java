package com.doa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
	
	public List<Student> getAllStudentDetails()
	{
		List<Student> list = new ArrayList<>();
		Student s = null;
		
		try 
		{
			query = "select * from student order by id asc";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				s = new Student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setDob(rs.getString("dob"));
				s.setAddress(rs.getString("address"));
				s.setQualification(rs.getString("qualification"));
				s.setEmail(rs.getString("email"));
				
				list.add(s);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return list;
	}
	
	public Student getStudentById(int id)
	{
		Student s = null;
		
		try 
		{
			query = "select * from student where id = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				s = new Student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setDob(rs.getString("dob"));
				s.setAddress(rs.getString("address"));
				s.setQualification(rs.getString("qualification"));
				s.setEmail(rs.getString("email"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return s;
	}
	
	public boolean updateStudentDetails(Student student)
	{
		boolean result = false;
		
		try 
		{
			query = "update student set name = ?,dob = ?,address = ?,qualification = ?,email = ? where id = ?";
			ps =  con.prepareStatement(query);
			ps.setString(1,student.getName());
			ps.setString(2,student.getDob());
			ps.setString(3,student.getAddress());
			ps.setString(4,student.getQualification());
			ps.setString(5,student.getEmail());
			ps.setInt(6, student.getId());
			
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
