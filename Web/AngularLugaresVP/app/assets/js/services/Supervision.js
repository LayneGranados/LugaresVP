(function() {
  'use strict';
  var Supervision = function($resource, BaseUrl) {
    return $resource(BaseUrl + '/supervision/:supervisionId', {
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
  };
  angular
    .module('vp.services')
    .factory('Supervision', Supervision);
})();
