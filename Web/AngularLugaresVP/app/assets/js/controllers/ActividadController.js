(function() {
  'use strict';

  function ActividadController(Actividad, toaster, SweetAlert) {

    var self = this;

    this.actividades = Actividad.query();
    self.selectedActividad = new Actividad();
    self.rowObject = null;
    self.mensaje = 'Crear Nueva Actividad';
    this.cancel = function() {
      self.selectedActividad = new Actividad();
    };

    this.save = function() {
      if (self.selectedActividad.id === undefined) {
        self.selectedActividad.$save({}, function() {
          self.gridOptions.data.push(self.selectedActividad);
          toaster.pop('success', "Actividad", "Nueva Actividad Guardada");
          self.cancel();
        });
      } else {
        self.selectedActividad.$update({}, function(data) {
          angular.extend(self.rowObject, data);
          toaster.pop('warning', "Actividad", "Actividad Editada");
          self.cancel();
        });
      }
    };

    this.edit = function(row) {
      console.log("Editar fila");
      //var rows = self.gridApi.selection.getSelectedRows();
      self.selectedActividad = angular.copy(row.entity);
      self.rowObject = row.entity;
      self.mensaje = "Editar Actividad";
    };

    this.delete = function(row) {
      /*var rows = self.gridApi.selection.getSelectedRows();
      var indexOfDelete = self.gridOptions.data.indexOf(rows[0]);
      rows[0].$delete({}, function() {
        self.gridOptions.data.splice(indexOfDelete, 1);
      });*/
      var fila = row;
      SweetAlert.swal({
          title: "¿Esta Seguro?",
          text: "No se podra recuperar el registro",
          type: "warning",
          showCancelButton: true,
          confirmButtonColor: "#DD6B55",
          confirmButtonText: "Si, Eliminar",
          closeOnConfirm: false
        },
        function(isConfirm) {
          if (isConfirm) {
            row.entity.$delete({}, function(data) {
              var i = self.gridOptions.data.indexOf(row.entity);
              self.gridOptions.data.splice(i, 1);
              SweetAlert.swal('Eliminado', '', 'success');
            });
          }
        });
    };

    this.gridOptions = {
      paginationPageSize: 15,
      data: self.actividades,
      columnDefs: [{
        field: 'id',
        name: 'Acciones',
        cellTemplate: 'templates/common/edit-button.html',
        width: 75,
        enableSorting: false,
        enableHiding: false,
        enableColumnMenu: false
      }, {
        field: 'nombre'
      }, {
        field: 'descripcion'
      }],
      multiSelect: false,
      enableRowSelection: false,
      enableRowHeaderSelection: false
    };

    this.gridOptions.onRegisterApi = function(gridApi) {
      self.gridApi = gridApi;
    };
    this.gridOptions.appScopeProvider = this

  }

  angular.module('vp.controllers').controller('ActividadController', ActividadController);

})();
