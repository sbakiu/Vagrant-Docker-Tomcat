package com.allianz.simpleproject.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.allianz.simpleproject.entities.Student;
import com.mysql.cj.jdbc.PreparedStatement;

public class StudentDao {

	public ArrayList<Student> getStudents(Connection connection) {

		if (connection == null)
			return null;
		
		ArrayList<Student> students = new ArrayList<Student>();

		try {

			PreparedStatement ps = (PreparedStatement) connection
					.prepareStatement("SELECT enrolment_number, name, surname FROM students ORDER BY enrolment_number");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Student student = new Student(rs.getInt("enrolment_number"), rs.getString("name"), rs.getString("surname"));
				students.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return students;
	}
}
