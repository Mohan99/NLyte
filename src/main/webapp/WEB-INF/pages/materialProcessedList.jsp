<%@ page contentType="text/html; charset = UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!-- Page Content -->
<div id="page-wrapper">
	<div class="container-fluid">

		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default" style="margin-top: 50px;">
					<div class="panel-heading">
						<h4>Material Lookup - View Processed Data</h4>
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
										style="width: 100%;">
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
												<!-- <th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Browser: activate to sort column ascending"
													style="width: 150px;">Date</th> -->
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Designation: activate to sort column ascending"
													style="width: 188px;">View Matched</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Project Name: activate to sort column ascending"
													style="width: 50px;">View UnMatched</th>
													<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Project Name: activate to sort column ascending"
													style="width: 50px;">View MultipleMatched</th>
												
											</tr>
										</thead>

										<tbody>
											
											<form name="myForm" method="post">
												

												<c:forEach var="cust" items="${nlyteCustDataStgLst}"
													varStatus="counter">


													<tr class="gradeA odd" role="row">

														<td>${counter.count}</td>
														<td class="center">${cust.loginId}</td>
														<td class="center">${cust.userDisplayNameTx}</td>
														<td class="center">${cust.descriptionTx}</td>
														<%-- <td class="center">${cust.lstUpdtDtm}</td> --%>

														
															<td class="center"><h5 style="color: red">(${cust.matchCount})</h5><a href="#"
															onClick="myFunViewProcessed('${cust.cmsId}')"
															class="btn btn-warning"><i class="fa fa-edit fa-fw"></i></a></td>
														<td class="center"><h5 style="color: red">(${cust.unMatchCount})</h5><a href="#"
															onClick="myFunViewUnprocessd('${cust.cmsId}')"
															class="btn btn-warning"><i class="fa fa-edit fa-fw"></i></a></td>
															<td class="center"><h5 style="color: red">(${cust.multiMatchedCount})</h5><a href="#"
															onClick="myFunViewmultiMatched('${cust.cmsId}')"
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
	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/css/custom.css">
	<script src="<%=request.getContextPath()%>/js/jquey.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.datatable.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/js/datatable.bootstarp.min.js"></script>
	<script>
		function myFunReview(cmsId) {
			window.location.href = "NlyteReviewCustProcess?cmsId=" + cmsId;
			/* document.myForm.action = "NlyteReviewCustProcess?cmsId=" + cmsId;
			document.myForm.submit(); */
		}

		function myFun(cmsId) {
			window.location.href = "NlyteCustProcess?cmsId=" + cmsId;
			/* document.myForm = 
			document.myForm.submit(); */
		}
		function myFunViewProcessed(cmsId) {
			window.location.href = ("transDataViewProcessed?cmsId=" + cmsId);
			/* document.myForm = "transDataView?cmsId=" + cmsId;
			document.myForm.submit(); */
		}

		function myFunViewUnprocessd(cmsId) {
			window.location.href = ("transDataViewUnprocessed?cmsId=" + cmsId);
			/* document.myForm = "transDataView?cmsId=" + cmsId;
			document.myForm.submit(); */
		}
		function myFunViewmultiMatched(cmsId) {
			window.location.href = ("transDataViewMultiMatched?cmsId=" + cmsId);
			/* document.myForm = "transDataView?cmsId=" + cmsId;
			document.myForm.submit(); */
		}
		
		function myFunExport(cmsId) {
			window.location.href = "transDataViewExport?cmsId=" + cmsId;
			/* document.myForm.submit(); 
			document.myForm = "transDataViewExport?cmsId=" + cmsId;
			document.myForm.submit(); */
		}
	</script>

	<script>
		$(document).ready(
				function() {
					$('#dataTables-example').DataTable(
							{
								"pagingType" : "full_numbers",
								"lengthMenu" : [ [ 3, 5, 10, 25, 50,100,-1 ],
										[ 3, 5, 10, 25, 50, 100 ] ],
								"pageLength" : 5
							});
				});
	
		
	</script>