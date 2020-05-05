let statusRequest = new XMLHttpRequest();
let friendsListRequest = new XMLHttpRequest();
var timeoutId;
var webSocket = new WebSocket("ws://localhost:8080/comment");
let commentRequest = new XMLHttpRequest();
webSocket.onmessage = function (ev) {
    writeResponse(ev.data);
};

//1. Setting a status
function changeStatus() {
    statusRequest.open("POST", "Controller?action=SetStatus&status=" + document.getElementById("input").value, true);
    statusRequest.onreadystatechange = setStatus;
    statusRequest.send(null);
}


function setStatus() {
    if (statusRequest.status == 200) {
        if (statusRequest.readyState == 4) {
            var serverResponse = JSON.parse(statusRequest.responseText);
            var status = serverResponse.status;
            var div = document.getElementById("status");
            var p = document.getElementById("pStatus");
            p.innerHTML = "";
            var text = document.createTextNode(status);
            p.appendChild(text);
            div.appendChild(p);
        }
    }
}


//2. Displaying friends

getFriendlist();

function getFriendlist() {
    friendsListRequest.open("GET", "Controller?action=GetFriends");
    friendsListRequest.onreadystatechange = showFriends;
    friendsListRequest.send(null);
}

function showFriends() {
    if (friendsListRequest.status === 200) {
        if (friendsListRequest.readyState === 4) {
            clearTable();
            let text = JSON.parse(friendsListRequest.responseText);
            let table = document.getElementById("friends");
            let count = 1;
            for (let person in text) {
                let tr = document.createElement("tr");
                let tdName = document.createElement("td");
                let tdStatus = document.createElement("td");
                let tdNr = document.createElement("td");
                let chatButton = document.createElement("a");
                tdName.innerText = text[person].name;
                tdStatus.innerText = text[person].statusname;
                tdNr.innerText = count;
                chatButton.href = "Controller?action=GoToChat&friend=" + text[person].name;




                tr.appendChild(tdNr);
                tr.appendChild(tdName);
                tr.appendChild(tdStatus);
                tr.appendChild(chatButton);

                tr.className = "friendlist";
                table.appendChild(tr);
                count++;
            }
            timeoutId = setTimeout(getFriendlist, 20000);
        }
    }
}

function clearTable() {
    let friends = document.querySelectorAll('.friendlist');
    let table = document.getElementById('friends');
    for (let i = 0; i < friends.length; i++) {
        table.removeChild(friends[i]);
    }
}


function addFriend() {
    clearTimeout(timeoutId);
    friendsListRequest.open("POST", "Controller?action=AddFriend&name=" + document.getElementById("nameInput").value);
    friendsListRequest.onreadystatechange = getFriendlist;
    friendsListRequest.send(null);
}

//3. Adding comments to topics

let forms = document.getElementById("topics").getElementsByTagName("form");

let j = 0;
for (let i = 0; i < forms.length; i++) {
    j++;
    console.log(j);
    forms[i].addEventListener('submit', ev => {
        ev.preventDefault();
        let name = document.getElementById("name"+forms[i].id).value;
        console.log("name: " + name)
        let comment = document.getElementById("comment" + forms[i].id).value;
        let rating = document.getElementById("number" + forms[i].id).value;

        let total = forms[i].id + "-" + name + ": " + comment + "     Rating: " + rating + "/10"
        console.log("got here");
        console.log(name);
        console.log(comment);
        console.log(rating);

        commentRequest.open("POST", "/Controller?action=comment");
        commentRequest.setRequestHeader("Content-Type", 'application/x-www-form-urlencoded');
        commentRequest.send('name=${name}&comment=${comment}&rating=${rating}');

        document.getElementById("comment" + forms[i].id).value = '';
        document.getElementById("number" + forms[i].id).value = '';

        send(total);

    });
}

function send(text){
    webSocket.send(text);
}

function closeSocket() {
    webSocket.close();
}

function writeResponse(text) {
    console.log(text);

    if (text === "Connection Established") {

    }
    let split = text.split("-");
    console.log(split);
    document.getElementById("comments" + split[0]).innerHTML += "<br/>" + split[1];

}
// ajax jquery

function openChatWithFriend(username){
    if(username != null){
        $.get("Controller?action=GetMessages")
    }
}