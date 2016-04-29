angular
    .module('app')
    .controller('sockjs_controller', sockjs_controller);
    
    sockjs_controller.$inject = ['$scope'];

function sockjs_controller($scope) 
{   var vm = this;
    var sock = new SockJS('/mealplanner/generic');
    sock.onopen = function() {
        console.log('Opening'); 
        sock.send('hello');
    }
    sock.onmessage = function(e) {
        console.log('Received message:', e.data);  
        var now = new Date();
        var outStr = now.getHours()+':'+now.getMinutes()+':'+now.getSeconds();
        $("#updates").append(outStr + e.data + '<br/>');
        setTimeout(function(){saySomethingBack()},2000);
    }
    sock.onclose = function(){
        console.log('Closing');
    }

    function saySomethingBack() {
        console.log('Sending something back to the server');                    
        sock.send('Something back!');
    }
}
