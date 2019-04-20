//图片上传部分
// 初始化Web Uploader
var uploader = WebUploader.create({

    // 选完文件后，是否自动上传。
    auto: true,

    resize: true,

    // 文件接收服务端。
    server: 'upload',

    // 选择文件的按钮。可选。
    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
    pick: '#picker',

    // 只允许选择图片文件。
    accept: {
        title: 'Images',
        extensions: 'gif,jpg,jpeg,bmp,png',
        mimeTypes: 'image/*'
    }
});
uploader.on( 'uploadSuccess', function( file ,response) {
    // alert(response._raw);//cur
    $("#img_url").html(response._raw);
    $(".doc").click();
});
$(function () {
    $(".sidebar .active").click();
    $("#chat_bg .offline").click(exit);

//	发送事件
    $(".message .send span").click(publicMoment);
    $(".message #txt").keydown(function (e) {
        if (e.keyCode == 13) {
            $(".message .send span").click();
            e.preventDefault();
        }
    });

//  更新动态
    updateMoments();
//  图片上传面板
    $(".doc").click(function () {
        $("#uploadPanel").toggle(200);
    });
    $(".doc").click();
});

function updateMoments() {
    $.post("moment?" + new Date().getTime(),
        {
            "method": "getMomentList"
        },
        function (data) {
            $("#moment-container").html(data);
            // alert("update")
            momentEventBinding();
        }
    );
}

function publicMoment() {
    var txt = $("#txt").val();
    $("#txt").val("");
    $.post("moment?" + new Date().getTime(),
        {
            "method": "publicMoment",
            "txt": txt,
            "img_url": $("span#img_url").html(),
        },
        function () {
            updateMoments();
            $("span#img_url").html("");
        }
    );
}

function exit() {
    window.location.href = "login?" + new Date().getTime() + "&method=exit";
}


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

function hideComm() {
    //计算被隐藏的评论数
    var dis = $(".dis_cont");
    for (var i = 0; i < dis.length; i++) {
        //计算回复条数
        var len = dis[i].children.length;
        // alert(i + " " + len);
        if (len > 4) {
            $(".more")[i].style.display = "block";
        }
        if (len - 4 < 1) {
            // alert($(".more"));
            $(".more")[i].style.display = "none";
        }
        var p = $(".more")[i].children[0].children[0];
        p.innerText = len - 4 + "条";
    }

    //计算删除按钮
    var cont = $(".contain");
    var name2 = $(".name").text();
    var dele = $(".dele");
    for (var i = 0; i < cont.length; i++) {
        var name1 = cont[i].children[0].innerText;
        if (name1 == name2) {
            dele[i].style.display = "block";
        }
    }
}

function commentEventBinding() {
    $(".commentSenderLi").hide();
//显示更多
    var flag = true;
    $(".more").on("click", function () {
        var length = $(this).parent().children("li:nth-child(n+4)").length;
        if (flag) {
            $(this).parent().children(":hidden").show();
            $(this).children("span").html("收起");
            $(this).children("i").addClass("packup").removeClass("laydown");
            flag = false;
        } else {
            var len = $(this).parent().children("li:nth-child(n+4)").length;
            var moreText = "显示更多" + len + "条信息";
            $(this).parent().children("li:nth-child(n+4)").hide();
            $(this).children("span").html(moreText);
            $(this).children("i").removeClass("packup").addClass("laydown");
            flag = true;
        }
        $(".commentSenderLi").hide();
    });
}

