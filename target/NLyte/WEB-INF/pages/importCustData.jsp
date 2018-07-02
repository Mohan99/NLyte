<!-- Page Content -->

<head>
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
	filter: alpha(opacity=0);
}
.image-preview-input-title {
    margin-left:2px;
}
	
	</style>
</head>
  <div id="page-wrapper" style="margin-top: 70px;">
    <div class="container-fluid">
      <div class="row">    
		  
        <div class="col-xs-12 col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">  
           <h4> Import Customer Materials Data</h4>

				

					<!-- <s:file name="fileUpload" label="Select a File to upload" size="4000" />

					<s:submit value="submit" name="submit" />
 -->
				<!-- </s:form>
				<h2>
					File Name :
					<s:property value="fileUploadFileName" />
				</h2>
				<h2>
					Content Type :
					<s:property value="fileUploadContentType" />
				</h2>
				<h2>
					File :
					<s:property value="fileUpload" />
				</h2> -->


				<!-- image-preview-filename input [CUT FROM HERE]-->
				<form action="nlyteCustImport" namespace="/" method="POST"
					enctype="multipart/form-data">
            <div class="form-group input-group image-preview">
                <input type="text" class="form-control image-preview-filename" id="filename"
							disabled="disabled">
                <!-- don't give a name === doesn't send on POST/GET -->
                <span class="input-group-btn">
                    <!-- image-preview-clear button -->
                    <button type="button" class="btn btn-default image-preview-clear" style="display:none;">
                        <span class="glyphicon glyphicon-remove"></span> Clear
                    </button>
                    <!-- image-preview-input -->
                    <div class="btn btn-default image-preview-input">
                        <span class="glyphicon glyphicon-folder-open"></span>
                        <span
									class="image-preview-input-title">Browse</span> 
                       <input type="file" accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel, image/png, image/jpeg, image/gif" name="myFile" onchange="setfilename(this.value);" /> <!-- rename it -->
                   
                    </div>
                </span>
            </div><!-- /input-group image-preview [TO HERE]--> 
			<div class="form-group">
				<input type="submit" class="btn btn-primary" value="Submit"/>
				
			</div>
			</form>
			
        </div>
    </div>
      <!-- ... Your content goes here ... --> 
      
    </div>
  </div>
  <script>
	function setfilename(val) {
		var fileName = val.substr(val.lastIndexOf("\\") + 1, val.length);
		document.getElementById("filename").value = fileName;
	}
</script>
   <script>
    $(document).ready(function() {
        $('#dataTables-example').DataTable({
            responsive: true
        });
    });
    </script>
	<script>
	$(document).on('click', '#close-preview', function(){ 
    $('.image-preview').popover('hide');
    // Hover befor close the preview
    $('.image-preview').hover(
        function () {
           $('.image-preview').popover('show');
        }, 
         function () {
           $('.image-preview').popover('hide');
        }
    );    
});

$(function() {
    // Create the close button
    var closebtn = $('<button/>', {
        type:"button",
        text: 'x',
        id: 'close-preview',
        style: 'font-size: initial;',
    });
    closebtn.attr("class","close pull-right");
    // Set the popover default content
    $('.image-preview').popover({
        trigger:'manual',
        html:true,
        title: "<strong>Preview</strong>"+$(closebtn)[0].outerHTML,
        content: "There's no image",
        placement:'bottom'
    });
    // Clear event
    $('.image-preview-clear').click(function(){
        $('.image-preview').attr("data-content","").popover('hide');
        $('.image-preview-filename').val("");
        $('.image-preview-clear').hide();
        $('.image-preview-input input:file').val("");
        $(".image-preview-input-title").text("Browse"); 
    }); 
    // Create the preview image
    $(".image-preview-input input:file").change(function (){     
        var img = $('<img/>', {
            id: 'dynamic',
            width:250,
            height:200
        });      
        var file = this.files[0];
        var reader = new FileReader();
        // Set preview image into the popover data-content
        reader.onload = function (e) {
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
     