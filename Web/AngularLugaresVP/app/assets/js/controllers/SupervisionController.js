(function() {
  'use strict';
  function SupervisionController(Supervision, uiGridConstants, $scope, ModalService) {

    var self = this;
    $scope.name = null;

    
    $scope.showComplex = function() {
      var rows = self.gridApi.selection.getSelectedRows();
      ModalService.showModal({
        templateUrl: 'views/modalCalificaciones.html',
        controller: function(EvaulacionSupervicion,supervision){
          this.evaluacion = new EvaulacionSupervicion();
          this.evaluacion.id = supervision.id;
          this.evaluacion.$query();
          console.log(supervision);
        },
        controllerAS: 'controller',
        inputs: {
          supervision: rows[0]
        }
      }).then(function(modal) {
        modal.element.modal();

      });
    };

    this.supervisiones = Supervision.query();


    this.gridOptions = {
      paginationPageSize: 15,
      enableFiltering: true,
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
      exporterPdfDefaultStyle: {
        fontSize: 9
      },
      exporterPdfTableStyle: {
        margin: [10, 10, 10, 0]
      },
      exporterPdfTableHeaderStyle: {
        alignment: 'center',
        fontSize: 10,
        bold: true,
        italics: true,
        color: 'red'
      },
      exporterPdfHeader: {
        text: 'Supervisiones',
        style: 'headerStyle'
      },
      exporterPdfFooter: function(currentPage, pageCount) {
        return {
          text: currentPage.toString() + ' of ' + pageCount.toString(),
          style: 'footerStyle'
        };
      },
      exporterPdfCustomFormatter: function(docDefinition) {
        docDefinition.styles.headerStyle = {
          fontSize: 22,
          bold: true,
          alignment: 'center'
        };
        docDefinition.styles.footerStyle = {
          fontSize: 10,
          bold: true
        };
        return docDefinition;
      },
      exporterPdfOrientation: 'portrait',
      exporterPdfPageSize: 'LETTER',
      exporterPdfMaxGridWidth: 500,
      exporterCsvLinkElement: angular.element(document.querySelectorAll('.custom-csv-link-location')),
    };

    this.gridOptions.onRegisterApi = function(gridApi) {
      self.gridApi = gridApi;
    };

    self.supervisiones = {};
    self.filas = {};
    this.editar = function() {


    };
  }
  angular.module('blog.controllers').controller('SupervisionController', SupervisionController);

})();
