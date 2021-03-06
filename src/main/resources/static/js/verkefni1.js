var canvas;
var gl;
var vPosition;
var vColor;
var fugl = vec3(4,4,4);
var color = vec4(0.0, 0.0, 0.0, 1.0);
var liturAFugl = vec4(1.0, 1.0, 1.0, 1.0);
var fuglErLifandi = true;
var hleyptAf = false;
var skot = 4;

// this is a counter for the game, counts kills
var killCounter = 0;
// we use this to play the game.
var button = [37,39,32,0,32]; // left right shoot
// timer for the game, we use this both for the game clock and the countdown.
var start = new Date().getTime();
// this is activated when the game is over
var gameOver = 0; // 1 when over
// the new way to use the buttons we get from the service.
var t0 = document.getElementById("takkar0");
var t1 = document.getElementById("takkar1");
var t2 = document.getElementById("takkar2");
var t3 = document.getElementById("takkar3");
var t4 = document.getElementById("takkar4");


// kill counter in the html
let counterDisplayElem = document.querySelector('.counter-display');
// upload button, its published when the game is over.
let uploadButton = document.getElementById("upload");



// this was part of the old game.
var mouseX;             // Old value of x-coordinate
var movement = false;   // Do we move the paddle?

 // spadi
var verticesB = [
    vec2(  0.0, -0.80 ),
    vec2(  0.05, -0.98 ),
    vec2(  -0.05, -0.98 )
];

// fugl A
var verticesA = [
    vec2( 1.0, 0.9 ),
    vec2( 1.0, 0.8 ),
    vec2( 0.9, 0.9 ),
    vec2( 0.9, 0.8 )
];

// fugl A2
var verticesA2 = [
    vec2( 1.0, 0.7 ),
    vec2( 1.0, 0.6 ),
    vec2( 0.9, 0.7 ),
    vec2( 0.9, 0.6)
];

// fugl A3
var verticesA3 = [
    vec2( 1.0, 0.5 ),
    vec2( 1.0, 0.4 ),
    vec2( 0.9, 0.5 ),
    vec2( 0.9, 0.4 )
];

// the gun
var verticesC = [
    vec2( 0.005, -0.78 ),
    vec2(  0.005, -0.8 ),
    vec2( -0.005, -0.78 ),
    vec2( -0.005, -0.8 )
];

