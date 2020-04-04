<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="${resource(dir: 'stylesheets', file: 'bootstrap.css')}" type="text/css">
<body>
<div>
${flash.error}
    </div>
 <div>
 ${flash.message}
     </div>
    <div class="container">
        <div class="card">
            <div class="card-header">
                Forgot Password
            </div>
            <div class="card-body">
            <div class="form-group">
                 <g:form name="login"  action = "forgotPassword">
                    <div class="input-group mb-3">
                         <div class="input-group-prepend">
                            <span class="input-group-text" id="usernamelabel">Username/Email*</span>
                         </div>
                            <input id="username" type="text" class="form-control" placeholder="Username" aria-label="Username" name="usernamelabel">
                    </div>

                <div class="row">

                    <div class="col-4">
                        <button type="submit" class="btn btn-success">Submit</button>
                    </div>
                </div>
                 </g:form>
            </div>



            </div>
        </div>

</body>
</html>