package com.assent.city.master.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.assent.city.common.Validator;
import com.assent.city.master.entity.City;

/**
 * Servlet implementation class CityServlet
 */
@WebServlet(description = "CityServlet", urlPatterns = { "/servlet/CityServlet" })
public class CityServlet extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(CityServlet.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PropertyConfigurator.configure(System.getProperty("user.dir")+"/log4j.properties");
		HttpSession session = request.getSession();
		String msg ="";
		try
		{
			String formAction = Validator.replaceNull(request.getParameter("formAction"));
			
			logger.info("formAction :: "+formAction);
			City city = getEntity(request);
			if(formAction.equalsIgnoreCase("Save"))
			{
				if(!Validator.replaceNull(city.getCityId()).equalsIgnoreCase(""))
				{
					msg =new com.assent.city.master.model.City().updateCity(city);
				}
				else
				{
					msg =new com.assent.city.master.model.City().insertCity(city);
				}
			}
			else if(formAction.equalsIgnoreCase("Delete"))
			{
				String cityId = Validator.replaceNull(request.getParameter("cityId"));
				msg =new com.assent.city.master.model.City().deleteCity(cityId);
			}
			
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			session.setAttribute("msg",msg);
			response.sendRedirect(request.getContextPath()+"/city.jsp");
		}
	}
	
	private City getEntity(HttpServletRequest request)
	{
		City city = new City();
		
		city.setCityId(Validator.replaceNull(request.getParameter("hdnCityId")));
		city.setCityName(Validator.replaceNull(request.getParameter("txtCity")));
		
		return city;
	}

}
