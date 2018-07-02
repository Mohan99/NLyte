<%@ page contentType="text/html; charset = UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Page Content -->
<script type="text/javascript">
function myView()
{
    var commentsArray = []
	
	var processSelectedId;
	for (var p = 1; p <= 5; p++) {
     var commentObj = {}
    // var nctObj = {}
     commentObj.selectedVal=document.getElementById("processSelectedId["+p+"]").value; 
     commentObj.nctId=document.getElementById("nctId["+p+"]").value; 
     commentsArray.push(commentObj)
	}

	
	
	document.myForm.action = "processingMultipleCustomer?processSelectedId="+commentsArray;
	document.myForm.submit();
}

</script>
<div id="page-wrapper">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default" style="margin-top: 50px;">
					<div class="panel-heading">
						<h4>Material Lookup Updated List</h4>
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
													style="width: 171px;">NCT ID</th>
												<th class="sorting_asc" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-sort="ascending"
													aria-label="Name: activate to sort column descending"
													style="width: 171px;">Customer Model</th>
												<th class="sorting_asc" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-sort="ascending"
													aria-label="Name: activate to sort column descending"
													style="width: 171px;">Master Model</th>
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
													style="width: 188px;">Power Consuption</th>
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
												<!-- 		<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Designation: activate to sort column ascending"
													style="width: 188px;">Undef Ports</th> -->
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Designation: activate to sort column ascending"
													style="width: 188px;">Created Date</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Designation: activate to sort column ascending"
													style="width: 188px;">Updated Date</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Designation: activate to sort column ascending"
													style="width: 188px;">Status</th>
												<!-- <th style="width:60px;">Edit</th>-->
											</tr>
										</thead>
										<tbody>
											<form name="myForm" method="post">
												<c:forEach var="process" items="${listBean}"
													varStatus="counter">
													<%-- <c:choose>
														<c:when test="${customer.nmsId==0}">
															<tr class="gradeA odd" role="row" style="color: red">
														</c:when>
														<c:otherwise>
															<tr class="gradeA odd" role="row" style="color: green">
														</c:otherwise>
													</c:choose> --%>

													<!-- <tr class="gradeA odd" role="row"> -->
													<td>${counter.count}</td>
													<td class="sorting_1" >${process.nCustDataProcess.nlyteCustomerDataTransStg.nctId}</td>
													<td class="sorting_1">${process.nCustDataTransStg.modelTx}</td>
													<td>
													<input type="hidden" id="nctId[${counter.count}]"   name="nctId[${counter.count}]" value="${process.nCustDataProcess.nlyteCustomerDataTransStg.nctId}">
													
														<%-- <select id="model" name="model"
														onclick="getCustprocessedMultipleData(${process.nCustDataProcess.nlyteCustomerDataTransStg.nctId})"> --%>
														<select id="processSelectedId[${counter.count}]"   name="processSelectedId[${counter.count}]">
															<option value="">Select Model</option>
															<c:forEach var="model" items="${selectBean}">
															<c:choose>
														<c:when test="${process.nCustDataProcess.nlyteCustomerDataTransStg.nctId==model.nCustDataTransStg.nctId}">
															<option value="${model.nMasterDataTrans.nmtId}">${model.nMasterDataTrans.materialNmTx}</option>
														</c:when>
													</c:choose>
															</c:forEach>
													</select>
													</td>
													<td class="sorting_1">${process.nMasterDataTrans.modelTx}</td>
													<td class="center">${process.nCustDataTransStg.manufacturerTx}</td>
													<td class="center">${process.nCustDataTransStg.materialTypTx}</td>
													<td class="center">${process.nCustDataTransStg.materialSubTypTx}</td>
													<td class="center">${process.nCustDataTransStg.powerConsmptNm}</td>
													<td class="center">${process.nCustDataTransStg.widthNm}</td>
													<td class="center">${process.nCustDataTransStg.depthNm}</td>
													<td class="center">${process.nCustDataTransStg.heightNm}</td>
													<td class="center">${process.nCustDataTransStg.weightNm}</td>
													<td class="center">${process.nCustDataTransStg.copperPortsTx}</td>
													<td class="center">${process.nCustDataTransStg.fiberPortsTx}</td>
													<td class="center">${process.nCustDataTransStg.undefPortsTx}</td>
													<td class="center">${process.nCustDataTransStg.createdDt}</td>
													<td class="center">${process.nCustDataTransStg.updatedDt}</td>
													<td class="center">${process.nCustDataTransStg.activeYn}</td>

													</tr>
												</c:forEach>
											</form>

										</tbody>
									</table>
								</div>
								 <button type="submit" onClick="myView()" class="btn btn-primary">Process</button>
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

	<script>
	var processNctId;
function getCustprocessedMultipleData(processNctId){
alert(processNctId);
$.ajax({
       url: '<%=application.getContextPath()%>/getCustProcessedDataById',
						dataType : "json",
						type : "GET",
						contentType : 'application/json',
						mimeType : 'application/json',
						data : {
							"processNctId" : processNctId
						},

						success : function(response) {
							alert("success")
							if (response.responseText != null) {
								$("#model").append(response.responseText);
							}
						},
						error : function(response) {
							alert("fail")
							alert(response)
							console.log(response)
							if (response.responseText != null) {
								$("#model").append(response.responseText);
								//alert(response.responseText);
							}
						}
					});

		}
	</script>

	<%-- <script src="<%=request.getContextPath()%>/js/jquey-1.12.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.datatable.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/js/datatable.bootstarp.min.js"></script>

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