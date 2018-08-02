package test;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
 

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;


import model.Property;
 
@Path("test")
public class ConciliationTest 
{
	
	Property prop = new Property();
	
    @GET
    @Path("conciliation_test")
    @Produces(MediaType.APPLICATION_JSON)
	public Response conciliateTest()
	{

		try
		{
            KettleEnvironment.init();
       
            String port = "-1";
            String userName = "";
            String password = "";
            String databaseName = "C:\\rails_apps\\inventory_control\\db\\development.sqlite3";
            String host = "";
            String type = "SQLite";
            String access = "Native";
            
            String connectionName = "SQLite_inventory_control";

            DatabaseMeta databaseMeta = new DatabaseMeta( connectionName, type, access, host, databaseName, port, userName, password );
            
            String transformationLocation = "C:\\Users\\Rodrigo\\Documents\\Projetos\\Planilha de Controle de Estoque\\conciliation.ktr";
            
            TransMeta transformationMeta = new TransMeta(transformationLocation);
            
            transformationMeta.setUsingUniqueConnections(true);
            
            List<DatabaseMeta> databases = new ArrayList<>();
            databases.add(databaseMeta);
            transformationMeta.setDatabases(databases);           
            
            Trans trans = new Trans(transformationMeta);
            
            //String file = fileDetail.getFileName();
            
            //String tempDir = System.getProperty("java.io.tmpdir");
            
            String fileDir; //=tempDir + "\\" + file;
            
            fileDir =  "C:\\Users\\Rodrigo\\Documents\\Projetos\\Planilha de Controle de Estoque\\relatorio.xlsx";
                        
            trans.setParameterValue("PARAM_FILE", fileDir);

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
		
	}
	
	
}

