/**
 * Created by Administrator on 2017/6/29.
 */
//�����sidebar�л�ʱ�ı仯
$(".sidebar .lt").on("click",function(){
    $(this).addClass("icon-liaotian").removeClass("icon-liaotian1");
    $(this).css("color","rgb(9,187,7)").siblings().css("color","white");
    $(".txl").removeClass("icon-tongxunlu_tongxunlu").addClass("icon-tongxunlu");
    $(".sc").removeClass("icon-shoucang").addClass("icon-iconfont");

})
$(".sidebar .txl").on("click",function(){
    $(this).addClass("icon-tongxunlu_tongxunlu").removeClass("icon-tongxunlu");
    $(this).css("color","rgb(9,187,7)").siblings().css("color","white");
    $(".sc").removeClass("icon-shoucang").addClass("icon-iconfont");
    $(".lt").removeClass("icon-liaotian").addClass("icon-liaotian1");
})
$(".sidebar .sc").on("click",function(){
    $(this).addClass("icon-iconfont").removeClass("icon-shoucang");
    $(this).css("color","rgb(9,187,7)").siblings().css("color","white");
    $(".lt").removeClass("icon-liaotian").addClass("icon-liaotian1");
    $(".txl").removeClass("icon-tongxunlu_tongxunlu").addClass("icon-tongxunlu");
})
//�����˶Ի�
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


})
$(document).keydown(function(event){
    if(event.keyCode==13){
        $(".send span").click();
        event.preventDefault();
    }

});
/*custom*/
/*
function prepareEvents() {
    $(".talk>ul>li").each(function (key, li) {
        $(li).click(function () {
            window.location.href="chatroom.jsp?cid="+$(this).children("input").val();
        });
    });
}*/
$(function () {
    // Ĭ��ѡ��
    $(".sidebar .active").click();
});
