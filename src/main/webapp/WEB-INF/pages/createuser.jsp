<%@ page contentType="text/html; charset = UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="page-wrapper">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default" style="margin-top: 50px;">
					<div class="panel-heading">
						<h4>User Management - Create User</h4>
					</div>
				</div>


				<div class="col-md-6">
					<form class="form-horizontal" action="nlyteCreateUserCall"
						id="myform" method="post">
						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">User ID</label>
							<div class="col-sm-7">
								<c:choose>
									<c:when test="${uId ne null}">
										<span style="color: red;">*User Id Already Exists</span>
										<br />
									</c:when>
								</c:choose>
								<input type="text" name="userId" class="form-control"
									id="userId" placeholder="User ID" required="required">
							</div>
						</div>
						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">User First
								Name</label>
							<div class="col-sm-7">
								<input type="text" name="userFirstName" class="form-control"
									id="fname" placeholder="User First name" required="required">
							</div>
						</div>
						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">User Last
								Name</label>
							<div class="col-sm-7">
								<input type="text" name="userLastName" class="form-control"
									id="lname" placeholder="User Last name" required="required">
							</div>
						</div>
						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">User Middle
								Name</label>
							<div class="col-sm-7">
								<input type="text" name="userMiddleName" class="form-control"
									id="mname" placeholder="User Middle Mame">
							</div>
						</div>
						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">Set
								Password</label>
							<div class="col-sm-7">
								<input type="password" name="Password" class="form-control"
									id="pwd" placeholder="Set Password" onblur="funPwd()"
									required="required">
							</div>
						</div>
						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">Confirm
								Password </label>
							<div class="col-sm-7">
								<input type="password" class="form-control" id="cpwd"
									placeholder="Confirm Password" onblur="funCheckPwd()"
									required="required">
							</div>
						</div>
						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">Mobile No</label>
							<div class="col-sm-7">
								<input type="text" name="mobileNo" class="form-control"
									id="mobileNo" placeholder="XXXXXXXXXX" onblur="validatePhone()"
									required="required">
							</div>
						</div>
						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">Email ID</label>
							<div class="col-sm-7">
								<input type="text" name="emailId" class="form-control"
									id="email" placeholder="Email ID" onblur="checkEmail()"
									required="required">
							</div>
						</div>

						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">User Active</label>
							<div class="col-sm-7">
								<input type="checkbox" name="userActive" checked
									data-toggle="toggle" data-on="Active" data-off="In Active"
									data-onstyle="success" data-offstyle="danger">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-4 col-sm-8" style="margin-top: 10px;">
								<button type="submit" class="btn btn-primary">Submit</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!-- ... Your content goes here ... -->

	</div>
</div>



<script>
	function funPwd() {
		var pwd = document.getElementById("pwd").value;
		if (pwd == "") {
			alert("Set Password must not be empty");
			return false;
		} else
			return true;
	}

	function funCpwd() {
		var cpwd = document.getElementById("cpwd").value;
		if (cpwd == "") {
			alert("Confirm Password must not be empty");
			return false;
		} else
			return true;
	}

	function funCheckPwd() {
		var pwd = document.getElementById("pwd").value;
		var cpwd = document.getElementById("cpwd").value;
		if (pwd != cpwd) {
			alert("Set password and Confirm password must be same");
			return false;
		} else
			return true;
	}

	function checkEmail() {

		var email = document.getElementById('email');
		var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;

		if (!filter.test(email.value)) {
			alert('Please provide a valid email address');
			email.focus;
			return false;
		} else
			return true;
	}

	function validatePhone() {
		var phone = document.getElementById('mobileNo');
		var RE = /^[\d\.\-\+]+$/;
		if (!RE.test(phone.value)) {
			alert('You have entered an invalid phone number');
			phone.focus;
			return false;
		} else
			return true;
	}
</script>


