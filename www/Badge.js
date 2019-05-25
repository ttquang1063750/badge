const exec = require('cordova/exec');

class Badge {
    constructor() {}

    getBadgeCount() {
        return new Promise((success, error) => {
            exec(success, error, 'Badge', 'getBadgeCount', []);
        });
    }

    getUnreadNotifications() {
        return new Promise((success, error) => {
            exec(success, error, 'Badge', 'getUnreadNotifications', []);
        });
    }
}

if (typeof module !== "undefined") {
    module.exports = new Badge();
}
