(function() {
  'use strict';

  angular.module('blog.services', ['ngResource']);

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

  function Lugar($resource, BaseUrl) {
    return $resource(BaseUrl + '/lugar/:lugarId', {
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


  function TipoEmpleado($resource, BaseUrl) {
    return $resource(BaseUrl + '/tipoEmpleado/:tipoempleadoId', {
      tipoempleadoId: '@_id'
    }, {
      'save': {
        method: 'POST',
        isArray: false,
        headers: {
          'Content-Type': 'application/json'
        }
      },
      'update': {
        method: 'PUT',
        isArray: false,
        headers: {
          'Content-Type': 'application/json'
        }
      }
    });
  }

  function TipoIdentificacion($resource, BaseUrl) {
    return $resource(BaseUrl + '/tipoIdentificacion/:tipoIdentificacionId', {
      tipoIdentificacionId: '@_id'
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

  function TipoLugar($resource, BaseUrl) {
    return $resource(BaseUrl + 'tipoLugar/:tipolugarId', {
      tipolugarId: '@id'
    }, {
      'update': {
        method: 'PUT',
        isArray: false,
        url: BaseUrl + 'tipoLugar',
        headers: {
          'Content-Type': 'application/json'
        }
      },
      'delete': {
        method: 'POST',
        isArray: false,
        url: BaseUrl + 'tipoLugar/del',
        headers: {
          'Content-Type': 'application/json'
        }
      }
    });
  }
  angular
    .module('blog.services')
    .constant('BaseUrl', 'http://192.168.0.50:8080/InspeccionZonas/rest/')
    .factory('TipoLugar', TipoLugar)
    .factory('TipoEmpleado', TipoEmpleado)
    .factory('TipoIdentificacion', TipoIdentificacion)
    .factory('Lugar', Lugar)
    .factory('Login', Login)
    .factory('Supervision', Supervision)
    .factory('Evaluacion', Evaluacion)
    .factory('ActividadTipoLugar', ActividadTipoLugar)
    .factory('CategoriaVehiculo', CategoriaVehiculo);
})();
