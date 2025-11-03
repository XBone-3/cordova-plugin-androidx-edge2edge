var exec = require('cordova/exec');

exports.enable = function () {
    return new Promise(function (resolve, reject) {
        exec(resolve(true), reject(false), 'EdgeToEdge', 'enable', []);
    });
};

exports.disable = function () {
    new Promise(function (resolve, reject) {
        exec(resolve(true), reject(false), 'EdgeToEdge', 'disable', []);
    });
};
