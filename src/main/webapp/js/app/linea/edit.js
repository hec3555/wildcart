'use strict';

moduleLinea.controller('lineaEditController', ['$scope', '$http', '$location', 'toolService', '$routeParams', 
    function ($scope, $http, $location, toolService, $routeParams) {
        $scope.idLin = $routeParams.id;
        $scope.ob = "linea";
        
        $scope.ajaxDatoLinea = {
            id: null,
            cantidad: null,
            obj_Producto: {id: null},
            obj_Factura: {id: null}
        };
        
        $http({
            method: 'GET',
            url: '/json?ob=' + $scope.ob + '&op=get&id=' + $scope.idLin
        }).then(function (response) {
            $scope.status = response.status;
            $scope.ajaxDatoLinea = response.data.message;
        }, function (response) {
            $scope.ajaxDatoLinea = response.data.message || 'Request failed';
            $scope.status = response.status;
        });



        $scope.guardar = function () {
            var json = {
                id: $scope.ajaxDatoLinea.id,
                cantidad: $scope.ajaxDatoLinea.cantidad,
                id_producto: $scope.ajaxDatoLinea.obj_Producto.id,
                id_factura: $scope.ajaxDatoLinea.obj_Factura.id
            };
            $http({
                method: 'GET',
                withCredentials: true,
                url: '/json?ob=' + $scope.ob + '&op=update',
                params: {json: JSON.stringify(json)}
            }).then(function (response) {
                $scope.status = response.status;
                $scope.mensajeOK = true;
            }, function (response) {
                $scope.msgError = response.message;
                $scope.status = response.status;
            });
        };


        $scope.productoRefresh = function (quiensoy, consulta) {
            var form = quiensoy;
            if (consulta) {
                $http({
                    method: 'GET',
                    //withCredentials: true,
                    url: 'json?ob=producto&op=get&id=' + $scope.ajaxDatoLinea.obj_Producto.id
                }).then(function (response) {
                    if (response.data.message !== null) {
                        form.userForm.obj_producto.$setValidity('valid', true);
                        $scope.ajaxDatoLinea.obj_Producto = response.data.message;
                    } else {
                        form.userForm.obj_producto.$setValidity('valid', false);
                        $scope.msgError = "Error al acceder al usuario";
                    }
                }, function (response) {
                    form.userForm.obj_producto.$setValidity('valid', false);
                    $scope.msgError = "Error al acceder al usuario";
                });
            } else {
                form.userForm.obj_producto.$setValidity('valid', true);
            }
        };



    }]);
