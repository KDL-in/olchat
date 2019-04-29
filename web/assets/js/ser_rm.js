$(function () {
    //分页
    initPaperDiv();
    //提交按钮
    $(".modForm").submit(function (e) {
        e.preventDefault();
        modContent();
    });
});
function modContent() {
    $.post("server?"+new Date().getTime(),
        {
            "method": "modRecordContent",
            "id": $("#id-input").val(),
            "content": $("#content-input").val()
        },function () {
            $("#my-popup").modal();
            var pageNum = $("input#pageNumInp").val();
            updateUserListOfPage(pageNum);
        });
}
function modDivPoping(bt) {
    var tr = $(bt).parents(".recordTr");
    var pop = $("#my-popup");
    pop.find("#id-input").val(tr.find(".id-input").val());
    pop.find("#user-input").val(tr.find(".user-td").html());
    pop.find("#time-input").val(tr.find(".time-td").html());
    pop.find("#content-input").val(tr.find(".content-td").html());
    pop.find("#room-input").val(tr.find(".room-td").html());
    pop.find("#friend-input").val(tr.find(".friend-td").html());
    pop.modal();
}

//删除用户
function deleteRecord(idx) {
    $.post("server?" + new Date().getTime(), {
        "method": "deleteRecord",
        "record_id": idx,
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
            if(confirm("Delete record :" + idx)){
                deleteRecord(idx);
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
                var user_id = $(cb).parent().parent().find(".id-input").val();
                alert(user_id);
                deleteRecord(user_id);
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
            "method":"getRecorrdListOfPage",
            "pageNum":pageNum
        },function (data) {
            $("#listContainer").html(data);
            usersListEventBinding();
        });
}

function initPaperDiv() {
    updateUserListOfPage(1);
}