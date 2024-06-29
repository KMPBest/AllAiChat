package components.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import components.types.ImageType
import configs.uis.White
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

sealed class HeaderIconType {
  data class Vector(val image: ImageVector) : HeaderIconType()

  data class Resource(val image: DrawableResource) : HeaderIconType()
}

@Composable
fun Header(
  title: String? = "",
  modifier: Modifier = Modifier,
  leftContent: @Composable (() -> Unit)? = null,
  leftIcon: ImageType? = null,
  leffIconSize: Dp = 30.dp,
  leffIconColor: Color = Color.Unspecified,
  centerContent: @Composable (() -> Unit)? = null,
  rightContent: @Composable (() -> Unit)? = null,
  rightIcon: ImageType? = null,
  rightIconSize: Dp = 30.dp,
  rightIconColor: Color = Color.Transparent,
) {
  Row(
    modifier = modifier.padding(12.dp).fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    leftContent?.invoke() ?: run {
      IconButton(
        onClick = {},
      ) {
        when (leftIcon) {
          is ImageType.Vector -> {
            Icon(
              imageVector = leftIcon.image,
              contentDescription = null,
              modifier = Modifier.size(leffIconSize),
              tint = leffIconColor,
            )
          }

          is ImageType.Resource -> {
            Icon(
              painter = painterResource(leftIcon.image),
              contentDescription = null,
              modifier = Modifier.size(leffIconSize),
              tint = leffIconColor,
            )
          }

          is ImageType.Url -> {
            AsyncImage(
              model = leftIcon.image,
              contentDescription = null,
              modifier = Modifier.size(rightIconSize),
            )
          }

          is ImageType.Bitmap -> TODO()

          null -> {
            Box(modifier = Modifier.size(rightIconSize))
          }
        }
      }
    }

    centerContent?.invoke() ?: run {
      Text(
        text = title ?: "",
        modifier = Modifier.weight(1f),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodySmall,
        color = White,
      )
    }

    rightContent?.invoke() ?: run {
      IconButton(
        onClick = {},
      ) {
        when (rightIcon) {
          is ImageType.Vector -> {
            Icon(
              imageVector = rightIcon.image,
              contentDescription = null,
              modifier = Modifier.size(rightIconSize),
              tint = rightIconColor,
            )
          }

          is ImageType.Resource -> {
            Icon(
              painter = painterResource(rightIcon.image),
              contentDescription = null,
              modifier = Modifier.size(rightIconSize),
              tint = rightIconColor,
            )
          }

          is ImageType.Url -> {
            AsyncImage(
              model = rightIcon.image,
              contentDescription = null,
              modifier = Modifier.size(rightIconSize),
            )
          }
          is ImageType.Bitmap -> TODO()
          null -> {
            Box(modifier = Modifier.size(rightIconSize))
          }
        }
      }
    }
  }
}