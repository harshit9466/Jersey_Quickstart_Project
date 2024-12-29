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

import com.course.beans.CourseBeans;
import com.course.dao.CourseDao;
import com.course.util.Config;
import com.course.util.LoggerUtil;
import com.course.util.ResponseDetails;
import com.course.util.ResponseWrapper;
import com.course.util.UtilityClass;

@Path("api")
public class Courses 
{
	@SuppressWarnings("unchecked")
	@POST
	@Path("/course/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addDynamicRole(InputStream incomingData)
	{
		CourseDao courseDao = new CourseDao();
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
			String name = String.valueOf(json.get("name"));

			if(UtilityClass.isNull(name))
			{
				JSONObject obj = new JSONObject();
				obj.put("message", UtilityClass.generateRequiredFieldMessage("name"));
				obj.put("status", ResponseDetails.BAD_REQUEST_HttpStatusCode);
				LoggerUtil.log("\n" + "Response: " + obj.toString());
				return Response.status(ResponseDetails.BAD_REQUEST_HttpStatusCode).entity(obj.toString()).build();
			}

			CourseBeans courseBeans = new CourseBeans();
			courseBeans.setCourseName(name);

			ResponseWrapper<?> DBResponse = courseDao.addCourse(courseBeans);

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
	
	@Path("/course/list")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ListCourse(InputStream incomingData)
	{
		CourseDao couseDao = new CourseDao();
		Map<String,Object> responseObj = new HashMap<>();

		try
		{

				ResponseWrapper<?> DBResponse = couseDao.listCourses();
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
