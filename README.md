<p align="center">
  <h1 font-weight=bold align="center">RN-Contact-Tracing</h1>
</p>


<p align="center">
  <a href="https://www.npmjs.com/package/rn-contact-tracing"><img src="https://img.shields.io/npm/v/rn-contact-tracing" alt="NPM version"></a>
  <a href="/LICENSE"><img src="https://img.shields.io/npm/l/rn-contact-tracing" alt="License"></a>
  <a href="https://github.com/wix-incubator/rn-contact-tracing/actions?query=workflow%3ACI"><img src="https://github.com/wix-incubator/rn-contact-tracing/workflows/CI/badge.svg?style=flat-square" alt="CI"></a>
</p>

---

<p align="center">
  <h3 font-weight=bold align="center">  WIP Library - Don't Use in Production</h3>
</p>

---
 
## About
This is a react-native library for tracing close contact between 2 mobile devices by exchanging ephemeral tokens over BLE (Bluetooth Low Energy).  

The library will do the following:

1. Advertise message with specific _service_uuid_ and generated _ephemeral token_
2. Scan for for BLE signals with a specific _service_uuid_ and store the relevant scanned data into local device storage
3. Provide simple API for JS to init these tasks in background and retrieve the scanned _tokens_
4. Integrate with a module that will provide ephemerally encrypted tokens (might be optional part of the lib)

