//site.js

(function() {
    
    


    /*
        var ele = $("#username");
        ele.text("new User name");

        var main = $("#main");
        main.on("mouseenter", function () {

            main.style = "background-color: #888;";
        });

        main.on("mouseleave", function () {

            main.style = "";
        });

        var menuItems = $("ul.menu li a");
        menuItems.on("click", function () {
            var me = $(this);
            alert(me.text());
        });
    */
   

    var $sidebarAndWrapper = $("#sidebar,#wrapper");

    $("#sidebarToggle").on("click", function(){
        $("#sidebarToggle").hasClass("btn btn-default btn-lg")
        $sidebarAndWrapper.toggleClass("hide-sidebar");
    });

})();


