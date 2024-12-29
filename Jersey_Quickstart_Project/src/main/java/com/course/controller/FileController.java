package com.course.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONObject;

import com.course.util.LoggerUtil;
import com.course.util.UtilityClass;

@Path("/api")
public class FileController {

	private Boolean writeResult;
	
    @SuppressWarnings({ "unchecked" })
    @Path("/file/upload")
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile( @FormDataParam("file") InputStream uploadedInputStream, 
    		                    @FormDataParam("file") FormDataContentDisposition fileDetail,
    		                    @FormDataParam("type") String type)
    {
    try {

    	
    	// Check if the uploaded file is an image file       
        String filename = fileDetail.getFileName();
        String fileExtension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
        boolean isVideoFile = false;
        final String allowedVideoExtention[] = {"mp4", "mkv"};
        for (String extension : allowedVideoExtention) {
            if (extension.equals(fileExtension)) {
                isVideoFile = true;
                break;
            }
        }
        if (!isVideoFile) {
        	
        	JSONObject json = new JSONObject();
            json.put("message", "Upload "+Arrays.toString(allowedVideoExtention)+" video only");
            json.put("status", "404");

            LoggerUtil.log(json);
        	
            return Response.status(403)
                    .entity(json.toString())
                    .build();
        }
        
    	//Creating Folder and sending Data to dao and to writeToFle Method
        	switch (type.toUpperCase()) {
            case "LECTURE":
                type = "Lecture";
                break;
        	}

            String folderName = type;          
            
            String projectPath = "C:\\Users\\User\\Desktop\\course";
    		LoggerUtil.log("projectPath: "+ projectPath);
    		LoggerUtil.log();
    		
            String newFolderPath = projectPath + "\\src\\main\\webapp\\uploads\\" + folderName; // currentProjectPath + "/" + folderName;
            File folder = new File(newFolderPath);
            
            LoggerUtil.log("newFolderPath: " + newFolderPath);
            
            String randomFilename = UtilityClass.generateRandomNumber(9) + "_" + filename;
            
            String uploadDirectory = newFolderPath +"/"+ randomFilename;
            
            if(!folder.exists()) 
            {
                boolean isFolderCreated = folder.mkdirs();
                if(isFolderCreated) 
                {
                    LoggerUtil.log("Folder created successfully.");
                } 
                else 
                {
                    LoggerUtil.log("Failed to create folder.");
                }
            } 
            else 
            {
                LoggerUtil.log("Folder already exists.");
            }
            
            LoggerUtil.log(uploadDirectory);
            
            // Write InputStream to a temporary file
            File tempFile = File.createTempFile("upload_", filename);
            writeToFile(uploadedInputStream, tempFile.getPath());
            
            //Writing File to Disk
            writeResult = writeToFile(uploadedInputStream, uploadDirectory);
            if(!writeResult)
            {
            	JSONObject json = new JSONObject();
                json.put("message", "Maximum size should be 50MB");
                json.put("status", "406");

                LoggerUtil.log(json);
            	
                return Response.status(403)
                        .entity(json.toString())
                        .build();
            }
            
            String filePath = uploadDirectory;
            
            if(writeResult)
           {
            JSONObject json = new JSONObject();
            
			try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e1) {e1.printStackTrace();} 
            
            json.put("status", "200");
            json.put("message", "File Uploaded Successfully");
            JSONObject jo = new JSONObject();
//            jo.put("filepath", filePath); 
            jo.put("videoName", "http://localhost:8080/course/uploads/"+type+"/"+randomFilename);
            json.put("data", jo);
            
            LoggerUtil.log("!!!!!!!!!!!!!!!!!!!!!!!! "+json.toString());                   
            return Response.status(200)
            		.entity(json.toString())
            		.build();
           }
        } catch (Exception e) 
        { 
        	e.printStackTrace();  
        	JSONObject json = new JSONObject();
            json.put("status", "500");

            json.put("message", "Error while uploading the file.");
            json.put("errorMessage", e.getMessage());
            LoggerUtil.log("RESPONSE: "+json.toString());

            return Response.status(500)
            		.entity(json.toString())
            		.build();
        }  
        
    	JSONObject json = new JSONObject();
        json.put("status", "500");
        json.put("message", "Error while uploading the file");
        return Response.status(500)
        		.entity(json.toString())
        		.build();
    }

    private Boolean writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) throws IOException 
    {
    	
        OutputStream out = new FileOutputStream(uploadedFileLocation);
        int read = 0;
        byte buffer[] = new byte[1024];
        while((read = uploadedInputStream.read(buffer)) != -1) 
        {
        	//LoggerUtil.log( Arrays.toString(buffer) );
            out.write(buffer, 0, read);
        }
        out.flush();
        out.close();
        
        writeResult = true;
        
        //check File Size
        File uploadedFile = new File(uploadedFileLocation);
        if(uploadedFile.exists() && uploadedFile.length() > 100*1024*1024) //file allowed upto 50MB
        {
        	LoggerUtil.log("File Size Exceeded");
        	uploadedFile.delete();
        	writeResult = false;
        	
        	return writeResult;
        }
        
        return writeResult;
        
    }
}