window.onload = function init() {
    // update the buttons to the buttons each player has picked in settings.
    button[0] = t0.textContent;
    button[1] = t1.textContent;
    button[2] = t2.textContent;
    button[3] = t3.textContent;
    button[4] = t4.textContent;

    canvas = document.getElementById( "gl-canvas" );


    gl = WebGLUtils.setupWebGL( canvas );
    if ( !gl ) { alert( "WebGL isn't available" ); }

    gl.viewport( 0, 0, canvas.width, canvas.height );
    gl.clearColor( 0.5, 0.5, 1.0, 1.0 );

    //
    //  Load shaders and initialize attribute buffers
    //
    var program = initShaders( gl, "vertex-shader", "fragment-shader" );
    gl.useProgram( program );


    // Load the data into the GPU
    // buffer fyrir spada
    var bufferIdB;
    loadDataToGPU(bufferIdB,verticesB);

    var bufferIdA;
    loadDataToGPU(bufferIdA,verticesA);

    var bufferIdA2;
    loadDataToGPU(bufferIdA2,verticesA2);

    var bufferIdA3;
    loadDataToGPU(bufferIdA3,verticesA3);

    var bufferIdC;
    loadDataToGPU(bufferIdC,verticesC);

    // Associate out shader variables with our data buffer
    var vPosition = gl.getAttribLocation( program, "vPosition" );
    gl.enableVertexAttribArray( vPosition );
    vColor = gl.getUniformLocation( program, "rcolor");

    // Event listeners for mouse
    canvas.addEventListener("mousedown", function(e){
        movement = true;
        mouseX = e.offsetX;
    } );

    canvas.addEventListener("mouseup", function(e){
        movement = false;
    } );

    canvas.addEventListener("mousemove", function(e){
        if(movement) {
            var xmove = 2*(e.offsetX - mouseX)/canvas.width;
            mouseX = e.offsetX;
            for(i=0; i<3; i++) {
                verticesB[i][0] += xmove;
            }

            gl.bufferSubData(gl.ARRAY_BUFFER, 0, flatten(verticesB));
        }
    });

    // if button from the array is pushed we go left
    window.addEventListener("keydown", function(e){
        if(e.keyCode == button[1]) {
            for(i=0; i<3; i++) {
                verticesB[i][0] -= 0.08;
            }

            gl.bufferSubData(gl.ARRAY_BUFFER, 0, flatten(verticesB));
        }
    });
    // if button from the array is pushed we go right
    window.addEventListener("keydown", function(e){
        if(e.keyCode == button[2]) {
            for(i=0; i<3; i++) {
                verticesB[i][0] += 0.08;
            }

            gl.bufferSubData(gl.ARRAY_BUFFER, 0, flatten(verticesB));
        }
    });


    // if button from the array is pushed we shoot.
    window.addEventListener("keydown", function(e){
        if(e.keyCode == button[4] && !hleyptAf){
            var hnitSpadi = verticesB[0][0];
            hleyptAf = true;
            verticesC = [
                vec2(  (0.005+hnitSpadi), -0.78  ),
                vec2(  (0.005+hnitSpadi), -0.8 ),
                vec2( -(0.005-hnitSpadi), -0.78 ),
                vec2( -(0.005-hnitSpadi), -0.8 )
            ];
        }
    });


    render();
}

function loadDataToGPU(buffer, fylkiA){
    buffer = gl.createBuffer();
    gl.bindBuffer( gl.ARRAY_BUFFER, buffer );
    gl.bufferData( gl.ARRAY_BUFFER, flatten(fylkiA), gl.DYNAMIC_DRAW);
}

// Teikna fugl
function teiknaFugl(fylki,hradi,hylki){

    gl.bufferSubData(gl.ARRAY_BUFFER,0,flatten(fylki));
    gl.vertexAttribPointer( vPosition, 2, gl.FLOAT, false, 0, 0);
    gl.uniform4fv(vColor, flatten(liturAFugl));
    gl.drawArrays( gl.TRIANGLE_STRIP, 0, hylki);

    setTimeout( function() {

        // Ef fuglinn fl????????gur til vinstri
        if(fuglErLifandi){
            if(fylki[2][0] < -1.1){
                //fuglTilHaegri =  true;
                    fylki[0][0] = 1.0;
                    fylki[1][0] = 1.0;
                    fylki[2][0] = 0.9;
                    fylki[3][0] = 0.9;
            }
        for(var i=0; i<4; i++) {
                fylki[i] = vec2(fylki[i][0] - hradi, fylki[i][1]);
            }
        }
        }, 100);
}

// Teikna Spa????a
function teiknaSpada(){
    gl.bufferSubData(gl.ARRAY_BUFFER,0,flatten(verticesB));
    gl.vertexAttribPointer( vPosition, 2, gl.FLOAT, false, 0, 0);
    gl.uniform4fv (vColor, flatten(color));
    gl.drawArrays( gl.TRIANGLES, 0, 3);
}

function teiknaSkot(){
    // Teikna skot
    if(hleyptAf){
        var skot = 4;
        gl.bufferSubData(gl.ARRAY_BUFFER,0,flatten(verticesC));
        gl.vertexAttribPointer( vPosition, 2, gl.FLOAT, false, 0, 0);
        gl.uniform4fv(vColor, flatten(color));
        gl.drawArrays( gl.TRIANGLE_STRIP, 0, skot);

        var skothradi = 0.05;
        for(var i = 0; i<4; i++){
            verticesC[i][1] += skothradi;
        }
        if(verticesC[1][1] > 1.0 ) {
            hleyptAf = false;
            skotIndex = 0;
        }
        ennALifiFugl1();
        ennALifiFugl2();
        ennALifiFugl3();
    }
}

