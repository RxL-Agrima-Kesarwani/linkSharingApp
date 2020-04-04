<div class="card">
    <div class="card-header">
        Register
    </div>
    <div class="card-body">
        <div class="form-group">
        <g:if test="${flash.error}">
                          <div class="alert alert-primary" role="alert" >
                            ${flash.error}</div>
                          </g:if>
                           <g:if test="${flash.message}">
                                            <div class="alert alert-primary" role="alert" >
                                              ${flash.message}</div>
                                            </g:if>
        <g:uploadForm name="register"  action = "register">
            <div class="input-group mb-3">
                <div class="input-group-prepend">

                    <span class="input-group-text" id="firstnamelabel">
                        First name*
                    </span>
                </div>
                <input id="firstnamelabel" type="text" class="form-control" placeholder="First name"  name="firstnamelabel">
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="lastnamelabel">
                        Last name*
                    </span>
                </div>
                <input id="lastnamelabel" type="text" class="form-control" placeholder="Last name"  name="lastnamelabel">
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="emaillabel">Email*</span>
                </div>
                <input id="emaillabel" type="text" class="form-control" placeholder="Email"  name="emaillabel">
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="usernamelabel">Username*</span>
                </div>
                <input id="usernamelabel" type="text" class="form-control" placeholder="Username"  name="usernamelabel">
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend ">
                    <span class="input-group-text" id="passwordlabel">Password*</span>
                </div>
                <input id="password" type="password" class="form-control" placeholder="Password" aria-label="password" name="passwordlabel">
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend ">
                    <span class="input-group-text" id="confirmpasswordlabel">Confirm Password*</span>
                </div>
                <input id="password" type="password" class="form-control" placeholder="Confirm Password" aria-label="password" name="confirmpasswordlabel">
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend ">
                    <span class="input-group-text" >Photo</span>

                      		<input type="file" name="photo" />
                       </div>


            <button type="submit" class="btn btn-secondary">Register</button>





                </div>
            </div>
        </div>
        </g:uploadForm>
    </div>
</div>