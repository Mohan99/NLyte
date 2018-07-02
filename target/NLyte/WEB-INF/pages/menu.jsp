<!-- Navigation -->
<%@ page import=" com.gcs.nlyte.web.persistance.dao.entity.NlyteUsers" %>
 <%
 	boolean flag = false;
 	NlyteUsers user = (NlyteUsers) session.getAttribute("USER_OBJ");
 	if (user!=null && user.isAdminYn() == true) {
 		flag = true;
 	}
 	
 %>
 <head>
 <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.2/jquery-ui.min.js">
        </script>
  <script>
    $(document).ready(function() {
        function disableBack() { window.history.forward() }

        window.onload = disableBack();
        window.onpageshow = function(evt) { if (evt.persisted) disableBack() }
    });
</script>
 </head>
  <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	  <div class="navbar-header"> <a class="navbar-brand" href="#"> <img src="img/logo.png" width="180"> </a> <h3 class="headding">Nlyte Material Lookup Data</h3> </div>
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">  <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
    
    <!-- Top Navigation: Left Menu --> 
    
    <!-- Top Navigation: Right Menu -->
    <ul class="nav navbar-right navbar-top-links" >
      <!--<li class="dropdown navbar-default">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-bell fa-fw"></i> <b class="caret"></b>
                </a>
                <ul class="dropdown-menu dropdown-alerts">
                    <li>
                        <a href="#">
                            <div>
                                <i class="fa fa-comment fa-fw"></i> New Comment
                                <span class="pull-right text-muted small">4 minutes ago</span>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a class="text-center" href="#">
                            <strong>See All Alerts</strong>
                            <i class="fa fa-angle-right"></i>
                        </a>
                    </li>
                </ul>
            </li>-->
      <li class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i> Admin<b class="caret"></b> </a>
        <ul class="dropdown-menu dropdown-user">
          <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a> </li>
          <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a> </li>
          <li class="divider"></li>
          <li><a href="nlyteLogout"><i class="fa fa-sign-out fa-fw"></i> Logout</a> </li>
        </ul>
      </li>
    </ul>
    
    <!-- Sidebar -->
    <div class="navbar-default sidebar" role="navigation" ><!--margin-top: 16px; background-color: #0d4272-->
      <div class="sidebar-nav navbar-collapse" >
		  <div class="" style="margin-top: 16px; background-color: rgba(0,0,0,0.9); min-height: 100vh">
		  	<ul class="nav" id="side-menu">
            <li> <a href="Dashboard.html"><i class="fa fa-dashboard fa-fw"></i> Dashboard</a> </li>
           <%if(flag==true) {%>
            <li> <a href="#"><i class="fa fa-user-md fa-fw"></i> User Management<span class="fa arrow"></span></a>
              <ul class="nav nav-second-level">
                <li> <a href="nlyteUserCreate"> <i class="fa fa-user-plus fa-fw"></i> Create User </a> </li>
                <li><a href="nlyteUserList"><i class="fa fa-search fa-fw"></i> View And Edit/Delete User</a> 
                  <!--  <ul class="nav nav-third-level">
                                    <li>
                                        <a href="#">Third Level Item</a>
                                    </li>
                                </ul>--> 
                </li>
              </ul>
            </li>
            <%} %>
            <li> <a href="#"><i class="fa fa-list fa-fw"></i> Import Material Data <span class="fa arrow"></span></a>
              <ul class="nav nav-second-level">
                <li> <a href="nlyteImportReq"><i class="fa fa-download fa-fw"></i>  Import Nlyte Master Data</a> </li>
                <li> <a href="nlyteMasterDataList"><i class="fa fa-list fa-fw"></i>  Nlyte Master Data</a> </li>
                <li> <a href="nlyteCustImportReq"> <i class="fa fa-download fa-fw"></i>  Customer Materials Data</a> </li>
              </ul>
            </li>
            <li> <a href="#" onClick="disableBack()"><i class="fa fa-user-md fa-fw"></i> Material Lookup<span class="fa arrow"></span></a>
              <ul class="nav nav-second-level">
                <li> <a href="nlyteCustomerImportList"> <i class="fa fa-list fa-fw"></i> Material Lookup List</a> </li>
                <li> <a href="nlyteCustomerCellList"> <i class="fa fa-upload fa-fw"></i> Review and Edit Materials </a> </li>
                <li> <a href="nlyteCustomerrocessedList"> <i class="fa fa-eye fa-fw"></i> View Processed Data</a> </li>
              </ul>
            </li>
            
          </ul>
		  </div>
      </div>
    </div>
  </nav>
  