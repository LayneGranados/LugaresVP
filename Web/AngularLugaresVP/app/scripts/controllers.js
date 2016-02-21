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
    .module('blog.controllers')
    .controller('ActividadCreateController', ActividadCreateController)
    .controller('ActividadListController', ActividadListController)
    .controller('ActividadTipoLugarListController', ActividadTipoLugarListController)
    .controller('ActividadTipoLugarCreateController', ActividadTipoLugarCreateController)
    .controller('EvaluacionListController', EvaluacionListController)
    .controller('LoginController', LoginController)
    .controller('LugarCreateController', LugarCreateController)
    .controller('LugarListController', LugarListController)
    .controller('ModalQRController', ModalQRController)
    .controller('TipoIdentificacionCreateController', TipoIdentificacionCreateController)
    .controller('TipoIdentificacionListController', TipoIdentificacionListController);
})();
