(function() {
  'use strict';


  function ActividadTipoLugarListController(ActividadTipoLugar) {
    this.actividadTipoLugares = ActividadTipoLugar.query();
  }

  function ActividadTipoLugarCreateController(ActividadTipoLugar) {

    var self = this;
    this.create =
      function() {
        var actividadTipoLugar = new ActividadTipoLugar();
        actividadTipoLugar.actividad = parseInt(self.actividad.actividadId, 10);
        actividadTipoLugar.tipolugar = parseInt(self.tipolugar.tipolugarId, 10);
        actividadTipoLugar.$save();
      };
  }

  function ActividadListController(Actividad) {
    this.actividades = Actividad.query();
  }

  function ActividadCreateController(Actividad, CalificacionActividad, $scope, ModalService) {

    var self = this;
    $scope.name = null;

    $scope.close = function() {
      close({
        name: $scope.name,
      }, 500);
    };

    $scope.cancel = function() {
      $element.modal('hide');
      close({
        name: $scope.name,
      }, 500);
    };

    $scope.showComplex = function() {
      ModalService.showModal({
        templateUrl: 'views/agregarCalificacion.html',
        controller: '',
        inputs: {
          title: 'A More Complex Example'
        }
      }).then(function(modal) {
        modal.element.modal();
        modal.close.then(function(result) {
          $scope.complexResult = 'Name: ' + result.name + ', age: ' + result.age;
          var calificacion = new CalificacionActividad();
          calificacion.nombre = result.name;
          calificacion.actividadId = 1;
          calificacion.$save();
          self.gridOptions1.data.push({
            'nombre': self.actividad.nombre,
            'descripcion': self.actividad.descripcion,
            'id': 0
          });

        });
      });
    };

    $scope.showList = function() {
      ModalService.showModal({
        templateUrl: 'views/verCalificaciones.html',
        controller: 'ComplexController',
        inputs: {
          title: 'A More Complex Example'
        }
      }).then(function(modal) {
        modal.element.modal();
        modal.close.then(function(result) {
          $scope.complexResult = 'Name: ' + result.name + ', age: ' + result.age;

          self.gridOptions1.data.push({
            'nombre': self.actividad.nombre,
            'descripcion': self.actividad.descripcion,
            'id': 0
          });

        });
      });
    };


    self.create =
      function() {

        var actividad = new Actividad();
        actividad.nombre = self.actividad.nombre;
        actividad.descripcion = self.actividad.descripcion;

        if (actividad.id !== undefined) {

          actividad.$update();
          self.filas[0].nombre = actividad.nombre;
          self.filas[0].descripcion = actividad.descripcion;
          self.filas = null;
        } else {
          actividad.$save();
          self.gridOptions1.data.push({
            'nombre': actividad.nombre,
            'descripcion': actividad.descripcion,
            'id': 0
          });
        }

        self.actividad.nombre = '';
        self.actividad.descripcion = '';

      };

    this.actividades = Actividad.query();

    this.gridOptions1 = {
      paginationPageSize: 15,
      data: self.actividades,
      columnDefs: [{
        field: 'id'
      }, {
        field: 'nombre'
      }, {
        field: 'descripcion'
      }],
      multiSelect: false,
      enableRowSelection: true,
      enableRowHeaderSelection: false
    };

    this.gridOptions1.onRegisterApi = function(gridApi) {
      self.gridApi = gridApi;
    };

    self.actividad = {};
    self.filas = {};
    this.editar = function() {
      self.filas = self.gridApi.selection.getSelectedRows();
      self.actividad.nombre = self.filas[0].nombre;
      self.actividad.descripcion = self.filas[0].descripcion;
      self.actividad.id = self.filas[0].id;

    };

  }

  function CalificacionActividadListController(CalificacionActividad) {
    this.calificaciones = CalificacionActividad.query();
  }

  function CalificacionActividadCreateController(CalificacionActividad, uiGridConstants) {

    var self = this;

    self.create =
      function() {
        var calificacion = new CalificacionActividad();
        calificacion.nombre = self.calificacion.nombre;
        calificacion.actividad = parseInt(self.calificacion.actividad, 10);
        calificacion.id = self.calificacion.id;

        if (calificacion.id !== undefined) {
          calificacion.$update();
          self.filas[0].nombre = calificacion.nombre;
          self.filas[0].actividad = calificacion.actividad;
          self.filas = null;
        } else {
          calificacion.$save();
          self.gridOptions1.data.push({
            'nombre': calificacion.nombre,
            'actividad': calificacion.actividad,
            'id': 0
          });
        }

        self.calificacion.nombre = '';
        self.calificacion.actividad = '';

      };

    this.calificaciones = CalificacionActividad.query();

    this.gridOptions1 = {
      paginationPageSize: 15,
      data: self.calificaciones,
      columnDefs: [{
        field: 'id'
      }, {
        field: 'actividad'
      }, {
        field: 'nombre'
      }],
      multiSelect: false,
      enableRowSelection: true,
      enableRowHeaderSelection: false
    };

    this.gridOptions1.onRegisterApi = function(gridApi) {
      self.gridApi = gridApi;
    };

    self.calificacionActividad = {};
    self.filas = {};
    this.editar = function() {
      self.filas = self.gridApi.selection.getSelectedRows();
      self.calificacion.nombre = self.filas[0].nombre;
      self.calificacion.descripcion = self.filas[0].actividad;
      self.calificacion.id = self.filas[0].id;

    };
  }

  function CategoriaVehiculoListController(CategoriaVehiculo) {
    this.categorias = CategoriaVehiculo.query();
  }

  function CategoriaVehiculoCreateController(CategoriaVehiculo) {

    var self = this;
    this.create =
      function() {

        var categoria = new CategoriaVehiculo();
        categoria.nombre = self.categoria.nombre;
        categoria.descripcion = self.categoria.descripcion;
        categoria.calendario = self.categoria.calendario;
        if (self.categoria.nivel_servicio_id === undefined) {
          categoria.nivel_servicio_id = false;
        } else {
          categoria.nivel_servicio_id = self.categoria.nivel_servicio_id;
        }
        categoria.incluyeProtocolo = self.categoria.incluyeProtocolo;
        categoria.$save();
      };
  }

  function EvaluacionListController(Evaluacion) {
    this.evaluaciones = Evaluacion.query();
  }

  function LugarCreateController(Lugar, uiGridConstants, ModalService) {

    var self = this;

    self.create = function() {
      var lugar = new Lugar();
      lugar.nombre = self.lugar.nombre;
      lugar.descripcion = self.lugar.descripcion;
      lugar.tipolugar = parseInt(self.lugar.tipo_lugar_id, 10);
      lugar.id = self.lugar.id;

      if (lugar.id !== undefined) {
        lugar.$update();
        self.filas[0].nombre = lugar.nombre;
        self.filas[0].descripcion = lugar.descripcion;
        self.filas[0].tipolugar = lugar.tipolugar;
        self.filas = null;
      } else {
        lugar.$save();
        self.gridOptions1.data.push({
          'nombre': lugar.nombre,
          'descripcion': lugar.descripcion,
          'tipolugar': lugar.tipolugar,
          'id': 0
        });
      }

      self.lugar.nombre = '';
      self.lugar.descripcion = '';
      self.lugar.tipolugar = '';

    };

    this.lugares = Lugar.query();

    this.gridOptions1 = {
      paginationPageSize: 15,
      data: self.lugares,
      columnDefs: [{
        field: 'id'
      }, {
        field: 'tipolugar'
      }, {
        field: 'nombre'
      }, {
        field: 'descripcion'
      }],
      multiSelect: false,
      enableRowSelection: true,
      enableRowHeaderSelection: false
    };

    this.gridOptions1.onRegisterApi = function(gridApi) {
      self.gridApi = gridApi;
    };

    self.lugar = {};
    self.filas = {};
    this.editar = function() {
      self.filas = self.gridApi.selection.getSelectedRows();
      self.lugar.nombre = self.filas[0].nombre;
      self.lugar.descripcion = self.filas[0].descripcion;
      self.lugar.tipolugar = self.filas[0].tipolugar;
      self.lugar.id = self.filas[0].id;
    };

    this.showComplex = function() {
      var filas = self.gridApi.selection.getSelectedRows();
      if (filas[0] === undefined) {
        return;
      }
      var id = filas[0].id;
      if (id === undefined) {
        return;
      }
      ModalService.showModal({
        templateUrl: 'views/modalQR.html',
        controller: 'ModalQRController',
        inputs: {
          title: 'Codigo QR',
          qrCodeId: id
        },
        controllerAs: 'qrCtrl'
      }).then(function(modal) {
        modal.element.modal();
        modal.close.then(function(result) {});
      });
    };

  }

  function LugarListController(Lugar) {
    this.lugares = Lugar.query();
  }

  function LoginController($location) {
    this.login = function() {
      $location.path('actividad');
    };
    this.logout = function() {
      $location.path('login/');
    };
  }

  function ModalQRController(title, qrCodeId, $scope) {
    this.title = title;
    this.qrCodeId = qrCodeId;
    console.log('qrCodeId: ' + qrCodeId)
    this.close = function() {
      console.log('cierra esta ventana');
    }
  }

  function modalActividades() {

  }

  function PersonaCreateController(Persona, uiGridConstants) {

    var self = this;
    this.registarUsuario = true;
    self.create =
      function() {

        var persona = new Persona();
        persona.identificacion = self.persona.identificacion;
        persona.nombres = self.persona.nombres;
        persona.apellidos = self.persona.apellidos;
        persona.tipoidentificacion = parseInt(self.persona.tipoIdentificacionId, 10);
        persona.tipoempleado = parseInt(self.persona.tipoempleado, 10);
        persona.usuario = self.persona.usuario;
        persona.password = self.persona.password;
        persona.passwordrep = self.persona.passwordrep;

        if (persona.id !== undefined) {
          persona.$update();
          self.filas[0].identificacion = persona.identificacion;
          self.filas[0].tipoidentificacion = persona.tipoidentificacion;
          self.filas = null;
        } else {
          persona.$save();
          self.gridOptions1.data.push({
            'nombres': persona.nombre,
            'identificacion': persona.descripcion,
            'id': 0
          });
        }

        self.persona.nombres = '';
        self.persona.identificacion = '';

      };

    this.personas = Persona.query();

    this.gridOptions1 = {
      paginationPageSize: 15,
      data: self.personas,
      columnDefs: [{
        field: 'tipoidentificacion'
      }, {
        field: 'identificacion'
      }, {
        field: 'nombres'
      }, {
        field: 'apellidos'
      }, {
        field: 'tipoempleado'
      }, {
        field: 'usuario'
      }],
      multiSelect: false,
      enableRowSelection: true,
      enableRowHeaderSelection: false
    };

    this.gridOptions1.onRegisterApi = function(gridApi) {
      self.gridApi = gridApi;
    };

    self.persona = {};
    self.filas = {};
    this.editar = function() {
      self.filas = self.gridApi.selection.getSelectedRows();
      self.persona.nombre = self.filas[0].nombre;
      self.persona.descripcion = self.filas[0].descripcion;
      self.persona.id = self.filas[0].id;

    };


    this.create =
      function() {

        var persona = new Persona();
        persona.identificacion = self.persona.identificacion;
        persona.nombres = self.persona.nombres;
        persona.apellidos = self.persona.apellidos;
        persona.tipoidentificacion = parseInt(self.persona.tipoIdentificacionId, 10);
        persona.tipoempleado = parseInt(self.persona.tipoempleado, 10);
        persona.usuario = self.persona.usuario;
        persona.password = self.persona.password;
        persona.passwordrep = self.persona.passwordrep;
        persona.$save();
      };

  }

  function PersonaListController(Persona) {
    this.personas = Persona.query();
  }

  function SupervisionListController(Supervision, uiGridConstants, $scope, ModalService) {

    var self = this;
    $scope.name = null;

    $scope.close = function() {
      close({
        name: $scope.name,
      }, 500);
    };

    $scope.cancel = function() {
      $element.modal('hide');
      close({
        name: $scope.name,
      }, 500);
    };

    $scope.showComplex = function() {
      ModalService.showModal({
        templateUrl: 'views/modalCalificaciones.html',
        controller: 'SupervisionListController',
        inputs: {
          title: 'A More Complex Example'
        }
      }).then(function(modal) {
        modal.element.modal();
        modal.close.then(function(result) {
          $scope.complexResult = 'Name: ' + result.name + ', age: ' + result.age;
          var calificacion = new Supervision();
          calificacion.nombre = result.name;
          calificacion.actividad_id = 1;
          calificacion.$save();
          self.gridOptions1.data.push({
            'nombre': self.actividad.nombre,
            'descripcion': self.actividad.descripcion,
            'id': 0
          });

        });
      });
    };

    $scope.showList = function() {
      ModalService.showModal({
        templateUrl: 'views/verCalificaciones.html',
        controller: 'ComplexController',
        inputs: {
          title: 'A More Complex Example'
        }
      }).then(function(modal) {
        modal.element.modal();
        modal.close.then(function(result) {
          $scope.complexResult = 'Name: ' + result.name + ', age: ' + result.age;

          self.gridOptions1.data.push({
            'nombre': self.actividad.nombre,
            'descripcion': self.actividad.descripcion,
            'id': 0
          });

        });
      });
    };


    self.create = function() {

    };

    this.supervisiones = Supervision.query();

    this.gridOptions1 = {
      paginationPageSize: 15,
      data: self.supervisiones,
      columnDefs: [{
        field: 'id'
      }, {
        field: 'supervisor'
      }, {
        field: 'lugar'
      }, {
        field: 'fecha'
      }],
      multiSelect: false,
      enableRowSelection: true,
      enableRowHeaderSelection: false
    };

    this.gridOptions1.onRegisterApi = function(gridApi) {
      self.gridApi = gridApi;
    };

    self.supervisiones = {};
    self.filas = {};
    this.editar = function() {


    };
  }

  function TipoLugarCreateController(TipoLugar, uiGridConstants, $scope, ModalService) {
    var self = this;

    $scope.showComplex = function() {
      ModalService.showModal({
        templateUrl: 'views/modalActividades.html',
        controller: 'modalActividades',
        controllerAs: 'actividadesCtrl',
        inputs: {
          title: 'A More Complex Example'
        }
      }).then(function(modal) {
        modal.element.modal();
        modal.close.then(function() {

        });
      });
    };
    self.create = function() {
      var tipoLugar = new TipoLugar();
      tipoLugar.nombre = self.tipoLugar.nombre;
      tipoLugar.descripcion = self.tipoLugar.descripcion;
      tipoLugar.id = self.tipoLugar.id;
      console.log("TipoLugarID:", tipoLugar.id);
      if (tipoLugar.id !== undefined) {
        tipoLugar.$update();
        self.filas[0].nombre = tipoLugar.nombre;
        self.filas[0].descripcion = tipoLugar.descripcion;
        self.filas = null;
      } else {
        tipoLugar.$save(null, function(object) {
          self.gridOptions1.data.push({
            'nombre': object.nombre,
            'descripcion': object.descripcion,
            'id': object.id
          });
        });

      }
      self.tipoLugar = null


    };

    this.tipos = TipoLugar.query();


    this.gridOptions1 = {
      paginationPageSize: 15,
      data: self.tipos,
      columnDefs: [{
        field: 'id'
      }, {
        field: 'nombre'
      }, {
        field: 'descripcion'
      }],
      multiSelect: false,
      enableRowSelection: true,
      enableRowHeaderSelection: false
    };

    this.gridOptions1.onRegisterApi = function(gridApi) {
      self.gridApi = gridApi;
    };


    this.editar = function() {
      this.tipoLugar = {};
      this.filas = {};
      self.filas = self.gridApi.selection.getSelectedRows();
      self.tipoLugar.nombre = self.filas[0].nombre;
      self.tipoLugar.descripcion = self.filas[0].descripcion;
      self.tipoLugar.id = self.filas[0].id;

    };

    this.delete = function() {
      self.filas = {};
      self.filas = self.gridApi.selection.getSelectedRows();
      var tipoLugar = new TipoLugar();
      tipoLugar.id = self.filas[0].id;
      tipoLugar.$delete(null, function(object) {
        var index = self.gridOptions1.data.indexOf(self.filas[0]);
        self.gridOptions1.data.splice(index, 1);
      });

    }

    this.clear = function() {
      console.log("Entro en Clear");
      self.filas = null;
      self.tipoLugar = null
      self.gridApi.selection.clearSelectedRows();
    }
  }

  function TipoLugarListController(TipoLugar) {
    this.tipos = TipoLugar.query();
  }

  function TipoEmpleadoCreateController(TipoEmpleado, uiGridConstants) {
    var self = this;

    self.create =
      function() {
        var tipoEmpleado = new TipoEmpleado();
        tipoEmpleado.nombre = self.tipoEmpleado.nombre;
        tipoEmpleado.descripcion = self.tipoEmpleado.descripcion;
        tipoEmpleado.id = self.tipoEmpleado.id;
        if (tipoEmpleado.id !== undefined) {
          tipoEmpleado.$update();
          self.filas[0].nombre = tipoEmpleado.nombre;
          self.filas[0].descripcion = tipoEmpleado.descripcion;
          self.filas = null;
        } else {
          tipoEmpleado.$save();
          self.gridOptions1.data.push({
            'nombre': tipoEmpleado.nombre,
            'descripcion': tipoEmpleado.descripcion,
            'id': 0
          });
        }

        self.tipoEmpleado.nombre = '';
        self.tipoEmpleado.descripcion = '';

      };

    this.tipos = TipoEmpleado.query();

    this.gridOptions1 = {
      paginationPageSize: 15,
      data: self.tipos,
      columnDefs: [{
        field: 'id'
      }, {
        field: 'nombre'
      }, {
        field: 'descripcion'
      }],
      multiSelect: false,
      enableRowSelection: true,
      enableRowHeaderSelection: false
    };

    this.gridOptions1.onRegisterApi = function(gridApi) {
      self.gridApi = gridApi;
    };

    self.tipoEmpleado = {};
    self.filas = {};
    this.editar = function() {
      self.filas = self.gridApi.selection.getSelectedRows();
      self.tipoEmpleado.nombre = self.filas[0].nombre;
      self.tipoEmpleado.descripcion = self.filas[0].descripcion;
      self.tipoEmpleado.id = self.filas[0].id;

    };

  }

  function TipoEmpleadoListController(TipoEmpleado) {
    this.tipos = TipoEmpleado.query();
  }

  function TipoIdentificacionCreateController(TipoIdentificacion) {
    var self = this;
    self.create =
      function() {
        var tipoIdentificacion = new TipoIdentificacion();
        tipoIdentificacion.nombre = self.tipoIdentificacion.nombre;
        tipoIdentificacion.codigo = self.tipoIdentificacion.codigo;
        tipoIdentificacion.$save();
      };
  }

  function TipoIdentificacionListController(TipoIdentificacion) {
    this.tipos = TipoIdentificacion.query();
  }

  angular
    .module('blog.controllers', ['blog.services', 'angularModalService'])
    .controller('ActividadCreateController', ActividadCreateController)
    .controller('ActividadListController', ActividadListController)
    .controller('ActividadTipoLugarListController', ActividadTipoLugarListController)
    .controller('ActividadTipoLugarCreateController', ActividadTipoLugarCreateController)
    .controller('CalificacionActividadListController', CalificacionActividadListController)
    .controller('CalificacionActividadCreateController', CalificacionActividadCreateController)
    .controller('CategoriaVehiculoCreateController', CategoriaVehiculoCreateController)
    .controller('CategoriaVehiculoListController', CategoriaVehiculoListController)
    .controller('EvaluacionListController', EvaluacionListController)
    .controller('LoginController', LoginController)
    .controller('LugarCreateController', LugarCreateController)
    .controller('LugarListController', LugarListController)
    .controller('modalActividades', modalActividades)
    .controller('ModalQRController', ModalQRController)
    .controller('PersonaCreateController', PersonaCreateController)
    .controller('PersonaListController', PersonaListController)
    .controller('SupervisionListController', SupervisionListController)
    .controller('TipoLugarCreateController', TipoLugarCreateController)
    .controller('TipoLugarListController', TipoLugarListController)
    .controller('TipoIdentificacionCreateController', TipoIdentificacionCreateController)
    .controller('TipoIdentificacionListController', TipoIdentificacionListController)
    .controller('TipoEmpleadoCreateController', TipoEmpleadoCreateController)
    .controller('TipoEmpleadoListController', TipoEmpleadoListController);

})();
