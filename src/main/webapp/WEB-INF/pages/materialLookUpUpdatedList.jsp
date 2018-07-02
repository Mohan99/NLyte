<%@ page contentType="text/html; charset = UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Page Content -->
<style>
.loader {
	/* position: fixed;
	left: 0px;
	top: 0px; */
	width: 100%;
	height: 70px;
	/* z-index: 9999; */
	background: url('img/tenor.gif') 50% 50% no-repeat rgb(249, 249, 249);
	/* opacity: .8; */
}
</style>

<div id="page-wrapper">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default" style="margin-top: 50px;">
					<div class="panel-heading">
						<h4>Material Lookup List - View</h4>
					</div>
					<div class="loader"></div>
					<!-- /.panel-heading -->
					<div class="panel-body">
						<div id="dataTables-example_wrapper"
							class="dataTables_wrapper form-inline dt-bootstrap no-footer">
							<div class="row">
								<div class="col-sm-12">
									<div class="table-responsive">
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
														style="width: 171px;">Model</th>
													<!-- <th class="sorting_asc" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-sort="ascending"
													aria-label="Name: activate to sort column descending"
													style="width: 171px;">Updated Model</th> -->
													<th class="sorting_asc" tabindex="0"
														aria-controls="dataTables-example" rowspan="1" colspan="1"
														aria-sort="ascending"
														aria-label="Name: activate to sort column descending"
														style="width: 171px;">Manufacturer</th>
													<th class="sorting" tabindex="0"
														aria-controls="dataTables-example" rowspan="1" colspan="1"
														aria-label="Project Name: activate to sort column ascending"
														style="width: 248px;">Material Type</th>
													<th class="sorting" tabindex="0"
														aria-controls="dataTables-example" rowspan="1" colspan="1"
														aria-label="Browser: activate to sort column ascending"
														style="width: 150px;">Material Sub Type</th>


													<th class="sorting" tabindex="0"
														aria-controls="dataTables-example" rowspan="1" colspan="1"
														aria-label="Designation: activate to sort column ascending"
														style="width: 188px;">Width</th>
													<th class="sorting" tabindex="0"
														aria-controls="dataTables-example" rowspan="1" colspan="1"
														aria-label="Designation: activate to sort column ascending"
														style="width: 188px;">Depth</th>
													<th class="sorting" tabindex="0"
														aria-controls="dataTables-example" rowspan="1" colspan="1"
														aria-label="Designation: activate to sort column ascending"
														style="width: 188px;">Height</th>
													<th class="sorting" tabindex="0"
														aria-controls="dataTables-example" rowspan="1" colspan="1"
														aria-label="Designation: activate to sort column ascending"
														style="width: 188px;">Weight</th>
													<th class="sorting" tabindex="0"
														aria-controls="dataTables-example" rowspan="1" colspan="1"
														aria-label="Designation: activate to sort column ascending"
														style="width: 188px;">Copper Ports</th>
													<th class="sorting" tabindex="0"
														aria-controls="dataTables-example" rowspan="1" colspan="1"
														aria-label="Designation: activate to sort column ascending"
														style="width: 188px;">Fiber Ports</th>
													<th class="sorting" tabindex="0"
														aria-controls="dataTables-example" rowspan="1" colspan="1"
														aria-label="Designation: activate to sort column ascending"
														style="width: 188px;">Undefined Ports</th>
													<th class="sorting" tabindex="0"
														aria-controls="dataTables-example" rowspan="1" colspan="1"
														aria-label="Designation: activate to sort column ascending"
														style="width: 188px;">Power Consumption</th>

												</tr>
											</thead>
											<tbody>
												<form name="myForm" method="post">


													<c:forEach var="customer" items="${nlyteCustDataTransStg}"
														varStatus="counter">

														<tr class="gradeA odd" role="row">
															<td>${counter.count}</td>
															<td class="sorting_1">${customer.modelTx}</td>
															<td class="center">${customer.manufacturerTx}</td>
															<td class="center">${customer.materialTypTx}</td>
															<td class="center">${customer.materialSubTypTx}</td>
															<td class="center">${customer.widthNm}</td>
															<td class="center">${customer.depthNm}</td>
															<td class="center">${customer.heightNm}</td>
															<td class="center">${customer.weightNm}</td>
															<td class="center">${customer.copperPortsTx}</td>
															<td class="center">${customer.fiberPortsTx}</td>
															<td class="center">${customer.undefPortsTx}</td>
															<td class="center">${customer.powerConsmptNm}</td>
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
					<!-- /.table-responsive -->
				</div>
				<!-- /.panel-body -->
			</div>



		</div>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/custom.css">

		<!-- ... Your content goes here ... -->

	</div>
	<script>
		function back() {
		//alert(nmsId)
		window.location.href="nlyteCustomerImportList";
		/* document.myForm.action = "transMasterDataView?nmsId=" + nmsId;
		document.myForm.submit(); */		
		}
		</script>
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
								"lengthMenu" : [ [ 3, 5, 10, 25, 50, 100, -1 ],
										[ 3, 5, 10, 25, 50, 100 ] ],
								"pageLength" : 5
							});
				});
	</script>

	<script type="text/javascript">
		$(window).load(function() {
			$(".loader").fadeOut("slow");
		});
	
		
	</script>