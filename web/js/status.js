let xhr = new XMLHttpRequest();
//let friendsListRequest = new XMLHttpRequest();
var timeoutId;
var webSocket = new WebSocket("ws://localhost:8080/comment");
//let commentRequest = new XMLHttpRequest();
let names = [];
webSocket.onmessage = function (ev) {
    writeResponse(ev.data);
};

//1. Setting a status
function changeStatus() {
    xhr.open("POST", "Controller?action=SetStatus&status=" + document.getElementById("input").value, true);
    xhr.onreadystatechange = setStatus;
    xhr.send(null);
}


function setStatus() {
    if (xhr.status == 200) {
        if (xhr.readyState == 4) {
            var serverResponse = JSON.parse(xhr.responseText);
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
    xhr.open("GET", "Controller?action=GetFriends");
    xhr.onreadystatechange = showFriends;
    xhr.send(null);
}

function showFriends() {
    if (xhr.status === 200) {
        if (xhr.readyState === 4) {
            clearTable();
            let text = JSON.parse(xhr.responseText);
            let table = document.getElementById("friends");
            let count = 1;
            for (let person in text) {
                let tr = document.createElement("tr");
                let tdName = document.createElement("td");
                let tdStatus = document.createElement("td");
                let tdNr = document.createElement("td");

                tdName.innerText = text[person].name;
                tdStatus.innerText = text[person].statusname;
                tdNr.innerText = count;
                let btn = document.createElement("button");
                btn.className = "collapsible";

                btn.setAttribute("onClick", "openForm(" + (count) + ")");
                btn.id = "btnChat" + count;
                btn.type = "button";
                btn.innerHTML = "Chat";


                tr.appendChild(tdNr);
                tr.appendChild(tdName);
                tr.appendChild(tdStatus);
                tr.appendChild(btn);
                names[count -1] = text[person].name
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
    xhr.open("POST", "Controller?action=AddFriend&name=" + document.getElementById("nameInput").value);
    xhr.onreadystatechange = getFriendlist;
    xhr.send(null);
}

//3. Adding comments to topics

let forms = document.getElementById("topics").getElementsByTagName("form");

let j = 0;
for (let i = 0; i < forms.length; i++) {
    j++;
    console.log(j);
    forms[i].addEventListener('submit', ev => {
        ev.preventDefault();
        let name = document.getElementById("name" + forms[i].id).value;
        console.log("name: " + name)
        let comment = document.getElementById("comment" + forms[i].id).value;
        let rating = document.getElementById("number" + forms[i].id).value;

        let total = forms[i].id + "-" + name + ": " + comment + "     Rating: " + rating + "/10"
        console.log("got here");
        console.log(name);
        console.log(comment);
        console.log(rating);

        xhr.open("POST", "/Controller?action=comment");
        xhr.setRequestHeader("Content-Type", 'application/x-www-form-urlencoded');
        xhr.send('name=${name}&comment=${comment}&rating=${rating}');

        document.getElementById("comment" + forms[i].id).value = '';
        document.getElementById("number" + forms[i].id).value = '';

        send(total);

    });
}

function send(text) {
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


$(document).ready(function () {
    getFriendlist();
    setTimeout(function () {
        genChatWindow();
    }, 300);
});


function genChatWindow(count) {
    let div = document.getElementById("chats");


    let div2 = document.createElement("div");
    div2.id = "form";
    div2.className = "chat-popup";
    let form = document.createElement("form");
    form.className = "form-container";
    form.method = "POST";
    let h1 = document.createElement("h1");
    console.log(count)
    h1.innerHTML = names[count-1];
    h1.id = "recipient";
    // h1.innerHTML="chat";
    let lb1 = document.createElement("label");
    lb1.htmlFor = "msg";
    lb1.innerHTML = "Message";
    let input = document.createElement("input");
    input.name = "msg";
    input.required = true;
    input.style.margin = "5px";
    input.id = "msgID";
    let btnsubm = document.createElement("button");
    btnsubm.type = "button";
    btnsubm.className = "btn";
    btnsubm.innerHTML = "Send";
    btnsubm.id = "sendbtn";
    let btnclose = document.createElement("button");
    btnclose.type = "button";
    btnclose.className = "btn cancel";
    btnclose.innerHTML = "Close";

    btnclose.setAttribute("onclick", "closeForm()");

    let divMessages = document.createElement("div");
    divMessages.id = "messages";

    form.appendChild(h1);
    form.append(divMessages);
    form.appendChild(lb1);
    form.appendChild(input);
    form.appendChild(btnsubm);
    form.appendChild(btnclose);

    div2.appendChild(form);
    div.appendChild(div2);
    count++;

    $(document).ready(function () {
        $("#sendbtn").click(function () {
            let msg = document.getElementById("msgID").value;
            let recip = ($("#recipient").html() + "@ucll.be").toLowerCase();
            console.log(recip)
            $.ajax({
                type: "POST",
                url: "Controller?action=Message",
                data: {recipient: recip, message: msg},
                dataType: "json",
                success: function (json) {
                },
                error: function () {
                    alert("Error bij post");
                }
            });
        });

    });
}


function closeForm(count) {
    document.getElementById("form").style.display = "none";
    document.getElementById("chats").innerHTML = '';
}

function openForm(count) {
    document.getElementById("chats").innerHTML = '';

    genChatWindow(count);
    getReceivedMessages();
    // getSentMessages();
    document.getElementById("form").style.display = "block";
}

function getReceivedMessages() {
    let docu = document.getElementById("messages");
    let recip = ($("#recipient").html() + "@ucll.be").toLowerCase();
    console.log(recip)
    docu.innerHTML = "";
    $.ajax({
        type: "GET",
        url: "Controller?action=GetMessages",
        data: {recipient: recip },
        dataType: "json",
        success: function (json) {
            for(let x in json) {
                let newmsg = document.createElement("p");
                let test= json[x].split("__--");
                if(test[1] === "1"){
                    newmsg.style.textAlign="right";
                }
                else if(test[1] === "0"){
                    newmsg.style.textAlign="left";
                }
                newmsg.innerText = test[0];

                $('#messages').append(newmsg);
            }
            setTimeout(getReceivedMessages, 5000);
        }
    });
}
