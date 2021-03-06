'use strict';

moduleService.service('sessionService', ['$location', function ($location) {
        var isSessionActive = false;
        var userName = "";
        var usuariologeadoID = "";
        var carrito = 0;
        var observerCallbacks = [];
        return {
            getUserName: function () {
                return userName;
            },
            setUserName: function (name) {
                userName = name;
            },
            isSessionActive: function () {
                return isSessionActive;
            },
            setId: function (id) {
                usuariologeadoID = id;
            },
            getId: function () {
                return usuariologeadoID;
            },
            setSessionActive: function () {
                isSessionActive = true;
            },
            setSessionInactive: function () {
                isSessionActive = false;
            },
            setCountCart: function (cantidad) {
                carrito = cantidad;

                //Para que sirve el callback()
                //https://www.quora.com/What-is-the-call-back-function-in-AngularJS
                angular.forEach(observerCallbacks, function (callback) {
                    callback();
                });
            },
            getCountCart: function () {
                return carrito;
            },
            //register an observer
            //Entiendo que puedo guardar todos los observables en el array observerCallbacks y que cada vez que el objeto 
            // se actualice , angular detectara que observable se ha actualizado y lo actualizara en toda la aplicacion
            registerObserverCallback: function (callback) {
                observerCallbacks.push(callback);
            }
        };

    }]);