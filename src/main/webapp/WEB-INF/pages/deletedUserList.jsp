<%@ page contentType = "text/html; charset = UTF-8" %>
<%@ taglib prefix = "s" uri = "/struts-tags" %> 
<script type="text/javascript">
function myFun(userId)
{
window.location.href="nlyteEditUser?userId="+userId;
/* document.myForm.action ="nlyteEditUser?userId="+userId;

document.myForm.submit(); */
}
function myFunDelete(nlyteUserId)
{
	window.location.href="nlyteActivateUser?nlyteUserId="+nlyteUserId;
/* document.myForm.action ="nlyteDeleteUser?nlyteUserId="+nlyteUserId;
document.myForm.submit(); */
}


</script>
<!-- Page Content -->
  <div id="page-wrapper">
    <div class="container-fluid">
      <div class="row">				
               <div class="col-lg-12">
          
          <div class="panel panel-default" style="margin-top: 50px;">
			<div class="panel-heading">
						<h4>User Management - Deleted User List</h4>
					</div>
          		  			  
            <!-- /.panel-heading -->
            <div class="panel-body">
              <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                <div class="row">
                  <div class="col-sm-12">
                    <table width="100%" class="table table-striped table-bordered table-hover dataTable no-footer dtr-inline" id="dataTables-example" role="grid" aria-describedby="dataTables-example_info" style="width: 100%; font-size: 13.5px; margin-top: 10px;">
                      <thead>
                        <tr role="row" class="bg-warning">
                          <th class="sorting_asc" tabindex="0" aria-controls="dataTables-example" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Name: activate to sort column descending" style="width: 171px;">User ID </th>
                          <th class="sorting" tabindex="0" aria-controls="dataTables-example" rowspan="1" colspan="1" aria-label="Browser: activate to sort column ascending" style="width: 207px;">User Display Name</th>
                          <th class="sorting" tabindex="0" aria-controls="dataTables-example" rowspan="1" colspan="1" aria-label="Designation: activate to sort column ascending" style="width: 188px;">First Name</th>
                          <th class="sorting" tabindex="0" aria-controls="dataTables-example" rowspan="1" colspan="1" aria-label="Project Name: activate to sort column ascending" style="width: 148px;">Last Name</th>
                          <th class="sorting" tabindex="0" aria-controls="dataTables-example" rowspan="1" colspan="1" aria-label="Project Name: activate to sort column ascending" style="width: 148px;">Middle Name</th>
                          <th class="sorting" tabindex="0" aria-controls="dataTables-example" rowspan="1" colspan="1" aria-label="Project Name: activate to sort column ascending" style="width: 148px;">Email ID</th>
                          <th class="sorting" tabindex="0" aria-controls="dataTables-example" rowspan="1" colspan="1" aria-label="Project Name: activate to sort column ascending" style="width: 148px;">Contact No</th>
                          <th style="width:60px;">Activate</th>
                        </tr>
                      </thead>
                      <tbody>
                      <form name="myForm" method="post">
                       
                       <s:iterator value = "nlyteUsers"> 
                   
                    <tr class="gradeA odd " role="row">
                          <td class="sorting_1"> <s:property  value = "loginId"/> </td>
                            <td class="center">  <s:property  value = "userDisplayNameTx"/></td>
                          <td class="center"> <s:property  value = "userFirstNameTx"/> </td>
                          <td class="center"><s:property  value = "userLastNameTx"/>   </td>
                          <td class="center"><s:property  value = "userMiddleNameTx"/>  </td>  	
						<td class="center"><s:property  value = "emailId"/>  </td>  	
						<td class="center"><s:property  value = "mobileTx"/>  </td>						
						<td class="disabled"><a href="#" class="btn btn-info" onClick="myFunDelete('<s:property  value = "nlyteUserId"/>')">Activate</a></td>
		  
<%-- 						  <td class="center"><a href="#" onClick="myFun(<s:property  value = "loginId"/>)" class="btn btn-warning"><i class="fa fa-edit fa-fw"></i></a></td>
 --%>						  
                        </tr>
                                  
                       </s:iterator> 
                      </form>      
  
                      </tbody>
                    </table>
                  </div>
                </div>
                  
                </div>
              </div>
              <!-- /.table-responsive --> 
            </div>
            <!-- /.panel-body --> 
          </div>
                
                
                
            </div>
      
      <!-- ... Your content goes here ... --> 
      
    </div>
  </div>
  <script src="<%=request.getContextPath()%>/js/jquery.datatable.min.js"></script>

	<script
		src="<%=request.getContextPath()%>/js/datatable.bootstarp.min.js"></script>
	<script src="js/metisMenu.min.js"></script>
	<script src="js/startmin.js"></script>
	<script>
		$(document).ready(
				function() {
					$('#dataTables-example').DataTable(
							{
								"pagingType" : "full_numbers",
								"lengthMenu" : [ [ 3, 5, 10, 25, 50,100, -1 ],
										[ 3, 5, 10, 25, 50, 100 ] ],
								"pageLength" : 5
							});
				});
	
		
	</script>