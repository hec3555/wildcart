'use strict';

moduleFactura.controller('facturaCreateController', ['$scope', '$http', '$location', 'toolService', '$routeParams', 
    function ($scope, $http, $location, toolService, $routeParams) {
        $scope.id = $routeParams.id;
        $scope.myDate = new Date();
        $scope.ob = "linea";
        
        $scope.guardar = function () {
            var json = {
                id: null,
//                cantidad : ,
//                id_producto : ,
//                id_factura : 
            };
            $http({
                method: 'GET',
                withCredentials: true,
                url: '/json?ob=' + $scope.ob + '&op=create',
                params: {json: JSON.stringify(json)}
            }).then(function (response) {
                $scope.status = response.status;
                $scope.mensaje = true;
            }, function (response) {
                $scope.ajaxDatoFactura = response.data.message || 'Request failed';
                $scope.status = response.status;
            });
        };


        $scope.isActive = toolService.isActive;

    }]);