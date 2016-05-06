(function() {
  'use strict';
  angular.module('vp.services').factory('Empleado', function($resource, BaseUrl) {
    return $resource(BaseUrl + 'empleado/', {}, {
      delete: {
        method: 'POST',
        url: BaseUrl + 'empleado-delete'
      },
      query: {
        isArray: true,
        method: 'GET'
      },
      update: {
        method: 'PUT'
      }
    });
  });
})();
