<%@ page contentType="text/html; charset = UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String cmsId = (String) request.getParameter("cmsId");
%>
<script>
function myFunExport() {		
	window.location.href = "transMasterDataExport";			
}
	</script>
<!-- Page Content -->
<div id="page-wrapper">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12">
			<div class="panel panel-default" style="margin-top: 50px;">
			<div class="panel-heading">
						<h4>Material Lookup - View Matched data</h4>
					</div>
				
					<!-- /.panel-heading -->
					<div class="panel-body">
						<b style="color: blue">*note:Where Blue Color Indicates Nlyte
							Data</b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><br>Black is Customer
							Data</b>
						<div align="right">
							<a href="#" class="btn"
								style="color: #fff; background-color: #5bc0de; border-color: #46b8da;"
								onClick="myFunExport()">Excel</a> </br>
							<div id="dataTables-example_wrapper"
								class="dataTables_wrapper form-inline dt-bootstrap no-footer">
								<div class="row">
									<div class="col-sm-12">
										<div class="table-responsive">
											<table width="100%"
												class="table table-striped table-bordered table-hover dataTable no-footer dtr-inline"
												id="dataTables-example" role="grid"
												aria-describedby="dataTables-example_info"
												style="width: 100%;">
												<thead>
													<tr role="row" class="bg-warning">
														<th class="sorting_asc" tabindex="0"
															aria-controls="dataTables-example" rowspan="1"
															colspan="1" aria-sort="ascending"
															aria-label="Name: activate to sort column descending"
															style="width: 171px;">S.No</th>
														<th class="sorting_asc" tabindex="0"
															aria-controls="dataTables-example" rowspan="1"
															colspan="1" aria-sort="ascending"
															aria-label="Name: activate to sort column descending"
															style="width: 171px;">Customer Model</th>
														<th class="sorting_asc" tabindex="0"
															aria-controls="dataTables-example" rowspan="1"
															colspan="1" aria-sort="ascending"
															aria-label="Name: activate to sort column descending"
															style="width: 171px; color: #2966FE">Nlyte Material
															name</th>
														<th class="sorting" tabindex="0"
															aria-controls="dataTables-example" rowspan="1"
															colspan="1"
															aria-label="Designation: activate to sort column ascending"
															style="width: 188px; color: #2966FE">Match
															percentage</th>

														<th class="sorting_asc" tabindex="0"
															aria-controls="dataTables-example" rowspan="1"
															colspan="1" aria-sort="ascending"
															aria-label="Name: activate to sort column descending"
															style="width: 171px; color: #2966FE">Manufacturer</th>
														<th class="sorting" tabindex="0"
															aria-controls="dataTables-example" rowspan="1"
															colspan="1"
															aria-label="Project Name: activate to sort column ascending"
															style="width: 248px; color: #2966FE">Material Type</th>
														<th class="sorting" tabindex="0"
															aria-controls="dataTables-example" rowspan="1"
															colspan="1"
															aria-label="Browser: activate to sort column ascending"
															style="width: 150px; color: #2966FE">Material Sub
															Type</th>

														<th class="sorting" tabindex="0"
															aria-controls="dataTables-example" rowspan="1"
															colspan="1"
															aria-label="Designation: activate to sort column ascending"
															style="width: 188px; color: #2966FE">Power
															Consumption</th>
														<th class="sorting" tabindex="0"
															aria-controls="dataTables-example" rowspan="1"
															colspan="1"
															aria-label="Designation: activate to sort column ascending"
															style="width: 188px; color: #2966FE">Width</th>
														<th class="sorting" tabindex="0"
															aria-controls="dataTables-example" rowspan="1"
															colspan="1"
															aria-label="Designation: activate to sort column ascending"
															style="width: 188px; color: #2966FE">Depth</th>
														<th class="sorting" tabindex="0"
															aria-controls="dataTables-example" rowspan="1"
															colspan="1"
															aria-label="Designation: activate to sort column ascending"
															style="width: 188px; color: #2966FE">Height</th>
														<th class="sorting" tabindex="0"
															aria-controls="dataTables-example" rowspan="1"
															colspan="1"
															aria-label="Designation: activate to sort column ascending"
															style="width: 188px; color: #2966FE">Weight</th>
														<th class="sorting" tabindex="0"
															aria-controls="dataTables-example" rowspan="1"
															colspan="1"
															aria-label="Designation: activate to sort column ascending"
															style="width: 188px; color: #2966FE">Copper Ports</th>
														<th class="sorting" tabindex="0"
															aria-controls="dataTables-example" rowspan="1"
															colspan="1"
															aria-label="Designation: activate to sort column ascending"
															style="width: 188px; color: #2966FE">Fiber Ports</th>


														<th class="sorting_asc" tabindex="0"
															aria-controls="dataTables-example" rowspan="1"
															colspan="1" aria-sort="ascending"
															aria-label="Name: activate to sort column descending"
															style="width: 171px;">Manufacturer</th>
														<th class="sorting" tabindex="0"
															aria-controls="dataTables-example" rowspan="1"
															colspan="1"
															aria-label="Project Name: activate to sort column ascending"
															style="width: 248px;">Material Type</th>
														<th class="sorting" tabindex="0"
															aria-controls="dataTables-example" rowspan="1"
															colspan="1"
															aria-label="Browser: activate to sort column ascending"
															style="width: 150px;">Material Sub Type</th>
														<th class="sorting" tabindex="0"
															aria-controls="dataTables-example" rowspan="1"
															colspan="1"
															aria-label="Designation: activate to sort column ascending"
															style="width: 188px;">Width</th>
														<th class="sorting" tabindex="0"
															aria-controls="dataTables-example" rowspan="1"
															colspan="1"
															aria-label="Designation: activate to sort column ascending"
															style="width: 188px;">Depth</th>
														<th class="sorting" tabindex="0"
															aria-controls="dataTables-example" rowspan="1"
															colspan="1"
															aria-label="Designation: activate to sort column ascending"
															style="width: 188px;">Height</th>
														<th class="sorting" tabindex="0"
															aria-controls="dataTables-example" rowspan="1"
															colspan="1"
															aria-label="Designation: activate to sort column ascending"
															style="width: 188px;">Weight</th>
														<th class="sorting" tabindex="0"
															aria-controls="dataTables-example" rowspan="1"
															colspan="1"
															aria-label="Designation: activate to sort column ascending"
															style="width: 188px;">Copper Ports</th>
														<th class="sorting" tabindex="0"
															aria-controls="dataTables-example" rowspan="1"
															colspan="1"
															aria-label="Designation: activate to sort column ascending"
															style="width: 188px;">Fiber Ports</th>
														<th class="sorting" tabindex="0"
															aria-controls="dataTables-example" rowspan="1"
															colspan="1"
															aria-label="Designation: activate to sort column ascending"
															style="width: 188px;">Undefined Ports</th>
														<th class="sorting" tabindex="0"
															aria-controls="dataTables-example" rowspan="1"
															colspan="1"
															aria-label="Designation: activate to sort column ascending"
															style="width: 188px;">Power Consumption</th>

													</tr>
												</thead>
												<tbody>

													<form name="myForm" method="post">
														<c:forEach var="customer" items="${nlyteCustMastDataBean}"
															varStatus="counter">
															<%-- <c:choose>
														<c:when test="${customer.nCustDataTransStg.nmsId==0}">
															<tr class="gradeA odd" role="row" style="color: red">
														</c:when>
														<c:otherwise>
															<tr class="gradeA odd" role="row" style="color: green">
														</c:otherwise>
													</c:choose> --%>
															<tr class="gradeA odd" role="row">
																<td>${counter.count}</td>
																<td class="sorting_1">${customer.nCustDataTransStg.modelTx}</td>
																<td class="center" style="color: #2966FE">${customer.nMasterDataTrans.materialNmTx}</td>
																<td class="center" style="color: #2966FE">${customer.nCustDataTransStg.matchPercentage}</td>
																<td class="center" style="color: #2966FE">${customer.nMasterDataTrans.manufacturerTx}</td>
																<td class="center" style="color: #2966FE">${customer.nMasterDataTrans.materialTypTx}</td>
																<td class="center" style="color: #2966FE">${customer.nMasterDataTrans.materialSubTypTx}</td>
																<td class="center" style="color: #2966FE">${customer.nMasterDataTrans.powerConsmptNm}</td>
																<td class="center" style="color: #2966FE">${customer.nMasterDataTrans.widthNm}</td>
																<td class="center" style="color: #2966FE">${customer.nMasterDataTrans.depthNm}</td>
																<td class="center" style="color: #2966FE">${customer.nMasterDataTrans.heightNm}</td>
																<td class="center" style="color: #2966FE">${customer.nMasterDataTrans.weightNm}</td>
																<td class="center" style="color: #2966FE">${customer.nMasterDataTrans.copperPortsTx}</td>
																<td class="center" style="color: #2966FE">${customer.nMasterDataTrans.fiberPortsTx}</td>

																<td class="center">${customer.nCustDataTransStg.manufacturerTx}</td>
																<td class="center">${customer.nCustDataTransStg.materialTypTx}</td>
																<td class="center">${customer.nCustDataTransStg.materialSubTypTx}</td>
																<td class="center">${customer.nCustDataTransStg.widthNm}</td>
																<td class="center">${customer.nCustDataTransStg.depthNm}</td>
																<td class="center">${customer.nCustDataTransStg.heightNm}</td>
																<td class="center">${customer.nCustDataTransStg.weightNm}</td>
																<td class="center">${customer.nCustDataTransStg.copperPortsTx}</td>
																<td class="center">${customer.nCustDataTransStg.fiberPortsTx}</td>
																<td class="center">${customer.nCustDataTransStg.undefPortsTx}</td>
																<td class="center">${customer.nCustDataTransStg.powerConsmptNm}</td>


															</tr>
														</c:forEach>
													</form>

												</tbody>
											</table>
											<div align="left">
					<a href="#" class="btn"
				style="color: #fff; background-color: #5bc0de; border-color: #46b8da;"
				onClick="back()">Back</a></div>

		</div>
										</div>
									</div>
								</div>

							</div>
						</div>
					</div>
					<!-- /.table-responsive -->
				</div>
				<!-- /.panel-body -->
			</div>
		<script>
		function back() {
		//alert(nmsId)
		window.location.href="nlyteCustomerrocessedList";
		/* document.myForm.action = "transMasterDataView?nmsId=" + nmsId;
		document.myForm.submit(); */		
		}
		</script>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/custom.css">

		<!-- ... Your content goes here ... -->

	</div>
	<%-- 	<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
 --%>
	<script src="<%=request.getContextPath()%>/js/jquery.datatable.min.js"></script>

	<script
		src="<%=request.getContextPath()%>/js/datatable.bootstarp.min.js"></script>
	<script src="js/metisMenu.min.js"></script>
	<script src="js/startmin.js"></script>
	<script>
		function myFunExport() {
			//alert("CMSID ");
			var cmsId =
	<%=cmsId%>
		;
			//alert("cmsid "+cmsId);
			window.location.href = "transDataViewExport?cmsId=" + cmsId;
			/* document.myForm.action = "NlyteReviewCustProcess?cmsId=" + cmsId;
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