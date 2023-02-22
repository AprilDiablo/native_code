package com.example.native_code

import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity: FlutterActivity() {
    private val channel = "com.example.native_code/info";

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, channel).setMethodCallHandler {
            call, result ->
            if (call.method == "getMessageFromNativeCode") {
                val message = getMessage()

                if(message.isNotEmpty()) {
                    result.success(message)
                } else {
                    result.error("UNAVAILABLE", "Message from Kotlin code is empty", null);
                }
            } else {
                result.notImplemented()
            }
        }
    }

    private fun getMessage(): String {
        return "Message from Kotlin code"
    }
}
