let xhr = new XMLHttpRequest();
let webSocket = new WebSocket("ws://localhost:8080/comment");

webSocket.onmessage = function (ev) {
    writeResponse(ev.data);
};


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