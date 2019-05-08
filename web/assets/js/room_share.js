/**
 * Created by Administrator on 2017/6/29.
 */
//todo 长时进程8000
//最左侧sidebar切换时的变化
$(".sidebar .lt").on("click", function () {
    $(this).addClass("icon-liaotian").removeClass("icon-liaotian1");
    $(this).css("color", "rgb(9,187,7)").siblings().css("color", "white");
    $(".txl").removeClass("icon-tongxunlu_tongxunlu").addClass("icon-tongxunlu");
    $(".sc").removeClass("icon-shoucang").addClass("icon-iconfont");
    if ($(this).hasClass("active") == false)
        window.location.href = "chatroom.jsp";

})
$(".sidebar .txl").on("click", function () {
    $(this).addClass("icon-tongxunlu_tongxunlu").removeClass("icon-tongxunlu");
    $(this).css("color", "rgb(9,187,7)").siblings().css("color", "white");
    $(".sc").removeClass("icon-shoucang").addClass("icon-iconfont");
    $(".lt").removeClass("icon-liaotian").addClass("icon-liaotian1");
    if (!$(this).hasClass("active"))
        window.location.href = "main.jsp";
})
$(".sidebar .sc").on("click", function () {
    $(this).addClass("icon-iconfont").removeClass("icon-shoucang");
    $(this).css("color", "rgb(9,187,7)").siblings().css("color", "white");
    $(".lt").removeClass("icon-liaotian").addClass("icon-liaotian1");
    $(".txl").removeClass("icon-tongxunlu_tongxunlu").addClass("icon-tongxunlu");
    if (!$(this).hasClass("active"))
        window.location.href = "moment.jsp";
})
/*//机器人对话
$(".send span").click(function(){
    //通过获得输入框的文本生成对话
    var self= $('<div class="self"><span>'+$("#txt").val()+'</span><img src="assets/images/1.jpg" alt=""/></div>')
    $(".container").append(self);
    //发送ajax请求
    $.ajax({
        url:"http://www.tuling123.com/openapi/api",
        type:"post",
        data:"key=e39a340d87da47829c3bee5c4df64203&info="+$("#txt").val(),
        success:function(data){
           var robot= $('<div class="person"><img src="assets/images/1.jpg" alt=""/><span>'+data.text+'</span></div>')
            $(".container").append(robot);
            var heightdis = $(".container").height() - $(".dialouges").height();
            console.log(heightdis);
            if(heightdis >=0){
                $(".dialouges").scrollTop(heightdis);
            }
        }
    })
    //清空输入框的内容
    $("#txt").val("");

})*/
/*
发送对话
 */


/*custom*/
function addNewFriend(id1, id2) {
    $.post("visit?" + new Date().getTime(),
        {
            "method": "addNewFriend",
            "user1_id": id1,
            "user2_id": id2,
        },
        function (data) {
            alert(data);
        }
    );
}

function showChatRecords() {
    var room_id = $("input[name='room_id']").val();
    var type = $("input[name='type']").val();
    // alert(type);
    // alert(cid);
    if (room_id == "" && type < 5) {
        return;
    }

    $.post("visit?" + new Date().getTime(),
        {
            "method": "showChatRecords",
            "room_id": room_id,
            "type": type,
            "startDate": startDate.getTime(),
            "endDate": endDate.getTime()
        },
        function (data) {
            data=data.replace(/\[em_(\d+)\]/g, '<img class ="face-img" src="assets/gif/$1.gif" />');
            // console.log(data);
            $(".container").html(data);
        });
}

function sendMessage() {
    var type = $("input[name='type']").val();
    if ($("input[name='room_id']").val() == "" && type < 5) return;
    if ($("#txt").val().trim() == "") {
        $("#txt").val("");
        $("input[name='type']").val(type >= 5 ? 5 : 0);
        $("input[name='target_name']").val("");
        return;
    }
    // alert($("input[name='type']").val());
    $.post("visit?" + new Date().getTime(),
        {
            "method": "sendMessage",
            "txt": $("#txt").val(),
            "type": type,
            "target_name": $("input[name='target_name']").val()
        },
        function () {
            showChatRecords();
            scrollBottom();
        });
    $("#txt").val("");
    $("input[name='type']").val(type < 5 ? 0 : 5);
    $("input[name='target_name']").val("");

}