//右键菜单，回复，删除，十分复杂
function rightMemuEventBinding(dis_cont) {
    $(dis_cont).children("li").each(function (key, li) {
        // var selectId = $(this).find("input[name='cur_user_id']").val();
        var moment_id = $(li).parent().parent().parent().find("input[name='moment_id']").val();
        var user_id = $("input[name='user_id']").val();
        var senderLi = $(li).parent().find(".commentSenderLi");
        var senderInput = senderLi.children("input");
        $(li).contextmenu(function (e) {
            // var myId = $("input[name='user_id']").val();
            var menu = [
                [
                    '回复', //title
                    function (dom) {
                        senderLi.show();
                        senderInput.focus();
                        senderInput.keydown(function (e) {
                            if (e.keyCode == 13) {
                                $.post("moment?" + new Date().getTime(),
                                    {
                                        "method":"publicComment",
                                        "content": senderInput.val().toString().trim(),
                                        "user_id":user_id,
                                        "reply_id":user_id,
                                        "moment_id":moment_id
                                    },
                                    function (data) {
                                        senderInput.val("");
                                        $(li).parent().parent().find(".comment .discuss").click();
                                        $(li).parent().parent().find(".comment .discuss").click();
                                    });
                            }
                        });
                    } // 点击菜单项的回调

                ],
                [
                    '回复该评论',
                    function (data) {
                        var reply_id = $(li).find("input[name='cmt_user_id']").val();
                        var reply_name = $(li).find("input[name='cmt_user_name']").val();
                        senderLi.show();
                        senderInput.val("@"+reply_name+": ");
                        senderInput.focus();
                        senderInput.keydown(function (e) {
                            if (e.keyCode == 13) {
                                $.post("moment?" + new Date().getTime(),
                                    {
                                        "method":"publicComment",
                                        "content": senderInput.val().toString().split(":")[1].trim(),
                                        "user_id":user_id,
                                        "reply_id":reply_id,
                                        "moment_id":moment_id
                                    },
                                    function (data) {
                                        //todo 隐藏输入
                                        // alert(data);
                                        senderInput.val("");
                                        $(li).parent().parent().find(".comment .discuss").click();
                                        $(li).parent().parent().find(".comment .discuss").click();
                                    });
                            }
                        });
                    }
                ],
                [
                    '删除该评论',
                    function (data) {
                        var comment_id = $(li).find("input[name='comment_id']").val();
                        if (confirm("delete?") == true) {

                            $.post("moment?" + new Date().getTime(),
                                {
                                    "method": "deleteComment",
                                    "comment_id": comment_id
                                },
                                function () {
                                    $(li).parent().parent().find(".comment .discuss").click();
                                    $(li).parent().parent().find(".comment .discuss").click();
                                }
                            );
                        }
                    }
                ]
            ];
            // alert(test);
            ContextMenu.render(e, menu, this, "light"); //开始渲染
        });
    });

};

function momentEventBinding() {
    //添加向下的图标
    $(".more i").addClass("laydown");
//	评论事件
    $(".contain .discuss").click(function () {
        // $(this).parent().find(".dis_cont").toggle();
        var dis_cont = $(this).parent().parent().children(".dis_cont");
        dis_cont.toggle();
        $.post("moment?" + new Date().getTime(),
            {
                "method": "getCommentList",
                "moment_id": $(dis_cont).parent().parent().find("input[name='moment_id']").val(),
            },
            function (data) {
                dis_cont.html(data);
                commentEventBinding();
                //右键点击事件
                rightMemuEventBinding(dis_cont);
            }
        );

    });
    hideComm();

    //点赞
    var flagAgree = true;
    $(".like").on("click", function () {
        var user_id = $("input[name='user_id']").val();
        var moment_user_id = $(this).parent().parent().parent().find("input[name='moment_user_id']").val();
        if (user_id == moment_user_id) {
            alert("不能点赞自己");
            return;
        }
        var agreeCount = parseInt($(this).children("p").text());

        if (flagAgree) {
            $(this).children("i").addClass("agree").removeClass("unagree");
            $(this).children("p").text(++agreeCount);
            flagAgree = false;
        } else {
            $(this).children("i").addClass("unagree").removeClass("agree");
            $(this).children("p").text(--agreeCount);
            flagAgree = true;
        }
    });

    //自己的状态加删除操作
    $(".dele").on("click", function () {
        var content = $(this).parent().parent().parent();
        if (confirm("Delete?") == true) {
            $.post("moment?" + new Date().getTime(),
                {
                    "method": "deleteMoment",
                    "moment_id": content.find("input[name='moment_id']").val()
                },
                function () {
                    // alert("删除成功");
                }
            );
            content.remove();
            hideComm();
        }

    });


}
