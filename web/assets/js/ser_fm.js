
$(function () {
    //分页
    initPaperDiv();
});

function deleteFriendship(idx) {
    $.post("server?" + new Date().getTime(), {
        "method": "deleteFriendship",
        "fs_id": idx,
    }, function () {
        var pageNum = $("input#pageNumInp").val();
        updateUserListOfPage(pageNum);
    });
}

//分页列表事件绑定
function usersListEventBinding() {
    //分页导航
    $("ul.am-pagination").children("li:not(:first):not(:last)").click(function () {
        var pageNum = $(this).children("a").html();
        updateUserListOfPage(pageNum);
    })
    $("#lastPage").click(function () {
        var pageNum = $("ul.am-pagination").find("li[class='am-active']").children("a").html();
        updateUserListOfPage(pageNum - 1);
    });
    $("#nextPage").click(function () {
        var pageNum = $("ul.am-pagination").find("li[class='am-active']").children("a").html();
        updateUserListOfPage(pageNum + 1);
    });
    //删除按钮
    $("button.deleButton").each(function (idx, bt) {
        $(bt).click(function () {
            var idx = $(bt).find("input").val();
            if(confirm("Delete user :" + idx)){
                deleteFriendship(idx);
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
                var id = $(cb).parent().parent().find("td.id-td").html();
                deleteFriendship(id);
            });
        }
    });
    /*    //修改按钮
        $(".modButton").each(function (idx, bt) {
            $(bt).click(function () {
                modDivPoping(bt);
            });
        });*/
}
//更新列表
function updateUserListOfPage(pageNum) {
    $.post("server?"+new Date().getTime(),
        {
            "method":"getFriendshipListOfPage",
            "pageNum":pageNum
        },function (data) {
            $("#listContainer").html(data);
            usersListEventBinding();
        });
}
function initPaperDiv() {
    updateUserListOfPage(1);
}