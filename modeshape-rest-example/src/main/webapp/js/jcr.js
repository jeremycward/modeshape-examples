$(document).ready(function () {
    $('#itemTree').on('select_node.jstree', function (e, data) {
        $("#propertiesPanel").empty()
        var whNode = data.instance.get_node(data.selected).original.whNode
        if (whNode){
            //var parent =data.instance.get_parent(data.selected).original.whNode
            var propertiesViewModel = []
            var idPath = []
            pushIdPath(idPath,data.instance,data.selected[0])
            populatePropertiesViewModel(idPath,data.instance,propertiesViewModel)
            while(propertiesViewModel.length>0){
                var  source = $("#props-template").html();
                var template = Handlebars.compile(source);
                var html = template(propertiesViewModel.pop())
                $("#propertiesPanel").prepend(html)
            }

        }
    }).jstree(
        {
            'core': {
                'multiple': false,
                'data': moreTreeData

            },
            'plugins' : ['contextmenu'],
            'contextmenu' : {items: contextMenuItems}
        });

});
function populatePropertiesViewModel(idPath,jsTree,viewModel){
    var currentNode = jsTree.get_node(idPath.pop()).original;
    var whNode =currentNode.whNode
    var title = currentNode.text
    var properties = []
    var keys = _.keys(whNode)
    while(keys.length>0){
        var thisKey = keys.pop()
        if (thisKey.startsWith("whouse:")){
            var label = thisKey.split(':')[1]
            properties.unshift({label:label,value:whNode[thisKey] })
        }
    }
    if(properties.length > 0){
        viewModel.push({title: title, properties:properties})
    }
    if (idPath.length>0){
        populatePropertiesViewModel(idPath,jsTree,viewModel)
    }

}
function pushIdPath(idPath,jsTree,currentNode){
    idPath.push(currentNode);
    var parent = jsTree.get_parent(currentNode)
    if (parent){
            if (parent !=='#')
            pushIdPath(idPath,jsTree,parent)
    }
}

function moreTreeData(obj, treeCallback) {
    if (obj.id === '#') {
        $.ajax({
                dataType: 'json',
                url: "/modeshape-rest-example/restful-services/warehouse",
                data: {depth: -1},
                success: function (data) {
                    treeCallback.call(this, getWorkSpaceNamesFromwareHouse(data))
                }
            }
        );
    } else {
        var itemsUrl = obj.original.warehouseData.items
        $.ajax({

                dataType: 'json',
                data: {depth: -1},
                url: itemsUrl,
                success: function (data) {
                    var treeNodes = []
                    addtreeNodes(data, treeNodes)
                    treeCallback.call(this, treeNodes)
                }
            }
        );
    }
}

function addtreeNodes(restNode, treeNode) {
    var kidsNames = _.keys(restNode.children)
    kidsNames.forEach(function (thisKid) {
        if (thisKid != "jcr:system") {
            var thisRestNode = restNode.children[thisKid]
            var newTreeNode = {text: thisKid, whNode: thisRestNode, children: []}
            treeNode.push(newTreeNode)
            if (thisRestNode.children) {
                addtreeNodes(thisRestNode,newTreeNode.children)
            }
        }
    })
}

function getWorkSpaceNamesFromwareHouse(data) {
    var result = []
    _.forEach(data.workspaces, function (value) {

        result = _.concat(result, {text: value.name, children: true, warehouseData: value});
    })
    return result;
}

$(window).load(function () {
    console.log("window loaded");
});






