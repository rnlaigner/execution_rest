package rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
 
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.repository.RepositoryDirectoryInterface;
import org.pentaho.di.repository.kdr.KettleDatabaseRepository;
import org.pentaho.di.repository.kdr.KettleDatabaseRepositoryMeta;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;

import exception.MyApplicationException;
import model.Property;
 
@Path("/")
public class Conciliator 
{
	
	Property prop = new Property();
	
	@POST
	@Path("/conciliation")  //Your Path or URL to call this service
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response conciliate(
	        @DefaultValue("true") @FormDataParam("enabled") boolean enabled,
	        @FormDataParam("file") InputStream uploadedInputStream,
	        @FormDataParam("file") FormDataContentDisposition fileDetail) throws MyApplicationException 
	{

		try
		{
            /**
             * Initialize the Kettle Enviornment
             */
            KettleEnvironment.init();
            
            //Create a instance of kettle database repository
            //KettleDatabaseRepository repository = new KettleDatabaseRepository();
            
            //Create a instance of database meta
            
            String port = "-1";
            String userName = "";
            String password = "";
            String databaseName = prop.getDatabaseLocation();
            String host = "";
            String type = "SQLite";
            String access = "Native";
            
            //String repositoryId = "KettleDatabaseRepository";
            //String repositoryName = "OMHRepository";
            
            String connectionName = "SQLite_inventory_control";

            DatabaseMeta databaseMeta = new DatabaseMeta( connectionName, type, access, host, databaseName, port, userName, password );
            
            //databaseMeta.setAccessType(access_type);
            
            //Create a instance of kettle repository  meta
            
            
            /*
            KettleDatabaseRepositoryMeta kettleDatabaseMeta = new KettleDatabaseRepositoryMeta(
            		repositoryId, repositoryName, repositoryName, databaseMeta );
            */
            
            //Initialize the repository

            //repository.init( kettleDatabaseMeta );
            
            //Connect to the repository default
            
            //String repUserName = "";
            //String repPassword = "";

            //repository.connect( repUserName, repPassword );
            
            //Load the root directory

            //RepositoryDirectoryInterface directory = repository.loadRepositoryDirectoryTree();

            /**
             * Create a trans object to properly assign the ktr metadata.
             * 
             * @filedb: The ktr file path to be executed.
             * 
             */
            //TransMeta transformationMeta = new TransMeta(prop.getTransformationLocation(), repository);
            
            TransMeta transformationMeta = new TransMeta(prop.getTransformationLocation());
            
            transformationMeta.setUsingUniqueConnections(true);
            //transformationMeta.setName("transformationMeta");
            
            List<DatabaseMeta> databases = new ArrayList<>();
            databases.add(databaseMeta);
            transformationMeta.setDatabases(databases);
            
            //Se nao funcionar do jeito que esta....
            //transformationMeta.setFilename(newFilename);
            
            //Read the saved transformation  meta data using the directory and the transformation name
            
            //String transformationName = prop.getTransformationLocation();
            
            //TransMeta transformationMeta = repository.loadTransformation(transformationName, directory, null, true, null ) ;
            
            
            Trans trans = new Trans(transformationMeta);
            
            String fileLocation = fileDetail.getFileName();
            
            String tempDir = System.getProperty("java.io.tmpdir");
            
            String fileDir =  tempDir + "\\" + fileLocation;
                        
            //trans.setParameterValue("PARAM_FILE", fileDir.);

            // Execute the transformation
            trans.execute(null);
            trans.waitUntilFinished();

            // checking for errors
            if (trans.getErrors() > 0) 
            {
                System.out.println("Erroruting Transformation");
                return Response.status(502).entity("Transformation error").build();
            }
            
            return Response.status(200).entity("Transformation success").build();

        } 
		catch (KettleException e) 
		{
            // TODO Auto-generated catch block
            e.printStackTrace();
            return Response.status(502).entity("Exception : " + e.getMessage()).build();
        } 
		catch (IOException e) {
			// TODO Auto-generated catch block
        	e.printStackTrace();
            return Response.status(502).entity("Exception : " + e.getMessage()).build();
		}
		
	}
	
	
}
