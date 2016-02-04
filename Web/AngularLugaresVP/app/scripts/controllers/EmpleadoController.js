(function(){
  function EmpleadoCreateController(Persona, uiGridConstants) {

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
        field: 'tipo Identificacion'
      }, {
        field: 'Identificacion'
      }, {
        field: 'Nombres'
      }, {
        field: 'Apellidos'
      }, {
        field: 'Tipo Empleado'
      }, {
        field: 'Usuario'
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

  function EmpleadoListController(Persona) {
    this.personas = Persona.query();
  }


  angular.module('blog.controllers')
  .controller('EmpleadoCreateController', EmpleadoCreateController)
  .controller('EmpleadoListController', EmpleadoListController)
}());