function ennALifiFugl1(){
    // Athuga hvort skot hitti fugl 1
    if( verticesC[0][0] < verticesA[0][0] &&
        verticesC[1][0] < verticesA[1][0] &&
        verticesC[3][0] > verticesA[3][0] &&
        verticesC[3][1] > verticesA[3][1] ){
            hleyptAf = false;
            fugl[0] = 0;
            skot =0;
            killCounter ++;
    }
}

function ennALifiFugl2(){
    // Athuga hvort skot hitti fugl 1
    if( verticesC[0][0] < verticesA2[0][0] &&
        verticesC[1][0] < verticesA2[1][0] &&
        verticesC[3][0] > verticesA2[3][0] &&
        verticesC[3][1] > verticesA2[3][1] ){
            hleyptAf = false;
            fugl[1] = 0;
            skot =0;
            killCounter ++;
    }
}

function ennALifiFugl3(){
    // Athuga hvort skot hitti fugl 1
    if( verticesC[0][0] < verticesA3[0][0] &&
        verticesC[1][0] < verticesA3[1][0] &&
        verticesC[3][0] > verticesA3[3][0] &&
        verticesC[3][1] > verticesA3[3][1] ){
            hleyptAf = false;
            fugl[2] = 0;
            skot = 0;
            killCounter ++;
    }
}

// this is to make the bird reappear when they have been shot.
function reapear(){
    if((fugl[0] == 0) && (gameOver != 1)){
        setTimeout(function(){
            fugl[0] = 4;
        }, 1000);
    }

    if((fugl[1] == 0) && (gameOver != 1)){
        setTimeout(function(){
            fugl[1] = 4;
        }, 1000);
    }

    if((fugl[2] == 0) && (gameOver != 1)){
        setTimeout(function(){
            fugl[2] = 4;
        }, 1000);
    }


}

// update the kill counter
function updateDisplay(){
    counterDisplayElem.innerHTML = killCounter;
    document.getElementById("score").value = killCounter;
};

// this checks if the timer is over then the game is over, disables the birds.
// this also makes the upload button appear.
function checkTime(){
    var end = new Date().getTime();
    var time = end - start;
    if(time > 30000) {
        fugl[0] = 0;
        fugl[1] = 0;
        fugl[2] = 0;
        gameOver = 1;
        uploadButton.type = "submit";
        document.getElementById("gameover").innerHTML = "Game finished, do you want to upload the results?";
    }
}

function render() {

    gl.clear( gl.COLOR_BUFFER_BIT );

    var speed = vec3(0.01,0.008,0.006);

    // Teikna spa????a
    teiknaSpada();

    //Teikna fuglinn
    teiknaFugl(verticesA,speed[0],fugl[0]);

    //Teikna annan fuglinn
    teiknaFugl(verticesA2,speed[1],fugl[1]);

    //Teikna ????ri????ja fuglinn
    teiknaFugl(verticesA3,speed[2],fugl[2]);


    //Teikna skot og kanna hvort fuglinn s???? ???? l????fi.
    teiknaSkot(fugl);

    // try to make the birds reappear
    reapear();

    // calls the time function
    checkTime();

    // converts the timer for the countdown clock
    var now = new Date().getTime();
    var time = (now - start)/1000;
    var myTimer = Math.round(30 - time);

    // if the timer has 5 or less sec after, the timer will be red
    if(myTimer <= 5){
        document.getElementById("timer").style.color = "red";
    }
    // counts down to 0.
    if(myTimer >= 0){
        document.getElementById("timer").innerHTML = myTimer;
    }

    // calls the updateDisplay function.
    updateDisplay();
    window.requestAnimFrame(render);
}