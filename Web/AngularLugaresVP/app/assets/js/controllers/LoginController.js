(function() {
  'use strict';
  function LoginController($location) {
    this.user = {};
    var self = this;
    this.login = function() {
      if (self.usuario === 'admin' && self.password === 'venturaplaza2016') {
        $location.path('actividad');
      }
    };
    this.logout = function() {
      $location.path('login/');
    };
  }
  angular.module('blog.controllers').controller('LoginController', LoginController);
})();
