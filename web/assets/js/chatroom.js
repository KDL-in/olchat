
function updateData() {//定时调用从数据库更新数据
    //更新成员列表
    // alert($(".data[name='cid']").val());
    // alert($(".data[name='cid']").val());
    $.post("visit?"+new Date().getTime(),{'room_id':$("input[name='room_id']").val(),"method":"updateData"});
}
function showOnline() {//重新加载在线学生
    // $(".talk").load("showOnline.jsp?"+new Date().getTime());
    $.post("showOnline.jsp?"+new Date().getTime(),{},function (data) {
        $(".talk").html(data)
        //绑定头像点击事件
        $(".talk>ul>li").each(function (key, li) {
            $(li).click(function () {//点击设置 @ 对象
                var tar = $(li).find(".content>h3").html();
                if(tar==$("input[name='user_name']").val()) return;
                $("input[name='target_name']").val(tar);
                var str = "@ "+tar +": " +$("#txt").val();
                $("#txt").val(str);
            });
        });
    });

}
function prepareIntervalCall() {
    window.setInterval("updateData()",5000);
    // window.setInterval("showOnline()",3000);
}

$(function () {
    //定时访问设置
    prepareIntervalCall();
    updateData();
    showOnline();
    //点击头像 @ 见showOnline


});