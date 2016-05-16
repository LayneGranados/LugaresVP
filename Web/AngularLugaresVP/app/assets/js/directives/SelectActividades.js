(function() {
  'use strict';
  angular.module('vp').directive('selectActividad', function(Actividad, ActividadTipoLugar) {
    return {
      link: function($scope) {

        if ($scope.noLugar !== undefined && $scope.noLugar !== null) {
          $scope.list = [];
          ActividadTipoLugar.noLugar({
            idLugar: $scope.noLugar
          }, function(data) {
            angular.forEach(data, function(value) {
              var actividad = new Actividad();
              actividad.id = value.actividadid;
              actividad.nombre = value.actividadnombre;
              $scope.list.push(actividad);
            });
          });
        } else {
          $scope.list = Actividad.query();
        }

      },
      restrict: 'E',
      scope: {
        object: '=',
        tabidx: '=',
        noLugar: '=',
        list: '='
      },
      templateUrl: 'templates/directives/selectGeneral.html'
    };
  });
})();
