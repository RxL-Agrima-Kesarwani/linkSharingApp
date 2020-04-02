<div class="container-fluid">
    <div class="row">
        <div class="col-2">
            <g:img dir="images" file="displayUser.png" width="95px"/>
        </div>
        <div class="col-10">
            <div class="container-fluid">
                <div class="row d-flex">
                    <div class="">${postData.user.firstName}</div>
                     <div class="">${postData.user.lastName}</div>
                    <div class="text-muted p-8 mx-2">@${postData.user.userName}</div>
                    <div class="text-muted p-8 mx-2">8 mins</div>
                    <div class="ml-auto text-info">${postData.topicName.topicName}</div>
                </div>
                <div class="row my-2">
                    Lorem ipsum dolor sit amet consectetur, adipisicing elit. Quas, cupiditate quo minus praesentium voluptates porro reprehenderit veniam hic, nam.
                </div>
                <div class="row d-flex">
                    <g:img dir="images" file="displayFacebook.png" width="20"/>
                    <g:img dir="images" file="displayTwitter.png" width="20"/>
                    <g:img dir="images" file="displayGoogle.png" width="20"/>
                    <a href="#" class="ml-auto">View post</a>
                </div>
            </div>
        </div>
    </div>
</div>