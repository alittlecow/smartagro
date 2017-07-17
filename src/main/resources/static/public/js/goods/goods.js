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
        url: '/goods/list',
        datatype: "json",
        mtype: 'POST',
        postData: {"token": localStorage.token},
        colModel: [
            {label: '商品ID', name: 'id', width: 100, hidden: true},
            {label: '名称', name: 'name', width: 50},
            {
                label: '商品类型', name: 'type', width: 50, formatter: function (value, options, row) {
                if (value === 1) {
                    return '<span">设备使用次数</span>';
                }
                if (value === 2) {
                    return '<span">账户充值</span>';
                }
            }
            },
            {label: '商品价格(元)', name: 'money', width: 50},
            {label: '商品数值', name: 'value', width: 50},
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
        add: function () {

            layer.open({
                type: 1,
                skin: 'layui-layer-demo',
                title: "添加元素",
                area: ['500px', '450px'],
                shadeClose: false,
                content: jQuery("#add"),
                btn: ['修改', '取消'],
                btn1: function (index) {
                    var data = {
                        "type": $("input[name='type']:checked").val(),
                        "name": $("#name").val(),
                        "value": $("#value").val(),
                        "money": $("#money").val()
                    };
                    $.ajax({
                        type: "POST",
                        url: "/goods/add",
                        contentType: "application/json",
                        data: JSON.stringify(data),
                        success: function (result) {
                            if (result.result == 'success') {
                                alert("添加成功！");
                                vm.reload();
                            } else {
                                alert(result.message);
                            }
                        }
                    });
                }
            });
        },
        update: function (event) {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            $.get("/goods/info/" + id, function (r) {
                $("input[name='type'][value=" + r.data.type + "]").attr("checked", true);
                $("#name").val(r.data.name);
                $("#value").val(r.data.value);
                $("#money").val(r.data.money);
            });
            layer.open({
                type: 1,
                skin: 'layui-layer-demo',
                title: "修改元素",
                area: ['500px', '450px'],
                shadeClose: false,
                content: jQuery("#add"),
                btn: ['修改', '取消'],
                btn1: function (index) {
                    var data = {
                        "type": $("input[name='type']:checked").val(),
                        "name": $("#name").val(),
                        "value": $("#value").val(),
                        "money": $("#money").val(),
                        "id": id
                    };
                    $.ajax({
                        type: "POST",
                        url: "/goods/update",
                        contentType: "application/json",
                        data: JSON.stringify(data),
                        success: function (result) {
                            if (result.result == 'success') {
                                alert("修改成功！");
                                vm.reload();
                            } else {
                                alert(result.message);
                            }
                        }
                    });
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
                    url: "/goods/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (result) {
                        if (result.result == 'success') {
                            alert("添加成功！");
                            vm.reload();
                        } else {
                            alert(result.message);
                        }
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
                success: function (result) {
                    if (result.result == 'success') {
                        alert("添加成功！");
                        vm.reload();
                    } else {
                        alert(result.message);
                    }
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


