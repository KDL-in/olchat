function initIndex() {
    $.post("server?" + new Date().getTime(),
        {
            "method": "getInfoList"
        }, function (data) {
            // alert(data);
            $(".initDataDiv").html(data);
        });
    $.post("server?"+new Date().getTime(),
        {
            "method": "getInfoData"
        },function (data) {
            // alert(data.nOfUsers);
            $("dd#nOfImgs").html(data.nOfImgs);
            $("dd#nOfRecords").html(data.nOfRecords);
            $("dd#nOfRooms").html(data.nOfRooms);
            $("dd#nOfUsers").html(data.nOfUsers);
        },'json'
        );
}

$(function () {
    //加载首页信息
    initIndex();
});