(function() {
  'use strict';
  angular.module('vp').directive('selectActividad', function(Actividad) {
    return {
      link: function($scope) {
        $scope.list = Actividad.query();
      },
      restrict: 'E',
      scope: {
        object: '='
      },
      templateUrl: 'templates/directives/selectGeneral.html'
    };
  });
})();
