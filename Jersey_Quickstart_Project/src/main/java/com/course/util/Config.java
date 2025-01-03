package com.course.util;

import java.io.InputStream;
import java.util.Properties;

public class Config
{
	private static final Properties properties = new Properties();

	static
	{
		try(InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties"))
		{
			if(input != null)
			{
				properties.load(input);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static String getProperty(String key, String defaultValue)
	{
		return properties.getProperty(key, defaultValue);
	}

	public static String getProperty(String key)
	{
		return properties.getProperty(key);
	}
}