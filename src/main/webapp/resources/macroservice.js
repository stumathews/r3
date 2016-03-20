/* 
 * Copyright (c) 2016, Stuart
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
(function() {
    'use strict';

    angular
        .module('app')
        .factory('macroservice', macroservice);
    
    macroservice.$inject = ['$http'];

    function macroservice($http) { 
                
        var service = {
            getUnits : getUnits,
            carbs: carbUnit,
            proteins : proteinUnit,
            fats : fatUnit
        };
        return service;        
        
        function fnGotUnitdefinitions(response) {        
            carbUnit = response.data.carbUnit;
            proteinUnit = response.data.proteinUnit;
            fatUnit = response.data.fatUnit;            
        }
        function getUnits(){
            // Now go get the data from the server and re-initialize the model bindings
            $http({ method: 'GET',  url: '/mealplanner/settings/unitdefinitions_json' 
            }).then(fnGotUnitdefinitions, function errorCallback(response) { console.log('error'); });            
        }   
    }
})();
