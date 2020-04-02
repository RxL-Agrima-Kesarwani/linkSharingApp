<html>
    <head>
        <title>homePage</title>
              <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Document</title>
                <link rel="stylesheet" href="${resource(dir: 'stylesheets', file: 'bootstrap.css')}" type="text/css">
    </head>
    <body>
        <div class="container" >
            <div class="card ">
                <div class="card-header ">
                    <div>
                     Link Sharing Application Edit Profile
                    </div>
                </div>
                <div class="card-body">
                    <div class="container-fluid ">
                        <div class="row">
                            <div class = "col-5">
                            <h1>USER  PROFILE</h1>
                            <h2> USER TOPICS</h2>
                                <!--g:render template = "recentShares" /-->
                                <g:render template = "topPosts" />
                            </div>
                            <div class = "col-7">
                                <g:render template = "profileComponent" />
                                <g:render template = "changePasswordComponent" />
                            </div>
                        </div>
                    </div>
                 </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    </body>
</html>