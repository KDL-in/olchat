$(function () {
    initPaperDiv();
    //提交按钮
    $(".modForm").submit(function (e) {
        e.preventDefault();
        modRoom();
    });
});
function modRoom() {
    // alert($("#type-select").val());
    $.post("server?"+new Date().getTime(),
        {
            "method": "modRoom",
            "id": $("#id-input").val(),
            "name":$("#name-input").val(),
            "password":$("#password-input").val(),
            "admin_id": $("#admin-input").val()
        },function () {
            $("#my-popup").modal();
            var pageNum = $("input#pageNumInp").val();
            updateUserListOfPage(pageNum);
        });
}
function modDivPoping(bt) {
    var tr = $(bt).parents(".recordTr");
    var pop = $("#my-popup");
    pop.find("#id-input").val(tr.find(".id-td").html());
    pop.find("#name-input").val(tr.find(".name-td").html());
    pop.find("#admin-input").val(tr.find(".admin-td span").html());
    pop.find("#password-input").val(tr.find(".password-td").html());
    pop.modal();
}
function deleteRoom(idx) {
    $.post("server?" + new Date().getTime(), {
        "method": "deleteRoom",
        "room_id": idx,
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
                deleteRoom(idx);
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
                var room_id = $(cb).parent().parent().find("td.id-td").html();
                deleteRoom(room_id);
            });
        }
    });
    //修改按钮
    $(".modButton").each(function (idx, bt) {
        $(bt).click(function () {
            modDivPoping(bt);
        });
    });
}
//更新列表
function updateUserListOfPage(pageNum) {
    $.post("server?"+new Date().getTime(),
        {
            "method":"getRoomListOfPage",
            "pageNum":pageNum
        },function (data) {
            $("#listContainer").html(data);
            usersListEventBinding();
        });
}
function initPaperDiv() {
    updateUserListOfPage(1);
}
