(function() {
  'use strict';
  angular.module('blog.services').factory('TipoEmpleado', function($resource, BaseUrl) {
    return $resource(BaseUrl + 'tipoEmpleado/', {}, {
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
