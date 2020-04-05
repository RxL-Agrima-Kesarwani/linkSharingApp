<div class="container">
    <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#shareLinkModal">Share Link</button>
    <!-- Modal -->
    <div class="modal fade" role="dialog" id="shareLinkModal">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                <h5 class="modal-title">Share Link</h5>
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
                        <g:uploadForm name="shareLink"  action = "shareLink">
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="link">Link*</span>
                                </div>
                                <input id="username" type="text" class="form-control" placeholder="Link"  name="link">
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend ">
                                    <span class="input-group-text" id="description">Description*</span>
                                </div>
                                <input id="description" type="text" class="form-control" placeholder="Description"  name="description">
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend ">

                                    <span class="input-group-text" id="topic">Topic*</span>
                                </div>
                                 <g:select from="${topicList}" class="form-control"  name="topiclabel"   optionKey="id" optionValue="name" />
                            </div>
                            <button type="submit" class="btn btn-success">Share</button>
                        </g:uploadForm>
                    </div>
                </div>
                <div class="modal-footer">
                    <div class="col-6">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>