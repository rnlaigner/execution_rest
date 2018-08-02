package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Property 
{
	
	private Properties properties;
	
	public Property(){
		loadPropValues();
	}

	private void loadPropValues() 
	{
		 
		//String result = "";
		InputStream inputStream;
		
		try {
			properties = new Properties();
			String propFileName = "config.properties";
 
			//inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			
			String currentUsersHomeDir = System.getProperty("user.home");
			
			String directoryFile = currentUsersHomeDir + "\\" + propFileName;
			
			inputStream = new FileInputStream( directoryFile );
 
			if (inputStream != null) {
				properties.load(inputStream);
			} 
			/*
			else 
			{
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			*/
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			//log 
		}
	}
	
	public String getDatabaseLocation() throws IOException
	{
		String databaseLocation = properties.getProperty("DATABASE_LOCATION");
		return databaseLocation;
	}
	
	public String getTransformationLocation() throws IOException
	{
		String transformationLocation = properties.getProperty("TRANSFORMATION_LOCATION");
		return transformationLocation;
	}
	
}
