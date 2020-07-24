WEBSOCKET_URL = "ws://localhost:8080/AppStore/appStatus"
var userName = document.getElementById("username_holder").innerText.split(" ")[2]
var body = document.getElementById("app_status_body")
var webSocket = new WebSocket(WEBSOCKET_URL);
webSocket.onopen = function (message) {
    wsOpen(message);
};

function addApp(appData) {
    arts = appData.split("|")
    let appJSON = JSON.parse(arts[0])
    let appStr = "<div class='col-lg-4 col-md-6 mb-4'><div class='card h-100'><a href='individualPage.html?id=" + appJSON["name"] + "'><img class='card-img-top' src='" + appJSON["logo"] + "' alt='" + appJSON["name"] + "'></a><div class='card-body'><h4 class='card-title'><a href='individualPage.html?id=" + appJSON["id"] + "'>" + appJSON["name"] + "</a></h4><h5>Version = " + appJSON["version"] + "</h5><p class='card-text'>" + appJSON["description"] + "</p></div><div class='card-footer'><small class='text-muted'>Rating: " + appJSON["rating"] + "</small></div>" + "<div class='card-footer'><small class='text-muted'>" + arts[1] + ":" + arts[2] + "</div></div>"
    body.innerHTML += appStr
}

function wsGetMessage(message) {
    body.innerHTML = ""
    var parts = message.data.split("\t")
    parts.forEach(addApp)
}

webSocket.onmessage = function (message) {
    wsGetMessage(message);
};

function wsOpen(message) {
    body.innerHTML = "<p> Websocket Connected</p>"
    webSocket.send(userName)
}