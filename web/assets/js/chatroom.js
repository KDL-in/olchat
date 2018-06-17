
function updateData() {//定时调用从数据库更新数据
    //更新成员列表
    // alert($(".data[name='cid']").val());
    // alert($(".data[name='cid']").val());
    $.post("visit?"+new Date().getTime(),{'cid':$(".data[name='cid']").val(),"method":"updateData"});
}
function showOnline() {//重新加载在线学生
    $(".talk").load("showOnline.jsp?"+new Date().getTime());
}
function prepareIntervalCall() {
    window.setInterval("updateData()",5000);
    window.setInterval("showOnline()",3000);
}

$(function () {
    //定时访问设置
    prepareIntervalCall();
    updateData();
    showOnline();
});