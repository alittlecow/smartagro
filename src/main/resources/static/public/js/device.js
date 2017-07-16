//@ sourceURL=js/app/inner_task_statistics.js
$(
    function () {
    $("#jqGrid").jqGrid({
        url: window.T.rootPath + '/device/queryListDevice',
        datatype: "json",
         //postData:'name=px',  //查询参数
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
        showAdd: false,
        showUpdate: false,
        title: null,
        menu: {
            parentName: null,
            parentId: 0,
            type: 1,
            orderNum: 0
        },
        device: {
            id: null,
            code: null,
            useStatus: null,
            isBreakdown: null,
            totalMoney: null,
            totalTime:null
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
        add: function () {  //新增按钮事件
            vm.showList = false;
            vm.showAdd = true;
            vm.showUpdate = false;
            vm.title = "新增";
            vm.menu = {code: null, parentId: 0, type: 1, orderNum: 0};
            vm.getMenu();
        },
        update: function (event) {  //修改按钮事件
            var menuId = getSelectedRow();
            if (menuId == null) {
                return;
            }

            $.get(window.T.rootPath + '/device/queryDeviceById/' + menuId, function (r) {
                vm.showList = false;
                vm.showAdd = false;
                vm.showUpdate = true;
                vm.title = "修改";
                //vm.menu = r.data;
                vm.device = r.data;

                vm.getMenu();
            });
        },
        del: function (event) { //删除按钮事件
            var menuIds = getSelectedRows();
            if (menuIds == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: window.T.rootPath + '/device/deleteDevice',
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
        search: function (event) { //条件查询按钮事件
                var searchParams = {};
                $(".pageSearchElement :input").each(function () {
                    if ($(this).val()) {
                        //rules += '"' + $(this).attr("name") + '":"' + $(this).val() + '"';
                        searchParams[$(this).attr("name")]=$(this).val();
                    }
                })
                //ParamJson = '{' + rules + '}';
                var postData = $("#jqGrid").jqGrid("getGridParam", "postData");
                $.extend(postData, searchParams);
                $("#jqGrid").jqGrid("setGridParam", { search: true }).trigger("reloadGrid", [{ page: 1}]);  //重载JQGrid
        },
        saveOrUpdate: function (event) {    //新增或修改确定按钮事件
            debugger;
            if (vm.showAdd == true){
                $.ajax({
                    type: "POST",
                    url: window.T.rootPath + '/device/addDevice',
                    contentType: "application/json",
                    data: JSON.stringify(vm.device),
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
            } else if (vm.showUpdate == true) {
                $.ajax({
                    type: "POST",
                    url: window.T.rootPath + '/device/updateDevice',
                    contentType: "application/json",
                    data: JSON.stringify(vm.device),
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
            }

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