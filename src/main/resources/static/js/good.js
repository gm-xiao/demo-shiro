layui.use([ 'layer', 'form', 'table' ],function() {
    var layer = layui.layer, form = layui.form, table = layui.table;

    var goodObject = {
        table : function () {
            goodTable = table.render({
                id:'goodTable',
                elem: document.getElementById('goodTable'),
                url:'/good/list',
                cols: [[
                    { field:'id', checkbox: true },
                    { field:'code', title:'商品编号', width:150, align:'center' },
                    { field:'name', title:'商品名称', width:150, align:'center' },
                    { field:'typeName', title:'商品类型', width:150, align:'center' },
                    { field:'brandName', title:'商品品牌', width:150, align:'center' },
                    { field:'unit', title:'单位', width:150, align:'center' },
                    { field:'price', title:'商品单价', width:150, align:'center' },
                    { field:'createTime', title:'创建时间', width:200, align:'center' },
                    { field:'state', title:'状态', width:200, align:'center' ,templet:'#stateBar'},
                    { fixed: 'right', width:150, toolbar:'#bar' }
                ]],
                where:{},
                page: true,
                limit:10,
                loading: true,
                limits: [10,20,30,50],
            });

            return this;
        },
        updateTable : function () {
            var data = {};
            goodTable.reload({
                url: '/good/list?timestamp='+ Date.parse(new Date()),
                where : data,
                page : {curr: 1}
            });
        },
        onBar : function () {
            var obj = this;

            // 监听工具条
            table.on('tool(goodTable)', function( red ){
                var data = red.data,event = red.event;
                if( 'edit' === event ){
                    layer.msg("功能没开发，你咬我啊",{icon:1});
                }else if( 'del' === event ){
                    obj.del( data.id );
                }
            });


            form.on('submit(save)', function(data){
                obj.save();
                return false;
            });

        },
        del : function (id) {
            var obj = goodObject;
            function delCallback(red){
                if( red.code == 200  ){
                    layer.msg(red.msg,{icon:1});
                    setTimeout(function(){
                        obj.updateTable();
                    },1000);
                }else{
                    layer.msg(red.msg,{icon:5});
                    return;
                }
            };
            layer.confirm('是否删除商品信息?', {icon: 3, title:'提示'}, function(index){
                layer.close(index);
                var option = { type : 'POST',  async : true, cache : false, url : '/good/del', data : {'id':id}, success : delCallback , error : function(data){console.log(data)} };
                $.ajax(option);
            });
        },
        save : function () {
            var obj = this;

            var saveCallback = function( red){
                if( red.code == 200  ){
                    layer.msg(red.msg,{icon:1});
                    setTimeout(function(){
                        window.location.href = '/goods';
                    },1000);
                }else{
                    layer.msg(red.msg,{icon:5});
                    return;
                }
            };
            layer.confirm('是否提交商品信息?', {icon: 3, title:'提示'}, function(index){
                layer.close(index);
                var option = { type : 'POST',  async : true, cache : false, url : '/good/save', data : $(document.getElementById('saveForm')).serialize(), success : saveCallback , error : function(data){console.log(data)} };
                $.ajax(option);
            });
            return obj;
        }
    }

    goodObject.table().onBar();


});