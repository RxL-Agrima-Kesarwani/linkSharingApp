<div class="card ">
    <div class="card-header d-flex " >
        <div> Top Posts</div>
        <div class="ml-auto">
            <select name="time" id="time">
                <option selected>Today</option>
                <option value="1">1 Day</option>
                <option value="2">1 Month</option>
                <option value="3">Three</option>

            </select>
            class="ml-auto">
            <select name="time" id="time">
                <option selected>Today</option>
                <option value="1">1 Day</option>
                  <g:each in = "${list}" var = "${a}">
                    <g:if test = "${a.topicName}"
                    </g:if>
                    </select>
        </div>

        <div class="card-body container-fluid">
            <div class="row">
                <g:each in = "${list}" var = "${a}">
                    <g:if test = "${a.topicName}"
                        <div class="col-2">
                            <g:img dir="images" file="displayUser.png" width = "100px" height = "120px"/>
                            </div>
                            <div class="col-10">
                                <div class="container-fluid">
                                    <div class="row d-flex">
                                        <b> ${a.firstName + " "}${a.lastName +" "}
                                        </b>
                                        <div class="">Agrima Kesarwani "</div>
                                        <div class="text-muted p-8 mx-2">@agrima</div>
                                        <div class="text-muted p-8 mx-2">8 mins</div>
                                        <div class="ml-auto text-info">Grails</div>
                                    </div>
                                    <div class="row my-2">
                                        Lorem ipsum dolor sit amet consectetur, adipisicing elit. Quas, cupiditate quo minus praesentium voluptates porro reprehenderit veniam hic, nam.
                                    </div>
                                    <div class="row d-flex">
                                        <g:img dir="images" file="displayFacebook.png" width="40" height="40"/>
                                            <g:img dir="images" file="displayTwitter.png" width="40" height="40"/>
                                                <g:img dir="images" file="displayGoogle.png" width="40" height="40"/>
                                                    <a href="#" class="ml-auto">View post</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </g:if>
                </g:each>
            </div>
        </div>