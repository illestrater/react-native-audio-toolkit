import { AppRegistry, DeviceEventEmitter, NativeModules, NativeEventEmitter } from 'react-native';

const RCTAudioPlayer = NativeModules.AudioPlayer;

emitEvent = (evt) => {
  this.emit(evt.status, evt);
}

warpEventResponse = (handler, event, payload) => {
  const additionalKeys = payload || {};
  handler({ type: event, ...additionalKeys });
}

registerEventHandler = (handler) => {
  const emitter = new NativeEventEmitter(RCTAudioPlayer);

  const events = [
    'ended',
  ];

  for (let i = 0; i < events.length; i++) {
    DeviceEventEmitter.addListener(events[i], warpEventResponse.bind(null, handler, events[i]));
  }

  console.log('registering headless task');

  AppRegistry.registerHeadlessTask('AudioPlayerHeadless', () => handler);
}

module.exports.emitEvent = emitEvent;
module.exports.registerEventHandler = registerEventHandler;