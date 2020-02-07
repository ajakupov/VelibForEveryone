/*
Call generic wms service for GoogleMaps v2
John Deck, UC Berkeley
Inspiration & Code from:
	Mike Williams http://www.econym.demon.co.uk/googlemaps2/ V2 Reference & custommap code
	Brian Flood http://www.spatialdatalogic.com/cs/blogs/brian_flood/archive/2005/07/11/39.aspx V1 WMS code
	Kyle Mulka http://blog.kylemulka.com/?p=287  V1 WMS code modifications
	http://search.cpan.org/src/RRWO/GPS-Lowrance-0.31/lib/Geo/Coordinates/MercatorMeters.pm
	
Modified 4/01/2006 by Tom Cole (tcole6@gmail.com) for Google Maps JSP Taglibrary
*/
function WMSSpec (map, baseUrl, name, shortName, layers, srs, version, styles, format, copyright, bgcolor, transparent) {
	this.MAGIC_NUMBER=6356752.3142;
	this.DEG2RAD=0.0174532922519943;
	this.PI=3.14159267;
	this.map = map;
	this.baseUrl = baseUrl;
	this.name = name;
	this.shortName = shortName;
	this.layers = layers;
	this.srs = srs;
	this.version = version;
	if (styles)
		this.styles = styles;
	else
		this.styles = null;
	this.format = format;
	if (copyright)
		this.copyright = copyright;
	else
		this.copyright = "(c) Unknown";
	if (bgcolor)
		this.bgcolor = bgcolor;
	if (transparent)
		this.transparent = transparent;
}
WMSSpec.prototype.isPng = function() {
	return false;
}
WMSSpec.prototype.getOpacity = function() {
	return 1;
}
WMSSpec.prototype.getTileUrl = function(a,b,c) {
	var lULP = new GPoint(a.x*256,(a.y+1)*256);
	var lLRP = new GPoint((a.x+1)*256,a.y*256);
	var lUL = G_NORMAL_MAP.getProjection().fromPixelToLatLng(lULP,b,c);
	var lLR = G_NORMAL_MAP.getProjection().fromPixelToLatLng(lLRP,b,c);
    var lBbox=lUL.x+","+lUL.y+","+lLR.x+","+lLR.y;
    var lSRS="EPSG:4326";
	var lURL= this.baseUrl;
	lURL+="&REQUEST=GetMap";
	lURL+="&SERVICE=WMS";
	lURL+="&VERSION=" + this.version;
	lURL+="&LAYERS="+this.layers;
	if (this.styles && this.styles != 'default')
		lURL+="&STYLES="+this.styles;
	else
		lURL+="&STYLES=";
	lURL+="&FORMAT="+this.format;
	lURL+="&BGCOLOR=0xFFFFFF";
	lURL+="&TRANSPARENT=TRUE";
	lURL+="&SRS="+this.srs;
	lURL+="&BBOX="+lBbox;
	lURL+="&WIDTH=256";
	lURL+="&HEIGHT=256";
	lURL+="&reaspect=false";
	lURL+="&EXCEPTIONS=INIMAGE";
	return lURL;
}
WMSSpec.prototype.getCopyright = function () {
	return this.copyright;
}
WMSSpec.prototype.getCopyright = function (a, b) {
	return {prefix:"Map: © ", copyrightTexts:[this.copyright]}; 
}
WMSSpec.prototype.maxResolution = function() {
	return 17;
}
WMSSpec.prototype.minResolution = function() {
	return 0;
}