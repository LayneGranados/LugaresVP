(function() {
  'use strict';
  angular.module('blog.services').factory('Lugar', function($resource, BaseUrl) {
    return $resource(BaseUrl + 'lugar/:lugarId', {
      lugarId: '@_id'
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
