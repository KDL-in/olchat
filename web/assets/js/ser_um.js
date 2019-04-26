
$(function () {
    //分页
    initPaperDiv();
    //提交按钮
    $(".modForm").submit(function (e) {
        e.preventDefault();
        modUser();
    });
});
//修改用户
function modUser() {
    // alert($("#type-select").val());
    $.post("server?"+new Date().getTime(),
        {
            "method": "modUser",
            "id": $("#id-input").val(),
            "user_name":$("#user-name-input").val(),
            "nickname":$("#nickname-input").val(),
            "img_url":$("#img-url-input").val(),
            "type": $("#type-select").val()
        },function () {
            $("#my-popup").modal();
            var pageNum = $("input#pageNumInp").val();
            updateUserListOfPage(pageNum);
        });
}

//删除用户
function deleteUser(idx) {
    $.post("server?" + new Date().getTime(), {
        "method": "deleteUser",
        "user_id": idx,
    }, function () {
        var pageNu;m = $("input#pageNumInp").val();
        updateUserListOfPage(pageNum);
    });
}

function modDivPoping(bt) {
    var tr = $(bt).parents(".recordTr");
    var pop = $("#my-popup");
    pop.find("#id-input").val(tr.find(".id-td").html());
    pop.find("#user-name-input").val(tr.find(".user-name-td").html());
    pop.find("#nickname-input").val(tr.find(".nickname-td").html());
    pop.find("#img-url-input").val(tr.find(".img-url-td img").attr("src"));
    pop.find("#type-input").val(tr.find(".type-td").html());
    pop.modal();
}

//分页列表事件绑定
function usersListEventBinding() {
    //分页导航
    $("ul.am-pagination").children("li").click(function () {
        var pageNum = $(this).children("a").html();
        updateUserListOfPage(pageNum);
    })
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
                var user_id = $(cb).parent().parent().find("td.id-td").html();
                deleteUser(user_id);
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