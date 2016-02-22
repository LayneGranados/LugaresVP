(function() {
  'use strict';

  function LugarController(Lugar, ModalService) {

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
        modal.close.then(function() {});
      });
    };

  }

  angular.module('blog.controllers').controller('LugarController', LugarController);
})();


/*function LugarCreateController(Lugar, uiGridConstants, ModalService) {

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



self.lugar = {};
self.filas = {};
this.editar = function() {
self.filas = self.gridApi.selection.getSelectedRows();
self.lugar.nombre = self.filas[0].nombre;
self.lugar.descripcion = self.filas[0].descripcion;
self.lugar.tipolugar = self.filas[0].tipolugar;
self.lugar.id = self.filas[0].id;
};



}

function LugarListController(Lugar) {
this.lugares = Lugar.query();
}*/
