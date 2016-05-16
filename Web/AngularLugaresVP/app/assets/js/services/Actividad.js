(function() {
  'use strict';
  angular.module('vp.services').factory('Actividad', function($resource, BaseUrl) {
    return $resource(BaseUrl + 'actividad/', {id:'@id'}, {
      query: {
        isArray: true,
        method: 'GET'
      },
      save: {
        isArray: false,
        method: 'POST'
      },
      update: {
        method: 'PUT'
      },
      delete: {
        url :BaseUrl + 'actividad/del',
        method: 'POST'
      }
    });
  });
})();
