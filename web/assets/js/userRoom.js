function updateRoomList() {
    // alert($("input[name='uid']").val());
    $.post("visit", {"method": "showRoomList", "user_id": $("input[name='user_id']").val()}, function (data) {
        $(".talk").html(data);
        $(".talk>ul>li.room-li").each(function (key, li) {
            $(li).click(function () {
                window.location.href = "visit?sendTo=0&method=enterChatroom&room_id=" + $(this).children("input").val();
            });
        });
        //好友点击事件
        $(".talk>ul>li.friend-li").each(function (key, li) {
            $(li).click(function () {
                $.post("visit?"+new Date().getTime(),
                    {
                        "sendTo": 1,
                        "method": "enterChatroom",
                        "user_id":$("input[name='user_id']").val(),
                        "friend_id": $(li).children("input.friend_id").val()
                    },
                    function (data) {
                        $("div div div.user").html(data);
                        $("div div input[name='type']").val(5);
                        showChatRecords();
                        scrollBottom();
                    }
                );
            });

        });
    });
}
var updateRoomsHandle;
function prepareIntervalCallOfUserRoom() {
    updateRoomsHandle = window.setInterval("updateRoomList()", 1000);
    // window.clearInterval(updateRoomsHandle);
}

function createChatroom() {
    var name = prompt("Your chatroom name", "");
    if(name=="") {
        alert("Empty name!");
        return;
    }
    var psw = prompt("Your chatroom password", "");
    $.post("visit?" + new Date().getTime(),
        {
            "method": "createChatroom",
            "name":name,
            "password":psw,
            "total_num":0,
            "admin_id":$("input[name='user_id']").val(),
        },
        function (data) {
            alert("success");
            updateRoomList();
        });
}

$(function () {
    prepareIntervalCallOfUserRoom();//长时间进程
    updateRoomList();//显示聊天室列表
    //创建群
    $(".plus").click(createChatroom);
});