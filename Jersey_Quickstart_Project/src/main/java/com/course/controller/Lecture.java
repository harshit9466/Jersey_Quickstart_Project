package com.course.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.course.beans.LectureBeans;
import com.course.dao.CourseDao;
import com.course.dao.LectureDao;
import com.course.util.Config;
import com.course.util.LoggerUtil;
import com.course.util.ResponseDetails;
import com.course.util.ResponseWrapper;
import com.course.util.UtilityClass;

@Path("/api")
public class Lecture 
{
	@SuppressWarnings("unchecked")
	@POST
	@Path("/lecture/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addDynamicRole(InputStream incomingData)
	{
		LectureDao lectureDao = new LectureDao();
		StringBuilder jsonData = new StringBuilder();

		try
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line = null;
			while((line = in.readLine()) != null)
			{
				jsonData.append(line);
			}
			LoggerUtil.log("/course/add JSON:" + jsonData);
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(jsonData.toString());
			long courseId = (long) json.get("courseId");
			String lectureName = String.valueOf(json.get("name"));
			String notes = String.valueOf(json.get("notes"));
			String videoName = String.valueOf(json.get("videoName"));
			
			if(UtilityClass.isNull(courseId))
			{
				JSONObject obj = new JSONObject();
				obj.put("message", UtilityClass.generateRequiredFieldMessage("courseId"));
				obj.put("status", ResponseDetails.BAD_REQUEST_HttpStatusCode);
				LoggerUtil.log("\n" + "Response: " + obj.toString());
				return Response.status(ResponseDetails.BAD_REQUEST_HttpStatusCode).entity(obj.toString()).build();
			}
			
			if(UtilityClass.isNull(lectureName))
			{
				JSONObject obj = new JSONObject();
				obj.put("message", UtilityClass.generateRequiredFieldMessage("lectureName"));
				obj.put("status", ResponseDetails.BAD_REQUEST_HttpStatusCode);
				LoggerUtil.log("\n" + "Response: " + obj.toString());
				return Response.status(ResponseDetails.BAD_REQUEST_HttpStatusCode).entity(obj.toString()).build();
			}
			
			if(UtilityClass.isNull(notes))
			{
				JSONObject obj = new JSONObject();
				obj.put("message", UtilityClass.generateRequiredFieldMessage("notes"));
				obj.put("status", ResponseDetails.BAD_REQUEST_HttpStatusCode);
				LoggerUtil.log("\n" + "Response: " + obj.toString());
				return Response.status(ResponseDetails.BAD_REQUEST_HttpStatusCode).entity(obj.toString()).build();
			}
			
			if(UtilityClass.isNull(videoName))
			{
				JSONObject obj = new JSONObject();
				obj.put("message", UtilityClass.generateRequiredFieldMessage("videoName"));
				obj.put("status", ResponseDetails.BAD_REQUEST_HttpStatusCode);
				LoggerUtil.log("\n" + "Response: " + obj.toString());
				return Response.status(ResponseDetails.BAD_REQUEST_HttpStatusCode).entity(obj.toString()).build();
			}

			LectureBeans lectureBeans = new LectureBeans();
			lectureBeans.setCourseId(courseId);
			lectureBeans.setLectureName(lectureName);
			lectureBeans.setNotes(notes);
			lectureBeans.setVideoName(videoName);

			ResponseWrapper<?> DBResponse = lectureDao.addLecture(lectureBeans);

			if(DBResponse.getSuccess() >= 1)
			{
				Map<String,Object> responseObj = new HashMap<>();
				responseObj.put("status", DBResponse.getStatus());
				responseObj.put("message", DBResponse.getMessage());
				LoggerUtil.log("\n" + "Response: " + responseObj.toString());
				return Response.status(DBResponse.getStatus()).entity(responseObj).build();
			}
			else
			{
				Map<String,Object> responseObj = new HashMap<>();
				responseObj.put("status", DBResponse.getStatus());
				responseObj.put("message", DBResponse.getMessage());
				if(Config.getProperty("ENVIRONMENT").equals("DEV"))
				{
					responseObj.put("errorMessage", DBResponse.getData());
				}
				LoggerUtil.log("\n" + "Response: " + responseObj.toString());
				return Response.status(DBResponse.getStatus()).entity(responseObj).build();
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			Map<String,Object> responseObj = new HashMap<>();
			responseObj.put("status", ResponseDetails.INTERNAL_SERVER_ERROR_HttpStatusCode);
			responseObj.put("message", ResponseDetails.INTERNAL_SERVER_ERROR_ResponseMessage);
			return Response.status(ResponseDetails.INTERNAL_SERVER_ERROR_HttpStatusCode).entity(responseObj).build();
		}
	}
	
	@Path("/lecture/list")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response listLecture(InputStream incomingData)
	{
		LectureDao lectureDao = new LectureDao();
		Map<String,Object> responseObj = new HashMap<>();
		StringBuilder jsonData = new StringBuilder();

		try
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line = null;
			while((line = in.readLine()) != null)
			{
				jsonData.append(line);
			}
			LoggerUtil.log("/course/add JSON:" + jsonData);
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(jsonData.toString());
			long courseId = (long) json.get("courseId");
			
			if(UtilityClass.isNull(courseId))
			{
				responseObj.put("message", UtilityClass.generateRequiredFieldMessage("courseId"));
				responseObj.put("status", ResponseDetails.BAD_REQUEST_HttpStatusCode);
				LoggerUtil.log("\n" + "Response: " + responseObj.toString());
				return Response.status(ResponseDetails.BAD_REQUEST_HttpStatusCode).entity(responseObj.toString()).build();
			}
			
			LectureBeans lectureBeans = new LectureBeans();
			lectureBeans.setCourseId(courseId);
			
				ResponseWrapper<?> DBResponse = lectureDao.listLecture(lectureBeans);
				if(DBResponse.getSuccess() == 1)
				{
					responseObj.put("status", DBResponse.getStatus());
					responseObj.put("message", DBResponse.getMessage());
					responseObj.put("data", DBResponse.getData());
					LoggerUtil.log("\n" + "Response: " + responseObj.toString());
					return Response.status(DBResponse.getStatus()).entity(responseObj).build();
				}
				else if(DBResponse.getSuccess() == 0)
				{
					responseObj.put("status", DBResponse.getStatus());
					responseObj.put("message", DBResponse.getMessage());
					responseObj.put("data", new ArrayList<JSONObject>());
					LoggerUtil.log("\n" + "Response: " + responseObj.toString());
					return Response.status(ResponseDetails.OK_HttpStatusCode).entity(responseObj).build();
				}
				else
				{
					responseObj.put("status", DBResponse.getStatus());
					responseObj.put("message", DBResponse.getMessage());
					responseObj.put("data", new ArrayList<JSONObject>());
					if(Config.getProperty("ENVIRONMENT").equals("DEV"))
					{
						responseObj.put("errorMessage", DBResponse.getData());
					}
					LoggerUtil.log("\n" + "Response: " + responseObj.toString());
					return Response.status(DBResponse.getStatus()).entity(responseObj).build();
				}
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
			responseObj.put("status", ResponseDetails.INTERNAL_SERVER_ERROR_HttpStatusCode);
			responseObj.put("message", ResponseDetails.INTERNAL_SERVER_ERROR_ResponseMessage);
			return Response.status(ResponseDetails.INTERNAL_SERVER_ERROR_HttpStatusCode).entity(responseObj).build();
		}
	}
}
