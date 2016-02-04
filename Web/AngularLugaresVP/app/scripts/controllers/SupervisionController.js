(function() {
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
      enableGridMenu: true,
     enableSelectAll: true,
     exporterCsvFilename: 'myFile.csv',
     exporterPdfDefaultStyle: {fontSize: 9},
     exporterPdfTableStyle: {margin: [10, 10, 10, 0]},
     exporterPdfTableHeaderStyle: {alignment:'center',fontSize: 10, bold: true, italics: true, color: 'red'},
     exporterPdfHeader: { text: "Supervisiones", style: 'headerStyle' },
     exporterPdfFooter: function ( currentPage, pageCount ) {
       return { text: currentPage.toString() + ' of ' + pageCount.toString(), style: 'footerStyle' };
     },
     exporterPdfCustomFormatter: function ( docDefinition ) {
       docDefinition.styles.headerStyle = { fontSize: 22, bold: true,alignment:'center' };
       docDefinition.styles.footerStyle = { fontSize: 10, bold: true };
       return docDefinition;
     },
     exporterPdfOrientation: 'portrait',
     exporterPdfPageSize: 'LETTER',
     exporterPdfMaxGridWidth: 500,
     exporterCsvLinkElement: angular.element(document.querySelectorAll(".custom-csv-link-location")),
    };

    this.gridOptions1.onRegisterApi = function(gridApi) {
      self.gridApi = gridApi;
    };

    self.supervisiones = {};
    self.filas = {};
    this.editar = function() {


    };
  }
  angular.module('blog.controllers').controller('SupervisionListController', SupervisionListController);

})();
