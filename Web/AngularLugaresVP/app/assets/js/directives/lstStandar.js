(function() {
  'use strict';
  angular.module('vp').directive('lstStandard', function() {
    return {
      link: function() {},
      restrict: 'E',
      scope: {
        controller: '='
      },
      templateUrl: 'templates/directives/lst-standard.html'
    };
  });
})();
