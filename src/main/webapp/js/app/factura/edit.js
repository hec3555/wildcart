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
                $scope.mensaje = true;
            }, function (response) {
                $scope.ajaxDatoFactura = response.data.message || 'Request failed';
                $scope.status = response.status;
            });
        };


    }]);