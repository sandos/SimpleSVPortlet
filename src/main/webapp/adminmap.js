    require(["esri/map", "dojo/domReady!"], function(Map) { 
       if( $('#esrimapAdm_container').length == 0 ) {
       console.log("Setting up map admin");
        map = new Map("esrimapAdm", {
          center: [16.546, 59.611],
          zoom: 13,
          basemap: "streets"
         });
        } else {
          console.log("NOT Setting up map admin");
          map = new Map("esrimapAdm", {
            center: [16.546, 59.611],
            zoom: 13,
            basemap: "streets"
         });
        }
    });
  