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
          <div class="card-header d-flex ">
            Topic
            <div class = "col-4">
              <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            </div>
            <div class = "col-4">
              <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </div>
          </div>
          <div class="card-body">
            <div class="form-group">
              <div class="col-2">
                <img width = "100px" height = "120px"
                src="data:image/jpg;base64,${session.getAttribute("userPhoto")}"/>
              </div>
              <g:form name="topicComponent" controller="dashboard" onSubmit action = "addTopic">
                <div class="input-group mb-3">
                 <input type ="text" name="topicName" class ="form-control" placeholder="Topic Name"/>
                  <button type="submit" class="btn btn-secondary">Save</button>
                </div>
                <div class="input-group mb-3">
                  <div class="row d-flex">
                    <div class="text-muted p-8 mx-2">@${userInfo.userName}</div>
                    <div class="text-muted p-8 mx-2">Subscriptions ${userInfo.countSubscription}</div>
                    <div class="text-muted p-8 mx-2">Topics ${userInfo.countTopic} </div>
                  </div>
                </div>
                <div class="input-group mb-3">
                <g:select from="${["SERIOUS","CASUAL","VERY_SERIOUS"]}" name="seriousness" />
                <g:select from="${["PUBLIC","PRIVATE"]}" name="visibility" />


                </div>

              </g:form>

                <button type="submit" class="btn btn-secondary">Send </button>
                <button type="submit" class="btn btn-secondary">Delete </button>
                <button type="submit" class="btn btn-secondary">Edit</button>

            </div>
          </div>
        </div>
      </div>
    </body>
  </html>