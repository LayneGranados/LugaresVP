(function() {
  'use strict';
  angular.module('vp.services').factory('Persona', function Persona($resource, BaseUrl) {
    return $resource(BaseUrl + '/persona/:personaId', {
      lugarId: '@_id'
    }, {
      'save': {
        method: 'POST',
        isArray: false,
        headers: {
          'Content-Type': 'application/json'
        }
      }
    });
  });
})();