function exit() {
    window.location.href = "login?" + new Date().getTime() + "&method=exit";
}

var flag = false;

function toggleUpload() {
    if (flag && $("input[name='room_id']").val() == "") return;
    flag = true;
    $("#uploadPanel").toggle(200);
}



function scrollBottom() {
    var heightdis = $(".container").height() - $(".dialouges").height();
    if (heightdis >= 0) {
        $(".dialouges").scrollTop(heightdis + 100);
    }
}

var scrollHandler;
var lock = false;
$(function () {
    // 默认选中
    $(".sidebar .active").click();
    //进入加载最近聊天记录
    showChatRecords();
    //长时间线程更新当前聊天
    // window.setInterval("showChatRecords()", 4000);
    //长时间线程滚动底部
    scrollHandler = window.setInterval("scrollBottom()", 3000);
    //发送消息
    $(".send span").click(sendMessage);
    //注销绑定
    $("#chat_bg .offline").click(exit);
    //选择图片面板
    $(".doc").click(toggleUpload);
    $(".doc").click();
    //搜索
    $('.searchRoom').on('keypress', function (event) {
        if (event.keyCode == 13) {
            searchChatroom(this);
        }
    });
    $('.searchRoom').on('keyup', function (event) {
        if (event.keyCode == 27) {
            //恢复更新
            updateRoomList();
            updateRoomsHandle =setInterval("updateRoomList()", 3000);
        }
    });
    //enter 发送
    $("#txt").keydown(function (e) {
        if (e.keyCode == 13) {
            $(".send span").click();
            e.preventDefault();
        }
    });
    //滚动取消聊天自动滚动
    // console.log("here");
    $(".dialouges").mouseenter(function () {
        // console.log("enter");
        window.clearInterval(scrollHandler);
    });
    $(".dialouges").mouseleave(function () {
        // console.log("leave");
        scrollHandler = setInterval("scrollBottom()", 3000);
    });
    //发送表情
    $.qqface({
        textarea : $('#txt'),
        handle : $('#face')
    });


});
/*
$(document).keydown(function (event) {
    if (event.keyCode == 13&&!$(this).hasClass("searchRoom")) {
        $(".send span").click();
        event.preventDefault();
    }
});*/

/*日期选择*/
function getToday() {
    var today = new Date();
    today.setDate(today.getDate() + 1);
    today.setHours(0);
    today.setMinutes(0);
    today.setSeconds(0);
    today.setMilliseconds(-1);
    return today;
}

var startDate = getToday();
var endDate = getToday();
startDate.setTime(startDate.getTime() - 7 * 24 * 60 * 60 * 1000);

$(function () {
    $('#my-startDate').text(startDate.format("yyyy-M-d"));
    $('#my-endDate').text(endDate.format("yyyy-M-d"));
    console.log(startDate);
    console.log(endDate);
    var $alert = $('#my-alert');
    $('#my-start').datepicker().on('changeDate.datepicker.amui', function (event) {
        if (event.date.valueOf() > endDate.valueOf()) {
            $alert.find('p').text('The start date should be less than the end date').end().show();
        } else {
            $alert.hide();
            startDate = new Date(event.date);
            $('#my-startDate').text($('#my-start').data('date'));
        }
        /*在这里对时间段进行查询*/
        $(this).datepicker('close');
    });

    $('#my-end').datepicker().on('changeDate.datepicker.amui', function (event) {
        // alert("");
        if (event.date.valueOf() < startDate.valueOf()) {
            $alert.find('p').text('结束日期应大于开始日期！').end().show();
        } else {
            $alert.hide();
            endDate = new Date(event.date);
            endDate.setTime(endDate.getTime() + 24 * 60 * 60 * 1000 - 1)
            $('#my-endDate').text($('#my-end').data('date'));
        }
        $(this).datepicker('close');
    });
    $("#my-end").datepicker();
});
/*日期格式化*/
Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(),    //day
        "h+": this.getHours(),   //hour
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter
        "S": this.getMilliseconds() //millisecond
    }
    if (/(y+)/.test(format)) format = format.replace(RegExp.$1,
        (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o) if (new RegExp("(" + k + ")").test(format))
        format = format.replace(RegExp.$1,
            RegExp.$1.length == 1 ? o[k] :
                ("00" + o[k]).substr(("" + o[k]).length));
    return format;
}