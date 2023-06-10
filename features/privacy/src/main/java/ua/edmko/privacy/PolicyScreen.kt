package ua.edmko.privacy

import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.webkit.WebViewAssetLoader
import androidx.webkit.WebViewClientCompat
import ua.edmko.core.ui.components.Toolbar

@Composable
fun PolicyScreen(back: () -> Unit) {
    Scaffold(
        topBar = {
            Toolbar(title = stringResource(R.string.privacy_policy)) {
                back()
            }
        },
    ) {
        AndroidView(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            factory = { context ->
                WebView(context).also { webView ->
                    val assetLoader = WebViewAssetLoader.Builder()
                        .addPathHandler("/assets/", WebViewAssetLoader.AssetsPathHandler(context))
                        .build()
                    webView.webViewClient = object : WebViewClientCompat() {
                        override fun shouldInterceptRequest(
                            view: WebView?,
                            request: WebResourceRequest,
                        ): WebResourceResponse? {
                            return assetLoader.shouldInterceptRequest(request.url)
                        }
                    }
                    val webViewSettings: WebSettings = webView.settings
                    webViewSettings.allowFileAccess = false
                    webViewSettings.allowContentAccess = false
                    webView.loadUrl("https://appassets.androidplatform.net/assets/privacy_policy.html")
                }
            },
        )
    }
}
