'use strict';

moduleProducto.controller('productoEditController', ['$scope', '$http', '$location', 'toolService', '$routeParams',
    function ($scope, $http, $location, toolService, $routeParams) {
        $scope.id = $routeParams.id;
        $scope.ob = "producto";
        
        
        $scope.ajaxDatoProducto = {
            id: null,
            codigo: null,
            desc: null,
            existencias: null,
            precio: null,
            foto: "default.svg",
            obj_tipoProducto: {id: null}
        };
        
        $http({
            method: 'GET',
            url: '/json?ob=' + $scope.ob + '&op=get&id=' + $scope.id
        }).then(function (response) {
            $scope.status = response.status;
            $scope.ajaxDatoProducto = response.data.message;
            if($scope.ajaxDatoProducto.foto === ""){
                $scope.ajaxDatoProducto.foto = "default.svg";
            }
        }, function (response) {
            $scope.ajaxDatoProducto = response.data.message || 'Request failed';
            $scope.status = response.status;
        });


        $scope.guardar = function () {
            $scope.upload();
            var foto = $scope.ajaxDatoProducto.foto;
            if ($scope.file !== undefined) {
                foto = $scope.file.name;
            }
            var json = {
                id: $scope.ajaxDatoProducto.id,
                codigo: $scope.ajaxDatoProducto.codigo,
                desc: $scope.ajaxDatoProducto.desc,
                existencias: $scope.ajaxDatoProducto.existencias,
                precio: $scope.ajaxDatoProducto.precio,
                foto: foto,
                id_tipoProducto: $scope.ajaxDatoProducto.obj_tipoProducto.id
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
                $scope.ajaxDatoProducto = response.data.message || 'Request failed';
                $scope.status = response.status;
            });
        };


        $scope.tipoProductoRefresh = function (quiensoy, consulta) {
            var form = quiensoy;
            if (consulta) {
                $http({
                    method: 'GET',
                    url: 'json?ob=tipoproducto&op=get&id=' + $scope.ajaxDatoProducto.obj_tipoProducto.id
                }).then(function (response) {
                    form.userForm.obj_tipoProducto.$setValidity('valid', true);
                    $scope.ajaxDatoProducto.obj_tipoProducto = response.data.message;
                }, function (response) {
                    form.userForm.obj_tipoProducto.$setValidity('valid', false);
                    $scope.ajaxDatoProducto.obj_tipoProducto.desc = "Error al acceder al tipo de producto";
                });
            } else {
                form.userForm.obj_tipoProducto.$setValidity('valid', true);
            }
        };
        
        $scope.upload = function () {
            var file = $scope.file;
            var oformData = new FormData();
            oformData.append('file', file);

            $http({
                headers: {'Content-Type': undefined},
                method: 'POST',
                data: oformData,
                url: 'json?ob=producto&op=addimage'
            }).then(function (response) {
                console.log(response);
            }, function (response) {
                console.log(response);
            });
        };
    }]).directive('fileModel', ['$parse', function ($parse) {
        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                var model = $parse(attrs.fileModel);
                var modelSetter = model.assign;

                element.bind('change', function () {
                    scope.$apply(function () {
                        modelSetter(scope, element[0].files[0]);
                    });
                });
            }
        };
    }]);
