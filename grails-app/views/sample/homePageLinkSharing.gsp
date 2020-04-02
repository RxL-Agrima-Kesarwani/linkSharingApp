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
                    <div class = "row">
                    <div class = "col-6">
                     Link Sharing Application
                     </div>
                     <div class = "col-3">
                      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                      </div>
                       <div class = "col-3">
                       <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                    </div>
                </div>
            </div>
                <div class="card-body">

                    <div class="container-fluid ">
                        <div class="row">
                            <div class = "col-7">
                                <g:render template = "recentShares" model="[posts: information]" />
                                <g:render template = "topPosts" />
                            </div>
                            <div class = "col-5">
                                <g:render template = "loginComponent" />
                                <g:render template = "registerComponent" />
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