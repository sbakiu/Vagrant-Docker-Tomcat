package com.allianz.simpleproject.entities;

public class Student {
	private int matriculation;
	private String name;
	private String surname;
	
	
	public Student(int matriculation, String name, String surname) {
		this.matriculation = matriculation;
		this.name = name;
		this.surname = surname;
	}

	public int getMatriculation() {
		return matriculation;
	}
	
	public void setMatriculation(int matriculation) {
		this.matriculation = matriculation;
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
