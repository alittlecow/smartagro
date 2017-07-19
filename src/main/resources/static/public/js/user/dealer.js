$(function () {
    $('#treeview1').treeview({
        data: [{
            "id": 1,
            "text": "城市管理",
            "level": 0,
            "tag": 0,
            "nodes": [{
                "id": 1001,
                "text": "北京",
                "level": 0,
                "tag": 0,
                "href": "account!editAccount.html"
            }, {
                "id": 1003,
                "text": "条件",
                "level": 0,
                "tag": 0,
                "href": "system!listGroup.html"
            }, {"id": 1002, "text": "黄河入海流", "level": 0, "tag": 0, "href": "account!list.html"}, {
                "id": 1004,
                "text": "欲穷千里目",
                "level": 0,
                "tag": 0,
                "href": "account!chgPasswd.html"
            }, {"id": 1005, "text": "办事处管理", "level": 0, "tag": 0, "href": "td!officeList.html"}, {
                "id": 1006,
                "text": "工作组管理",
                "level": 0,
                "tag": 0,
                "href": "group!list.html"
            }, {"id": 1007, "text": "工作区管理", "level": 0, "tag": 0, "href": "workSpace!list.html"}, {
                "id": 1008,
                "text": "业务员管理",
                "level": 0,
                "tag": 0,
                "href": "marketingaAccount!listSalesman.html"
            }, {"id": 1009, "text": "日照香炉生紫烟", "level": 0, "tag": 0, "href": "td!hotelList.html"}, {
                "id": 1010,
                "text": "更上一层楼",
                "level": 0,
                "tag": 0,
                "href": "td!saleList.html"
            }, {"id": 1022, "text": "帮助文档", "level": 0, "tag": 0, "href": "fileDownload!downloadX.html"}],
            "href": ""
        }, {
            "id": 9,
            "text": "汇总数据",
            "level": 0,
            "tag": 0,
            "nodes": [{"id": 1020, "text": "礼拜", "level": 0, "tag": 0, "href": "calculation!edit.html"}, {
                "id": 1021,
                "text": "杜甫",
                "level": 0,
                "tag": 0,
                "href": "calculationRecrd!list.html"
            }],
            "href": ""
        }, {
            "id": 2,
            "text": "业务记录模块",
            "level": 0,
            "tag": 0,
            "nodes": [{
                "id": 1011,
                "text": "酒楼变更记录",
                "level": 0,
                "tag": 0,
                "href": "hotelChange!list.html"
            }, {
                "id": 1013,
                "text": "吉拉拉",
                "level": 0,
                "tag": 0,
                "href": "tdyhRecord!patBarcodeRecordList.html"
            }, {
                "id": 1014,
                "text": "窜货记录",
                "level": 0,
                "tag": 0,
                "href": "tdyhRecord!tranGoodsRecordList.html"
            }, {"id": 1027, "text": "业务员调动记录", "level": 0, "tag": 0, "href": "logInfo!personChange.html"}, {
                "id": 1029,
                "text": "组长调动记录",
                "level": 0,
                "tag": 0,
                "href": "logInfo!groupLeaderChange.html"
            }, {
                "id": 1030,
                "text": "窜货统计",
                "level": 0,
                "tag": 0,
                "href": "tdyhRecord!statTranGoodsList.html"
            }, {"id": 1031, "text": "窜货配置", "level": 0, "tag": 0, "href": "tdyhRecord!tranSet.html"}],
            "href": ""
        }, {
            "id": 3,
            "text": "账务信息模块",
            "level": 0,
            "tag": 0,
            "nodes": [{
                "id": 1015,
                "text": "组长待确认账单",
                "level": 0,
                "tag": 0,
                "href": "td!groupLeaderStatementList.html?type=0"
            }, {
                "id": 1018,
                "text": "组长已确认账单",
                "level": 0,
                "tag": 0,
                "href": "td!groupLeaderStatementList.html?type=1"
            }, {
                "id": 1019,
                "text": "组长已挂起账单",
                "level": 0,
                "tag": 0,
                "href": "td!groupLeaderStatementList.html?type=2"
            }],
            "href": ""
        }, {
            "id": 5,
            "text": "财务对账模块",
            "level": 0,
            "tag": 0,
            "nodes": [{"id": 5001, "text": "待确认账单", "level": 0, "tag": 0, "href": "finance!list.html"}, {
                "id": 5002,
                "text": "已确认账单",
                "level": 0,
                "tag": 0,
                "href": "finance!confirmList.html"
            }],
            "href": ""
        }, {
            "id": 6,
            "text": "未兑换积分数据",
            "level": 0,
            "tag": 0,
            "nodes": [{"id": 5004, "text": "未兑换积分汇总", "level": 0, "tag": 0, "href": "td!surplusSummary.html"}],
            "href": ""
        }, {
            "id": 7,
            "text": "积分兑换数据",
            "level": 0,
            "tag": 0,
            "nodes": [{"id": 5003, "text": "积分兑换汇总", "level": 0, "tag": 0, "href": "td!pointSummary.html"}],
            "href": ""
        }, {
            "id": 8,
            "text": "积分数据",
            "level": 0,
            "tag": 0,
            "nodes": [{
                "id": 5005,
                "text": "积分汇总",
                "level": 0,
                "tag": 0,
                "href": "td!integralSummary.html"
            }, {"id": 5006, "text": "积分记录", "level": 0, "tag": 0, "href": "td!integralSummaryList.html"}],
            "href": ""
        }, {
            "id": 10,
            "text": "微信管理",
            "level": 0,
            "tag": 0,
            "nodes": [{"id": 1023, "text": "微信红包设置", "level": 0, "tag": 0, "href": "wx!list.html"}, {
                "id": 1024,
                "text": "微信积分配置",
                "level": 0,
                "tag": 0,
                "href": "wxIntegral!list.html"
            }, {"id": 1025, "text": "拍码设置", "level": 0, "tag": 0, "href": "patBarcode!edit.html"}, {
                "id": 1026,
                "text": "微信修改日志查询",
                "level": 0,
                "tag": 0,
                "href": "logInfo!list.html"
            }],
            "href": ""
        }, {
            "id": 11,
            "text": "微信兑换数据",
            "level": 0,
            "tag": 0,
            "nodes": [{
                "id": 1028,
                "text": "微信兑换记录",
                "level": 0,
                "tag": 0,
                "href": "td!pointSummaryList1.html?pointType=2"
            }],
            "href": ""
        }, {
            "id": 12,
            "text": "活动管理",
            "level": 0,
            "tag": 0,
            "nodes": [{"id": 1201, "text": "活动管理", "level": 0, "tag": 0, "href": "activity!list.html"}, {
                "id": 1202,
                "text": "活动统计",
                "level": 0,
                "tag": 0,
                "href": "activityPlay!list.html"
            }],
            "href": ""
        }],
        showCheckbox: true,
        levels: 1,
        onNodeChecked: function (event, data) {
            //选中父节点，则自动选择子节点
            if (data.nodes != null) {
                var arrayInfo = data.nodes;
                for (var i = 0; i < arrayInfo.length; i++) {
                    // $('#treeview1').treeview('checkNode', [ arrayInfo[i].nodeId, { silent: true } ]);
                    $('#treeview1').treeview('toggleNodeChecked', [arrayInfo[i].nodeId, {silent: true}]);
                }
            }
        },
        onNodeUnchecked: function (event, data) {
            //取消选中父节点，则自动取消选择子节点
            if (data.nodes != null) {
                var arrayInfo = data.nodes;
                for (var i = 0; i < arrayInfo.length; i++) {
                    // $('#treeview1').treeview('checkNode', [ arrayInfo[i].nodeId, { silent: true } ]);
                    $('#treeview1').treeview('toggleNodeChecked', [arrayInfo[i].nodeId, {silent: true}]);
                }
            }
        }

    });

});

function dosome(num) {
    if (num == 1) {
        $('#treeview1').treeview('checkAll', {silent: true});//全选
    } else if (num == 2) {
        $('#treeview1').treeview('uncheckAll', {silent: true});//取消全选
    } else if (num == 3) {
        $('#treeview1').treeview('collapseAll', {silent: true});//折叠
    } else if (num == 4) {
        $('#treeview1').treeview('expandAll', {levels: 2, silent: true});//展开所有二级节点
    }
}
function getDisabled() {
    var checkedArr = $('#treeview1').treeview('getChecked', '');
    alert(checkedArr.length);
}