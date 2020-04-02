let xhr = new XMLHttpRequest();
var timeoutId;


//1. Setting a status
function changeStatus() {
     xhr.open("GET","Controller?action=SetStatus&status="+document.getElementById("input").value,true);
     xhr.onreadystatechange = setStatus;
     xhr.send(null);
}


function setStatus() {
    if(xhr.status == 200){
        if(xhr.readyState == 4){
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
    console.log("refresh");
    xhr.open("GET","Controller?action=GetFriends");

    xhr.onreadystatechange = showFriends;
    xhr.send(null);
}

function showFriends() {

    if(xhr.status === 200){
        if(xhr.readyState === 4){
            clearTable();
            var text = JSON.parse(xhr.responseText);
            var table = document.getElementById("friends");
            var count = 1;
            for(var person in text){
                var tr = document.createElement("tr");
                var tdName = document.createElement("td");
                var tdStatus = document.createElement("td");
                var tdNr = document.createElement("td");
                var chatButton = document.createElement("a");
                tdName.innerText = text[person].name;
                tdStatus.innerText = text[person].status;
                tdNr.innerText = count;
                chatButton.href = "Controller?action=GoToChat&friend="+text[person].name;

                tr.appendChild(tdNr);
                tr.appendChild(tdName);
                tr.appendChild(tdStatus);
                tr.appendChild(chatButton);

                tr.className = "friendlist";
                table.appendChild(tr);
                count++;
            }
            timeoutId = setTimeout(getFriendlist,20000);
        }
    }
}

function clearTable(){
    let friends = document.querySelectorAll('.friendlist');
    let table = document.getElementById('friends');
    for (let i=0;i<friends.length;i++){
        table.removeChild(friends[i]);
    }
}


function addFriend() {
    clearTimeout(timeoutId);
    xhr.open("POST","Controller?action=AddFriend&name="+document.getElementById("nameInput").value);
    xhr.send(null);
    getFriendlist();
}