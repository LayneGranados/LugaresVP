(function() {
  'use strict';
  angular.module('vp').directive('form2element', function() {
    return {
      link: function() {},
      restrict: 'E',
      scope: {
        controller: '='
      },
      templateUrl: 'templates/directives/form2element.html'
    };
  });
})();
