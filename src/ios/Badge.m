#import "Badge.h"
@implementation Badge

- (void)getBadgeCount:(CDVInvokedUrlCommand*)command
{
    int count = [UIApplication sharedApplication].applicationIconBadgeNumber;
    CDVPluginResult* result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsInt:count];
    [self.commandDelegate sendPluginResult:result callbackId:_callbackId];
}

- (void)getUnreadNotifications:(CDVInvokedUrlCommand*)command
{
    #if defined(__IPHONE_10_0) && __IPHONE_OS_VERSION_MAX_ALLOWED >= __IPHONE_10_0
        [[UNUserNotificationCenter currentNotificationCenter] getDeliveredNotificationsWithCompletionHandler:^(NSArray<UNNotification *> * _Nonnull notifications) {
            CDVPluginResult* result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsArray:notifications];
            [self.commandDelegate sendPluginResult:result callbackId:_callbackId];
        }];
    #endif
}

@end
