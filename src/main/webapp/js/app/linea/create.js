'use strict';

moduleLinea.controller('lineaCreateController', ['$scope', '$http', '$location', 'toolService', '$routeParams', 
    function ($scope, $http, $location, toolService, $routeParams) {
        
        $scope.ob = "linea";

        $scope.idFac = $routeParams.id;

        $scope.ajaxDatoLinea = {
            id: null,
            cantidad: null,
            obj_producto: {id: null},
            obj_factura: {id: null}
        };
        
        
        if($routeParams.id !== "undefined" && $routeParams.id !== "" && $routeParams.id){
            $scope.ajaxDatoLinea.obj_factura.id = $scope.idFac;
            $scope.idFacExist = true;
            $http({
                    method: 'GET',
                    //withCredentials: true,
                    url: 'json?ob=factura&op=get&id=' + $scope.ajaxDatoLinea.obj_factura.id
                }).then(function (response) {
                    if (response.data.message !== null) {
                        $scope.ajaxDatoLinea.obj_factura = response.data.message;
                    } else {
                        $scope.ajaxDatoLinea.obj_factura.id = "Error al acceder a la factura";
                    }
                }, function (response) {
                    $scope.ajaxDatoLinea.obj_factura.id = "Error al acceder a la factura";
                });
        }else{
            $scope.idFacExist = false;
        }
        $scope.guardar = function () {
            var json = {
                id: null,
                cantidad: $scope.cantidad,
                id_producto: $scope.ajaxDatoLinea.obj_producto.id,
                id_factura: $scope.ajaxDatoLinea.obj_factura.id
            };
            $http({
                method: 'GET',
                withCredentials: true,
                url: '/json?ob=' + $scope.ob + '&op=create',
                params: {json: JSON.stringify(json)}
            }).then(function (response) {
                $scope.status = response.status;
                $scope.mensajeOK = true;
            }, function (response) {
                $scope.ajaxDatoLinea = response.message;
                $scope.status = response.status;
            });
        };


        $scope.productoRefresh = function (quiensoy, consulta) {
            var form = quiensoy;
            if (consulta) {
                $http({
                    method: 'GET',
                    //withCredentials: true,
                    url: 'json?ob=producto&op=get&id=' + $scope.ajaxDatoLinea.obj_producto.id
                }).then(function (response) {
                    if (response.data.message !== null) {
                        form.userForm.obj_producto.$setValidity('valid', true);
                        $scope.ajaxDatoLinea.obj_producto = response.data.message;
                    } else {
                        form.userForm.obj_producto.$setValidity('valid', false);
                        $scope.ajaxDatoLinea.obj_producto.id = "Error al acceder al usuario";
                    }
                }, function (response) {
                    form.userForm.obj_producto.$setValidity('valid', false);
                    $scope.ajaxDatoLinea.obj_producto.id = "Error al acceder al usuario";
                });
            } else {
                form.userForm.obj_producto.$setValidity('valid', true);
            }
        };



    }]);