There is a chance this lib will be updated after Google & Apple will release the full [Contact Tracing API](https://www.apple.com/covid19/contacttracing/) solution.


### Challanges
1. iOS limitation of using ble while the app in the background - [link](https://developer.apple.com/library/archive/documentation/NetworkingInternetWeb/Conceptual/CoreBluetooth_concepts/CoreBluetoothBackgroundProcessingForIOSApps/PerformingTasksWhileYourAppIsInTheBackground.html) 
2. Estimate the distance between 2 devices without violating user's privacy, using the data we can send over the ble


## Working plan

 Functionality | Android | iOS | 
:------------ | :-------------| :-------------| 
Scan in foreground | :white_check_mark: |  :white_check_mark: |
Advertise in foreground | :white_check_mark: |  :white_check_mark: |
Scan in background | :white_check_mark: | TODO |
Advertise in background | :white_check_mark: | TODO |
Save scanned data into local DB | :white_check_mark: | :white_check_mark: |
Return scanned data to JS | :white_check_mark: | :white_check_mark: | 
Pass scannng & advertising configuration from JS (intervals..) | :white_check_mark: | :white_check_mark: |
Integration with tokens provider  |TODO|TODO|
Tests  |TODO|TODO|]
Features for rssi calibration (GPS, Proximity)  |WIP|TODO|
---


## Getting started

### Example Project 

The Example project can be used as a reference of how to use the rn-contact-tracing API and as a helpful tool to fine-tune the scanning/advertising configuration

<img align="right" width="200"  src="docs/example.gif">

### How to run the example project
```properties
npm install
cd example/ios
pod install
For Android - npm run android 
For iOS - npm run ios
``` 
In Android - Click on Request Location Permission button

### Installation
`yarn add rn-contact-tracing`

or

`npm install rn-contact-tracing --save`


<details>
<summary><strong> Android - Steps to manually link the library</strong></summary>
   
#### `android/settings.gradle`
```groovy
include ':rn-contact-tracing'
project(':rn-contact-tracing').projectDir = new File(rootProject.projectDir, '../node_modules/rn-contact-tracing/lib/android')
```

#### `android/app/build.gradle`
```groovy
dependencies {
   ...
   implementation project(":rn-contact-tracing")
}
```

#### `android/app/src/main/.../MainApplication.java`
On top, where imports are:

```java
import com.wix.specialble.SpecialBlePackage;
```

Add the `RNLocationPackage` class to your list of exported packages.

```java
@Override
protected List<ReactPackage> getPackages() {
    return Arrays.asList(
            List<ReactPackage> packages = new PackageList(this).getPackages();
            ...
            packages.add(new SpecialBlePackage());
            ...
    );
}
```
</details>



### Supported Platforms
* iOS 10+
* Android API 21+


## Methods

### Summary

* [`setConfig`](#setconfigconfig)
* [`getConfig`](#getconfigcallback)
* [`startBLEService`](#startbleserviceconfig)
* [`stopBLEService`](#stopbleservice)
* [`startBLEScan`](#startblescanconfig)
* [`stopBLEScan`](#stopblescan)
* [`startBLEAdvertise`](#advertiseconfig)
* [`stopAdvertise`](#stopadvertise)
* [`getScansByKey`](#getscansbykeypubkey)
* [`getAllDevices`](#getalldevices)
* [`exportAllScansCsv`](#exportallscanscsv)
* [`exportAllDevicesCsv`](#exportalldevicescsv)
* [`cleanDevicesDB`](#cleandevicesdb)
* [`cleanScansDB`](#cleanscansdb)


---


### Details

#### `setConfig(config)`

```javascript
   SpecialBle.setConfig(config);
```

Sets configuration options that will be used in scanning & advertising tasks.

**Parameters:**

| Name   | Type   | Required | Description |
| ------ | ------ | -------- | ----------- |
| config | object | Yes      | See below.  |

Supported options:

* `serviceUUID` - the ServiceUUID which identify the BLE broadcast you are going to advertise and scan for.
* `scanDuration` -  scanning duration in milisec
* `scanInterval` - the time in milisec between every scan
* `advertiseDuration` - advertising duration in milisec (up to 180000ms)
* `advertiseInterval` - the time in milisec between every advertising
* `token` -  temporary token to advertise (for testing)

For Android
* `advertiseTXPowerLevel` - advertise TX power level [docs](https://developer.android.com/reference/android/bluetooth/le/AdvertiseSettings.Builder#setTxPowerLevel(int))
* `scanMatchMode` - match mode for Bluetooth LE scan filters hardware match [docs](https://developer.android.com/reference/android/bluetooth/le/ScanSettings.Builder#setMatchMode(int))
* `notificationTitle` - the title of the foreground service notification
* `notificationContent` - the content of the foreground service notification

---


#### `getConfig(callback)`

```javascript
   SpecialBle.getConfig((config) => {
    ....
   })
```

Gets the scanning & advertising configuration options that are currently defined in the library

---

#### `startBLEService(config)`

```javascript
SpecialBle.startBLEService(config);
```

Starts BLE background task scanning for a specific  - config is optional

---

#### `stopBLEService()`

```javascript
SpecialBle.stopBLEService();
```

Stops the background service and all the tasks the service executing

---

#### `startBLEScan(config)`

```javascript
SpecialBle.startBLEScan(config);
```

Starts BLE scanning in foreground - config is optional

---

#### `stopBLEScan()`

```javascript
SpecialBle.stopBLEScan();
```

Starts BLE scanning 

---

#### `advertise(config)`

```javascript
SpecialBle.advertise(config);
```

Starts BLE advertising in foreground - config is optional

---

#### `stopAdvertise()`

```javascript
SpecialBle.stopAdvertise();
```

Stops BLE advertising 

---


#### `getScansByKey(token, callback)`

```javascript
 SpecialBle.getScansByKey(token, (scans) => {
    ...
 })
```
Get list of scans events for a specific token, each object contains:
* `scan_id` - unique id
* `scan_timestamp` - epoch time of the scan event in 
* `public_key` - token key
* `scan_address` - scaned device address
* `scan_rssi` - rssi strength
* `scan_tx` - tx strength
* `scan_protocol` - the protocol used to scan the data (currently GAP/GATT)

---

#### `getAllDevices()`

```javascript
 SpecialBle.getAllDevices((devices) => {
       setDevices(devices)
   })
```
Get list of unique devices that were scanned, each object contains:


* `device_first_timestamp` - epoch time of the first scan event
* `device_last_timestamp` - epoch time of the last scan event
* `public_key` - token key
* `device_address` - scaned device address
* `device_rssi` - rssi strongest value
* `device_tx` - tx strongest value
* `device_protocol` - the protocol used to scan the data (currently GAP/GATT)

---

#### `exportAllScansCsv()`

```javascript
 SpecialBle.exportAllScansCsv();
```
Export the full Scans events DB to csv file

---


#### `exportAllDevicesCsv()`

```javascript
SpecialBle.exportAllDevicesCsv();
```
Export the full Devices DB to csv file

---


#### `cleanDevicesDB()`

```javascript
SpecialBle.cleanDevicesDB();
```
Clear all scanned devices

---

#### `cleanScansDB()`

```javascript
SpecialBle.cleanScansDB();
```
Clear all scans

---

#### Events from Native to JS
- `scanningStatus` - event can be true/false
- `advertisingStatus` - event can be  true/false
- `foundDevice` - event has 2 params: {event.device_name, event.device_address}
- `error` - {event.error_message}

---

### Why did we build this lib?
Due to COVID-19 pandemic, several groups and health authorities released apps that will help to identify and notify people that are at risk of exposure.

Some of these apps are written with RN and based on tracking user location which is not enough such as [Hamagen](https://github.com/MohGovIL/hamagen-react-native), and they willing to add BLE based functionality.  

There are lots of great libs that expose ble functionality for RN, i.e [react-native-ble-plx](https://github.com/Polidea/react-native-ble-plx) & [react-native-ble-manager](https://github.com/innoveit/react-native-ble-manager) but we wanted reduce the amount of dependancies as much as possible and exectue very specfic BLE functionality in background.

In addition, we looked at several great apps written for the same purpose in native, but each one of them is not written in a way that we could use as a stand-alone library. 
[OpenTrace](https://github.com/opentrace-community)) - includes the full business logic (UI..) that we don't want to use. 
[DP^3T Project](https://github.com/DP-3T) - include cryptography logic that we prefer to replace

##### Privacy (what do we advertise and save to DB)
TBD
 


### References
* https://github.com/opentrace-community
* ....


# rn-contact-tracing-testing
