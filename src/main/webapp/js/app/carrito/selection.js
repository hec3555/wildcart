'use strict';
moduleComponent.component('carritoSelection', {
    templateUrl: 'js/app/carrito/selection.html',
    controllerAs: 'c',
    controller: cController,
    bindings: {
        msg: '=',
        idProducto: '='
    }
});

function cController() {
    var self = this;
    self.msg = "prueba";
    



}