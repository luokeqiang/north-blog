import {app, protocol, BrowserWindow, session} from 'electron'
//  Session can only be received when app is ready
const ses = session.fromPartition('persist:name')
try {
  // The path to the extension in 'loadExtension' must be absolute
  await ses.loadExtension(path.resolve('node_modules/vue-devtools/vender'))
} catch (e) {
  console.error('Vue Devtools failed to install:', e.toString())
}
