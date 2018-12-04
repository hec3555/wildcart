'use strict';
moduleComponent.component('carritoSelection', {
    templateUrl: 'js/app/producto/selection.html',
    controllerAs: 'c',
    controller: cController,
    bindings: {
        obj: '=',
        onTipousuarioSet: '&'
    },
});

function cController($http) {
    //console.log("ccontroler....");
    var self = this;
    self.ob = "producto";
    
}






