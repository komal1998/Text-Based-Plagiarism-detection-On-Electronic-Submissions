package com.plagarism;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.search.highlight.TextFragment;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
/**
 * Servlet implementation class PlagarismServlet
 */
public class PlagarismServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String UPLOAD_DIRECTORY = "C:/temp";   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlagarismServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String,TextFragment> getLinkSFromGoogleTemp=new HashMap<String,TextFragment>();
		//process only if its multipart content
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                                         new DiskFileItemFactory()).parseRequest(request);
               String excludeUrl="";
                for(FileItem item1 : multiparts){
                	
                	if(item1.isFormField()){
                        String name =item1.getFieldName();
                        if(name!=null && name.equalsIgnoreCase("excludeUrl")){
                        	excludeUrl=item1.getString();
                        }
                	}
                }
                
                
                
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        String name = new File(item.getName()).getName();
                        item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
                        File file= new File(UPLOAD_DIRECTORY + File.separator + name);
                        BufferedReader reader;
                		try {
                			reader = new BufferedReader(new FileReader(UPLOAD_DIRECTORY + File.separator + name));
                			String line = reader.readLine();
                			String data="";
                			while (line != null) {
                				System.out.println(line);
                				// read next line
                				data=data+line;
                				line = reader.readLine();
                			}
                			reader.close();
                			if(data!=null && data.length()>0)
                			{
                				request.getSession().setAttribute("phaseWord",line);
                				Map<String,TextFragment> getLinkSFromGoogle=DownloadFromGoogle.getLinkSFromGoogle(data,excludeUrl);
                				getLinkSFromGoogleTemp.putAll(getLinkSFromGoogle);
                			}
                			
                		} catch (IOException e) {
                			e.printStackTrace();
                		}
                        
                    }
                    else if(item.isFormField()){
                        String name =item.getFieldName();
                        if(name!=null && name.equalsIgnoreCase("plagarismData")){
	                        String plagarismData=item.getString();
	            			request.getSession().setAttribute("phaseWord",plagarismData);
	            			if(plagarismData!=null && plagarismData.length()>0)
	            			{
	            				Map<String,TextFragment> getLinkSFromGoogle=DownloadFromGoogle.getLinkSFromGoogle(plagarismData,excludeUrl);
	            				
	            				//response.sendRedirect("index.jsp");
	            				getLinkSFromGoogleTemp.putAll(getLinkSFromGoogle);
	            			}
                        }
                    } 
                    
                    
                }
                
            
               //File uploaded successfully
               request.setAttribute("message", "File Uploaded Successfully");
            } catch (Exception ex) {
               request.setAttribute("message", "File Upload Failed due to " + ex);
            }          
          
        }else{
            request.setAttribute("message",
                                 "Sorry this Servlet only handles file upload request");
        }
        System.out.println("getLinkSFromGoogleTemp----"+getLinkSFromGoogleTemp);
        request.getSession().setAttribute("getLinkSFromGoogle",getLinkSFromGoogleTemp);
        float calculatedValue=0,maxValue=0;
        int count=getLinkSFromGoogleTemp.values().size();
        
        for(TextFragment setTextFragment:getLinkSFromGoogleTemp.values())
        {
        	if(setTextFragment.getScore()>maxValue)
        	{
        		maxValue=setTextFragment.getScore();
        	}
        	calculatedValue=calculatedValue+setTextFragment.getScore();
        }
        System.out.println("calculatedValue="+calculatedValue);
        float percentage=(float)(calculatedValue*100)/(maxValue*count);
        request.getSession().setAttribute("percentage",percentage);
        System.out.println("percentage="+percentage);
        request.getRequestDispatcher("/result.jsp").forward(request, response);
				// TODO Auto-generated method stub
		
	}

}
