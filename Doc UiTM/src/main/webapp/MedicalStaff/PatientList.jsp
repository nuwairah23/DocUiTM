<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
  response.addHeader("Pragma", "no-cache");
  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
  response.addHeader("Cache-Control", "pre-check=0, post-check=0");
  response.setDateHeader("Expires", 0);

  if(session.getAttribute("sessionId")==null)	  
      response.sendRedirect("/DocUiTM/Login.jsp");
  %>     
 <%	String id = (String)session.getAttribute("sessionId");%>
<%	String role = (String)session.getAttribute("sessionRole");%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>List of Patients | Doc UiTM</title>
	<link rel="stylesheet" href="css/meslist.css">
	<link href="/images/images/logo/favicon.png" rel="shortcut icon" type="image/vnd.microsoft.icon">
</head>

<body background="image/medBG-green.png">
	
	<header>
		<img class="logo" src="image/smallerlogof.png" alt="logo">
		<ul>
			<li class="selectMed"><a href="HomeController?action=homeS">HOME</a></li>
			<li class="selHo"><a href="BookingController?action=listBookings&role=<%=role%>">BOOKING</a></li>
			<li class="selHo"> <a href="MedicineController?action=listMedicine&role=<%=role%>">MEDICINE</a></li>
			<li class="selHo"> <a href="PatientController?action=listPatient&role=<%=role%>">PATIENTS</a></li>
			<li class="selHo"> <a href="#">PROFILE</a></li>
		</ul>
		<a class="cta" href="LogoutController"><button>LOGOUT</button></a>
	</header>
	
	<section class="banner" >
		<script type="text/javascript">
			window.addEventListener
				("scroll", function()
					{
					var header = document.querySelector("header");
					header.classList.toggle("sticky", window.scrollY > 0);
					}
				)
		</script>
	
		<div class="title-highlight"> <br><br><br><br><br><br><br><br><br><br><br>
			<h3><mark><a>List of Patients</a></mark></h3>
			<h1><mark>Search here </mark><br></h1><br>
			
		</div>
	</section>
		<br>
	<div class="row">
		<div class="col-md-4">
		
			
		</div>
		<div class="col-md-4">
			<form action="SearchPatientController" method="get">
				<input type="text" class="form-control" name="q" placeholder="Search">
			</form>
	</div>
	<br><br>
		
	</div>
	<p></p>
	<div class="col-md-4 text-right" style="padding-right:150px; padding-bottom:30px">
			<a href="PatientController?action=listPatient&role=<%=role %>" class="btn btn-primary">Show All</a><br><br>
		</div>
	<div class="table-pad">
	<table class="table table-bordered table-stripped table-hover">
		<thead>
			<tr>
			    <th>Patient Id</th>
			    <th>Name</th>
			    <th>Gender</th>
			    <th class="text-center">Actions</th>
			 </tr>
  		</thead>
  		<tbody>
			 <c:forEach items="${patients}" var="pat" varStatus="patients">
			  <tr>
			    <td><c:out value="${pat.patientid}" /></td>
			    <td><c:out value="${pat.patientname}" /></td>
			    <td><c:out value="${pat.gender}" /></td>
			           
			    <td><a href="PatientController?action=viewPatient&role=<%=role %>&patientid=<c:out value="${pat.patientid}" />" class="lol">View</a></td>
			  </tr>
			  </c:forEach>
		</tbody>
	</table>
</div>

