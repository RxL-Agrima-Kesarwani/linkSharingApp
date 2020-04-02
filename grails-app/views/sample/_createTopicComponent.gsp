<div class="container">

    <!-- Trigger the modal with a button -->
    <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#addTopicModal">Add Topic</button>
    <!-- Modal -->
    <div class="modal fade" id="addTopicModal" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>

                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <g:uploadForm name="createTopic"  action = "addTopic">
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="name"> Topic Name*</span>
                                </div>
                                <input id="name" type="text" class="form-control" placeholder="Name"  name="topicnamelabel">
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend ">
                                    <span class="input-group-text" id="description">Visibility*      </span>
                                </div>
                                <select name="visibility" id="visibility">
                                    <option selected value="1">Public</option>
                                    <option value="0">Private</option>
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