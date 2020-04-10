<div class="container">
                <div class="card">
                    <div class="card-header">
                        Reset Password
                    </div>
                    <div class="card-body">
                        <div class="form-group">
                            <g:form name="resetPassword" controller="login"  action = "resetPassword">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="usernamelabel">Username/Email*</span>
                                    </div>
                                    <input id="username"  value="${userName}" readonly type="text" class="form-control" placeholder="Username" name="usernamelabel">
                                </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="passwordlabel">Password*</span>
                                </div>
                                <input id="username" type="password" class="form-control" placeholder="Password"  name="passwordlabel">
                            </div>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="confirmpasswordlabel">Confirm Password*</span>
                            </div>
                            <input id="username" type="password" class="form-control" placeholder="Confirm Password" name="confirmpasswordlabel">
                        </div>
                        <div class="row">
                            <div class="col-4">
                                <button type="submit" class="btn btn-success">Submit</button>
                            </div>
                        </div>
                    </g:form>
                </div>
            </div>
