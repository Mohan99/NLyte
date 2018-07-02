<%@ page contentType = "text/html; charset = UTF-8" %>
<%@ taglib prefix = "s" uri = "/struts-tags" %>
<!-- Page Content -->
  <div id="page-wrapper">
    <div class="container-fluid">
      <div class="row">
        <div class="col-lg-12">
          <h3 class="page-header">Edit User</h3>
             
          <div class="col-md-6">
          
          <form class="form-horizontal" action="nlyteUpdateUser"  method="post" >  
          <s:iterator value = "nlyteEditUser"> 
          <input type="hidden" name="nlyteUserId" id="nlyteUserId" value="<s:property  value = "nlyteUserId"/>" />
              <div class="form-group">
              
                <label for=" " class="col-sm-5 control-label">User ID</label>
                <div class="col-sm-7">
                <input type="text"  class="form-control" name="userId"   value="<s:property  value = "loginId"/>"  label="userId"/>
                </div>
              </div>
              <div class="form-group">
                <label for=" " class="col-sm-5 control-label">User First Name</label>
                <div class="col-sm-7">
                 <input type="text"  class="form-control" name="userFirstName"  value="<s:property  value = "userFirstNameTx"/>" label="userFirstNameTx"/>                  
                </div>
              </div>
              <div class="form-group">
                <label for=" " class="col-sm-5 control-label">User Last Name</label>
                <div class="col-sm-7">
                <input type="text"    class="form-control" name="userLastName" value="<s:property  value = "userLastNameTx"/>" label="userLastNameTx"/>                 
                </div>
              </div>
              <div class="form-group">
                <label for=" " class="col-sm-5 control-label">User Middle Name</label>
                <div class="col-sm-7">
                <input type="text"  class="form-control" name="userMiddleName" value="<s:property  value = "userMiddleNameTx"/>" label="userMiddleNameTx"/>                  
                </div>
              </div>
              <!-- <div class="form-group">
                <label for=" " class="col-sm-5 control-label">Set Password</label>
                <div class="col-sm-7">
                  <input type="password" name="Password" class="form-control" id="" placeholder="Set Password">
                </div>
              </div>
                              <div class="form-group">
                <label for=" " class="col-sm-5 control-label">Confirm Password </label>
                <div class="col-sm-7">
                  <input type="password" class="form-control" id="" placeholder="Confirm Password">
                </div>
              </div> -->
              
              <div class="form-group">
                <label for=" " class="col-sm-5 control-label">Mobile No</label>
                <div class="col-sm-7">
                 <input type="text" class="form-control" name="mobileNo" value="<s:property  value = "mobileTx"/>" label="mobileTx"/>                    
                </div>
              </div>
              <div class="form-group">
                <label for=" " class="col-sm-5 control-label">Email ID</label>
                <div class="col-sm-7">
                  <input type="text" name="emailId"  value="<s:property  value = "emailId"/>"   class="form-control" id="" >
                </div>
              </div>
              <!--<div class="form-group">
                <label for=" " class="col-sm-5 control-label">Contry</label>
                <div class="col-sm-7">
                  <select class="form-control">
                    <option>--Option--</option>
                    <option>India</option>
                    <option>USA</option>
                    <option>Australia</option>
                  </select>
                </div>
              </div>-->
              <!--<div class="form-group">
    <label for=" " class="col-sm-5 control-label">City</label>
    <div class="col-sm-7">
      <select class="form-control">
                 <option>--Option--</option>
                  <option>Delhi</option>
                  <option>Bangalore</option>
                  <option>Hyderabad</option>
                  
                </select>
    </div>
    
  </div>-->
           <!--    <div class="form-group">
                <label for=" " class="col-sm-5 control-label">User Active</label>
                <div class="col-sm-7">
                  <input type="checkbox" name="userActive" checked data-toggle="toggle" data-on="Active" data-off="In Active" data-onstyle="success" data-offstyle="danger">
                </div> -->
              </div>
              <div class="form-group">
                <div class="col-sm-offset-4 col-sm-8" style="margin-top:10px;">
                  <button type="submit" class="btn btn-primary">Update</button>
         <!--        </div>
              </div> -->

              
        
        </s:iterator>  
        
    </form> 
          </div>
        </div>
      </div>
      
      <!-- ... Your content goes here ... --> 
      
    </div>
  </div>