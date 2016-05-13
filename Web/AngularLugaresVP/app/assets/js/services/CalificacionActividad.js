(function() {
  'use strict';

  function CalificacionActividad($resource, BaseUrl) {
    return $resource(BaseUrl + 'calificacion-actividad/:id', {
      id: '@id'
    }, {
      delete: {
        method: 'POST',
        url: BaseUrl + 'calificacion-actividad-delete/'
      },
      query: {
        method: 'GET',
        isArray: true
      },
      save: {
        method: 'POST'
      },
      update: {
        method: 'PUT'
      }
    });
  }
  angular.module('vp.services').factory('CalificacionActividad', CalificacionActividad);
})();
