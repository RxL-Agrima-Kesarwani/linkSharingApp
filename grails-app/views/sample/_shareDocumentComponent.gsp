<div class="container">
    <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#shareDocumentModal">Share Document</button>
    <!-- Modal -->
    <div class="modal fade" role="dialog" id="shareDocumentModal">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title">Share Document</h5>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                    <g:if test="${flash.error}">
                                      <div class="alert alert-primary" role="alert" >
                                        ${flash.error}</div>
                                      </g:if>
                                       <g:if test="${flash.message}">
                                                        <div class="alert alert-primary" role="alert" >
                                                          ${flash.message}</div>
                                                        </g:if>
                        <g:uploadForm name="shareDocument"  action = "shareDocumentFinal">
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="document">Document*</span>
                                </div>
                                <input type="file" name="document" enctype="multipart/form-data" />
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend ">
                                    <span class="input-group-text" id="description">Description*</span>
                                </div>
                                <input id="description" type="text" class="form-control" placeholder="Description"  name="descriptionlabel">
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend ">

                                    <span class="input-group-text" id="topic">Topic*</span>
                                </div>

                                <g:select from="${topicList}" class="form-control"  name="topiclabel"   optionKey="id" optionValue="name" />
                            </div>
                            <div class="row">
                                <div class="col-8">
                                    <button type="submit" class="btn btn-success">Share</button>
                                </div>
                                <div class="col-4">
                                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </g:uploadForm>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>