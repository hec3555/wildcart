'use strict';

moduleLinea.controller('lineaEditController', ['$scope', '$http', '$location', 'toolService', '$routeParams', 
    function ($scope, $http, $location, toolService, $routeParams) {
        $scope.id = $routeParams.id;
        $scope.ob = "linea";
        
        $http({
            method: 'GET',
            url: '/json?ob=' + $scope.ob + '&op=get&id=' + $scope.id
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
                id_producto: $scope.ajaxDatoLinea.id_producto,
                id_factura: $scope.ajaxDatoLinea.id_factura
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
                $scope.ajaxDatoLinea = response.data.message || 'Request failed';
                $scope.status = response.status;
            });
        };


    }]);