#import <Cordova/CDV.h>
#import <UserNotifications/UserNotifications.h>

@interface Badge : CDVPlugin {
}

- (void)getBadgeCount:(CDVInvokedUrlCommand*)command;
- (void)getUnreadNotifications:(CDVInvokedUrlCommand*)command;
@end
