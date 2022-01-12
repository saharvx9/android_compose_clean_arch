package com.sahar.composecleanarchitecture.ui.pages.articlelist.widgets

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bumptech.glide.Glide
import com.sahar.composecleanarchitecture.data.model.ArticleItem
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@ExperimentalMaterialApi
@Composable
fun ArticleItemView(item: ArticleItem,onClickItem:(item:ArticleItem)->Unit) {
    Card(
        onClick = { onClickItem(item) },
        modifier = Modifier
            .padding(15.dp, 10.dp)
            .heightIn(max = 100.dp),
        shape = RoundedCornerShape(15.dp),
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 10.dp
    ) {
        ListItem(
            modifier = Modifier.padding(0.dp, 10.dp),
            text = { TextItem(text = item.title) },
            singleLineSecondaryText = false,
            icon = {
                item.urlToImage?.let { ArticleImage(it) } ?: run { Text(text = "image is null") }
            }
        )
    }
}

@Composable
private fun TextItem(text: String?) {
    Text(
        text = text ?: "",
        style = TextStyle(color = MaterialTheme.colors.secondary, fontSize = 14.sp),
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
private fun ArticleImage(image: String) {
    GlideImage( // CoilImage, FrescoImage
        imageModel = image,
        modifier = Modifier.size(50.dp),
        circularReveal = CircularReveal(duration = 250),
        requestBuilder = { Glide.with(LocalContext.current).asDrawable().circleCrop() },
        // shows an indicator while loading an image.
        loading = { loading ->

            ConstraintLayout(
                modifier = Modifier.fillMaxSize()
            ) {
                val indicator = createRef()
                CircularProgressIndicator(
                    progress = loading.progress,
                    modifier = Modifier.constrainAs(indicator) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )
            }
        },
        // shows an error text if fail to load an image.
        failure = {
            Text(text = "image request failed.")
        })
}