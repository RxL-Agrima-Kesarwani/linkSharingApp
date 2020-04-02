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
                Change Password
            </div>
            <div class="card-body"> 
            <div class="form-group">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="password">Password*</span>
                    </div>
                    <input id="username" type="password" class="form-control"  name="password">
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend ">
                        <span class="input-group-text" id="confirmPassword">Confirm Password*</span>
                    </div>
                    <input id="password" type="password" class="form-control" name="confirmPassword">
                </div>
                <div class="row">

                    <div class="col-4">
                        <button type="submit" class="btn btn-success">Update</button>
                    </div>
                </div>
            </div>
            </div>
        </div>
    </div>
</body>
</html>