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
<g:render template ="changePasswordComponent" model=[userName:userName]/>

    </body>
</html>