<!-- Footer start here -->
	<div><img class="foot" src="image/footermed.png" alt="footer"></div>
	
	<div class="feet">
		<div class="generic-container flex flex-wrap">
			<div class="flex  flex-wrap w-4/4 md:w-full body-text-2">
	
	<div class="rowcol">			
		<div data-component="accordion" class="accordion-root">
			<div class="accordion-title">
				<h5 class="flex items-center"><span class="column-title">About Us</span></h5> 
		    </div> 
			<div class="accordion-content overflow-hidden" style="height: 155px;"> 
			<ul class="column-list mt-2 list-none pl-0">
			<li class="column-list-item"><a href="#Our Network">Our Network</a></li> 
			<li class="column-list-item"><a href="#BrandActivities">Brand Activities</a></li> 
			<li class="column-list-item"><a href="#Newsroom">Newsroom</a></li>
		 	<li class="column-list-item"><a href="#Careers">Careers</a></li></ul>
		 	</div>
		 </div>
	</div> 
	
	<div class="rowcol">
		<div data-component="accordion" class="accordion-root">
		 	<div class="accordion-title">
		 		<h5 class="flex items-center"><span class="column-title">Support</span></h5>
		 	</div> 
		  	<div class="accordion-content overflow-hidden" style="height: 125px;"> 
		 		<ul class="column-list mt-2 list-none pl-0">
		 		<li class="column-list-item"><a href="#faqs">FAQs</a></li> 
		 		<li class="column-list-item"><a href="#store-finder">Find a Store</a></li> 
		 		<li class="column-list-item"><a href="#self-help">Self Help </a></li> 
		 		<li class="column-list-item"><a href="#contact-us">Contact Us</a></li></ul>
		  	</div>
		</div>
	</div> 
		 
	<div class="rowcol">
		<div data-component="accordion" class="accordion-root">
		    <div class="accordion-title">
		    	<h5 class="flex items-center"><span class="column-title">Terms &amp; Notices</span></h5>
		    </div> 
		    <div class="accordion-content overflow-hidden" style="height: 96px;"> 
		    	<ul class="column-list mt-2 list-none pl-0">
		    	<li class="column-list-item"><a href="#important-notices">Important Notices</a></li> 
		    	<li class="column-list-item"><a href="#terms-conditions">Terms &amp; Conditions</a></li> 
		    	<li class="column-list-item"><a href="#privacy-notice">Privacy Notice</a></li></ul><br>
		    	<h5 class="column-title">Connect with Us</h5> 
		    	<div class="flex mt-2 mb-6">
		    		<a href="https://www.facebook.com/uitmrasmi/" target="_blank"><i class="fa-brands fa-facebook-square"></i></a> 
		    		<a href="https://twitter.com/uitmofficial" target="_blank"><i class="fa-brands fa-instagram"></i></a> 
		    		<a href="https://www.instagram.com/uitm.official/" target="_blank"><i class="fa-brands fa-twitter"></i></a> 
		    		<a href="https://www.youtube.com/channel/UCjIWjQSyTuVDD-GHPYcg7Pw" target="_blank"><i class="fa-brands fa-youtube"></i></a> 
		    	</div>
		     </div>
		 </div>
	</div> 
		    
	<div class="rowcol">
		<div class="rowcol"><h5 class="column-title"> Contact Us</h5> 
		    <div class="sp-module-content">
				<div class="custom">
					<table style="width: 100%;"><tbody>
					  <tr><td>
						<span style="color: #d6d6d6;"><strong>Universiti Teknologi MARA (UiTM)</strong></span><br>
						<span style="color: #d6d6d6;">40450 Shah Alam, Selangor Darul Ehsan</span><br>
						<span style="color: #d6d6d6;">Malaysia</span><br><br>
						<span style="color: #d6d6d6;"><a style="color: #d6d6d6;" href="mailto:webmedia@uitm.edu.my">webmedia@uitm.edu.my</a></span><br><br>
						<span style="color: #d6d6d6;"><strong>Tel</strong>: <span style="color: #ffffff;">+603-5544 2000</span></span><br>
					  </td></tr>
					</tbody></table>
				</div>
			</div> 
		</div>
	</div>
			</div>
		</div>
	</div>
	
<!-- Tiny footer -->
			<div class="tiny"><a>© COPYRIGHT UNIVERSITI TEKNOLOGI MARA 2020.</a></div>

</body>

</html>