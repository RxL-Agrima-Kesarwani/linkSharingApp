<!DOCTYPE html>
<html>
    <head>
          </head>
    <title>
    </title>
    </head>
    <body>

    <div class = "fieldcontain">
    <label for = "email"> EMAIL</label>
    <g:textField name = "email" value = "$(email)" />
    </div>

<div class = "fieldcontain">
    <label for = "sendInvitation"> SEND INVITATION</label>
    <g:textArea name = "sendInvitationmail" value = "$(sendInvitation)" />
    </div>
    <g:render template="_dashboardSendInvitation" />
               </body>

    </html>
