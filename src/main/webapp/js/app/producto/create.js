'use strict';

moduleProducto.controller('productoCreateController', ['$scope', '$http', '$location', 'toolService', '$routeParams', 
    function ($scope, $http, $location, toolService, $routeParams) {

        $scope.ob = "producto";
        $scope.ajaxDatoProducto = {
            id: null,
            desc: null
        };
        $scope.guardar = function () {
            var json = {
                id: null,
                codigo: $scope.codigo,
                desc: $scope.descripcion,
                existencias: $scope.existencias,
                precio: $scope.precio,
                foto: $scope.foto,
                id_tipoProducto: $scope.ajaxDatoProducto.id
            };
            $http({
                method: 'GET',
                withCredentials: true,
                url: '/json?ob=' + $scope.ob + '&op=create',
                params: {json: JSON.stringify(json)}
            }).then(function (response) {
                $scope.status = response.status;
                $scope.mensaje = true;
                $scope.error = false;
            }, function (response) {
                $scope.mensaje = false;
                $scope.error = true;
                $scope.ajaxDatoProducto = response.data.message || 'Request failed';
                $scope.status = response.status;
            });
        };
        $scope.isActive = toolService.isActive;
        $scope.tipoProductoRefresh = function (quiensoy, consulta) {
            var form = quiensoy;
            if (consulta) {
                $http({
                    method: 'GET',
                    url: 'json?ob=tipoproducto&op=get&id=' + $scope.ajaxDatoProducto.id
                }).then(function (response) {
                    form.userForm.id_tipoProducto.$setValidity('valid', true);
                    $scope.ajaxDatoProducto = response.data.message;
                }, function (response) {
                    form.userForm.id_tipoProducto.$setValidity('valid', false);
                    $scope.ajaxDatoProducto.desc = "Error al acceder al tipo de producto";
                });
            } else {
                form.userForm.id_tipoProducto.$setValidity('valid', true);
            }
        };
    }]);