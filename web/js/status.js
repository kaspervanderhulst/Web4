let xhr = new XMLHttpRequest();


function changeStatus() {
     xhr.open("GET","Controller?action=GetStatus",true);
     xhr.onreadystatechange = getData;
     xhr.send(null);
}

function getData() {
    if(xhr.status == 200){
        if(xhr.readyState == 4){
            console.log("getdata hier");
            console.log(xhr.responseText);
            var status = xhr.responseText;
            var div = document.getElementById("status");
            var p = document.createElement("p");
            var text = document.createTextNode(status);
            p.appendChild(text);
            div.appendChild(p);
        }
    }
}
