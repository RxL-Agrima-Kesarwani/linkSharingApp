<html>
    <head>
        <title>homePage</title>
              <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Document</title>
                <link rel="stylesheet" href="${resource(dir: 'stylesheets', file: 'bootstrap.css')}" type="text/css">
    </head>
    <body>
<div class="container">

    <!-- Trigger the modal with a button -->
    <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#subscribeTopicModal">Subscribe  Topic</button>
    <!-- Modal -->
    <div class="modal fade" id="subscribeTopicModal" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>

                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <g:uploadForm name="createTopic"  action = "subscribeTopic">
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="name"> Topic Name*</span>
                                </div>
                                <input id="name" type="text" class="form-control" placeholder="Name"  name="topicnamelabel">
                            </div>
                            <div class="input-group mb-3">
                                                            <div class="input-group-prepend ">
                                                                <span class="input-group-text" id="selectType">Select type      </span>
                                                            </div>
                                                            <select name="selectType" >
                                                                <option selected value= "2">Very Serious</option>
                                                                <option value="1">Serious</option>
                                                                 <option value="0">Casual</option>
                                                            </select>
                                                        </div>

                            <div class="row">
                                <div class="col-8">
                                    <button type="submit" class="btn btn-success">Save</button>
                                </div>

                            </div>
                        </g:uploadForm>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button"  class="btn btn-danger" data-dismiss="modal">Close</button>
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