<div class="container-fluid">
    <div class="card ">
        <div class="card-header ">
            <div class="row">
                <div class="col-2">

                        <img width = "100px" height = "120px"
                        src="data:image/jpg;base64,${session.getAttribute("userPhoto")}"/>
                </div>
                <div class="card-body ">
                    <div class="col-10">
                        <div class="container-fluid">
                            <div class="row d-flex">
                              <div class="text-muted p-8 mx-2">${userInfo.name}</div>
                                <div class="text-muted p-8 mx-2">@${userInfo.userName}</div>
                                 <div class="text-muted p-8 mx-2">Subscriptions ${userInfo.countSubscription} </div>
                                  <div class="text-muted p-8 mx-2">Topics ${userInfo.countTopic} </div>

                                  </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
