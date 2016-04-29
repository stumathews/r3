/* global angular */

(function() {
    'use strict';

    angular
        .module('app')
        .controller('macro_controller', macro_controller);

    macro_controller.$inject = ['macroservice'];

    function macro_controller(macroservice) 
    {       
        var vm = this;
        
        macroservice.getUnits();
        vm.carbUnit = macroservice.units.carbs;
        vm.proteinUnit = macroservice.units.proteins;
        vm.fatUnit = macroservice.units.fats;
        
        vm.reset = function() {
            vm.carbUnit = 15;
            vm.proteinUnit = 7;
            vm.fatUnit = 5;
        };   
    }
})();
