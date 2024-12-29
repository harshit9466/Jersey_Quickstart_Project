package com.course.util;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UtilityClass 
{
	public static boolean isNull(Object value) {
	    if (value == null) {
//	    	LoggerUtil.log("2 is Null TRUE");
	        return true;
	    }
	    if (value instanceof String || value instanceof Integer ) {
//	    	LoggerUtil.log("2 is Null elseif TRUE");
	        String strValue = String.valueOf(value);
	        return (strValue.equalsIgnoreCase("null") || strValue.trim().isEmpty());
	    }
        if (value instanceof List) {
            List<?> list = (List<?>) value;
            return list.isEmpty();
        }
        if (value.getClass().isArray()) {
            return java.lang.reflect.Array.getLength(value) == 0;
        }
	    // Add more specific checks for other datatypes if needed
	    return false; // If the object is of a different datatype, it is not considered null.
	}
	
    public static String generateRequiredFieldMessage(String fieldName) {
        return fieldName + " is required";
    }
    
	// Method to generate a random one-time password (OTP)
	public static String generateRandomNumber(int digits) {
		SecureRandom random = new SecureRandom();
//		int digits = 9;
		StringBuilder sb = new StringBuilder(digits);
		for (int i = 0; i < digits; i++) {
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}
    
    public static void closeResources(Connection connection, PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        if(connection != null)
        {
        	try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    
    public static void closeResources(Connection connection, ResultSet resultSet, PreparedStatement statement) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        if(connection != null)
        {
        	try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    
    public static void closeResources(ResultSet resultSet, PreparedStatement statement) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void closeResources(PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void closeResources(ResultSet resultSet, Statement statement) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void closeResources(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
