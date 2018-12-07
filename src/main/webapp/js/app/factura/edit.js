'use strict';

moduleFactura.controller('facturaEditController', ['$scope', '$http', '$location', 'toolService', '$routeParams',
    function ($scope, $http, $location, toolService, $routeParams) {
        $scope.id = $routeParams.id;
        $scope.myDate = new Date();
        $scope.ob = "factura";

        $http({
            method: 'GET',
            url: '/json?ob=' + $scope.ob + '&op=get&id=' + $scope.id
        }).then(function (response) {
            $scope.status = response.status;
            $scope.ajaxDatoFactura = response.data.message;
        }, function (response) {
            $scope.ajaxDatoFactura = response.data.message || 'Request failed';
            $scope.status = response.status;
        });


        $scope.guardar = function () {
            var json = {
                id: $scope.ajaxDatoFactura.id,
                fecha: null,
                iva: $scope.ajaxDatoFactura.iva,
                id_usuario: $scope.ajaxDatoFactura.obj_Usuario.id
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
                $scope.ajaxDatoFactura = response.data.message || 'Request failed';
                $scope.status = response.status;
            });
        };



        $scope.usuarioRefresh = function (quiensoy, consulta) {
            var form = quiensoy;
            if (consulta) {
                $http({
                    method: 'GET',
                    //withCredentials: true,
                    url: 'json?ob=usuario&op=get&id=' + $scope.ajaxDatoFactura.obj_Usuario.id
                }).then(function (response) {
                    if (response.data.message != null) {
                        form.userForm.obj_usuario.$setValidity('valid', true);
                        $scope.ajaxDatoFactura.obj_Usuario = response.data.message;
                    } else {
                        form.userForm.obj_usuario.$setValidity('valid', false);
                        $scope.ajaxDatoFactura.obj_Usuario.nombre = "Error al acceder al usuario";
                        $scope.ajaxDatoFactura.obj_Usuario.ape1 = "";
                    }
                }, function (response) {
                    form.userForm.obj_usuario.$setValidity('valid', false);
                    $scope.ajaxDatoFactura.obj_Usuario.nombre = "Error al acceder al usuario";
                    $scope.ajaxDatoFactura.obj_Usuario.ape1 = "";
                });
            } else {
                form.userForm.obj_usuario.$setValidity('valid', true);
            }
        };

    }]);