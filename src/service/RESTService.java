package service;

import model.RESTResponse;

public class RESTService 
{

    public static RESTResponse buildRESTResponse(String status, int entriesUpdated, int entriesAdded, String duration, String msg) 
	{
		RESTResponse response = new RESTResponse(status, entriesUpdated, entriesAdded, duration, msg);
		return response;
    }
	
}
