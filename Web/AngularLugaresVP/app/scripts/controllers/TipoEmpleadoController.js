(function() {
  function TipoEmpleadoCRUDController(TipoEmpleado, uiGridConstants) {
    var self = this;
    //lista todos los Tipos de empleados
    this.tipos = TipoEmpleado.query();
    //opciones del grid
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
      self.tipoEmpleado = null
      self.gridApi.selection.clearSelectedRows();
    }

    this.edit = function() {
      this.tipoEmpleado = {};
      this.filas = {};
      self.filas = self.gridApi.selection.getSelectedRows();
      self.tipoEmpleado.nombre = self.filas[0].nombre;
      self.tipoEmpleado.descripcion = self.filas[0].descripcion;
      self.tipoEmpleado.id = self.filas[0].id;
    };
    self.save = function() {
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
        tipoEmpleado.$save(null, function(object) {
          self.gridOptions1.data.push({
            'nombre': object.nombre,
            'descripcion': object.descripcion,
            'id': object.id
          });
        });
      }
      self.tipoEmpleado = null
    };

    this.delete = function() {
      self.filas = {};
      self.filas = self.gridApi.selection.getSelectedRows();
      var tipoEmpleado = new TipoEmpleado();
      tipoEmpleado.id = self.filas[0].id;
      tipoEmpleado.$delete(null, function(object) {
        var index = self.gridOptions1.data.indexOf(self.filas[0]);
        self.gridOptions1.data.splice(index, 1);
      });

    }

  }

  function TipoEmpleadoListController(TipoEmpleado) {
    this.tipos = TipoEmpleado.query();
  }

  angular.module('blog.controllers')
    .controller('TipoEmpleadoCRUDController', TipoEmpleadoCRUDController)
    .controller('TipoEmpleadoListController', TipoEmpleadoListController);

}());
