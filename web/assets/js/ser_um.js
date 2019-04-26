$(function () {
    //分页
    initPaperDiv();

});
//批量删除

//删除用户
function deleteUser(idx) {
    $.post("server?" + new Date().getTime(), {
        "method": "deleteUser",
        "user_id": idx,
    }, function () {
        var pageNum = $("input#pageNumInp").val();
        updateUserListOfPage(pageNum);
    });
}

//分页列表事件绑定
function usersListEventBinding() {
    //分页导航
    $("ul.am-pagination").children("li").click(function () {
        var pageNum = $(this).children("a").html();
        updateUserListOfPage(pageNum);
    });
    $("#lastPage").click(function () {
        var pageNum = $("ul.am-pagination").find("li[class='am-active']").children("a").html();
        updateUserListOfPage(pageNum - 1);
    });
    //删除按钮
    $("button.deleButton").each(function (idx, bt) {
        $(bt).click(function () {
            var idx = $(bt).find("input").val();
            if(confirm("Delete user :" + idx)){
                deleteUser(idx);
            }
        });
    });
    //批量删除-全选按钮
    $("input#selectAll").click(function () {
        $("tbody input[type='checkbox']").each(function (idx, cb) {
            $(cb).click();
        })
    });
    //批量删除
    $("button.mulDeleButton").click(function () {
        if (confirm("Multiply deleting?")) {
            $("tbody input:checked[type='checkbox']").each(function (idx, cb) {
                var user_id = $(cb).parent().parent().find("td#idTd").html();
                deleteUser(user_id);
            });
        }
    });
}
//更新列表
function updateUserListOfPage(pageNum) {
    $.post("server?"+new Date().getTime(),
        {
            "method":"getUserListOfPage",
            "pageNum":pageNum
        },function (data) {
            $("#listContainer").html(data);
            usersListEventBinding();
        });
}

function initPaperDiv() {
    updateUserListOfPage(1);
}