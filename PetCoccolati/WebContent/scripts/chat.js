window.purechatApi = {
	l : [],
	t : [],
	on : function() {
		this.l.push(arguments);
	}
};
(function() {
	var done = false;
	var script = document.createElement('script');
	script.async = true;
	script.type = 'text/javascript';
	script.src = 'https://app.purechat.com/VisitorWidget/WidgetScript';
	document.getElementsByTagName('HEAD').item(0).appendChild(script);
	script.onreadystatechange = script.onload = function(e) {
		if (!done
				&& (!this.readyState || this.readyState == 'loaded' || this.readyState == 'complete')) {
			var w = new PCWidget({
				c : '642a182f-f742-4926-8c6b-b9fcec823dc1',
				f : true
			});
			done = true;
		}
	};
})();