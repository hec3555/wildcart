'use strict';

moduleProducto.controller('productoPlist_carritoController', ['$scope', '$http', '$location', 'toolService', '$routeParams', "countcartService", '$mdDialog',
    function ($scope, $http, $location, toolService, $routeParams, countcartService, $mdDialog) {

        $scope.totalPages = 1;
        $scope.select = ["5", "10", "25", "50", "500"];
        $scope.ob = "producto";
        
        // el orden lo dejo, por si posteriormente queremos ordenar por precio, por ejemplo
        if (!$routeParams.order) {
            $scope.orderURLServidor = "";
            $scope.orderURLCliente = "";
        } else {
            $scope.orderURLServidor = "&order=" + $routeParams.order;
            $scope.orderURLCliente = $routeParams.order;
        }

        if (!$routeParams.rpp) {
            $scope.rpp = "10";
        } else {
            $scope.rpp = $routeParams.rpp;
        }

        if (!$routeParams.page) {
            $scope.page = 1;
        } else {
            if ($routeParams.page >= 1) {
                $scope.page = $routeParams.page;
            } else {
                $scope.page = 1;
            }
        }

        $scope.resetOrder = function () {
            $location.url($scope.ob + "/plist_1/" + $scope.rpp + "/1");
            $scope.activar = "false";
        };


        $scope.ordena = function (order, align) {
            if ($scope.orderURLServidor === "") {
                $scope.orderURLServidor = "&order=" + order + "," + align;
                $scope.orderURLCliente = order + "," + align;
            } else {
                $scope.orderURLServidor += "-" + order + "," + align;
                $scope.orderURLCliente += "-" + order + "," + align;
            }


            ;
            $location.url($scope.ob + "/plist_1/" + $scope.rpp + "/" + $scope.page + "/" + $scope.orderURLCliente);
        };

        $scope.add = function (id) {
            $http({
                method: 'GET',
                url: '/json?ob=carrito&op=add&producto=' + id + '&cantidad=1'
            }).then(function (response) {

                for (var i = 0; i < response.data.message.length; i++) {
                    if (id === response.data.message[i].obj_Producto.id) {
                        $scope.ajaxDataCantidad = response.data.message[i].cantidad;
                        $scope.ajaxDataExistencias = response.data.message[i].obj_Producto.existencias;
                        if ($scope.ajaxDataCantidad === $scope.ajaxDataExistencias) {
                            $scope.msgModal = 'Ha agregado al carrito la cantidad de productos existentes';
                        }else{
                             $scope.msgModal = 'Agregado al carrito';
                        }
                    }
                }
                countcartService.updateCarrito();

            }, function (response) {
                $scope.status = response.status;
                $scope.error = $scope.status + " " + response.message || 'Request failed';
            });
        };
        
        //getcount
        $http({
            method: 'GET',
            url: '/json?ob=' + $scope.ob + '&op=getcount'
        }).then(function (response) {
            $scope.status = response.status;
            $scope.ajaxDataProductosNumber = response.data.message;
            $scope.totalPages = Math.ceil($scope.ajaxDataProductosNumber / $scope.rpp);
            if ($scope.page > $scope.totalPages) {
                $scope.page = $scope.totalPages;
                $scope.update();
            }
            pagination2();
        }, function (response) {
            $scope.ajaxDataProductosNumber = response.data.message || 'Request failed';
            $scope.status = response.status;
        });

        $http({
            method: 'GET',
            url: '/json?ob=' + $scope.ob + '&op=getpage&rpp=' + $scope.rpp + '&page=' + $scope.page + $scope.orderURLServidor
        }).then(function (response) {
            $scope.status = response.status;
            $scope.ajaxDataProductos = response.data.message;
        }, function (response) {
            $scope.status = response.status;
            $scope.ajaxDataProductos = response.data.message || 'Request failed';
        });



        $scope.update = function () {
            $location.url($scope.ob + "/plist_1/" + $scope.rpp + "/" + $scope.page + "/" + $scope.orderURLCliente);
        };

        //paginacion neighbourhood
        function pagination2() {
            $scope.list2 = [];
            $scope.neighborhood = 1;
            for (var i = 1; i <= $scope.totalPages; i++) {
                if (i === $scope.page) {
                    $scope.list2.push(i);
                } else if (i <= $scope.page && i >= ($scope.page - $scope.neighborhood)) {
                    $scope.list2.push(i);
                } else if (i >= $scope.page && i <= ($scope.page - -$scope.neighborhood)) {
                    $scope.list2.push(i);
                } else if (i === ($scope.page - $scope.neighborhood) - 1) {
                    if ($scope.page >= 4) {
                        $scope.list2.push("...");
                    }
                } else if (i === ($scope.page - -$scope.neighborhood) + 1) {
                    if ($scope.page <= $scope.totalPages - 3) {
                        $scope.list2.push("...");
                    }
                }
            }
        }
        ;

        $scope.isActive = toolService.isActive;
    }



]);