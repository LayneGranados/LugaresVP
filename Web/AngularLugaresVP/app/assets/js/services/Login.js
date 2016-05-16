(function() {
  'use strict';
  var Login = function($resource, BaseUrl) {
    return $resource(BaseUrl + '/login/:loginId', {
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
    .factory('Login', Login);
})();
