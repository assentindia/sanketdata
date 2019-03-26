<%@page import="com.assent.city.master.model.City"%> 
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
	String msg="";
	if(session.getAttribute("msg")!=null)
	{
		msg=session.getAttribute("msg").toString();
		session.removeAttribute("msg");
	}
	
	List<com.assent.city.master.entity.City> cityList = new City().getCities();
	
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>:: City Master ::</title>
	<link href="<%=request.getContextPath() %>/css/style.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript">
		function submitPage(formAction)
		{
			if(document.frmCity.txtCity.value=="")
			{
				alert("Please enter city");
				document.frmCity.txtCity.focus();
			}
			else
			{
				document.frmCity.action="<%=request.getContextPath() %>/servlet/CityServlet?formAction="+formAction;
				document.frmCity.method="post";
				document.frmCity.submit();
			}
		}
		
		function editPage(aparam,bparam)
		{
			document.frmCity.hdnCityId.value=aparam;
			document.frmCity.txtCity.value=bparam;
		}
		function deletePage(aparam,formAction)
		{
			document.frmCity.action="<%=request.getContextPath() %>/servlet/CityServlet?formAction="+formAction+"&cityId="+aparam;
			document.frmCity.method="post";
			document.frmCity.submit();
		}
	</script>
</head>
<body>
	<form name="frmCity" id="frmCity">
		<table width="50%" align="center" border="0">
			<tr>
				<td>
					<div class="div1">
					    <table width="100%" border="0">
					        <tr><td><%=msg %></td></tr>
					    	<tr>
					    		<td>
						    		<label for="fname">City Name</label>
						    		<input type="hidden" id=hdnCityId name="hdnCityId"> 
						    		<input class="textfield" type="text" id="txtCity" name="txtCity" placeholder="Your city.."> 
					    		</td>
					    	</tr>
					    	<tr>
					    		<td align="center"><input type="Button" value="Submit" class="button" onclick="submitPage('Save')"></td>
					    	</tr>
					    </table>
					</div>
				</td>
			</tr>
			<tr>
				<td height="20"></td>
			</tr>
			<tr>
				<td>
					<table id="customers">
					  <tr>
					    <th>S.N.</th>
					    <th>City Name</th>
					    <th>Action</th>
					  </tr>
					  <%
					  	for(int i=0;i<cityList.size();i++)
					  	{
					  		com.assent.city.master.entity.City city =cityList.get(i);
					  %>
					  <tr>
					    <td><%=i+1 %></td>
					    <td><%=city.getCityName() %></td>
					    <td><a href="#" onclick="editPage('<%=city.getCityId() %>','<%=city.getCityName() %>')">Edit</a> | 
					    <a href="#" onclick="deletePage('<%=city.getCityId() %>','Delete')">Delete</a></td>
					  </tr>
					  <%} %>
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>