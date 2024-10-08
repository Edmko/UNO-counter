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
import ua.edmko.core.ui.theme.AppTheme

@Composable
fun PolicyScreen(back: () -> Unit) {
    Scaffold(
        backgroundColor = AppTheme.colors.background,
        topBar = { Toolbar(title = stringResource(R.string.privacy_policy_screen_title)) { back() } },
    ) { paddings ->

        AndroidView(
            modifier = Modifier
                .padding(paddings)
                .fillMaxSize(),
            factory = { context ->
                WebView(context).also { webView ->
                    val assetLoader = WebViewAssetLoader
                        .Builder()
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
                    webView.loadUrl(context.getString(R.string.privacy_policy_url))
                }
            },
        )
    }
}
