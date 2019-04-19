/*// 计算rem 模板宽375px
(function(temWid){
	resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
	recalc = function (){
		var cWidth = document.documentElement.clientWidth || document.body.clientWidth;
		if (!cWidth) {
			return;

		};
		document.documentElement.style.fontSize = cWidth*(100/temWid) + 'px';
	};
	if (!document.addEventListener) {
		return;
	};
	window.addEventListener(resizeEvt, recalc, false);
    document.addEventListener('DOMContentLoaded', recalc, false);
})(375);*/
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
});
function updateMoments() {
    $.post("moment?" + new Date().getTime(),
        {
          "method":"getMomentList"
        },
        function (data) {
            $("#moment-container").html(data);
            // alert("update")
            commentEventBinding();
        }
    );
}
function publicMoment() {
    var txt = $("#txt").val();
    $("#txt").val("");
    $.post("moment?"+new Date().getTime(),
        {
            "method":"publicMoment",
            "txt":txt,
        //    todo img_url
        },
        function () {
            updateMoments()
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
        if (len > 4) {
            $(".more")[i].style.display = "block";
        }
        if (len - 4 < 1) {
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
    //添加向下的图标
    $(".more i").addClass("laydown");
//	评论事件
    $(".contain .discuss").click(function () {
        // $(this).parent().find(".dis_cont").toggle();
        $(this).parent().parent().children(".dis_cont").toggle();
    });
    hideComm();

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
    });

    /*	//计算图片显示方式
        var img = $(".images");
        for(var i = 0; i < img.length; i++){
            //计算图片数量
            var imgCount = $(".images")[i].children[0].children.length;
            //计算图片宽与高
            var img = $(".img");
            if(imgCount === 1){
                $(".images")[i].children[0].style.height = 1.75 + "rem";
                for(var j = 0; j < img[i].children.length; j++){
                    img[i].children[j].style.width = 1.75 + "rem";
                    img[i].children[j].style.height = 1.75 + "rem";
                    if(j%3 === 0){
                        img[i].children[j].style.marginLeft = "0";
                    }
                }
            }else if (imgCount === 2) {
                $(".images")[i].children[0].style.height = 1.34 + "rem";
                for(var j = 0; j < img[i].children.length; j++){
                    img[i].children[j].style.width = 1.34 + "rem";
                    img[i].children[j].style.height = 1.34 + "rem";
                    if(j%3 === 0){
                        img[i].children[j].style.marginLeft = "0";
                    }
                }
            }else if (imgCount === 4) {
                $(".images")[i].children[0].style.height = 2.72 + "rem";
                for(var j = 0; j < img[i].children.length; j++){
                    img[i].children[j].style.width = 1.34 + "rem";
                    img[i].children[j].style.height = 1.34 + "rem";
                    if(j%2 === 0){
                        img[i].children[j].style.marginLeft = "0";
                    }
                    if (j > 1){
                        img[i].children[j].style.marginTop = 0.04 + "rem";
                    }
                }
            }else {
                //计算显示多少行
                var lineNumber = Math.ceil(imgCount/3);
                //计算高度
                $(".images")[i].children[0].style.height = lineNumber*0.88 + (lineNumber-1)*0.04 + "rem";
                for(var j = 0; j < img[i].children.length; j++){
                    img[i].children[j].style.width = 0.88 + "rem";
                    img[i].children[j].style.height = 0.88 + "rem";
                    if(j%3 === 0){
                        img[i].children[j].style.marginLeft = "0";
                    }

                    if (j > 2){
                        img[i].children[j].style.marginTop = 0.04 + "rem";
                    }
                }
            }
        }*/

    //点赞
    var flagAgree = true;
    $(".like").on("click", function () {
        var user_id = $("input[name='user_id']").val();
        var moment_user_id = $(this).parent().parent().parent().find("input[name='moment_user_id']").val();
        if(user_id==moment_user_id){
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
        if(confirm("Delete?")==true){
            $.post("moment?"+new Date().getTime(),
                {
                    "method":"deleteMoment",
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
