//
//  Config.h
//  rn-contact-tracing
//
//  Created by Ran Greenberg on 23/04/2020.
//

#import <Foundation/Foundation.h>

static long DEFAULT_SCAN_INTERVAL = 2 * 1000; // seconds
static long DEFAULT_SCAN_DURATION = 5 * 1000; // seconds
static long DEFAULT_ADVERTISE_INTERVAL = 3 * 1000; // seconds
static long DEFAULT_ADVERTISE_DURATION = 7 * 1000; // seconds
static NSString* _Nonnull DEFAULT_SERVICE_UUID = @"00000000-0000-1000-8000-00805F9B34FB";
static NSString* _Nonnull DEFAULT_TOKEN = @"1234-ios-token";
static NSString* _Nonnull DEFAULT_NOTIFICATION_LARGE_ICON = @"large_icon";
static NSString* _Nonnull DEFAULT_NOTIFICATION_SMALL_ICON = @"small_icon";

static NSString* _Nonnull KEY_SERVICE_UUID = @"serviceUUID";
static NSString* _Nonnull KEY_TOKEN = @"token";
static NSString* _Nonnull KEY_SCAN_DURATION = @"scanDuration";
static NSString* _Nonnull KEY_SCAN_INTERVAL = @"scanInterval";
static NSString* _Nonnull KEY_ADVERTISE_DURATION = @"advertiseDuration";
static NSString* _Nonnull KEY_ADVERTISE_INTERVAL = @"advertiseInterval";
static NSString* _Nonnull KEY_NOTIFICATION_LARGE_ICON_PATH = @"notificationLargeIconPath";
static NSString* _Nonnull KEY_NOTIFICATION_SMALL_ICON_PATH = @"notificationSmallIconPath";

NS_ASSUME_NONNULL_BEGIN

@interface Config : NSObject

+(void)SetConfig:(NSDictionary*) configDict;
+(NSDictionary*)GetConfig;

@end

NS_ASSUME_NONNULL_END
