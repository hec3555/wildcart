'use strict';

moduleFactura.controller('facturaEditController', ['$scope', '$http', '$routeParams',
    function ($scope, $http, $routeParams) {
        $scope.id = $routeParams.id;
        $scope.myDate = new Date();
        $scope.ob = "factura";

        $http({
            method: 'GET',
            url: '/json?ob=' + $scope.ob + '&op=get&id=' + $scope.id
        }).then(function (response) {
            $scope.status = response.status;
            $scope.ajaxDatoFactura = response.data.message;
            $scope.ajaxDatoFacturaFecha = response.data.message.fecha;
            $scope.resultado = $scope.ajaxDatoFacturaFecha.slice(0, 3);

            switch ($scope.resultado) {
                case "ene":
                    $scope.fecha = $scope.ajaxDatoFacturaFecha.replace("ene", "jan");
                    break;
                case "abr":
                    $scope.fecha = $scope.ajaxDatoFacturaFecha.replace("abr", "apr");
                    break;
                case "ago":
                    $scope.fecha = $scope.ajaxDatoFacturaFecha.replace("ago", "aug");
                    break;
                case "dic":
                    $scope.fecha = $scope.ajaxDatoFacturaFecha.replace("dic", "dec");
                    break;
                default:
                    $scope.fecha = $scope.ajaxDatoFacturaFecha;
                    break;
            }
            $scope.dt = new Date($scope.fecha);
        }, function (response) {
            $scope.ajaxDatoFactura = response.data.message || 'Request failed';
            $scope.status = response.status;
        });


        $scope.guardar = function () {
            var json = {
                id: $scope.ajaxDatoFactura.id,
                fecha: $scope.dt,
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


        //CALENDARIO        
        $scope.myDate = new Date();
        $scope.minDate = new Date(
                $scope.myDate.getFullYear(),
                $scope.myDate.getMonth() - 2,
                $scope.myDate.getDate());

        $scope.maxDate = new Date(
                $scope.myDate.getFullYear(),
                $scope.myDate.getMonth() + 2,
                $scope.myDate.getDate());

    }]);