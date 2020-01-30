var app = require('express');
var server = require('http').createServer(app);
// http server를 socket.io server로 upgrade한다
var io = require('socket.io')(server);
var list = [];
// connection event handler
// connection이 수립되면 event handler function의 인자로 socket인 들어온다
io.on('connection', function(socket) {

    // 접속한 클라이언트의 정보가 수신되면
    socket.on('login', function(data) {
        console.log('Client logged-in:\n name:' + data.name + '\n userid: ' + data.userid + '\n roomid: ' + data.roomid);

        // socket에 클라이언트 정보를 저장한다
        socket.name = data.name;
        socket.userid = data.userid;
        socket.roomid = data.roomid;

        // 접속된 모든 클라이언트에게 메시지를 전송한다
        // io.emit('login', data );

        //참석자 배열에 room 아이디 없으면 추가 해줌
        if(typeof(list['room'+data.roomid]) == "undefined") list['room'+data.roomid] = {};
        list['room'+data.roomid][data.userid] = data.name;
        // list['room'+data.roomid].push(data);

        // room에 join한다
        socket.join(data.roomid);
        // room에 join되어 있는 클라이언트에게 메시지를 전송한다
        console.log(list['room'+data.roomid]);
        io.to(data.roomid).emit('login', list['room'+data.roomid]);
    });

    // 클라이언트로부터의 메시지가 수신되면
    socket.on('chat', function(data) {
        console.log('id : ' + socket.userid + ' name : ' + socket.name + ' rommid : ' + socket.roomid + ' message : ' + data.message + ' date : ' + data.date)
        var msg = {
            id : socket.userid,
            name : socket.name,
            message : data.message,
            date : data.date
        };

        // 메시지를 전송한 클라이언트를 제외한 모든 클라이언트에게 메시지를 전송한다
        // socket.broadcast.emit('chat', msg);

        // 메시지를 전송한 클라이언트에게만 메시지를 전송한다
        // socket.emit('s2c chat', msg);

        // 접속된 모든 클라이언트에게 메시지를 전송한다
        // io.emit('s2c chat', msg);

        // 특정 클라이언트에게만 메시지를 전송한다
        io.to(socket.roomid).emit('chat', msg);
    });

    // force client disconnect from server
    socket.on('forceDisconnect', function() {
        socket.disconnect();
        io.emit('logout', socket.userid );
    })

    socket.on('disconnect', function() {
        // io.emit('logout', socket.userid );
        if(socket.userid != undefined) {
            if(typeof(list['room'+socket.roomid][socket.userid]) != undefined) delete list['room'+socket.roomid][socket.userid];
        }

        io.to(socket.roomid).emit('logout', socket.userid);
        console.log('user disconnected: ' + socket.name);

    });
});

server.listen(3001, function() {
    console.log('Socket IO server listening on port 3001');
});