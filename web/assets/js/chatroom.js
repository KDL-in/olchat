function updateData() {//定时调用从数据库更新数据
    //更新成员列表
    // alert($(".data[name='cid']").val());
    // alert($(".data[name='cid']").val());
    $.post("visit?" + new Date().getTime(), {'room_id': $("input[name='room_id']").val(), "method": "updateData"});
}



function deleteMember(deId) {
    //删除的不能是自己
    // console.log(deId);
    if($("input[name='user_id']").val()==deId){
        alert("管理员~");
        return;
    }
    $.post("visit?" + new Date().getTime(),
        {
            "method": "deleteMember",
            "user_id":deId,
            "room_id":$("input[name='room_id']").val()
        },
        function () {
            updateData();
            showOnline();
        });
}

function showOnline() {//重新加载在线学生
    // $(".talk").load("showOnline.jsp?"+new Date().getTime());
    $.post("showOnline.jsp?" + new Date().getTime(), {}, function (data) {
        $(".talk").html(data)
        $(".talk>ul>li").each(function (key, li) {
            //绑定头像点击事件
            $(li).click(function () {//点击设置 @ 对象
                var tar = $(li).find(".content>h3").html();
                if (tar == $("input[name='user_name']").val())return;
                $("input[name='target_name']").val(tar);
                var str = "@ " + tar + ": " + $("#txt").val();
                $("#txt").val(str);
            });
            //右键菜单
            if (!isAdmin) return;
            $(li).contextmenu(function (e) {
                var deId=$(this).find("input[name='cur_user_id']").val();
                var menu = [
                    // '选择操作', //合理的html或纯文字
                    // 'menu2',
                    '|', //分隔符
                    [
                        '移除用户', //title
                        function (dom) {
                            // console.log(dom);
                            deleteMember(deId)
                        } // 点击菜单项的回调
                    ],
                ];

                // alert(test);
                ContextMenu.render(e, menu, this, "light"); //开始渲染
            });
        });
    });

}

function prepareIntervalCall() {
    window.setInterval("updateData()", 5000);
    window.setInterval("showOnline()",3000);
}

var isAdmin = false;
$(function () {
    isAdmin = $("input[name='admin_id']").val()==$("input[name='user_id']").val();
    //定时访问设置
    prepareIntervalCall();
    updateData();
    showOnline();
    //点击头像 @ 见showOnline

});
