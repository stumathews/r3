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
        .controller('macro_controller', macro_controller);
    macro_controller.$inject = ['$window','$http'];

    function macro_controller($window, $http) 
    {       
        var vm = this;

        // Pull thymleaf model data that was placed in window
        // set the model real quick so this prevents flicker
        vm.carbUnit = $window.carbUnit;
        vm.proteinUnit = $window.proteinUnit;
        vm.fatUnit = $window.fatUnit;

        vm.fnGotUnitdefinitions = function(response) {        
            vm.carbUnit = response.data.carbUnit;
            vm.proteinUnit = response.data.proteinUnit;
            vm.fatUnit = response.data.fatUnit;
        }

        // Now go get the data from the server and re-initialize the model bindings
        $http({ method: 'GET',  url: '/mealplanner/settings/unitdefinitions_json' 
        }).then(vm.fnGotUnitDefinitions, function errorCallback(response) { console.log('error'); });

        vm.reset = function() {
            vm.carbUnit = 15;
            vm.proteinUnit = 7;
            vm.fatUnit = 5;
        };                

        vm.sock = new SockJS('/mealplanner/generic');
        vm.sock.onopen = function() {
            console.log('Opening'); 
            vm.sock.send('hello');
        }
        vm.sock.onmessage = function(e) {
            console.log('Received message:', e.data);  
            var now = new Date();
            var outStr = now.getHours()+':'+now.getMinutes()+':'+now.getSeconds();
            $("#updates").append(outStr + e.data + '<br/>');
            setTimeout(function(){saySomethingBack()},2000);
        }
        vm.sock.onclose = function(){
            console.log('Closing');
        }

        function saySomethingBack() {
            console.log('Sending something back to the server');                    
            vm.sock.send('Something back!');
        }        
    }
})();
