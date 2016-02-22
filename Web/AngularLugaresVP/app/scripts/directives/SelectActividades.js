(function() {
  'use strict';
  angular.module('blog').directive('selectActividad', function(Actividad) {
    return {
      link: function($scope) {
        $scope.list = Actividad.query();
      },
      restrict: 'E',
      scope: {
        object: '='
      },
      templateUrl: 'views/directives/selectGeneral.html'
    };
  });
})();
