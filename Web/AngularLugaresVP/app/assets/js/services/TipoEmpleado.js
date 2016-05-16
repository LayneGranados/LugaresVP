(function() {
  'use strict';
  angular.module('vp.services').factory('TipoEmpleado', function($resource, BaseUrl) {
    return $resource(BaseUrl + 'tipoEmpleado/', {
      id: '@id'
    }, {
      delete: {
        method: 'POST',
        url: BaseUrl + 'tipo-empleado-delete'
      },
      save: {
        method: 'POST'
      },
      update: {
        method: 'PUT'
      }
    });
  });
})();
