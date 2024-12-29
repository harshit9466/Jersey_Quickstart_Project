package com.course.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.course.beans.LectureBeans;
import com.course.util.LoggerUtil;
import com.course.util.ResponseDetails;
import com.course.util.ResponseWrapper;
import com.course.util.UtilityClass;

public class LectureDao 
{
	public ResponseWrapper<?> addLecture(LectureBeans lectureBeans)
	{
		String query = "INSERT INTO lectures (course_id, name, notes, video) VALUES (?,?,?,?)";
		Connection connection = ConnectionManager.getNewTempConnection();
		PreparedStatement stmt = null;
		int rowsAffected = 0;
		try
		{

			stmt = connection.prepareStatement(query);

			int index = 1;
			stmt.setLong(index++, lectureBeans.getCourseId());
			stmt.setString(index++, lectureBeans.getLectureName());
			stmt.setString(index++, lectureBeans.getNotes());
			stmt.setString(index++, lectureBeans.getVideoName());

			rowsAffected = stmt.executeUpdate();

			if(rowsAffected == 0)
			{
				return new ResponseWrapper<String>(	0,
													"lecture could not created",
													ResponseDetails.INTERNAL_SERVER_ERROR_HttpStatusCode,
													ResponseDetails.INTERNAL_SERVER_ERROR_ResponseMessage);
			}
			else if(rowsAffected >= 1)
			{
				return new ResponseWrapper<String>(1, ResponseDetails.OK_HttpStatusCode, "lecture created successfully");
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
	
	public ResponseWrapper<?> listLecture(LectureBeans lectureBeans)
	{
		List<Map<String,Object>> list = new ArrayList<>();
		Connection connection = ConnectionManager.getNewTempConnection();
		ResultSet resultSet = null;
		PreparedStatement stmt = null;

		StringBuilder listDynamicRoles = new StringBuilder();
		listDynamicRoles.append("SELECT ")
						.append("c.id as course_id, ")
						.append("c.name as course_name, ")
						.append("l.id as lecture_id, ")
						.append("l.name as lecture_name, ")
						.append("l.notes, ")
						.append("l.video, ")
						.append("FROM ")
						.append("courses c ")
						.append("LEFT JOIN lectures l ON l.course_id = c.id ")
						.append("WHERE ")
						.append("l.course_id = ? ");

		try
		{
			stmt = connection.prepareStatement(listDynamicRoles.toString());
			int index = 1;
			stmt.setLong(index++, lectureBeans.getCourseId());

			LoggerUtil.log("listLecture: " + stmt);
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

					tempObj.put("courseId", resultSet.getObject("course_id"));
					tempObj.put("courseName", resultSet.getObject("course_name"));
					tempObj.put("lectureId", resultSet.getObject("lecture_id"));
					tempObj.put("lectureName", resultSet.getObject("lecture_name"));
					tempObj.put("lecctureNotes", resultSet.getObject("notes"));
					tempObj.put("lectureVideo", resultSet.getObject("video"));

					list.add(tempObj);
				}
				return new ResponseWrapper<>(1, list, ResponseDetails.OK_HttpStatusCode, "Lecture list");
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
