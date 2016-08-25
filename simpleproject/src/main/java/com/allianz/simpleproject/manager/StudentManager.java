package com.allianz.simpleproject.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.allianz.simpleproject.dao.StudentDao;
import com.allianz.simpleproject.db.DBConnection;
import com.allianz.simpleproject.entities.Student;

public class StudentManager {

	public ArrayList<Student> getStudents() {
		ArrayList<Student> students = null;
		Connection connection = null;
		try {
			DBConnection dbConnection = DBConnection.getInstance();
			connection = dbConnection.getConnection();
			StudentDao dao = new StudentDao();
			students = dao.getStudents(connection);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					if (!connection.isClosed())
						connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return students;
	}

}
