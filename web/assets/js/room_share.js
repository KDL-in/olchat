/**
 * Created by Administrator on 2017/6/29.
 */
//�����sidebar�л�ʱ�ı仯
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
})
/*//�����˶Ի�
$(".send span").click(function(){
    //ͨ������������ı����ɶԻ�
    var self= $('<div class="self"><span>'+$("#txt").val()+'</span><img src="assets/images/1.jpg" alt=""/></div>')
    $(".container").append(self);
    //����ajax����
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
    //�������������
    $("#txt").val("");

})*/
/*
���ͶԻ�
 */



/*custom*/

function showChatRecords() {
    var cid = $("input[name='room_id']").val();
    // alert(cid);
    if (cid == "") return;
    $.post("visit?" + new Date().getTime(), {"method": "showChatRecords", "room_id": cid}, function (data) {
        $(".container").html(data);
        var heightdis = $(".container").height() - $(".dialouges").height();
        console.log(heightdis);
        if (heightdis >= 0) {
            $(".dialouges").scrollTop(heightdis + 10);
        }
    });
}

function sendMessage() {
    if ($("input[name='room_id']").val() == "") return;
    // alert($("input[name='type']").val());
    $.post("visit?" + new Date().getTime(),
        {
            "method": "sendMessage",
            "txt": $("#txt").val(),
            "type": $("input[name='type']").val(),
            "target_name": $("input[name='target_name']").val()
        },
        function () {
            showChatRecords();
        });
    $("#txt").val("");
    $("input[name='type']").val(0);
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

function searchChatroom(o) {
    $.post("visit?"+new Date().getTime(),
        {"method":"searchChatroom","keyWord":$(".searchRoom").val()},
        function (data) {
            if(data=="false"){
                alert("NO RESULT");
                return;
            }
            var psw = prompt("Enter Password", "");
            //������ȷ����������
            $.post("visit?"+new Date().getTime(),
                {
                    "method":"addToChatroom",
                    "user_id":$("input[name='user_id']").val(),
                    "room_psw":psw
                },
                function (data) {
                    alert(data);
                    updateRoomList();
            });
    });
    $(o).val("");
}

$(function () {
    // Ĭ��ѡ��
    $(".sidebar .active").click();
    //���������������¼
    showChatRecords();
    //��ʱ���̸߳��µ�ǰ����
    window.setInterval("showChatRecords()", 2000);
    //������Ϣ
    $(".send span").click(sendMessage);
    //ע����
    $("#chat_bg .offline").click(exit);
    //ѡ��ͼƬ���
    $(".doc").click(toggleUpload);
    $(".doc").click();
    //����
    $('.searchRoom').on('keypress', function (event) {if (event.keyCode == 13) {searchChatroom(this);}});
    //����Ⱥ

});
/*
$(document).keydown(function (event) {
    if (event.keyCode == 13&&!$(this).hasClass("searchRoom")) {
        $(".send span").click();
        event.preventDefault();
    }

});*/
