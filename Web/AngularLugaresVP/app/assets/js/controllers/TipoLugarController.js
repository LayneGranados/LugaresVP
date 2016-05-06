(function() {
  'use strict';

  function TipoLugarCRUDController(TipoLugar, uiGridConstants, $scope, ModalService) {
    var self = this;

    //Popup de actividades
    this.showComplex = function() {
      self.filas = self.gridApi.selection.getSelectedRows();
      if (self.filas.length !== 1) {
        return;
      }
      ModalService.showModal({
        templateUrl: 'templates/modalActividades.html',
        controller: 'ModalActividadesController',
        controllerAs: 'modalActCtrl',
        inputs: {
          tipoLugar: self.filas[0]
        }
      }).then(function(modal) {
        modal.element.modal();
        modal.close.then(function() {});
      });
    };
    //cargar Inicial de los tipos
    this.tipos = TipoLugar.query();
    //Opciones para el grid
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


    this.clear = function() {
      self.filas = null;
      self.tipoLugar = null;
      self.gridApi.selection.clearSelectedRows();
    };
    this.edit = function() {
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
        if (object.id === -1) {
          var index = self.gridOptions1.data.indexOf(self.filas[0]);
          self.gridOptions1.data.splice(index, 1);
        }
      });
    };
    self.save = function() {
      var tipoLugar = new TipoLugar();
      tipoLugar.nombre = self.tipoLugar.nombre;
      tipoLugar.descripcion = self.tipoLugar.descripcion;
      tipoLugar.id = self.tipoLugar.id;
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
      self.tipoLugar = null;
    };
  }

  function TipoLugarListController(TipoLugar) {
    this.tipos = TipoLugar.query();
  }

  angular
    .module('blog.controllers')
    .controller('TipoLugarCRUDController', TipoLugarCRUDController)
    .controller('TipoLugarListController', TipoLugarListController);
})();
