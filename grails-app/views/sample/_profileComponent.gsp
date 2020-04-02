<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="${resource(dir: 'stylesheets', file: 'bootstrap.css')}" type="text/css">
<body>
    <div class="container">
        <div class="card">
            <div class="card-header">
                Profile
            </div>
            <div class="card-body"> 
            <div class="form-group">
              <g:uploadForm name="editProfile"  action = "editProfile">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="firstnamelabel">First name*</span>
                    </div>
                    <input id="firstnamelabel" type="text" class="form-control" placeholder=""  name="firstnamelabel">
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="lastnamelabel">Last name*</span>
                    </div>
                    <input id="lastnamelabel" type="text" class="form-control" placeholder="Last name" name="lastnamelabel">
                </div>
                    <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="usernamelabel">Username*</span>
                    </div>
                    <input id="usernamelabel" type="text" class="form-control" placeholder="" name="usernamelabel">
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
                        <span class="input-group-text" id="photolabel">Photo</span>
                    </div>
                 <input type="file" name="photolabel" />

                </div>
                <div class="row">
                                      </div>
                    <div class="col-6">
                    <button type="submit" class="btn btn-secondary">Update</button>
                        
                    </div>
                </div>
                </g:uploadForm>
            </div>
            </div>
        </div>
    </div>
</body>
</html>