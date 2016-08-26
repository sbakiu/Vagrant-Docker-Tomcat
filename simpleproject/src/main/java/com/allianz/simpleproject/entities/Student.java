package com.allianz.simpleproject.entities;

public class Student {
	private int enrolmentNumber;
	private String name;
	private String surname;
	
	
	public Student(int enrolmentNumber, String name, String surname) {
		this.enrolmentNumber = enrolmentNumber;
		this.name = name;
		this.surname = surname;
	}

	public int getEnrolmentNumber() {
		return enrolmentNumber;
	}
	
	public void setMatriculation(int enrolmentNumber) {
		this.enrolmentNumber = enrolmentNumber;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
}
