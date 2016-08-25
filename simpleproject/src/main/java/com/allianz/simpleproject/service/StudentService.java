package com.allianz.simpleproject.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.allianz.simpleproject.db.DBConnection;
import com.allianz.simpleproject.entities.Student;
import com.allianz.simpleproject.manager.StudentManager;

/**
 * RestFUL Service to manage a simple Students service.
 * 
 * @author Sadik
 *
 */

@Path("StudentService")
public class StudentService {

	@GET
	public String sayPlainTextHello() {
		return "Hello Jersey";
	}

	@GET
	@Path("students")
	@Produces( MediaType.APPLICATION_JSON )
	public Response getStudents() {
		List<Student> students = null;
		StudentManager manager = new StudentManager();
		students = manager.getStudents();
		if (students == null)
			return Response.ok("No entiries available").build();
		GenericEntity<List<Student>> entity = new GenericEntity<List<Student>>(students) {};
		return Response.ok(entity).build();
	}

	/*
	 * @GET
	 * 
	 * @Path("students/{number}")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public Student
	 * getStudentByMatricelNumber(){ return null; }
	 */
}
