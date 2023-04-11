
window.onload = function realtimeClock() {
  var d = new Date();
  var h = d.getHours();
  var m = d.getMinutes();
  var s = d.getSeconds();
  var amPm = (h > 12) ? "PM" : "AM";
  h = (h > 12) ? (h - 12) : h;
  h = ("0" + h).slice(-2);
  m = ("0" + m).slice(-2);
  s = ("0" + s).slice(-2);
  document.getElementById("clock").innerHTML =
    h + " : " + m + " : " + s + " : " + amPm;

  var l = setTimeout(realtimeClock, 500);
}
