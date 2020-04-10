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
            <div class="card">
                <div class="card-header d-flex">
                    <div class = "">
                        Link Sharing Application
                    </div>

                    <div class = "">
                        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                    </div>
                    <div class = "">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                    </div>
                    <div class = "">
                        <g:render template = "addTopicComponent" />
                    </div>
                    <div class = "">
                        <g:render template = "shareLinkTopicComponent" />
                    </div>
                    <div class = "">
                        <g:render template = "shareDocumentTopicComponent" />
                    </div>
                    <div class = "">
                        <g:render template = "shareDocumentTopicComponent" />
                    </div>
                    <div class = "">
                        <g:link action="logout">
                        Log Out</g:link>
                    </div>
                     <div class = "">
                         <select name="Page" id="Page">
                                        <option selected>${userInfo.userName}</option>
                                         <option value="Profile" controller="sample" action="editProfile">Profile</option>
                                        <option value="Users">Users</option>
                                        <option value="Topic">Topic</option>
                                        <option value="Posts">Posts</option>
                                        <option value="Logout">
                                         <g:link action="logout"> Log Out</g:link>
                                        </option>
                                    </select>
                      </div>

                </div>

            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>