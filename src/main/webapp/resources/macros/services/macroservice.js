/* global angular */

(function() {
    'use strict';

    // Register a custom service called macroservice.
    angular
        .module('app')
        .factory('macroservice', macroservice);
    
    macroservice.$inject = ['$http'];

    function macroservice($http) {        
              
        var units = {};
        var getUnits = function(){
            $http.get('/mealplanner/settings/unitdefinitions_json').then(function(response) {
                units = response;
            });
        };
        
        var service = {            
                        getUnits : getUnits,
                        units : units
                       }; 
        return service;        
    }
})();
