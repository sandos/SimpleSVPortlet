    require(["esri/map", "dojo/domReady!"], function(Map) { 
       console.log("Setting up map view");
        map = new Map("esrimap", {
      center: [16.546, 59.611],
      zoom: 13,
      basemap: "streets"
     });
    });
  