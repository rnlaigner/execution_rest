package rest;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;
 
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import exception.MyApplicationException;
import service.RESTService;
 
@Path("/file")
public class RestFile 
{
	//private RESTService service;// = new RESTService();
	
	@POST
	@Path("/upload")  //Your Path or URL to call this service
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response uploadFile(
	        @DefaultValue("true") @FormDataParam("enabled") boolean enabled,
	        @FormDataParam("file") InputStream uploadedInputStream,
	        @FormDataParam("file") FormDataContentDisposition fileDetail) throws MyApplicationException 
	{
	     //Your local disk path where you want to store the file
	    String uploadedFileLocation = "C://rails_apps/" + fileDetail.getFileName();
	    System.out.println(uploadedFileLocation);
	    // save it
	    File  objFile=new File(uploadedFileLocation);
	    if(objFile.exists())
	    {
	        objFile.delete();

	    }
	    
	    try
	    {
	    	saveToFile(uploadedInputStream, uploadedFileLocation);

	    	String output = "File uploaded via Jersey based RESTFul Webservice to: " + uploadedFileLocation;

	    	return Response
	    			.status(200)
	    			//.entity(output)
	    			.entity(RESTService.buildRESTResponse(Status.OK.name(), 0, 0, "0", output))
	    			.build();
	    }
	    catch(Exception e)
	    {
	    	throw new MyApplicationException("Could not save file", e);
	    }

	}
	
	private void saveToFile(InputStream uploadedInputStream,
	        String uploadedFileLocation) 
	{

	    try {
	        OutputStream out = null;
	        int read = 0;
	        byte[] bytes = new byte[1024];

	        out = new FileOutputStream(new File(uploadedFileLocation));
	        while ((read = uploadedInputStream.read(bytes)) != -1) {
	            out.write(bytes, 0, read);
	        }
	        out.flush();
	        out.close();
	    } catch (IOException e) {

	        e.printStackTrace();
	    }

	}
}
