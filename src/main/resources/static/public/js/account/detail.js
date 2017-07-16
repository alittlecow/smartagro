$(function () {
    $("#jqGrid").jqGrid({
        url: '/account/list',
        datatype: "json",
        mtype: 'POST',
        postData: {"token": localStorage.token},
        colModel: [
            {label: '用户ID', name: 'userId', width: 100},
            {label: '账户余额', name: 'money', width: 30},
            {label: '创建时间', name: 'createTime', sortable: false, width: 70},
            {label: '更新时间', name: 'updateTime', width: 70},
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
        query: {
            userId: null
        }
    },
    methods: {
        queryList: function (event) {
            var data = {
                "token": localStorage.token,
                "userId": vm.query.userId
            };
            $("#jqGrid").jqGrid('setGridParam', {
                url: '/account/list',
                postData: data,
            }).trigger('reloadGrid');
        },
        update: function (event) {
            var accountIds = getSelectedRow();
            if (accountIds == null) {
                return;
            }
            $.ajax({
                type: "POST",
                url: "/account/queryAccountById",
                contentType: "application/json",
                data: JSON.stringify(accountIds),
                success: function () {
                }
            });
        },
        del: function (event) {
            var menuIds = getSelectedRows();
            if (menuIds == null) {
                return;
            }
            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: "../sys/menu/delete",
                    contentType: "application/json",
                    data: JSON.stringify(menuIds),
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


