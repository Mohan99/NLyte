<%@ page contentType="text/html; charset = UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<%-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.js" ></script>--%>

<script src="http://malsup.github.com/jquery.form.js"></script>
<style type="text/css">
#progressbox {
	width: 100%;
	background-color: none;
}

#progressbar {
	width: 100%;
	height: 70px;
	background: url('<%=request.getContextPath()%>/img/tenor.gif') 50% 50%
		no-repeat rgb(249, 249, 249);
}
</style>
</head>


<%-- <script type="text/javascript">
$(window).load(function() {
    $(".loader").fadeOut("slow");
})
</script> --%>
<!--<script>
	function myFun(cmsId) {
		document.myForm.action = "NlyteCustProcess?cmsId=" + cmsId;
		document.myForm.submit();
	}
	function myFunView(cmsId) {
		document.myForm.action = "transDataView?cmsId=" + cmsId;
		document.myForm.submit();
	}
	
	function myFunExport(cmsId) {
		document.myForm.action = "transDataViewExport?cmsId=" + cmsId;
		document.myForm.submit();
	}
</script>-->
<!-- Page Content -->

<div id="page-wrapper">
	<div class="container-fluid">

		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default" style="margin-top: 50px;">
					<div class="panel-heading">
						<h4>Material Lookup - Material Lookup List</h4>
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
													style="width: 188px;">Status</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Project Name: activate to sort column ascending"
													style="width: 50px;">View</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Project Name: activate to sort column ascending"
													style="width: 50px;">Delete</th>
												<!-- <th style="width:60px;">Edit</th>-->
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
														<td class="center"><c:choose>
																<c:when test="${cust.status=='Y'}">
																	<c:choose>
																		<c:when test="${cust.processStatus=='Y'}">Processed<br />
																		</c:when>
																		<c:otherwise>
																			<a href="#" class="btn btn-info"
																				onClick="myFunReview('${cust.cmsId}')">Review</a>
																			<br />
																		</c:otherwise>
																	</c:choose>
																	<br />
																</c:when>
																<c:otherwise>
																	<a href="#" class="btn "
																		style="color: #fff; background-color: #5bc0de; border-color: #46b8da;"
																		onClick="myFun('${cust.cmsId}')">Process</a>
																	<br />
																</c:otherwise>
															</c:choose></td>
														<td class="center"><a href="#"
															onClick="myFunView('${cust.cmsId}')"
															class="btn btn-warning"><i class="fa fa-edit fa-fw"></i></a></td>
														<td class="center"><a href="#"
															onClick="myFunDelete('${cust.cmsId}')"
															class="btn btn-warning"><i class="fa fa-trash-o"></i></a></td>

													</tr>
												</c:forEach>
												<div align="center" id="progressbox" style="display: none;">
													<div id="progressbar"></div>
												</div>
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
	<%-- 	 <script src="<%=request.getContextPath()%>/js/jquery.min.js"></script> 
 --%>
	<script src="<%=request.getContextPath()%>/js/jquery.datatable.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/js/datatable.bootstarp.min.js"></script>
	<script>
		function myFunReview(cmsId) {
			window.location.href = "NlyteReviewCustProcess?cmsId=" + cmsId;
		}

		function myFun(cmsId) {
			$(document).ready(function() {

				$.ajax({
					url : "NlyteCustProcess?cmsId=" + cmsId,
					processData : $("#progressbox").show(),
					success : function(result) {
						window.location = "nlyteCustomerImportList";
					}
				});

			});
		}
		function myFunView(cmsId) {

			window.location.href = ("transDataView?cmsId=" + cmsId);
		}

		function myFunDelete(cmsId) {
			var status = confirm("Are you sure! You want to delete?")
			if (status)
				window.location.href = ("nlyteCustDelete?cmsId=" + cmsId);
		}

		function myFunExport(cmsId) {
			window.location.href = "transDataViewExport?cmsId=" + cmsId;
		}
	</script>
	<script type="text/javascript">
		
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