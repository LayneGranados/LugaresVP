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

  function EvaluacionListController(Evaluacion) {
    this.evaluaciones = Evaluacion.query();
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
    .controller('ActividadTipoLugarListController', ActividadTipoLugarListController)
    .controller('ActividadTipoLugarCreateController', ActividadTipoLugarCreateController)
    .controller('EvaluacionListController', EvaluacionListController)
    .controller('LoginController', LoginController)
    .controller('ModalQRController', ModalQRController)
    .controller('TipoIdentificacionCreateController', TipoIdentificacionCreateController)
    .controller('TipoIdentificacionListController', TipoIdentificacionListController);
})();
