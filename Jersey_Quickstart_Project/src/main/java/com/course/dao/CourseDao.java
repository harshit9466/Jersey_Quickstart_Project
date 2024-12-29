package com.course.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.course.beans.CourseBeans;
import com.course.util.LoggerUtil;
import com.course.util.ResponseDetails;
import com.course.util.ResponseWrapper;
import com.course.util.UtilityClass;

public class CourseDao 
{
	public ResponseWrapper<?> addCourse(CourseBeans courseBeans)
	{
		String query = "INSERT INTO courses (name) VALUES (?)";
		Connection connection = ConnectionManager.getNewTempConnection();
		PreparedStatement stmt = null;
		int rowsAffected = 0;
		try
		{

			stmt = connection.prepareStatement(query);

			int index = 1;
			stmt.setString(index, courseBeans.getCourseName());

			rowsAffected = stmt.executeUpdate();

			if(rowsAffected == 0)
			{
				return new ResponseWrapper<String>(	0,
													"Course could not created",
													ResponseDetails.INTERNAL_SERVER_ERROR_HttpStatusCode,
													ResponseDetails.INTERNAL_SERVER_ERROR_ResponseMessage);
			}
			else if(rowsAffected >= 1)
			{
				return new ResponseWrapper<String>(1, ResponseDetails.OK_HttpStatusCode, "Course created successfully");
			}
			else
			{
				return new ResponseWrapper<String>(	-2,
													ResponseDetails.INTERNAL_SERVER_ERROR_HttpStatusCode,
													"Something went wrong");
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
			return new ResponseWrapper<String>(	-1,
												"An error occurred: " + ex.getMessage(),
												ResponseDetails.INTERNAL_SERVER_ERROR_HttpStatusCode,
												ResponseDetails.INTERNAL_SERVER_ERROR_ResponseMessage);

		} finally
		{
			UtilityClass.closeResources(connection, stmt);
		}
	}

	public ResponseWrapper<?> listCourses()
	{
		List<Map<String,Object>> list = new ArrayList<>();
		Connection connection = ConnectionManager.getNewTempConnection();
		ResultSet resultSet = null;
		PreparedStatement stmt = null;

		StringBuilder listDynamicRoles = new StringBuilder();
		listDynamicRoles.append("SELECT ")
						.append("c.* ")
						.append("FROM ")
						.append("courses c ");

		try
		{
			stmt = connection.prepareStatement(listDynamicRoles.toString());

			LoggerUtil.log("listCourses: " + stmt);
			resultSet = stmt.executeQuery();

			if(!resultSet.isBeforeFirst())
			{
				// The ResultSet is empty
				return new ResponseWrapper<String>(0, ResponseDetails.NOT_FOUND_HttpStatusCode, "No details found");
			}
			else
			{
				while(resultSet.next())
				{

					Map<String,Object> tempObj = new HashMap<>();

					tempObj.put("courseId", resultSet.getObject("id"));
					tempObj.put("courseName", resultSet.getObject("name"));

					list.add(tempObj);
				}
				return new ResponseWrapper<>(1, list, ResponseDetails.OK_HttpStatusCode, "Course list");
			}

		}
		catch(SQLException ex)
		{
			ConnectionManager.getConn();
			ex.printStackTrace();
			return new ResponseWrapper<>(	-1,
											"An error occurred: " + ex.getMessage(),
											ResponseDetails.INTERNAL_SERVER_ERROR_HttpStatusCode,
											ResponseDetails.INTERNAL_SERVER_ERROR_ResponseMessage);
		} finally
		{
			UtilityClass.closeResources(resultSet, stmt);
		}
	}
	
}
