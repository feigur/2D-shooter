var playerName = "[[${LoggedInUser.username}]]";
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
}