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
        </div>
    </div>

    <div class="card-body container-fluid">
        <g:each  in="${topPosts}" var="post" >
            <g:render template="postSummary" model="[postData: post]" />
        </g:each>
    </div>
</div>
