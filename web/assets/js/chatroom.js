function updateData() {//定时调用从数据库更新数据
    //更新成员列表
    // alert($(".data[name='cid']").val());
    // alert($(".data[name='cid']").val());
    $.post("visit?" + new Date().getTime(), {'room_id': $("input[name='room_id']").val(), "method": "updateData"});
}
function addFriendTo(user_id, room_id) {
    $.post("visit?"+new Date().getTime(),{
        "method":"addFriendTo",
        "user_id":user_id,
        "room_id":room_id
    },function () {
        updateData();
        showOnline();
    });
}
var updateHandle;
function searchChatroom(o) {
    //搜索时停止更新
    $(".wrapper .tabs .talk").html("");
    clearInterval(updateHandle);
    $.post("visit?" + new Date().getTime(),
        {
            "method": "searchChatroom",
            "keyWord": $(".searchRoom").val()
        },
        function (data) {//搜索到，直接显示，绑定事件
            $(".wrapper .tabs .talk").html(data);

            //好友点击事件
            $(".talk>ul>li.friend-li").each(function (key, li) {
                $(li).click(function () {
                    var selectId = $(this).find("input[class='friend_id']").val();
                    var roomId = $("input[name='room_id']").val();
                    if (confirm("Add him/her ?") == true) {
                        // alert(selectId + " " + myId);
                        addFriendTo(selectId, roomId);
                    }
                    //恢复更新

                    updateHandle =setInterval("showOnline()", 5000);
                });

            });
        });
    $(o).val("");
}

function deleteMember(deId) {
    //删除的不能是自己
    // console.log(deId);
    if ($("input[name='user_id']").val() == deId) {
        alert("管理员~");
        return;
    }
    $.post("visit?" + new Date().getTime(),
        {
            "method": "deleteMember",
            "user_id": deId,
            "room_id": $("input[name='room_id']").val()
        },
        function () {
            updateData();
            showOnline();
        });
}
function addNewFriend(id1, id2) {
    $.post("visit?"+new Date().getTime(),
        {
            "method": "addNewFriend",
            "user1_id":id1,
            "user2_id":id2,
        },
        function (data) {
            alert(data);
        }
        );
}
var isExistedInThisChat = true;

function showOnline() {//重新加载在线学生
    // $(".talk").load("showOnline.jsp?"+new Date().getTime());
    $.post("showOnline.jsp?" + new Date().getTime(), {}, function (data) {
        $(".talk").html(data);
        isExistedInThisChat = false;
        $(".talk>ul>li").each(function (key, li) {
            if ($(li).find(".content>input").val() == $("input[name='user_name']").val()) {
                isExistedInThisChat = true;
            }
            //绑定头像点击事件
            $(li).click(function () {//点击设置 @ 对象
                var tar = $(li).find(".content>input").val();
                if (tar == $("input[name='user_name']").val()) {
                    return;
                }
                $("input[name='target_name']").val(tar);
                var str = "@ " + tar + ": " + $("#txt").val();
                $("#txt").val(str);
            });
            //右键菜单
            // if (!isAdmin) return;
            $(li).contextmenu(function (e) {
                var selectId = $(this).find("input[name='cur_user_id']").val();
                var myId = $("input[name='user_id']").val();
                var menu = [
                    // '选择操作', //合理的html或纯文字
                    // 'menu2',
                    // '|', //分隔符
                    [
                        '移除用户', //title
                        function (dom) {
                            // console.log(dom);
                            if(!isAdmin){
                                alert("不是管理员");
                                return;
                            }
                            deleteMember(selectId)
                        } // 点击菜单项的回调
                    ],
                    [
                        '添加好友',
                        function (data) {
                            // addAsFriend();
                            addNewFriend(myId, selectId);
                        }
                    ]
                ];
                // alert(test);
                ContextMenu.render(e, menu, this, "light"); //开始渲染
            });
        });
        if (!isExistedInThisChat) {
            $.post("visit?" + new Date().getTime(), {"method": "exitChatroom"}, function () {
                window.location.href = "main.jsp?" + new Date().getTime();

            });
        }
    });

}

function prepareIntervalCall() {
    window.setInterval("updateData()", 5000);
    updateHandle = window.setInterval("showOnline()", 5000);
}

var isAdmin = false;
$(function () {
    isAdmin = $("input[name='admin_id']").val() == $("input[name='user_id']").val();
    //定时访问设置
    prepareIntervalCall();
    updateData();
    showOnline();
    //点击头像 @ 见showOnline

});
