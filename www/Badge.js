const exec = require('cordova/exec');

class Badge {
    constructor() {}

    getBadge(arg0) {
        return new Promise((success, error) => {
            exec(success, error, 'Badge', 'getBadge', [arg0]);
        });
    }
}

if (typeof module !== "undefined") {
    module.exports = new Badge();
}
