<div class="container">

    <!-- Trigger the modal with a button -->
    <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#sendInvitationModal">Send Invitation</button>
    <!-- Modal -->
    <div class="modal fade" id="sendInvitationModal" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>

                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <g:uploadForm name="sendInvitation"  action = "sendInvitation">
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="emailId"> Email id*</span>
                                </div>
                                <input id="name" type="text" class="form-control" placeholder="Email id"  name="emailId">
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend ">
                                    <span class="input-group-text" id="description">Topic*      </span>
                                </div>
                                <input id="name" type="text" class="form-control" placeholder="Topic"  name="topiclabel">
                            </div>
                            <div class="row">
                                <div class="col-8">
                                    <button type="submit" class="btn btn-success">Invite</button>
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