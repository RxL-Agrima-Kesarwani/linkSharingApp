<div class="card ">
    <div class="card-header d-flex " >
        <div> Recent Shares</div>
        <div class="ml-auto">

        </div>
    </div>

    <div class="card-body">
        <g:each  in="${recentShares}" var="post" >
            <g:render template="postSummary" model="[postData: post]" />
        </g:each>
    </div>
</div>