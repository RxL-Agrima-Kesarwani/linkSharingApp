<div class="container">


    <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#deleteTopicModal">Delete Topic</button>

    <div class="modal fade" id="deleteTopicModal" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                 <h5 class="modal-title">Delete Topic</h5>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>

                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <g:if test="${flash.deleteTopicError}">
                                                          <div class="alert alert-primary" role="alert" >
                                                            ${flash.deleteTopicError}</div>
                                                          </g:if>
                                                           <g:if test="${flash.deleteTopicMessage}">
                                                                            <div class="alert alert-primary" role="alert" >
                                                                              ${flash.deleteTopicMessage}</div>
                                                                            </g:if>
                        <g:uploadForm name="deleteTopic"  action = "deleteTopic">
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="name"> Topic Name*</span>
                                </div>
                                  <g:select from="${topicList}" class="form-control"  name="topiclabel"   optionKey="id" optionValue="name" />
                            </div>
                             <div class="row">
                                <div class="col-8">
                                    <button type="submit" class="btn btn-success">Delete</button>
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