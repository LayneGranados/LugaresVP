(function() {
  'use strict';
  angular.module('vp.controllers', ['vp.services', 'ui.bootstrap']);
  angular.module('vp', [
    'vp.controllers',
    'ui.bootstrap',
    'ui.router',
    'ui.grid',
    'ui.grid.pagination',
    'ui.grid.selection',
    'ui.grid.exporter',
    'monospaced.qrcode',
    'toaster',
    'ngAnimate',
    'oitozero.ngSweetAlert'
  ]);


})();
