<div class="container">
   <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#addTopicModal">Add Topic</button>
      <div class="modal fade" id="addTopicModal" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Add Topic</h5>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>

                </div>
                <div class="modal-body">
                    <div class="form-group">
                  <g:if test="${flash.topicError}">
                  <div class="alert alert-primary" role="alert" >
                    ${flash.topicError}</div>
                  </g:if>
                   <g:if test="${flash.topicMessage}">
                                    <div class="alert alert-primary" role="alert" >
                                      ${flash.topicMessage}</div>
                                    </g:if>

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
                                    <option selected value="PUBLIC">Public</option>
                                    <option value="PRIVATE">Private</option>
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