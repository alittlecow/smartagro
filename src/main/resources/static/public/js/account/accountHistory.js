$(function () {

//初始化日期控件
    $('.form_date').datetimepicker({
        // language: 'zh',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
    $("#jqGrid").jqGrid({
        url: '/account/queryUserAccountHistory',
        datatype: "json",
        mtype: 'POST',
        postData: {"token": localStorage.token},
        colModel: [
            {label: '账户ID', name: 'accountId', width: 100},
            {label: '调整金额', name: 'adjustMoney', width: 50},
            {label: '调整前金额', name: 'beforeAdjustMoney', width: 50},
            {label: '调整后金额', name: 'afterAdjustMoney', width: 50},
            {
                label: '调整类型', name: 'adjustType', width: 50, formatter: function (value, options, row) {
                if (value === 0) {
                    return '<span">用户充值</span>';
                }
                if (value === 1) {
                    return '<span">用户消费</span>';
                }
                if (value === 2) {
                    return '<span">分销商结算</span>';
                }
            }
            },
            {label: '生成时间', name: 'createTime', sortable: false, width: 70}
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 20, 30],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "data",
            page: "pageNo",
            total: "pages",
            records: "totalRecordNum"
        },
        prmNames: {
            //请求参数
            page: 'pageNo',
            rows: 'onePageNum'
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});


var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        queryParams: {
            accountId: "",
            beginTime: "",
            endTime: ""
        }
    },
    methods: {
        queryList: function (event) {
            var data = {
                "token": localStorage.token,
                "accountId": vm.queryParams.accountId,
                "beginTime": $("#beginTime").val(),
                "endTime": $("#endTime").val()
            };
            $("#jqGrid").jqGrid('setGridParam', {
                postData: data,
            }).trigger('reloadGrid');
        },
        update: function (event) {
            var ids = getSelectedRow();
            if (ids == null) {
                return;
            }
            $.ajax({
                type: "POST",
                url: "/account/queryAccountById",
                contentType: "application/json",
                data: JSON.stringify(ids),
                success: function () {
                }
            });
        },
        del: function (event) {
            var ids = getSelectedRows();
            if (ids == null) {
                return;
            }
            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: "../sys/menu/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                    }
                });
            });
        },
        saveOrUpdate: function (event) {
            var url = vm.menu.menuId == null ? "../sys/menu/save" : "../sys/menu/update";
            $.ajax({
                type: "POST",
                url: url,
                contentType: "application/json",
                data: JSON.stringify(vm.menu),
                success: function (r) {
                    alert(r.msg);
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                page: page
            }).trigger("reloadGrid");
        }
    }
});


