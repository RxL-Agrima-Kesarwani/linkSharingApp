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
                Login
            </div>
            <div class="card-body">
            <div class="form-group">
                 <g:uploadForm name="login"  action = "login">

                    <div class="input-group mb-3">
                         <div class="input-group-prepend">
                            <span class="input-group-text" id="usernamelabel">Username/Email*</span>
                         </div>
                            <input id="username" type="text" class="form-control" placeholder="Username" aria-label="Username" name="usernamelabel">
                    </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend ">
                        <span class="input-group-text" id="passwordlabel">Password*</span>
                    </div>
                    <input id="password" type="password" class="form-control" placeholder="Password" aria-label="password" name="passwordlabel">
                </div>
                <div class="row">
                    <div class="col-8">
                       <g:link controller="sample" action="forgotPassword">
                                              Forgot Password</g:link>
                    </div>
                    <div class="col-4">
                        <button type="submit" class="btn btn-success">Login</button>
                    </div>
                </div>
                 </g:uploadForm>
            </div>



            </div>
        </div>

</body>
</html>