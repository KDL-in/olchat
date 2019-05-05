var myChart = echarts.init(document.getElementById('container'));
//显示城市统计结果
function showCitiesStatistic(cities) {
    myChart.clear();
    var name_title = "用户分布统计"
    var subname = '显示Top 10城市'
    var nameColor = " rgb(55, 75, 113)"
    var name_fontFamily = '等线'
    var subname_fontSize = 15
    var name_fontSize = 18
    var mapName = 'china'
    var data = cities
/*
    var data = [
        {name:"北京",value:1},
        {name:"天津",value:0},
        {name:"河北",value:0},
        {name:"山西",value:0},
        {name:"内蒙古",value:0},
        {name:"辽宁",value:0},
        {name:"吉林",value:0},
        {name:"黑龙江",value:0},
        {name:"上海",value:1},
        {name:"江苏",value:3},
        {name:"浙江",value:0},
        {name:"安徽",value:0},
        {name:"福建",value:0},
        {name:"江西",value:1},
        {name:"山东",value:0},
        {name:"河南",value:0},
        {name:"湖北",value:0},
        {name:"湖南",value:0},
        {name:"重庆",value:0},
        {name:"四川",value:0},
        {name:"贵州",value:0},
        {name:"云南",value:0},
        {name:"西藏",value:0},
        {name:"陕西",value:0},
        {name:"甘肃",value:0},
        {name:"青海",value:0},
        {name:"宁夏",value:0},
        {name:"新疆",value:0},
        {name:"广东",value:7},
        {name:"广西",value:0},
        {name:"海南",value:0},
    ];
*/

    var geoCoordMap = {};
    var toolTipData = [

    ];

    /*获取地图数据*/
    myChart.showLoading();
    var mapFeatures = echarts.getMap(mapName).geoJson.features;
    myChart.hideLoading();
    mapFeatures.forEach(function(v) {
        // 地区名称
        var name = v.properties.name;
        // 地区经纬度
        geoCoordMap[name] = v.properties.cp;

    });

// console.log("============geoCoordMap===================")
// console.log(geoCoordMap)
// console.log("================data======================")
    console.log(data)
    console.log(toolTipData)
    var max = 10,
        min = 0; // todo
    var maxSize4Pin = 100,
        minSize4Pin = 20;

    var convertData = function(data) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
            var geoCoord = geoCoordMap[data[i].name];
            if (geoCoord) {
                res.push({
                    name: data[i].name,
                    value: geoCoord.concat(data[i].value),
                });
            }
        }
        return res;
    };
    option = {
        title: {
            text: name_title,
            subtext: subname,
            x: 'center',
            textStyle: {
                color: nameColor,
                fontFamily: name_fontFamily,
                fontSize: name_fontSize
            },
            subtextStyle:{
                fontSize:subname_fontSize,
                fontFamily:name_fontFamily
            }
        },
        tooltip: {
            trigger: 'item',
            formatter: function(params) {
                if (typeof(params.value)[2] == "undefined") {
                    var toolTiphtml = ''
                    for(var i = 0;i<toolTipData.length;i++){
                        if(params.name==toolTipData[i].name){
                            toolTiphtml += toolTipData[i].name+':<br>'
                            for(var j = 0;j<toolTipData[i].value.length;j++){
                                toolTiphtml+=toolTipData[i].value[j].name+':'+toolTipData[i].value[j].value+"<br>"
                            }
                        }
                    }
                    console.log(toolTiphtml)
                    // console.log(convertData(data))
                    return toolTiphtml;
                } else {
                    var toolTiphtml = ''
                    for(var i = 0;i<toolTipData.length;i++){
                        if(params.name==toolTipData[i].name){
                            toolTiphtml += toolTipData[i].name+':<br>'
                            for(var j = 0;j<toolTipData[i].value.length;j++){
                                toolTiphtml+=toolTipData[i].value[j].name+':'+toolTipData[i].value[j].value+"<br>"
                            }
                        }
                    }
                    console.log(toolTiphtml)
                    // console.log(convertData(data))
                    return toolTiphtml;
                }
            }
        },
        // legend: {
        //     orient: 'vertical',
        //     y: 'bottom',
        //     x: 'right',
        //     data: ['credit_pm2.5'],
        //     textStyle: {
        //         color: '#fff'
        //     }
        // },
        visualMap: {
            show: true,
            min: 0,
            max: 10,
            left: 'left',
            top: 'bottom',
            text: ['高', '低'], // 文本，默认为数值文本
            calculable: true,
            seriesIndex: [1],
            inRange: {
                // color: ['#3B5077', '#031525'] // 蓝黑
                color: ['#ffe4ff', '#800080'] // 红紫
                // color: ['#3C3B3F', '#605C3C'] // 黑绿
                // color: ['#0f0c29', '#302b63', '#24243e'] // 黑紫黑
                // color: ['#23074d', '#cc5333'] // 紫红
                // color: ['#A5CC82', '#00467F'] // 蓝绿
                // color: ['#1488CC', '#2B32B2'] // 浅蓝
                // color: ['#00467F', '#A5CC82'] // 蓝绿
                // color: ['#00467F', '#A5CC82'] // 蓝绿
                // color: ['#00467F', '#A5CC82'] // 蓝绿
                // color: ['#00467F', '#A5CC82'] // 蓝绿

            }
        },
        /*工具按钮组*/
        // toolbox: {
        //     show: true,
        //     orient: 'vertical',
        //     left: 'right',
        //     top: 'center',
        //     feature: {
        //         dataView: {
        //             readOnly: false
        //         },
        //         restore: {},
        //         saveAsImage: {}
        //     }
        // },
        geo: {
            show: true,
            map: mapName,
            label: {
                normal: {
                    show: false
                },
                emphasis: {
                    show: false,
                }
            },
            roam: true,
            itemStyle: {
                normal: {
                    areaColor: '#031525',
                    borderColor: '#3B5077',
                },
                emphasis: {
                    areaColor: '#2B91B7',
                }
            }
        },
        series: [{
            name: '散点',
            type: 'scatter',
            coordinateSystem: 'geo',
            data: convertData(data),
            symbolSize: function(val) {
                return 4;
            },
            label: {
                normal: {
                    fontSize:10,
                    formatter: '{b}',
                    position: 'right',
                    show: true
                },
                emphasis: {
                    show: true
                },

            },
            itemStyle: {
                normal: {
                    color: 'grey'
                }
            }
        },
            {
                type: 'map',
                map: mapName,
                geoIndex: 0,
                aspectScale: 0.75, //长宽比
                showLegendSymbol: false, // 存在legend时显示
                label: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: false,
                        textStyle: {
                            color: '#fff'
                        }
                    }
                },
                roam: true,
                itemStyle: {
                    normal: {
                        areaColor: '#031525',
                        borderColor: '#3B5077',
                    },
                    emphasis: {
                        areaColor: '#2B91B7'
                    }
                },
                animation: false,
                data: data
            },
            // {
            //     name: '点',
            //     type: 'scatter',
            //     coordinateSystem: 'geo',
            //     symbol: 'pin', //气泡
            //     symbolSize: function(val) {
            //         var a = (maxSize4Pin - minSize4Pin) / (max - min);
            //         var b = minSize4Pin - a * min;
            //         b = maxSize4Pin - a * max;
            //         return a * val[2] + b+5;
            //     },
            //     label: {
            //         normal: {
            //             show: true,
            //             textStyle: {
            //                 color: '#fff',
            //                 fontSize: 9,
            //             }
            //         }
            //     },
            //     itemStyle: {
            //         normal: {
            //             color: '#F62157', //标志颜色
            //         }
            //     },
            //     zlevel: 6,
            //     data: convertData(data),
            // },
            {
                name: 'Top 5',
                type: 'effectScatter',
                coordinateSystem: 'geo',
                data: convertData(data.sort(function(a, b) {
                    return b.value - a.value;
                }).slice(0, 5)),
                symbolSize: function(val) {
                    return (val[2]+10) / 1;
                },
                showEffectOn: 'render',
                rippleEffect: {
                    brushType: 'stroke'
                },
                hoverAnimation: true,
                label: {
                    normal: {

                        textBorderColor: "grey",
                        textBorderWidth: 3,
                        fontSize:16,
                        formatter: '{b}',
                        position: 'right',
                        show: true
                    }

                },
                itemStyle: {
                    normal: {

                        color: '#fff',
                        shadowBlur: 10,
                        shadowColor: 'width'


                    }
                },
                zlevel: 1
            },

        ]
    };
    myChart.setOption(option);

}
//用户发布统计
function placeStatistic() {
    $.post("statistics?" + new Date(),{
        "method":"placeStatistic"
    },function (objs) {
        //转换格式
        var arr = [];
        for (var city in objs) {
            var t ={};
            t.name = city;
            t.value = objs[city];
            arr.push(t);
        }
        // console.log(objs);
        showCitiesStatistic(arr);
    },'json');
}
//显示性别统计
function showSexStatistic(arr) {
    myChart.clear();
    var data = arr;
    option = {
        title : {
            text: '男女性别占比',
            subtext: '',
            x:'center'
        },
        color: ['#4c8edb', '#d23f52'],
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['男','女']
        },
        series : [
            {
                name: '',
                type: 'pie',
                radius : '55%',
                center: ['50%', '46%'],
                label: {
                    normal: {
                        formatter: '{b}：{d}%',
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '14',
                            fontWeight: 'bold'
                        }
                    }
                },
                data:data,
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    myChart.setOption(option);
}

//性别统计
function sexualStatistic() {
    $.post("statistics?" + new Date(),{
        "method":"sexualStatistic"
    },function (objs) {
        //数据格式转换
        var arr = new Array();
        for (var key in objs) {
            var t = {};
            t.name = key;
            t.value = objs[key];
            arr.push(t);
        }
        showSexStatistic(arr);
    },'json');
}

$(function () {
    //统计对象事件绑定
    $("li#comeFrom").click(function () {
        placeStatistic();
    });
    $("li#sexual").click(function () {
        sexualStatistic();
    });
    //初始化显示
    $("li#comeFrom").click();
    $("li#comeFrom").addClass("active")
});