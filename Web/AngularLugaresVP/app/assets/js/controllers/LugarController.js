(function() {
  'use strict';

  function LugarController(Lugar) {

    var self = this;
    this.lugares = Lugar.query();

    self.selectedLugar = new Lugar();
    this.cancel = function() {
      self.selectedLugar = new Lugar();
    };

    this.save = function() {
      if (self.selectedLugar.id === undefined) {
        self.selectedLugar.$save({}, function(data) {
          self.gridOptions.data.push(data);
          self.selectedLugar = new Lugar();
        });
      } else {
        self.selectedLugar.$update({}, function(data) {
          self.selectedLugar = new Lugar();
          self.gridOptions.data.splice(self.indexOf, 1);
          self.gridOptions.data.push(data);
        });
      }
    };

    this.edit = function() {
      var rows = self.gridApi.selection.getSelectedRows();
      self.selectedLugar = angular.copy(rows[0]);
      self.indexOf = self.gridOptions.data.indexOf(rows[0]);
    };

    this.delete = function() {
      var rows = self.gridApi.selection.getSelectedRows();
      var indexOfDelete = self.indexOf = self.gridOptions.data.indexOf(rows[0]);
      rows[0].$delete({}, function() {
        self.gridOptions.data.splice(indexOfDelete, 1);
      });
    };

    this.gridOptions = {
      paginationPageSize: 15,
      data: self.lugares,
      columnDefs: [{
        field: 'id'
      }, {
        displayName: 'Tipo De Lugar',
        field: 'tipoLugar.nombre'

      }, {
        field: 'nombre'
      }, {
        field: 'descripcion'
      }],
      multiSelect: false,
      enableRowSelection: true,
      enableRowHeaderSelection: false
    };

    this.gridOptions.onRegisterApi = function(gridApi) {
      self.gridApi = gridApi;
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

      /*ModalService.showModal({
        templateUrl: 'templates/modalQR.html',
        controller: 'ModalQRController',
        inputs: {
          title: 'Codigo QR',
          qrCodeId: id
        },
        controllerAs: 'qrCtrl'
      }).then(function(modal) {
        modal.element.modal();
        modal.close.then(function() {});
      });*/
    };

  }

  angular.module('vp.controllers').controller('LugarController', LugarController);
})();
