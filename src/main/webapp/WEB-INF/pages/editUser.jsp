<%@ page contentType="text/html; charset = UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- Page Content -->
<div id="page-wrapper">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default" style="margin-top: 50px;">
					<div class="panel-heading">
						<h4>User Management - Edit User</h4>
					</div>
				</div>
				<div class="col-md-6">
					<form class="form-horizontal" action="nlyteUpdateUser"
						method="post">
						<s:iterator value="nlyteEditUser">
							<input type="hidden" name="nlyteUserId" id="nlyteUserId"
								value="<s:property  value = "nlyteUserId"/>" />
							<div class="form-group">

								<label for=" " class="col-sm-5 control-label">User ID</label>
								<div class="col-sm-7">
									<input type="text" class="form-control" name="userId"
										disabled="disabled" value="<s:property  value = "loginId"/>"
										label="userId" /> <input type="hidden" class="form-control"
										name="userId" value="<s:property  value = "loginId"/>"
										label="userId" />
								</div>
							</div>
							<div class="form-group">
								<label for=" " class="col-sm-5 control-label">User First
									Name</label>
								<div class="col-sm-7">
									<input type="text" class="form-control" name="userFirstName"
										required="required"
										value="<s:property  value = "userFirstNameTx"/>"
										label="userFirstNameTx" />
								</div>
							</div>
							<div class="form-group">
								<label for=" " class="col-sm-5 control-label">User Last
									Name</label>
								<div class="col-sm-7">
									<input type="text" class="form-control" name="userLastName"
										required="required"
										value="<s:property  value = "userLastNameTx"/>"
										label="userLastNameTx" />
								</div>
							</div>
							<div class="form-group">
								<label for=" " class="col-sm-5 control-label">User
									Middle Name</label>
								<div class="col-sm-7">
									<input type="text" class="form-control" name="userMiddleName"
										value="<s:property  value = "userMiddleNameTx"/>"
										label="userMiddleNameTx" />
								</div>
							</div>
							
							<div class="form-group">
								<label for=" " class="col-sm-5 control-label">Mobile No</label>
								<div class="col-sm-7">
									<input type="text" class="form-control" id="mobileNo"
										onblur="validatePhone()" name="mobileNo" required="required"
										value="<s:property  value = "mobileTx"/>" label="mobileTx" />
								</div>
							</div>
							<div class="form-group">
								<label for=" " class="col-sm-5 control-label">Email ID</label>
								<div class="col-sm-7">
									<input type="text" id="email" name="emailId"
										onblur="checkEmail()" required="required"
										value="<s:property  value = "emailId"/>" class="form-control"
										id="">
								</div>
							</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-4 col-sm-8" style="margin-top: 10px;">
						<button type="submit" class="btn btn-primary">Update</button>
					</div>
				</div>



				</s:iterator>

				</form>
			</div>
		</div>

	</div>
</div>
<!-- ... Your content goes here ... -->

</div>

<script type="text/javascript">
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