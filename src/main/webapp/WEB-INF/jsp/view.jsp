<%@ page isELIgnored="false" %>
 
<script src="${pageContext.request.contextPath}/jquery-2.1.1.min.js" language="javascript"></script>
<script src="http://js.arcgis.com/3.10/"></script>
<script>
    require(["esri/map", "dojo/domReady!"], function(Map) { 
        map = new Map("esrimap", {
      center: [16.546, 59.611],
      zoom: 13,
      basemap: "streets"
     });
    });
  </script>

<div id="esrimap" style="width:800px;height:600px"></div>