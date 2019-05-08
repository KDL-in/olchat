function searchChatroom(o) {
    //搜索时停止更新
    $(".wrapper .tabs .talk").html("");
    clearInterval(updateRoomsHandle);
    $.post("visit?" + new Date().getTime(),
        {
            "method": "searchChatroom",
            "keyWord": $(".searchRoom").val()
        },
        function (data) {//搜索到，直接显示，绑定事件
            $(".wrapper .tabs .talk").html(data);
            $(".talk>ul>li.room-li").each(function (key, li) {
                $(li).click(function () {
                    if (confirm("Add into chatroom ?") == true) {
                        var psw = prompt("Welcome to enter our chatroom : " + "\nEnter Password", "");
                        //搜索正确，加入请求
                        $.post("visit?" + new Date().getTime(),
                            {
                                "method": "addToChatroom",
                                "user_id": $("input[name='user_id']").val(),
                                "room_id": $(this).find("input[class='room_id']").val(),
                                "room_psw": psw
                            },
                            function (data) {
                                updateRoomList();
                                alert(data);
                            });
                    }
                    //恢复更新
                    updateRoomList();
                    updateRoomsHandle =setInterval("updateRoomList()", 3000);
                });
            });
            //好友点击事件
            $(".talk>ul>li.friend-li").each(function (key, li) {
                $(li).click(function () {
                    var selectId = $(this).find("input[class='friend_id']").val();
                    var myId = $("input[name='user_id']").val();
                    if (confirm("Add a new friend ?") == true) {
                        // alert(selectId + " " + myId);
                        addNewFriend(myId, selectId);
                    }
                    //恢复更新
                    updateRoomList();
                    updateRoomsHandle =setInterval("updateRoomList()", 3000);
                });

            });
        });
    $(o).val("");
}

//退出聊天室
function exitFromChatroom(li) {
    $.post("visit?"+new Date().getTime(),
        {
            "method":"exitFromChatroom",
            "user_id": $("input[name='user_id']").val(),
            "room_id": $(li).find("input[name='cid']").val(),
        },function () {
            updateRoomList();
        });

}
//删除解散聊天室
function deleteChatroom(li) {
    // alert($(li).find("input[name='cid']").val());
    $.post("visit?"+new Date().getTime(),
        {
            "method":"deleteChatroom",
            "room_id": $(li).find("input[name='cid']").val(),
        },function () {
            updateRoomList();
        });
}
//删除好友关系
function deleteFriendship(li) {
    $.post("visit?" + new Date().getTime(), {
        "method": "deleteFriendship",
        "user2_id": $(li).find("input[name='friend_id']").val(),
        "user1_id":$("input[name='user_id']").val()
    }, function () {
        updateRoomList();
    });
}
//修改聊天室名
function modRoomName(li,nName) {
    $.post("visit?" + new Date().getTime(),
        {
            "method": "modRoomName",
            "room_id": $(li).find("input[name='cid']").val(),
            "name": nName
        }, function (data) {
            alert(data);
            updateRoomList();
        });
}

function rightMenuEventBinding() {
    //聊天室右键
    $(".room-li").each(function (idx, li) {
        $(li).contextmenu(function (e) {
            var admin_id = $(li).find("input[name='admin_id']").val();
            var user_id = $("input[name='user_id']").val();
            var menu = [
                [
                    '退出聊天室', //title
                    function (dom) {
                        if (admin_id == user_id) {
                            alert("你是管理员，别冲动");
                            return;
                        }
                        exitFromChatroom(li);
                    }
                ],[
                    '解散聊天室',
                    function (dom) {
                        if (admin_id != user_id) {
                            alert("你不是管理员");
                            return;
                        }
                        if (confirm("Delete the chatroom?")) {
                            deleteChatroom(li);
                        }
                    }
                ],[
                    '修改聊天室名',
                    function (dom) {
                        var nName = prompt("New Name is?");
                        if (nName.trim() == "") {
                            alert("不能为空");
                        }
                        modRoomName(li,nName);
                    }
                ]
            ];
            // alert(test);
            ContextMenu.render(e, menu, this, "light"); //开始渲染
        });
    });
    //好友右键
    $(".friend-li").each(function (idx, li) {
        $(li).contextmenu(function (e) {
            var menu = [
                [
                    '删除好友', //title
                    function (dom) {
                        if (confirm("Delete your friend?")) {
                            deleteFriendship(li);
                        }
                    }
                ]
            ];
            // alert(test);
            ContextMenu.render(e, menu, this, "light"); //开始渲染
        });
    });

}
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
        //右键菜单事件绑定
        rightMenuEventBinding();
    });
}
var updateRoomsHandle;
function prepareIntervalCallOfUserRoom() {
    updateRoomsHandle = window.setInterval("updateRoomList()", 4000);
    // window.clearInterval(updateRoomsHandle);
}

function createChatroom() {
    var name = prompt("Your chatroom name", "").trim();
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
            alert(data);
            updateRoomList();
        });
}

$(function () {
    prepareIntervalCallOfUserRoom();//长时间进程
    updateRoomList();//显示聊天室列表
    //创建群
    $(".plus").click(createChatroom);
});