<%@ page contentType="text/html; charset = UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	function myFun(cmsId) {
		document.myForm.action = "NlyteCustProcess?cmsId=" + cmsId;
		document.myForm.submit();
	}
	function myFunView(cmsId) {
		document.myForm.action = "transDataView?cmsId=" + cmsId;
		document.myForm.submit();
	}
</script>
<!-- Page Content -->
<div id="page-wrapper">
	<div class="container-fluid">

		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default" style="margin-top: 50px;">
					<div class="panel-heading">
						<h4>Material Lookup List</h4>
					</div>

					<!-- /.panel-heading -->
					<div class="panel-body">
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
													style="width: 171px;">S.No</th>
												<th class="sorting_asc" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-sort="ascending"
													aria-label="Name: activate to sort column descending"
													style="width: 171px;">User ID</th>
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
													style="width: 150px;">Date</th>

												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Project Name: activate to sort column ascending"
													style="width: 50px;">View</th>
												<!-- <th style="width:60px;">Edit</th>-->
											</tr>
										</thead>
										<tbody>
											<%-- <s :iterator value = "nlyteCustDataStgLst">  --%>
											<form name="myForm" method="post">
												<%-- <s:iterator value="nlyteCustDataStgLst">
													<tr class="gradeA odd " role="row">
														<td class="sorting_1"><s:property value="loginId" />
														</td>
														<td class="center"><s:property
																value="userDisplayNameTx" /></td>
														<td class="center"><s:property value="descriptionTx" />
														</td>
														<td class="center"><s:property value="lstUpdtDtm" />
														</td>
														<td class="center"><s:property value="status" /></td>
															<s:if test="#status=="TTT"">
														<s:if test="%{#status=='status'}">
															<td class="center">
															<td class="disabled"><p class="text-warning">Processed</p></td>
														</s:if>
														<s:elseif test="{#status=='N'}">
															<td class="disabled"><a href="#"
																class="btn btn-info"
																onClick="myFun(<s:property  value = "cmsId"/>)">Process</a></td>
														</s:elseif>
														<td class="center"><a href="nlyteCustomerCellList"
															class="btn btn-warning"><i class="fa fa-edit fa-fw"></i></a></td>
														<td class="center"><a href="processData"
															class="btn btn-warning"><i class="fa fa-edit fa-fw"></i></a></td>
													</tr>
												</s:iterator> --%>

												<c:forEach var="cust" items="${nlyteCustDataStgLst}"
													varStatus="counter">


													<tr class="gradeA odd" role="row">
														<td>${counter.count}</td>
														<td class="sorting_1">${cust.loginId}</td>
														<td class="center">${cust.userDisplayNameTx}</td>
														<td class="center">${cust.descriptionTx}</td>
														<td class="center">${cust.lstUpdtDtm}</td>

														<td class="center"><a href="#"
															onClick="myFunView('${cust.cmsId}')"
															class="btn btn-warning"><i class="fa fa-edit fa-fw"></i></a></td>

													</tr>
												</c:forEach>
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
	<%-- <script src="<%=request.getContextPath()%>/js/jquey-1.12.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.datatable.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/datatable.bootstarp.min.js"></script>
	
	<script>
		$(document).ready(
				function() {
					$('#dataTables-example').DataTable(
							{
								"pagingType" : "full_numbers",
								"lengthMenu" : [ [ 3, 5, 10, 25, 50, -1 ],
										[ 3, 5, 10, 25, 50, "All" ] ],
								"pageLength" : 5
							});
				});	
	</script> --%>