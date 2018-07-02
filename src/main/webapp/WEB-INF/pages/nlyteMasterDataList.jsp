<%@ page contentType="text/html; charset = UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script>
	function myFunView(nmsId) {
		//alert(nmsId)
		window.location.href="transMasterDataView?nmsId=" + nmsId;
		/* document.myForm.action = "transMasterDataView?nmsId=" + nmsId;
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
						<h4>Import Material Data - Nlyte Master</h4>
					</div>

					<!-- /.panel-heading -->
					<div class="panel-body">
					<div align="right">
					<a href="#" class="btn"
				style="color: #fff; background-color: #5bc0de; border-color: #46b8da;"
				onClick="myFunExport()">Excel</a></div>
						<div id="dataTables-example_wrapper"
							class="dataTables_wrapper form-inline dt-bootstrap no-footer">
							<div class="row">
								<div class="col-sm-12">
									<table width="100%"
										class="table table-striped table-bordered table-hover dataTable no-footer dtr-inline"
										id="dataTables-example" role="grid"
										aria-describedby="dataTables-example_info"
										style="width: 100%; margin-top: 10px;">
										<thead>
											<tr role="row" class="bg-warning">
												<th class="sorting_asc" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-sort="ascending"
													aria-label="Name: activate to sort column descending"
													style="width: 171px;">Serial Number</th>
												<th class="sorting_asc" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-sort="ascending"
													aria-label="Name: activate to sort column descending"
													style="width: 171px;">User Name</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Project Name: activate to sort column ascending"
													style="width: 248px;">File Name</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Browser: activate to sort column ascending"
													style="width: 150px;">Created Date</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Browser: activate to sort column ascending"
													style="width: 150px;">Updated Date</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Project Name: activate to sort column ascending"
													style="width: 50px;">View</th>
												<!-- <th style="width:60px;">Edit</th>-->
											</tr>
										</thead>
										<tbody>
											<form name="myForm" method="post">
												<%-- <s :iterator value = "nlyteCustDataStgLst">  --%>
												<%
													int counter = 1;
												%>
												<s:iterator value="nlyteMasterDataList">

													<tr class="gradeA odd " role="row">
														<td class="sorting_1"><%=counter%></td>
														<td class="center"><s:property
																value="userDisplayNameTx" /></td>
														<td class="center"><s:property value="descriptionTx" /></td>
														<td class="center"><s:property value="createdDt" /></td>
														<td class="center"><s:property value="lstUpdtDtm" /></td>
														<td class="center"><a href="#"
															onClick="myFunView('<s:property  value = "nmsId"/>')"
															class="btn btn-warning"><i class="fa fa-edit fa-fw"></i></a>
															<input type="hidden" name="nmsid"
															value="<s:property  value = "nmsId"/>" /></td>
													</tr>
													<%
													counter++;
												%>
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
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/custom.css">

		<!-- ... Your content goes here ... -->

	</div>
	<script>
		function myFunExport() {		
			window.location.href = "transMasterDataExport";			
		}
	</script>
	<%-- <script src="<%=request.getContextPath()%>/js/jquey.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.datatable.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/datatable.bootstarp.min.js"></script>
	
	<script>
		$(document).ready(
				function() {
					$('#dataTables-example').DataTable(
							{
								"pagingType" : "full_numbers",
								"lengthMenu" : [ [ 3, 5, 10, 25, 50,100,-1 ],
										[ 3, 5, 10, 25, 50, 100] ],
								"pageLength" : 5
							});
				});	
	</script> --%>