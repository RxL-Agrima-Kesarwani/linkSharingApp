<html>
	<head>
		<title>TopicPage</title>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Document</title>
		<link rel="stylesheet" href="${resource(dir: 'stylesheets', file: 'bootstrap.css')}" type="text/css">
	</head>
	<body>
		<div class="container" >
			<div class="card ">
				<div class="card-header ">
					<div class="row">
						<div class = "col-2">
							Topic Page
						</div>
						<div class = "col-2">
							<input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
						</div>
						<div class = "col-2">
							<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
						</div>
						<div class = "col-2">
							<g:render template = "shareLinkComponent" />
							</div>
							<div class = "col-2">
								<g:render template = "shareDocumentComponent" />
								</div>
								<div class = "col-2">
									<img style="width: inherit" width = "100px" height = "120px"
									src="data:image/jpg;base64,${session.getAttribute("userPhoto")}"/>
								</div>
							</div>
						</div>
					</div>
					</div>
					</div>
					<div class="card-body">
						<div class="container-fluid ">
							<div class="row">
								<div class = "col-4">
									<g:render template = "shareDocumentComponent" />
										<g:render template = "shareLinkComponent" />
										</div>
									</div>
									<div class = "col-8">
										<g:render template = "inboxDashboard" />
											<g:render template = "deleteTopic"/>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
						<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
						<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
					</body>
				</html>