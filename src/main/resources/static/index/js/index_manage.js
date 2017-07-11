window.onload = function () {


}

var vm = new Vue({
    el: '#pvuvBox',
    data: {
        list: [
            {"id": "00001", "title": "首页用户访问表1"},
            {"id": "00002", "title": "首页用户访问表2"},
            {"id": "00003", "title": "首页用户访问表3"},
            {"id": "00004", "title": "首页用户访问表4"}
        ]
    },
    methods: {
        del: function (p) {
            confirm('确定要删除选中的记录？', function () {
                // $.ajax({
                //     type: "POST",
                //     url: "../sys/menu/delete",
                //     contentType: "application/json",
                //     data: JSON.stringify(menuIds),
                //     success: function(r){
                //         if(r.code === 0){
                //             alert('操作成功', function(index){
                //                 vm.reload();
                //             });
                //         }else if(r.code === 1007){
                //             alert("菜单系统不可以删除");
                //         }else{
                //             alert(r.msg);
                //         }
                //
                //     }
                // });
                alert("单表： " + p + "已经删除！")
            });
        },
        edit: function (p) {
            alert('edit: ' + p);

        },
        up: function (p) {
            if (p - 1 < 0)
                return;
            vm.list = changeIndex(vm.list, p, p - 1);

        },
        down: function (p) {
            if (p + 1 > vm.list.length)
                return;
            vm.list = changeIndex(vm.list, p, p + 1);
        }
    }

});

function changeIndex(list, pre, next) {
    //复制对象
    var temp = $.extend(true, [], list);

    var preValue = temp[pre];
    var nextValue = temp[next];
    temp[next] = preValue;
    temp[pre] = nextValue;
    return temp;
}