<%@ page isELIgnored="false" %>
 
<script src="${pageContext.request.contextPath}/jquery-2.1.1.min.js" language="javascript"></script>
<!--<script src="http://js.arcgis.com/3.10/"></script>-->
<script>
    require(["esri/map", "dojo/domReady!"], function(Map) {
    console.log("Settig up map");
        map = new Map("esrimapAdm", {
      center: [16.546, 59.611],
      zoom: 13,
      basemap: "streets"
     });
    });
  </script>

<div id="esrimapAdm" style="width:800px;height:600px">MAP DIV</div>