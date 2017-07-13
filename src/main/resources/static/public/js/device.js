$(function () {
    $("#jqGrid").jqGrid({
        url: window.T.rootPath + '/device/queryListDevice',
        datatype: "json",
         postData:'name=px',
        colModel: [
            {label: '设备ID', name: 'id', index: "id", width: 40, key: true, hidden:true},
            {label: '设备编号', name: 'code', width: 60},
            {label: '状态', name: 'useStatus', width: 60,formatter: function (value, options, row) {
                if (value === 0) {
                    return '<span">未使用</span>';
                }
                if (value === 1) {
                    return '<span>使用中</span>';
                }
               }
            },
            {label: '是否故障', name: 'isBreakdown', width: 50,formatter: function (value, options, row) {
                if (value === 0) {
                    return '<span">正常</span>';
                }
                if (value === 1) {
                    return '<span>故障</span>';
                }
                }
            },
            {label: '产生总金额', name: 'totalMoney', width: 100},
            {label: '使用总时间', name: 'totalTime', width: 50,formatter: function (value, options, row) {
                    return '<span>' + Math.round(value/1000) + '秒' + '</span>';
            }}
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
    })
    ;
});

var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "menuId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url: "nourl"
        }
    }
};
var ztree;

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        menu: {
            parentName: null,
            parentId: 0,
            type: 1,
            orderNum: 0
        }
    },
    methods: {
        getMenu: function (menuId) {
            //加载菜单树
            $.get("../sys/menu/select", function (r) {
                ztree = $.fn.zTree.init($("#menuTree"), setting, r.data);
                var node = ztree.getNodeByParam("menuId", vm.menu.parentId);
                ztree.selectNode(node);

                vm.menu.parentName = node.name;
            })
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.menu = {parentName: null, parentId: 0, type: 1, orderNum: 0};
            vm.getMenu();
        },
        update: function (event) {
            var menuId = getSelectedRow();
            if (menuId == null) {
                return;
            }

            $.get("../sys/menu/info/" + menuId, function (r) {
                vm.showList = false;
                vm.title = "修改";
                vm.menu = r.data;

                vm.getMenu();
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
                        if (r.result === 'success') {
                            alert('操作成功', function (index) {
                                vm.reload();
                            });
                        } else {
                            alert(r.msg);
                        }

                    }
                });
            });
        },
        saveOrUpdate: function (event) {
            var url = vm.menu.menuId == null ?  window.T.rootPath + '/device/addDevice' :  window.T.rootPath + '/device/queryListDevice';
            $.ajax({
                type: "POST",
                url: url,
                contentType: "application/json",
                data: JSON.stringify(vm.menu),
                success: function (r) {
                    if (r.result === 'success') {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }

                }
            });
        },
        menuTree: function () {
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-lan',
                title: "选择菜单",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#menuLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    //选择上级菜单
                    vm.menu.parentId = node[0].menuId;
                    vm.menu.parentName = node[0].name;

                    layer.close(index);
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