<!-- Page Content -->

 


  <div id="" style="margin-top: 50px;">
    <div class="container-fluid">
      <div class="row">
        <div class="col-lg-12">
          <div class="col-md-4 col-md-offset-4">
            <h3 class="page-header">Nlyte Material Lookup Data</h3>
            
            <form action="nlyteLogon" method="post">
              <div class="form-group">
                <label for="exampleInputEmail1">User Name</label>
                <input type="text" class="form-control" name="userName" id="exampleInputEmail1" placeholder="User Name">
              </div>
              <div class="form-group">
                <label for="exampleInputPassword1">Password</label>
                <input type="password" class="form-control" name="password" id="exampleInputPassword1" placeholder="Password">
              </div>
              <div class="checkbox">
                <label>
                  <input type="checkbox">
                  Keep me signed in </label>
              </div>
              <!--<button type="submit" class="btn btn-default btn-primary">Submit</button>-->
           <!--    <a class="btn-primary btn"  > Login</a> -->
              <!-- onclick="javascript:logon()" -->
              <input  class="btn-primary btn"  type="submit" value="Login" >
            </form>
          </div>
        </div>
      </div>
      
      <!-- ... Your content goes here ... --> 
 <script>
 	function logon(){
 		alert("aa");
 		var $form=$(element).closest('form');
 		$form.submit();
 	}
 	window.open ("http://localhost:8080/NLyte/",
 			"mywindow","status=1,toolbar=0");
 </script>
 
 