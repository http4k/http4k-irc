let socket = null;

function connect() {
  console.log("Begin connect");

  const protocol = document.location.href.startsWith('https') ? 'wss' : 'ws';
  socket = new WebSocket(protocol + "://" + window.location.host + "/ws");

  socket.onerror = function () {
    console.log("socket error");
  };

  socket.onopen = function () {
    write("Connected");
  };

  socket.onclose = function () {
    write("Disconnected");
    setTimeout(connect, 5000);
  };

  socket.onmessage = function (event) {
    console.log(event)
    if (!(event.data instanceof Blob)) {
      received(event.data.toString());
    }
  };
}

function received(message) {
  write(message);
}

function write(message) {
  const line = document.createElement("p");
  line.className = "message";
  line.textContent = message;

  const messagesDiv = document.getElementById("messages");
  messagesDiv.appendChild(line);
  messagesDiv.scrollTop = line.offsetTop;
}

function onSend() {
  const input = document.getElementById("commandInput");
  if (input) {
    const text = input.value;
    if (text && socket) {
      socket.send(text);
      input.value = "";
    }
  }
}

function start() {
  connect();

  document.getElementById("sendButton").onclick = onSend;
  document.getElementById("commandInput").onkeydown = function (e) {
    if (e.key === "Enter") {
      onSend();
    }
  };
}

function initLoop() {
  if (document.getElementById("sendButton")) {
    start();
  } else {
    setTimeout(initLoop, 300);
  }
}

initLoop();
