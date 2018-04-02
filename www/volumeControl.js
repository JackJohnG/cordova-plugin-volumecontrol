
var exec = require('cordova/exec');

var PLUGIN_NAME = 'VolumeControl';

var VolumeControl = {
	setVolume: function(volume, cb) {
		exec(cb, null, PLUGIN_NAME, 'setVolume', [volume]);
	},
	getVolume: function() {
		return new Promise(resolve => {
			exec(function(result) {
			  resolve(result); 
			}, null, PLUGIN_NAME, 'getVolume', []);
	  	});
	},
	resetVolume: function(cb) {
		exec(cb, null, PLUGIN_NAME, 'resetVolume', []);
  	}
};

module.exports = VolumeControl;
