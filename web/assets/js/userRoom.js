function updateRoomList() {
    // alert($("input[name='uid']").val());
    $.post("visit",{"method":"showRoomList","user_id":$("input[name='user_id']").val()},function (data) {
        $(".talk").html(data);
        $(".talk>ul>li").each(function (key, li) {
            $(li).click(function () {
                window.location.href="visit?method=enterChatroom&room_id="+$(this).children("input").val();
            });
        });
    });
}

function prepareIntervalCallOfUserRoom() {
    window.setInterval("updateRoomList()",10000);
}
$(function () {
    prepareIntervalCallOfUserRoom();//长时间进程
    updateRoomList();//显示聊天室列表
});