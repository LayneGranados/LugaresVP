(function() {
  'use strict';

  function TipoLugarController(TipoLugar, stdfrm, goTwoFields, $uibModal) {
    var self = this;
    this.gridOptions = {};
    this.tipos = TipoLugar.query();
    this.element = new TipoLugar();
    this.rowObject = null;
    this.mensaje = 'Crear Nuevo Tipo De Lugar';
    this.tfrm = 'Tipo De Lugar';

    angular.extend(this, stdfrm(this, TipoLugar, 'Tipo De Lugar'));
    this.gridOptions = goTwoFields(this.tipos, this, null, {
      field: 'id',
      name: 'Acciones',
      cellTemplate: 'templates/common/edit-button-tipo-lugar.html',
      width: 90,
      enableSorting: false,
      enableHiding: false,
      enableColumnMenu: false
    });

    this.showActividades = function(row) {
      var modalInstance = $uibModal.open({
        animation: 'true',
        templateUrl: 'templates/modalActividades.html',
        controller: 'ModalActividadesController',
        controllerAs: 'controller',
        resolve: {
          tipoLugar: function() {
            return row.entity;
          }
        }
      });

    };
  }
  angular
    .module('vp.controllers')
    .controller('TipoLugarController', TipoLugarController);

})();
