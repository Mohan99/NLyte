<%@ page contentType="text/html; charset = UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
.image-preview-input {
	position: relative;
	overflow: hidden;
	margin: 0px;
	color: #333;
	background-color: #fff;
	border-color: #ccc;
}

.image-preview-input input[type=file] {
	position: absolute;
	top: 0;
	right: 0;
	margin: 0;
	padding: 0;
	font-size: 20px;
	cursor: pointer;
	opacity: 0;
	filter: alpha(opacity = 0);
}

.image-preview-input-title {
	margin-left: 2px;
}
.loader {
	/* position: fixed;
	left: 0px;
	top: 0px; */
	width: 100%;
	height: 70px;
	/* z-index: 9999; */
	background: url('img/tenor.gif') 50% 50% no-repeat rgb(249, 249, 249);
	display: none;
	/* opacity: .8; */
}
</style>


<div id="page-wrapper">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default" style="margin-top: 50px;">
					<div class="panel-heading">
						<h4>Import Material Data - Import Nlyte Master</h4>
					</div>
				</div>
				<div
					class="col-xs-12 col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
					<h3 class="page-header">Import Nlyte Materials Data</h3>
					<!-- image-preview-filename input [CUT FROM HERE]-->
					<form action="nlyteImport" namespace="/" method="POST"
						enctype="multipart/form-data">
						<div class="form-group input-group image-preview">
							<input type="text" class="form-control image-preview-filename"
								id="filename" disabled="disabled">
							<!-- don't give a name === doesn't send on POST/GET -->
							<span class="input-group-btn"> <!-- image-preview-clear button -->
								<button type="button"
									class="btn btn-default image-preview-clear"
									style="display: none;">
									<span class="glyphicon glyphicon-remove"></span> Clear
								</button> <!-- image-preview-input -->
								<div class="btn btn-default image-preview-input">
									<span class="glyphicon glyphicon-folder-open"></span> <span
										class="image-preview-input-title">Browse</span> <input
										type="file" required="required"
										accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel, image/png, image/jpeg, image/gif"
										name="myFile" id="myFile" />
									<!-- rename it -->
								</div>
							</span>
						</div>
						<!-- /input-group image-preview [TO HERE]-->
						<div class="form-group">

							<br />
							<c:choose>
								<c:when test="${mastHeaderCheck==1}">
									<span style="color: red;">*Missing Columns for Headers, Please Check</span>
									<br />
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${mastDataCheck==1}">
											<span style="color: red;">*Data is not in place, Please Check</span>
											<br />
										</c:when>
										<%-- <c:otherwise>
											<c:choose>
												<c:when test="${mastDataCheck==1}">
													<span style="color: red;">*Data is not in place, Please Check</span>
													<br />
												</c:when>
											</c:choose>
										</c:otherwise> --%>
									</c:choose>

								</c:otherwise>
							</c:choose>
							<br /> <br /> <span id="lblError" style="color: red;"></span> <br />
							<!-- <button type="submit" class="btn btn-primary"> Submit </button> -->
							<input type="submit" class="btn btn-primary" value="Submit"
								onclick="return ValidateExtension()" />
						</div>
						<div class="loader"></div>
					</form>
				</div>
			</div>
			<!-- ... Your content goes here ... -->

		</div>
	</div>
	<script src="<%=request.getContextPath()%>/js/jquey.min.js"></script>
	<script>
		function setfilename(val) {
			//alert("in setFileNmae");
			/* var fileName = val.substr(val.lastIndexOf("\\") + 1, val.length);
			document.getElementById("filename").value = fileName;
			if(fileName.contains(" "))
			{
				document.getElementById("filename").value="";
				alert("File Name should not contains space ");
				 $('.image-preview-clear').hide();
				return false;
				
			} */
		}
	</script>
	<script type="text/javascript">
		function ValidateExtension() {
			var allowedFiles = [ ".xlsx", ".xls" ];
			var fileUpload = document.getElementById("myFile");
			var lblError = document.getElementById("lblError");
			var regex = new RegExp("([a-zA-Z0-9\s_\\.\-:])+("
					+ allowedFiles.join('|') + ")$");
			if (!regex.test(fileUpload.value.toLowerCase())) {
				lblError.innerHTML = "Please upload files having extensions: <b>"
						+ allowedFiles.join(', ') + "</b> only.";
				return false;
			}
			lblError.innerHTML = "";
			myLoader();
			return true;
		}
	</script>

	<script>
		$(document).on('click', '#close-preview', function() {
			$('.image-preview').popover('hide');
			// Hover befor close the preview
			$('.image-preview').hover(function() {
				$('.image-preview').popover('show');
			}, function() {
				$('.image-preview').popover('hide');
			});
		});

		$(function() {
			// Create the close button
			var closebtn = $('<button/>', {
				type : "button",
				text : 'x',
				id : 'close-preview',
				style : 'font-size: initial;',
			});
			closebtn.attr("class", "close pull-right");
			// Set the popover default content
			$('.image-preview').popover({
				trigger : 'manual',
				html : true,
				title : "<strong>Preview</strong>" + $(closebtn)[0].outerHTML,
				content : "There's no image",
				placement : 'bottom'
			});
			// Clear event
			$('.image-preview-clear').click(function() {
				$('.image-preview').attr("data-content", "").popover('hide');
				$('.image-preview-filename').val("");
				$('.image-preview-clear').hide();
				$('.image-preview-input input:file').val("");
				$(".image-preview-input-title").text("Browse");
			});
			// Create the preview image
			$(".image-preview-input input:file").change(function() {
				var img = $('<img/>', {
					id : 'dynamic',
					width : 250,
					height : 200
				});
				var file = this.files[0];
				var reader = new FileReader();
				// Set preview image into the popover data-content
				reader.onload = function(e) {
					$(".image-preview-input-title").text("Change");
					$(".image-preview-clear").show();
					$(".image-preview-filename").val(file.name);
					img.attr('src', e.target.result);
					//$(".image-preview").attr("data-content",$(img)[0].outerHTML).popover("show");
				}
				reader.readAsDataURL(file);
			});
		});	
	</script>
	<script type="text/javascript">
		function myLoader() {
			$(".loader").show();
			/* $(".loader").fadeOut("slow"); */
		}		
	</script>