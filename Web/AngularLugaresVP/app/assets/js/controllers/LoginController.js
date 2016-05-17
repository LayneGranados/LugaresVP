(function() {
  'use strict';
  var loginController = function($state) {
    this.user = {};
    var self = this;
    this.login = function() {
      if (self.usuario === 'admin' && self.password === 'venturaplaza2016') {
        $state.go('app.dashboard', {}, {
          reload: true
        });
      }
    };
    this.logout = function() {
      $state.go('login', {}, {
        reload: true
      });
    };
  };
  angular.module('vp.controllers').controller('LoginController', loginController);
})();
