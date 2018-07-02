<!-- Page Content -->
<%@ page contentType = "text/html; charset = UTF-8" %>
<%@ taglib prefix = "s" uri = "/struts-tags" %>
<%@ page import=" com.gcs.nlyte.web.persistance.dao.entity.NlyteUsers" %>
<%@ page import=" com.gcs.nlyte.web.util.MD5Util" %>
<%
	NlyteUsers user = (NlyteUsers) session.getAttribute("USER_OBJ");
	MD5Util util=new MD5Util();
%>

  <div id="page-wrapper">
    <div class="container-fluid">
      <div class="row">
        <div class="col-lg-12">
        <div class="panel panel-default" style="margin-top: 50px;">
					<div class="panel-heading">
						<h4>Change Password</h4>
					</div>
		</div>
          <div class="col-md-6">
            <form class="form-horizontal" name="myForm" method="post" >
             <s:iterator value = "nlyteEditUser"> 
            <input type="hidden" name="nlyteUserId" id="nlyteUserId" value="<s:property  value = "nlyteUserId"/>">
            
            <div class="form-group">
                <label for=" " class="col-sm-5 control-label">Current Password</label>
                <div class="col-sm-7">
                  <input type="password" name="curPassword" id="curPassword"  class="form-control" id="" placeholder="Current Password">
                </div>
              </div>
             <div class="form-group">
                <label for=" " class="col-sm-5 control-label">New Password</label>
                <div class="col-sm-7">
                  <input type="password" name="newpwd" id="newpwd" class="form-control" id="" placeholder="Set Password">
                </div>
              </div>
              <div class="form-group">
                <label for=" " class="col-sm-5 control-label">Confirm New Password </label>
                <div class="col-sm-7">
                  <input type="password" class="form-control"  name="cnfpwd" id="cnfpwd"  placeholder="Confirm Password">
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-offset-4 col-sm-8" style="margin-top:10px;">
                 <button type="submit" class="btn btn-primary" onClick="funSub('${nlyteEditUser.nlyteUserId}')">Submit</button>
                <%-- <a href="#"  style="color: #fff; background-color: #5bc0de; border-color: #46b8da;" onClick="funSub('${nlyteEditUser.nlyteUserId}')"><i class="fa fa-edit fa-fw">Submit</i></a> --%>
                  
                </div>
              </div>
               </s:iterator> 
            </form>
          </div>        
      </div>
      
      <!-- ... Your content goes here ... --> 
      
    </div>
  </div>
  <%String curpass=user.getPasswordTx();
  System.out.println("curpass "+curpass);
 %>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/blueimp-md5/2.10.0/js/md5.js
"></script>
  <script>


function funSub(nlyteUserId)
{	
	var curP = document.getElementById("curPassword").value;
	var cnfpwd = document.getElementById("cnfpwd").value;
	var newpwd = document.getElementById("newpwd").value;
    var curPassword = md5(curP);
      
	var curpass1="<%=curpass%>";
	if(curP=="")
	{
		alert("Current Password must not be empty");
		return false;
	}
	if(newpwd=="")
	{
	alert("New Password must not be empty");
	return false;
	}
	if(cnfpwd=="")
	{
	alert("Confirm New Password must not be empty");
	return false;
	}
	if(curPassword!=curpass1)
	{
		alert("Current Password is wrong");
		return false;
	}		
	if (newpwd == cnfpwd) {
					if (curPassword == cnfpwd ||  curPassword == newpwd) {
						alert("Current Password and Confirm Password must not be same");
						return false;
						
					} else {
						document.myForm.action="changePassword?userId="+ nlyteUserId;	
						document.myForm.submit();
					}
				} else {
					alert("New Password and Confirm Password must be same");
					return false;
				}	
}
			
		</script>