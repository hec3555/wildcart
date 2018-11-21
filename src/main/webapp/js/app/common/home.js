'use strict';

moduleCommon.controller('homeController', ['$scope', '$location', 'toolService', 'sessionService',
    function ($scope, $location, toolService, sessionService) {
        $scope.loginH = false;

        if (sessionService.getUserName() !== "") {
            $scope.usuariologeado = sessionService.getUserName();
            $scope.loginH = true;
        };


        $scope.ruta = $location.path();

        $scope.isActive = toolService.isActive;

    }]);