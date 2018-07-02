<%@ page contentType="text/html; charset = UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="http://malsup.github.com/jquery.form.js"></script>
<%-- <style type="text/css">
 #progressbox {
	width: 50%;
	background-color: block;
}

#progressbar {
	width: 100%;
	height: 70px;
	background: url('../img/tenor.gif') 50% 50%
		no-repeat rgb(249, 249, 249);
} 

</style> --%>

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
						<h4>Nlyte Master Data - View</h4>
					</div>
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
													style="width: 171px;">Material Number</th>
												<th class="sorting_asc" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-sort="ascending"
													aria-label="Name: activate to sort column descending"
													style="width: 171px;">Material Name</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Project Name: activate to sort column ascending"
													style="width: 248px;">Model</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Browser: activate to sort column ascending"
													style="width: 150px;">Manufacturer</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Designation: activate to sort column ascending"
													style="width: 188px;">materialType</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Designation: activate to sort column ascending"
													style="width: 188px;">Material Sub Type</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Project Name: activate to sort column ascending"
													style="width: 50px;">Width</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Project Name: activate to sort column ascending"
													style="width: 50px;">Depth</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Project Name: activate to sort column ascending"
													style="width: 50px;">Height</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Project Name: activate to sort column ascending"
													style="width: 50px;">Weight</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Project Name: activate to sort column ascending"
													style="width: 50px;">Copper Ports</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Project Name: activate to sort column ascending"
													style="width: 50px;">Fiber Ports</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Project Name: activate to sort column ascending"
													style="width: 50px;">Undef Ports</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Project Name: activate to sort column ascending"
													style="width: 50px;">Power Consumption</th>

											</tr>
										</thead>
										<tbody>
											<%-- <s :iterator value = "nlyteCustDataStgLst">  --%>
											<form name="myForm" id="" onload="myFunView" method="post">


												<c:forEach var="master" items="${nlyteMasterDataTransStg}"
													varStatus="counter">


													<tr class="gradeA odd" role="row">
														<td>${counter.count}</td>
														<td class="sorting_1">${master.materialNm}</td>
														<td class="center">${master.materialNmTx}</td>
														<td class="center">${master.modelTx}</td>
														<td class="center">${master.manufacturerTx}</td>
														<td class="center">${master.materialTypTx}</td>
														<td class="center">${master.materialSubTypTx}</td>
														<td class="center">${master.widthNm}</td>
														<td class="center">${master.depthNm}</td>
														<td class="center">${master.heightNm}</td>
														<td class="center">${master.weightNm}</td>
														<td class="center">${master.copperPortsTx}</td>
														<td class="center">${master.fiberPortsTx}</td>
														<td class="center">${master.undefPortsTx}</td>
														<td class="center">${master.powerConsmptNm}</td>


													</tr>
												</c:forEach>


											</form>

										</tbody>
									</table>
								</div>
							</div>
						</div>

					</div>

				</div>

				<!-- /.table-responsive -->


				<!-- /.panel-body -->
			</div>




		</div>
	</div>
</div>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/custom.css">

<!-- ... Your content goes here ... -->


<%-- 	 <script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
 --%>
<script src="<%=request.getContextPath()%>/js/jquery.datatable.min.js"></script>
<script
	src="<%=request.getContextPath()%>/js/datatable.bootstarp.min.js"></script>
<script src="js/metisMenu.min.js"></script>
<script src="js/startmin.js"></script>
<script>
	function myFunView() {
		/*window.location.href="transDataView?cmsId=" + cmsId;*/
		$(document).ready(function() {

			$.ajax({
				url : "transMasterDataView",
				processData : $("#progressbox").show(),
				success : function(result) {
					$("#progressbox").display = "none"

					//window.location="transMasterDataView";
				}
			});

		});
		/* document.myForm.action = "transDataView?cmsId=" + cmsId;
		document.myForm.submit(); */
	}
</script>

<script type="text/javascript">
	$(window).load(function() {
		$(".loader").fadeOut("slow");
	});
</script>

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
