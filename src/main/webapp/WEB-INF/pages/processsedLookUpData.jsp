<%@ page contentType="text/html; charset = UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- Page Content -->
<div id="page-wrapper">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default" style="margin-top: 50px;">
					<div class="panel-heading">
						<h4>
							Material Lookup Data Of <span><a href="#"
								class="btn btn-primary pull-right">Export</a> </span>
						</h4>
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
										style="width: 100%; font-size: 12.5px; margin-top: 10px;">
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
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Project Name: activate to sort column ascending"
													style="width: 148px;">MFG</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Browser: activate to sort column ascending"
													style="width: 250px;">Type</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Designation: activate to sort column ascending"
													style="width: 188px;">S Type</th>

												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Project Name: activate to sort column ascending"
													style="width: 148px;">Width</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Project Name: activate to sort column ascending"
													style="width: 148px;">Depth</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Project Name: activate to sort column ascending"
													style="width: 148px;">Height</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Project Name: activate to sort column ascending"
													style="width: 148px;">Weight</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Project Name: activate to sort column ascending"
													style="width: 148px;">Copper</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Project Name: activate to sort column ascending"
													style="width: 148px;">Fiber</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Project Name: activate to sort column ascending"
													style="width: 148px;">Undefined</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Project Name: activate to sort column ascending"
													style="width: 148px;">Power Consumption</th>
												<!-- <th style="width:60px;">Progress</th>-->
											</tr>
										</thead>
										<tbody>
											<%
												int counter = 1;
											%>
											<s:iterator value="nlyteCustDtTransStgLst">
												<tr class="gradeA odd " role="row">
												<td class="sorting_1"><%=counter%></td>
													<td class="center"><s:property value="modelTx" /></td>
													<td class="center"><s:property value="manufacturerTx" /></td>
													<td class="center"><s:property value="materialTypTx" />
													</td>
													<td class="center"><s:property
															value="materialSubTypTx" /></td>
													<td class="center"><s:property value="widthNm" /></td>
													<td class="center"><s:property value="depthNm" /></td>
													<td class="center"><s:property value="heightNm" /></td>
													<td class="center"><s:property value="weightNm" /></td>
													<td class="center"><s:property value="copperPortsTx" />
													</td>
													<td class="center"><s:property value="fiberPortsTx" />
													</td>
													<td class="center"><s:property value="undefPortsTx" />
													</td>
													<td class="center"><s:property value="powerConsmptNm" />
													</td>
													<td class="center"><a href="nlyteCustomerCellList"
														class="btn btn-warning"><i class="fa fa-edit fa-fw"></i></a></td>
													<td class="center"><a href="processData"
														class="btn btn-warning"><i class="fa fa-edit fa-fw"></i></a></td>
												</tr>
												<%
													counter++;
												%>
											</s:iterator>
											<!-- <tr class="gradeA odd bg-danger" role="row">
                          <td>WS-X6248-RJ-45</td>
                          <td class="center">Cisco</td>
                          <td class="center">Network</td>
                          <td class="sorting_1">Module</td>
                          <td class="center">356</td>
                          <td class="center">406</td>
                          <td class="center">30</td>
                          <td class="center">2.31</td>
                          <td class="sorting_1">48</td>
                          <td class="center">0</td>
                          <td class="center">NULL</td>
                          <td class="center">0.1</td>
							 
                        </tr>
                        <tr class="gradeA odd" role="row">
                          <td>3C16950-US</td>
                          <td class="center">3Com</td>
                          <td class="center">Network</td>
                          <td class="sorting_1">Standard</td>
                          <td class="center">444.5</td>
                          <td class="center">300</td>
                          <td class="center">66.67</td>
                          <td class="center">5</td>
                          <td class="sorting_1">24</td>
                          <td class="center">0</td>
                          <td class="center">NULL</td>
                          <td class="center">0.2</td>
                           
                        </tr>
                        <tr class="gradeA odd" role="row">
                          <td>Twister 2131</td>
                          <td class="center">METRObility Optical Systems</td>
                          <td class="center">Network</td>
                          <td class="sorting_1">Standard</td>
                          <td class="center">86</td>
                          <td class="center">130</td>
                          <td class="center">40</td>
                          <td class="center">0.5</td>
                          <td class="sorting_1">1</td>
                          <td class="center">1</td>
                          <td class="center">NULL</td>
                          <td class="center">0.1</td>
                           
                        </tr>
                        <tr class="gradeA odd" role="row">
                          <td>IP380 (16 Port)</td>
                          <td class="center">Nokia</td>
                          <td class="center">Network</td>
                          <td class="sorting_1">Module</td>
                          <td class="center">354</td>
                          <td class="center">509</td>
                          <td class="center">89</td>
                          <td class="center">2.31</td>
                          <td class="sorting_1">48</td>
                          <td class="center">0</td>
                          <td class="center">NULL</td>
                          <td class="center">0.1</td>
                           
                        </tr>
                        <tr class="gradeA odd" role="row">
                          <td>FM 4000</td>
                          <td class="center">Cisco</td>
                          <td class="center">Network</td>
                          <td class="sorting_1">Module</td>
                          <td class="center">356</td>
                          <td class="center">406</td>
                          <td class="center">30</td>
                          <td class="center">2.31</td>
                          <td class="sorting_1">48</td>
                          <td class="center">0</td>
                          <td class="center">NULL</td>
                          <td class="center">0.1</td>
                           
                        </tr>
                        <tr class="bg-danger bg-danger" role="row">
                          <td>2109-F16</td>
                          <td class="center">IBM</td>
                          <td class="center">Network</td>
                          <td class="sorting_1">Module</td>
                          <td class="center">356</td>
                          <td class="center">406</td>
                          <td class="center">30</td>

                          <td class="center">2.31</td>
                          <td class="sorting_1">48</td>
                          <td class="center">0</td>
                          <td class="center">NULL</td>
                          <td class="center">0.1</td>
                           
                        </tr>
                        <tr class="gradeA odd bg-danger" role="row">
                          <td>Security Systems</td>
                          <td class="center">Kalatel</td>
                          <td class="center">Network</td>
                          <td class="sorting_1">Module</td>
                          <td class="center">356</td>
                          <td class="center">406</td>
                          <td class="center">30</td>
                          <td class="center">2.31</td>
                          <td class="sorting_1">48</td>
                          <td class="center">0</td>
                          <td class="center">NULL</td>
                          <td class="center">0.1</td>
                           
                        </tr>
                        <tr class="gradeA odd bg-danger" role="row">
                          <td>WS-X6248-RJ-45</td>
                          <td class="center">Cisco</td>
                          <td class="center">Network</td>
                          <td class="sorting_1">Module</td>
                          <td class="center">356</td>
                          <td class="center">406</td>
                          <td class="center">30</td>
                          <td class="center">2.31</td>
                          <td class="sorting_1">48</td>
                          <td class="center">0</td>
                          <td class="center">NULL</td>
                          <td class="center">0.1</td>
                           
                        </tr>
                        <tr class="gradeA odd bg-danger" role="row">
                          <td>Patapsco Liberator</td>
                          <td class="center">Promptus</td>
                          <td class="center">Network</td>
                          <td class="sorting_1">Module</td>
                          <td class="center">356</td>
                          <td class="center">406</td>
                          <td class="center">30</td>
                          <td class="center">2.31</td>
                          <td class="sorting_1">48</td>
                          <td class="center">0</td>
                          <td class="center">NULL</td>
                          <td class="center">0.1</td>
                           
                        </tr>
                        <tr class="gradeA odd bg-danger" role="row">
                          <td>WS-X6248-RJ-45</td>
                          <td class="center">Cisco</td>
                          <td class="center">Network</td>
                          <td class="sorting_1">Module</td>
                          <td class="center">356</td>
                          <td class="center">406</td>
                          <td class="center">30</td>
                          <td class="center">2.31</td>
                          <td class="sorting_1">48</td>
                          <td class="center">0</td>
                          <td class="center">NULL</td>
                          <td class="center">0.1</td>
                           
                        </tr>
                        <tr class="gradeA odd bg-danger" role="row">
                          <td>StorageWorks SAN 2/16</td>
                          <td class="center">Hewlett-Packard</td>
                          <td class="center">Network</td>
                          <td class="sorting_1">Module</td>
                          <td class="center">356</td>
                          <td class="center">406</td>
                          <td class="center">30</td>
                          <td class="center">2.31</td>
                          <td class="sorting_1">48</td>
                          <td class="center">0</td>
                          <td class="center">NULL</td>
                          <td class="center">0.1</td>
                           
                        </tr>
                        <tr class="gradeA odd bg-danger" role="row">
                          <td>WS-X6248-RJ-45</td>
                          <td class="center">Cisco</td>
                          <td class="center">Network</td>
                          <td class="sorting_1">Module</td>
                          <td class="center">356</td>
                          <td class="center">406</td>
                          <td class="center">30</td>
                          <td class="center">2.31</td>
                          <td class="sorting_1">48</td>
                          <td class="center">0</td>
                          <td class="center">NULL</td>
                          <td class="center">0.1</td>
                           
                        </tr>
                        <tr class="gradeA odd bg-danger" role="row">
                          <td>PowerEdge 2650</td>
                          <td class="center">Dell</td>
                          <td class="center">Server</td>
                          <td class="sorting_1">Module</td>
                          <td class="center">356</td>
                          <td class="center">406</td>
                          <td class="center">30</td>
                          <td class="center">2.31</td>
                          <td class="sorting_1">48</td>
                          <td class="center">0</td>
                          <td class="center">NULL</td>
                          <td class="center">0.1</td>
                           
                        </tr> -->

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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/custom.css">
<%-- <script src="<%=request.getContextPath()%>/js/jquey.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.datatable.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/datatable.bootstarp.min.js"></script>
	
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
	</script> --%>