(function() {
  'use strict';
  angular.module('vp.services').factory('Lugar', function($resource, BaseUrl) {
    return $resource(BaseUrl + 'lugar/:id', {
      id: '@id'
    }, {
      delete: {
        method: 'POST',
        url: BaseUrl + 'lugar-delete'
      },
      save: {
        method: 'POST',
        isArray: false
      },
      update: {
        method: 'PUT'
      }
    });
  });
})();
