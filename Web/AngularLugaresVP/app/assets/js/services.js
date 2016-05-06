(function() {
  'use strict';

  angular.module('vp.services', ['ngResource']);

  function ActividadTipoLugar($resource, BaseUrl) {
    return $resource(BaseUrl + '/actividadTipoLugar/:actividadTipoLugarId', {
      actividadTipoLugarId: '@id'
    }, {
      'save': {
        method: 'POST',
        isArray: false,
        headers: {
          'Content-Type': 'application/json'
        }
      },
      'queryLugar': {
        method: 'GET',
        isArray: true,
        headers: {
          'Content-Type': 'application/json'
        },
        url: BaseUrl + '/actividadTipoLugar/lugar'
      },
      'noLugar': {
        method: 'GET',
        isArray: true,
        headers: {
          'Content-Type': 'application/json'
        },
        url: BaseUrl + 'actividadTipoLugar/nolugar'
      },
      'eliminar': {
        method: 'POST',
        isArray: false,
        headers: {
          'Content-Type': 'application/json'
        },
        url: BaseUrl + 'actividadTipoLugar/del'
      }
    });
  }

  function CategoriaVehiculo($resource, BaseUrl) {
    return $resource(BaseUrl + '/categoriaVehiculo/:categoriaId', {
      categoriaId: '@_id'
    }, {
      'save': {
        method: 'POST',
        isArray: false,
        headers: {

          'Content-Type': 'application/json'
        }
      }
    });
  }

  function Evaluacion($resource, BaseUrl) {
    return $resource(BaseUrl + '/evaluacion/:evaluacionId', {
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
  }



  function Login($resource, BaseUrl) {
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
  }

  function Supervision($resource, BaseUrl) {
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
  }


  angular
    .module('vp.services')
    .constant('BaseUrl', '/REST/core/')
    .factory('Login', Login)
    .factory('Supervision', Supervision)
    .factory('Evaluacion', Evaluacion)
    .factory('ActividadTipoLugar', ActividadTipoLugar)
    .factory('CategoriaVehiculo', CategoriaVehiculo);
})();
