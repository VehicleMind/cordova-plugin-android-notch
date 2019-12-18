

var run = require("cordova/exec");

var AndroidNotchStatusBar = {


    hasCutout: function(success, error) {
        run(success, error, "AndroidNotchStatusBar", "hasCutout");
    },

    setLayout: function(success, error) {
        run(success, error, "AndroidNotchStatusBar", "setLayout");
    },

    getInsetTop: function (success, error) {
        run(success, error, "AndroidNotchStatusBar", "getInsetsTop");
    },
    
    getInsetRight: function (success, error) {
        run(success, error, "AndroidNotchStatusBar", "getInsetsRight");
    },
    
    getInsetBottom: function (success, error) {
        run(success, error, "AndroidNotchStatusBar", "getInsetsBottom");
    },
    
    getInsetLeft: function (success, error) {
        run(success, error, "AndroidNotchStatusBar", "getInsetsLeft");
    }

};


module.exports = AndroidNotchStatusBar;