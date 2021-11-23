/*var playerName = "[[${LoggedInUser.username}]]";
var playerButtons = [[${keys}]];

function initButtons() {
    for(let i = 0; i < playerButtons.length; i++) {
        if (playerButtons[i] == 32){
            document.getElementById(i).innerHTML =  "Space";
        }
        else{
            document.getElementById(i).innerHTML =  String.fromCharCode(playerButtons[i]);
        }

    }
    document.getElementById("username").innerHTML = playerName;
}

function updateUsername() {
    var newName = prompt("Change username to: ", playerName);
    if(newName == null || newName == ""){
        alert("Username invalid!");
    } else {
        playerName = newName;
        document.getElementById("username").innerHTML = newName;
        debug.log(playerName);
    }
}

function updateButtons(c){
    var newButton = prompt("Change button to: ", playerButtons[parseInt(c)]);

    if(newButton == null || newButton == "" || newButton.length > 1) {
        alert("One Button only!");
    } else {
        button_id = parseInt(newButton.toUpperCase().charCodeAt(0));
        if(playerButtons.includes(button_id)){
            alert("Button in use");
        }
        else{
            playerButtons[parseInt(c)] = button_id
            initButtons();
            debug.log(playerButtons);
        }
    }
}*/


//var playerName = "[[${LoggedInUser.username}]]";
//var playerButtons = /*[[${keys}]]*/ "";

var busy = false;

function setButtons() {
    for (let i = 0; i < playerButtons.length; i++) {
        if (playerButtons[i] == 32){
            document.getElementById(i).innerHTML =  "Space";
        }
        else{
            document.getElementById(i).innerHTML =  String.fromCharCode(playerButtons[i]);
        }

    }
    document.getElementById("username").innerHTML = playerName;
}

function updateUsername() {
    var newName = prompt("Change username to: ", playerName);
    if(newName == null || newName == ""){
        alert("Username invalid!");
    } else {
        playerName = newName;
        document.getElementById("newName").value = newName;
        document.getElementById("username").innerHTML = newName;
    }
}

function updateButtons(c){

    if ( !busy )
    {
        busy = true;
        var buttonElement = document.getElementById(c);
        buttonElement.innerHTML = 'Waiting for input...';
        document.addEventListener('keydown', e => {
            if (e.keyCode === 32) e.preventDefault();
            const k = e.keyCode;
            const key = (96 <= k && k <= 105) ? k - 48 : k;
            const boundKey = playerButtons[parseInt(c)];

            if ( !playerButtons.includes(key)) {
                playerButtons[parseInt(c)] = key;
                setButtons();
                busy = false;
            }
            else {
                if (boundKey === key) {
                    playerButtons[parseInt(c)] = boundKey;
                    busy = false;
                    setButtons();
                    return;
                }

                alert("Button in use! Try another one.");
                setButtons();
                busy = false;
            } } , { once : true }
        );
    }


    /*if(newButton == null || newButton == "" || newButton.length > 1) {
        alert("One Button only!");
    } else {
        button_id = parseInt(newButton.toUpperCase().charCodeAt(0));
        if(playerButtons.includes(button_id)){
            alert("Button in use");
        }
        else {
            playerButtons[parseInt(c)] = button_id;
            if (c == 0) {
                document.getElementById("b0").value = button_id;
            }
            if (c == 1) {
                document.getElementById("b1").value = button_id;
            }
            if (c == 2) {
                document.getElementById("b2").value = button_id;
            }
            if (c == 3) {
                document.getElementById("b3").value = button_id;
            }
            if (c == 4) {
                document.getElementById("b4").value = button_id;
            }
            initButtons();

        }
    }*/
}