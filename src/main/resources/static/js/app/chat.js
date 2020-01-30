
var chat = {
    init : function () {
        var _this = this;
        var rand_num = Math.floor(Math.random() * 10000) + 1;
        var user_id  = "user_"+rand_num;
        var user_nm = "유저 "+rand_num;
        var socket = io.connect('http://localhost:3001/');

        //채팅방 입장클릭
        $('#roomEnter').on('click', function (e) {
            var roomid = $("#roomid").val();

            socket.emit("login", {
                name: user_nm,
                userid: user_id,
                roomid: roomid
            });
        });

        socket.on("login", function(data) {
            $(".users-list").html('');
            for(var id in data){
                console.log('object => ', id, 'value => ', data[id]);
                $("user").find(".chat-user").addClass(id);
                $("user").find(".chat-user-name").html('<span>' + data[id] + '</span>');

                var user_html = $("user").html();

                $("user").find(".chat-user").removeClass(id);

                $(".users-list").append(user_html);
            }

        });

        socket.on("logout", function(data) {
            data = "."+data;
            $(data).remove();
        });

        socket.on("chat", function(data) {

            if(user_id == data.id) {
                $("chat").find(".chat-message").addClass('left');
            } else {
                $("chat").find(".chat-message").addClass('right');
            }

            $("chat").find(".message-author").html(data.name);
            $("chat").find(".message-date").html(data.date);
            $("chat").find(".message-content").html(data.message);

            $(".chat-discussion").append($("chat").html());

            $("chat").html('<div class="chat-message">\n' +
                            '<div class="message">\n' +
                            '<span class="message-author"></span>\n' +
                            '<span class="message-date"></span>\n' +
                            '<span class="message-content"></span>\n' +
                            '</div>\n' +
                            '</div>'
                            );

            $(".chat-discussion").scrollTop($(".chat-discussion")[0].scrollHeight);
        });

        $("#message").keydown(function(key) {
            //엔터 일때
            if (key.keyCode == 13) {
                //쉬프트 안눌렀을때 전송 (쉬프트+엔터 -> 줄바꿈)

                if (!key.shiftKey){
                    event.preventDefault();

                    var d = new Date();
                    var currentDate = d.getFullYear() + "년" + ( d.getMonth() + 1 ) + "월" + d.getDate() + "일";
                    var currentTime = d.getHours() + "시" + d.getMinutes() + "분" + d.getSeconds() + "초";
                    var date = currentDate + currentTime;

                    var message = $("#message").val();

                    socket.emit("chat", {
                        message : message,
                        date : date
                    });

                    $("#message").val('');
                }
            }
        });

    }

};

$(function () {

})

chat.init();