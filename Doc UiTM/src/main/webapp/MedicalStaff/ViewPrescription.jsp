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
<title>Patient's Profile</title>

<link rel="stylesheet" href="css/styles.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto'>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


<style>
html,body,h1,h2,h3,h4,h5,h6 {font-family: "Roboto", sans-serif}
</style>

</head>
<body background="image/medBG-green.png">

<!-- For header and sticky header -->
	<header>
		<img class="logo" src="image/smallerlogof.png" alt="logo">
		</header>
	
	
<nav>
	<ul>
	
	<li><b><a href = "PatientController?action=listPatient&role=<%= role %>"> List of Patients</a></b></li>
	
	</ul>

</nav>

<!-- Page Container -->
<div class="w3-content w3-margin-top" style="max-width:1400px;">


  <!-- The Grid -->
  <div class="w3-row-padding">
  
    <!-- Left Column -->
    <div class="w3-third">
    
      <div class="w3-white w3-text-grey w3-card-4">
        <div class="w3-display-container">
        <img src="data:image/jpg;base64,${patient.base64Image}" style="width:100%" alt="Avatar"/>
          <div class="w3-display-bottomleft w3-container w3-text-white">
          <h2><c:out value="${patient.patientname}"/></h2>
          </div>
        </div>
        <div class="w3-container">
          <p>Gender: <c:out value="${patient.gender}"/> </p>
          <p>Height: <c:out value="${patient.height}"/></p>
          <p>Weight: <c:out value="${patient.weight}"/></p>
          <hr>
          <br>
        
          <br>
        </div>
      </div><br>

    <!-- End Left Column -->
    </div>

    <!-- Right Column -->
    <div class="w3-twothird">
    
      <div class="w3-container w3-card w3-white w3-margin-bottom">
        <h2 class="w3-text-black w3-padding-16">PATIENT PROFILE</h2>
        <div class="w3-container">
          
          <h5 class="w3-text-grey">PERSONAL DETAILS</h5>
          
          <h6 class="w3-text-teal">User ID</h6>
          <label for="userid"></label><c:out value="${patient.patientid}"/><br>
          
          <h6 class="w3-text-teal">Name</h6>
          <label for="name"></label><c:out value="${patient.patientname}"/><br>
          
          <h6 class="w3-text-teal"><i class="fa fa-calendar fa-fw w3-margin-right"></i>Birthdate</h6>
          <label for="name"></label><c:out value="${patient.birthdate}"/><br>
          <h6 class="w3-text-teal">Blood type</h6>
          <label for="birthdate"></label><c:out value="${patient.bloodtype}"/><br>
         
          <br>
          
          <hr>
        </div>
      
      <div class="w3-container w3-card w3-white">
        <h3 class="w3-text-grey w3-padding-16"><i class="fa fa-certificate fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Medication Details</h3>
        <div class="w3-container">
          <h6 class="w3-text-teal">Medicine</h6>
          <label for="medicinename"><c:out value="${pres.medicine.medicinename}"/></label><br>
           
         
          <hr>
        </div>
        <div class="w3-container">
          <h6 class="w3-text-teal">Prescription</h6>
          <label for="description"><c:out value="${pres.description}"/></label><br>
          
          <h6 class="w3-text-teal">Prescribed by</h6>
          <label for="medstaffid"> Dr. <c:out value="${pres.staff.name}"/></label><br>
           
          <a href = "AddPrescriptionController?patientid=<c:out value="${patient.patientid}"/>"><br>
          <button>Add/Edit Prescription</button>
          </a>
          <hr>
        </div>
        
      </div>

    <!-- End Right Column -->
    </div>
    
  <!-- End Grid -->
  </div>
  
  <!-- End Page Container -->
</div>
</div>
<footer class="w3-container w3-teal w3-center w3-margin-top">
  
  
</footer>

</body>
</html>
