package com.example.entity;

public class Student {
	
	private int id;
	private String name;
	private int marks;
	
	
	
	public Student(int i , String nString , int m)
	{
		this.id  = i;
		this.name = nString;
		this.marks = m;
	}
	
	public int getMarks()
	{
		return marks;
	}
	public int getId()
	{
		return id;
	}
	public String getName()
	{
		return name;
	}
	
	public void setMarks(int m)
	{
		this.marks = m;
	}
	public void setId(int m)
	{
		this.id = m;
	}
	public void setName(String m)
	{
		this.name = m;
	}
	

}
