package com.allianz.simpleproject.service;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.allianz.simpleproject.entities.Student;
import com.allianz.simpleproject.manager.StudentManager;

import com.google.gson.Gson;
/**
 * RestFUL Service to manage a simple Students service.
 * 
 * @author Sadik
 *
 */

@Path("StudentService")
public class StudentService {
	@Context ServletContext context;

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
		
		Gson gson = new Gson();
		
		if (students == null)
			return Response.ok("No entiries available").build();
		
		return Response.ok(gson.toJson(students)).build();
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
