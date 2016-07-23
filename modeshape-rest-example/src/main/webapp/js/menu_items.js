
function contextMenuItems(node) {
    // The default set of all items
    var items = {
        renameItem: { // The "rename" menu item
            label: "RenameMe",
            action: function () {}
        },
        deleteItem: { // The "delete" menu item
            label: "DeleteMe",
            action: function () {}
        },
        anotherItem: { // The "delete" menu item
            label: "Another ",
            action: function () {}
        }

    };

    if ($(node).hasClass("folder")) {
        // Delete the "delete" menu item
        delete items.deleteItem;
    }

    return items;
}
