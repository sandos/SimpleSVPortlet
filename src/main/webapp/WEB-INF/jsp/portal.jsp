<%@ page isELIgnored="false" %>
 
<script src="${pageContext.request.contextPath}/leaflet.js" language="javascript"></script>
<script src="${pageContext.request.contextPath}/leaflet-src.js" language="javascript"></script>
<script src="${pageContext.request.contextPath}/jquery-2.1.1.min.js" language="javascript"></script>
<script>
function Demo()
{
    $('head').append('<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/leaflet.css">');
    var map = L.map('map');
    var osmUrl='http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png';
    var osmAttrib='Map data © <a href="http://openstreetmap.org">OpenStreetMap</a> contributors';
    var osm = new L.TileLayer(osmUrl, {minZoom: 1, maxZoom: 19, attribution: osmAttrib});
    
    // start the map in South-East England
    map.setView(new L.LatLng(51.3, 0.7),9);
    map.addLayer(osm);     
}
</script>
<marquee>tjo</marquee>
<script type="text/javascript">
    Demo();
</script>
<div id="map" style="width:800px;height:600px;"></div>
