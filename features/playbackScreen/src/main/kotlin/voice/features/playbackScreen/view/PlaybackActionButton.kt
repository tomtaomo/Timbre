package voice.features.playbackScreen.view

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import voice.core.strings.R
import voice.core.ui.icons.VoiceIcons
import voice.features.playbackScreen.BookPlayViewState

/**
 * 播放行两侧的次要按钮。
 *
 * 图标 32 dp，比 [SkipButton] 小一圈；点按区域仍然保留 48 dp，
 * 图标变小了也不至于按不准 —— 这两个键挪到下面本来就是为了好按。
 */
@Composable
internal fun PlaybackActionButton(
  imageVector: ImageVector,
  contentDescription: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  tint: Color = LocalContentColor.current,
  onLongClick: (() -> Unit)? = null,
) {
  Box(
    modifier = modifier
      .size(48.dp)
      .combinedClickable(
        onClick = onClick,
        onLongClick = onLongClick,
        indication = ripple(bounded = false, radius = 24.dp),
        interactionSource = remember { MutableInteractionSource() },
      ),
    contentAlignment = Alignment.Center,
  ) {
    Icon(
      modifier = Modifier.size(32.dp),
      imageVector = imageVector,
      contentDescription = contentDescription,
      tint = tint,
    )
  }
}

/**
 * 睡眠定时按钮。定时生效时图标换成 BedtimeOff 并染成主色，
 * 提示当前还有倒计时在跑。
 */
@Composable
internal fun SleepTimerButton(
  state: BookPlayViewState.SleepTimerViewState,
  onClick: () -> Unit,
) {
  val active = state !is BookPlayViewState.SleepTimerViewState.Disabled
  PlaybackActionButton(
    imageVector = if (active) VoiceIcons.BedtimeOff else VoiceIcons.Bedtime,
    contentDescription = stringResource(id = R.string.sleep_timer_action_open),
    tint = if (active) MaterialTheme.colorScheme.primary else LocalContentColor.current,
    onClick = onClick,
  )
}

/**
 * 书签按钮。
 *
 * 长按沿用原来的行为：直接在当前进度插一条书签，不弹列表。
 */
@Composable
internal fun BookmarkButton(
  onClick: () -> Unit,
  onLongClick: () -> Unit,
) {
  PlaybackActionButton(
    imageVector = VoiceIcons.CollectionsBookmark,
    contentDescription = stringResource(id = R.string.bookmark_title),
    onClick = onClick,
    onLongClick = onLongClick,
  )
}
